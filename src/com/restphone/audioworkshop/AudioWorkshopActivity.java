package com.restphone.audioworkshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class AudioWorkshopActivity extends FragmentActivity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.audio_workshop);

    findViewById(R.id.basic_audio_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        switchToFragment(new BasicAudioFragment());
      }
    });

    findViewById(R.id.basic_video_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        switchToFragment(new BasicVideoFragment());
      }
    });

    findViewById(R.id.basic_camera_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        switchToFragment(new BasicCameraDisplay());
      }
    });
  }

  private void switchToFragment(Fragment basicAudioFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.base_layout, basicAudioFragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }
}