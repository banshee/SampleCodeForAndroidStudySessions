package com.restphone.audioworkshop;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BasicAudioFragment extends Fragment {
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.basic_audio_player, container, false);
  }
  
  @Override
  public void onResume() {
    super.onResume();

    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.testmp3);
    mediaPlayer.start(); // no need to call prepare(); create() does that for
  }
}