package com.restphone.audioworkshop;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * This is a a demonstration fragment with a single ImageView. Clicking on the
 * view sends an intent that brings up the camera, and the picture that you take
 * is displayed.
 * 
 * <p>
 * 
 * Notice the code that replaces the imageview.  If you're not clearing your bitmaps, you'll quickly see:
 * 
 * <pre>java.lang.OutOfMemoryError: bitmap size exceeds VM budget</pre>
 */

public class BasicCameraDisplay extends Fragment {
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
    case TAKE_PICTURE:
      if (resultCode == Activity.RESULT_OK) {
        if (imageLocation != null) {
          ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.basic_camera_display_layout);
          if (layout != null) {
            layout.removeAllViews();
            ImageView img = new ImageView(getActivity());
            img.setImageDrawable(Drawable.createFromPath(imageLocation.getPath()));
            layout.addView(img);
          }
        }
      }
    }
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View result = inflater.inflate(R.layout.basic_camera_display,
        container,
        false);
    return result;
  }

  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(toString());
    getView().setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        takePhoto();
      }
    });
  }

  @Override
  public void onStop() {
    super.onStop();
    ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.basic_camera_display_layout);
    if (layout != null) {
      ImageView oldImg = (ImageView) layout.getChildAt(0);
      if (oldImg != null) {
        oldImg.setImageBitmap(null);
        layout.removeAllViews();
        System.gc();
      }
    }
  }

  public void takePhoto() {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
    imageLocation = Uri.fromFile(photo);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageLocation);
    startActivityForResult(intent, TAKE_PICTURE);
  }

  private static final int TAKE_PICTURE = 1;
  private Uri imageLocation;
}