package com.example.produtador

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.produtador.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.UUID


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var imagensSelecionadas = mutableListOf<Uri>()
    private val productsStorage = Firebase.storage.reference
    private val firestore = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val selectImagesActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val intent = result.data
                if(intent?.clipData != null){
                    val count = intent.clipData?.itemCount ?: 0
                    (0 until count).forEach{
                        val imageUri = intent.clipData?.getItemAt(it)?.uri
                        imageUri?.let{
                            imagensSelecionadas.add(it)
                        }
                    }
                }
                else{
                    val imageUri = intent?.data
                    imageUri?.let{ imagensSelecionadas.add(it) }
                }
                updateImages()
            }
        }
        binding.buttonImagesSelection.setOnClickListener{
            val intent = Intent(ACTION_GET_CONTENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE , true)
            intent.type = "image/*"
            selectImagesActivityResult.launch(intent)
        }
    }
    private fun updateImages() {
        binding.SelectedImages.text = imagensSelecionadas.size.toString()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveProduct){
            val productValidation = validateInformation()
            if(!productValidation){
                Toast.makeText(this,"Preencha os Campos", Toast.LENGTH_SHORT).show()
                return false
            }
            saveProduct()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun saveProduct() {
        val name = binding.editName.text.toString().trim()
        val category = binding.editCategory.text.toString().trim()
        val price = binding.editPrice.text.toString().trim()
        val description = binding.editDescription.text.toString().trim()
        val offer = binding.offer.text.toString().trim()
        val imagesByteArrays = getImagesByteArrays()
        val images = mutableListOf<String>()
        lifecycleScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                showLoading()
            }
            try {
                async {
                    imagesByteArrays.forEach {
                        val id = UUID.randomUUID().toString()
                        launch {
                            val imageStorage = productsStorage.child("products/images/$id")
                            val result = imageStorage.putBytes(it).await()
                            val downloadUrl = result.storage.downloadUrl.await().toString()
                            images.add(downloadUrl)
                        }
                    }
                }.await()
            }catch (e: java.lang.Exception){
                e.printStackTrace()
                withContext(Dispatchers.Main){
                    hideLoading()
                }
            }
            val product = Product(
                UUID.randomUUID().toString(),
                name,
                category,
                price.toFloat(),
                if(offer.isEmpty()) null else offer.toFloat(),
                if(description.isEmpty())null else description,
                images
            )
            firestore.collection("Produtos").add(product).addOnSuccessListener {
                hideLoading()
            }.addOnFailureListener{
                hideLoading()
                Log.e("Error",it.message.toString())
            }
        }
    }
    private fun hideLoading() {
        binding.ProgressBar.visibility = View.INVISIBLE
    }
    private fun showLoading() {
        binding.ProgressBar.visibility = View.VISIBLE
    }
    private fun getImagesByteArrays(): List<ByteArray> {
        val imagesByteArray = mutableListOf<ByteArray>()
        imagensSelecionadas.forEach{
            val stream = ByteArrayOutputStream()
            val imageBmp = MediaStore.Images.Media.getBitmap(contentResolver, it)
            if(imageBmp.compress(Bitmap.CompressFormat.JPEG,100,stream)){
                imagesByteArray.add(stream.toByteArray())
            }
        }
        return imagesByteArray
    }
    private fun validateInformation(): Boolean {
        if(binding.editPrice.text.toString().trim().isEmpty())
            return false
        if(binding.editName.text.toString().trim().isEmpty())
            return false
        if(binding.editCategory.text.toString().trim().isEmpty())
            return false
        if(imagensSelecionadas.isEmpty())
            return false
        return true
    }
}