package com.restphone.audioworkshop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class AudioWorkshopActivity extends FragmentActivity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.audio_workshop);

    BasicAudioFragment basicAudioFragment = new BasicAudioFragment();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.base_layout, basicAudioFragment);
    fragmentTransaction.commit();  
    }
}