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
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    createPlayer();
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.basic_audio_player, container, false);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mediaPlayer.stop();
    mediaPlayer.release();
    mediaPlayer = null;
  }

  private void createPlayer() {
    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.test_cbr);
    mediaPlayer.setLooping(true);

    mediaController = new MediaController(getActivity());
    View audioView = getActivity().findViewById(R.id.imageView1);
    mediaController.setAnchorView(audioView);
    
    // Notice that we are still not hooked up yet

    MediaPlayerControl x = new MediaController.MediaPlayerControl() {
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

      @Override
      public int getBufferPercentage() {
        return mediaPlayer != null ? (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration()) : 0;
      }

      @Override
      public int getCurrentPosition() {
        return mediaPlayer != null ? mediaPlayer.getCurrentPosition() : 0;
      }

      @Override
      public int getDuration() {
        return mediaPlayer != null ? mediaPlayer.getDuration() : 0;
      }

      @Override
      public boolean isPlaying() {
        return mediaPlayer.isPlaying();
      }

      @Override
      public void pause() {
        mediaPlayer.pause();
      }

      @Override
      public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
      }

      @Override
      public void start() {
        mediaPlayer.start();
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

  private MediaController mediaController;
  private MediaPlayer mediaPlayer;
}