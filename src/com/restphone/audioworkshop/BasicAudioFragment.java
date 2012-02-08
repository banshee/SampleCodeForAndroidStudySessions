package com.restphone.audioworkshop;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

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

    final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(),
        R.raw.testmp3);
    mediaPlayer.start(); // no need to call prepare(); create() does that for

    final MediaController mediaController = new MediaController(getActivity());
    View audioView = getActivity().findViewById(R.id.imageView1);
    mediaController.setAnchorView(audioView);

    // Notice that we are still not hooked up yet

    MediaPlayerControl x = new MediaController.MediaPlayerControl() {
      @Override
      public void start() {
        mediaPlayer.start();
      }

      @Override
      public void pause() {
        mediaPlayer.pause();
      }

      @Override
      public int getDuration() {
        return mediaPlayer.getDuration();
      }

      @Override
      public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
      }

      @Override
      public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
      }

      @Override
      public boolean isPlaying() {
        return mediaPlayer.isPlaying();
      }

      @Override
      public int getBufferPercentage() {
        float currentPosition = mediaPlayer.getCurrentPosition();
        float bufferLength = mediaPlayer.getDuration();
        return (int) ((currentPosition / bufferLength) * 1000);
      }

      @Override
      public boolean canPause() {
        return true;
      }

      @Override
      public boolean canSeekBackward() {
        return true;
      }

      @Override
      public boolean canSeekForward() {
        return true;
      }
    };

    mediaController.setMediaPlayer(x);

    audioView.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        mediaController.show();
        return true;
      }
    });
  }
}