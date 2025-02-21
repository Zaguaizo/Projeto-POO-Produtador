// Generated by view binder compiler. Do not edit!
package com.example.produtador.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.produtador.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final LinearLayoutCompat InfoProducts;

  @NonNull
  public final ProgressBar ProgressBar;

  @NonNull
  public final TextView SelectedImages;

  @NonNull
  public final Button buttonImagesSelection;

  @NonNull
  public final EditText editCategory;

  @NonNull
  public final EditText editDescription;

  @NonNull
  public final EditText editName;

  @NonNull
  public final EditText editPrice;

  @NonNull
  public final EditText offer;

  private ActivityMainBinding(@NonNull ScrollView rootView,
      @NonNull LinearLayoutCompat InfoProducts, @NonNull ProgressBar ProgressBar,
      @NonNull TextView SelectedImages, @NonNull Button buttonImagesSelection,
      @NonNull EditText editCategory, @NonNull EditText editDescription, @NonNull EditText editName,
      @NonNull EditText editPrice, @NonNull EditText offer) {
    this.rootView = rootView;
    this.InfoProducts = InfoProducts;
    this.ProgressBar = ProgressBar;
    this.SelectedImages = SelectedImages;
    this.buttonImagesSelection = buttonImagesSelection;
    this.editCategory = editCategory;
    this.editDescription = editDescription;
    this.editName = editName;
    this.editPrice = editPrice;
    this.offer = offer;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.InfoProducts;
      LinearLayoutCompat InfoProducts = ViewBindings.findChildViewById(rootView, id);
      if (InfoProducts == null) {
        break missingId;
      }

      id = R.id.ProgressBar;
      ProgressBar ProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (ProgressBar == null) {
        break missingId;
      }

      id = R.id.SelectedImages;
      TextView SelectedImages = ViewBindings.findChildViewById(rootView, id);
      if (SelectedImages == null) {
        break missingId;
      }

      id = R.id.buttonImagesSelection;
      Button buttonImagesSelection = ViewBindings.findChildViewById(rootView, id);
      if (buttonImagesSelection == null) {
        break missingId;
      }

      id = R.id.editCategory;
      EditText editCategory = ViewBindings.findChildViewById(rootView, id);
      if (editCategory == null) {
        break missingId;
      }

      id = R.id.editDescription;
      EditText editDescription = ViewBindings.findChildViewById(rootView, id);
      if (editDescription == null) {
        break missingId;
      }

      id = R.id.editName;
      EditText editName = ViewBindings.findChildViewById(rootView, id);
      if (editName == null) {
        break missingId;
      }

      id = R.id.editPrice;
      EditText editPrice = ViewBindings.findChildViewById(rootView, id);
      if (editPrice == null) {
        break missingId;
      }

      id = R.id.offer;
      EditText offer = ViewBindings.findChildViewById(rootView, id);
      if (offer == null) {
        break missingId;
      }

      return new ActivityMainBinding((ScrollView) rootView, InfoProducts, ProgressBar,
          SelectedImages, buttonImagesSelection, editCategory, editDescription, editName, editPrice,
          offer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
