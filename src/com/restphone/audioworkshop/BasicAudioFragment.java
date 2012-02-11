package com.restphone.audioworkshop;

import static com.google.common.base.Preconditions.checkState;
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

/**
 * This is a a demonstration fragment with a media player, media controller, and
 * an image view. Touching the image view brings up the media controller.
 * 
 * <p>
 * 
 * This code demonstrates a fairly stripped-down audio experience. It plays
 * audio in the visible activity. That has some implications that may not be
 * obvious. For example, rotating the phone destroys the activity playing audio.
 * 
 * <p>
 * 
 * The challenge to audio isn't in figuring out how to play audio in a visible
 * activity. It's in how you interact with the rest of the Android system. Think
 * about what the expected behavior is when your users do things liker rotate
 * the phone. You're going to run through the activity and fragment lifecycle,
 * so you'll need to do something sensible with the audio playback. Interrupting
 * it and restarting at the beginning of the track is rarely the right choice.
 * 
 * @see <a
 *      href="http://developer.android.com/reference/android/media/MediaPlayer.html">MediaPlayer</a>
 *      in the Android developers guide. There's a very useful diagram of the
 *      MediaPlayer state machine there.
 * @see <a
 *      href="http://developer.android.com/guide/topics/media/mediaplayer.html">Media&nbspPlayback</a>
 *      developer's guide section.
 * @see <a
 *      href="http://developer.android.com/guide/topics/fundamentals/fragments.html">Fragments</a>
 *      in the Android developers guide
 */

public class BasicAudioFragment extends Fragment {
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    createPlayer();
    createMediaController();
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.basic_audio_player, container, false);
  }

  @Override
  public void onDetach() {
    super.onDetach();

    mediaPlayer.stop();

    // MediaPlayer consumes system resources that need to be freed when you're
    // not
    // actually using the media. Don't just leave this up to garbage collection.
    mediaPlayer.release();

    // Setting mediaPlayer to null to tell mediaController that this mediaPlayer
    // isn't available any more. MediaController will make native calls to
    // mediaPlayer that
    // throw exceptions after release() is called.
    mediaPlayer = null;
  }

  /**
   * Creates a player using one of the mp3 files stored as raw resources in the
   * application.
   * 
   * In a real application, you'd probably want more sophisticated code to
   * choose where to get audio data.
   */
  private void createPlayer() {
    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.test_cbr);

    // Loop just to make the live demonstration easier.
    mediaPlayer.setLooping(true);
  }

  /**
   * Creates the controller (play, pause, rewind etc controls) for the player.
   */
  private void createMediaController() {
    checkState(mediaPlayer != null,
        "media player must be initialized before the media controller");

    mediaController = new MediaController(getActivity());

    mediaController.setAnchorView(getAudioView());

    MediaPlayerControl mediaPlayerControl = new MediaController.MediaPlayerControl() {
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
        return mediaPlayer != null ? (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration())
            : 0;
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
        return mediaPlayer != null && mediaPlayer.isPlaying();
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

    getAudioView().setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        mediaController.show();
        return true;
      }
    });

    mediaController.setMediaPlayer(mediaPlayerControl);
  }

  /**
   * @return The view chosen as the placeholder for audio in this fragment.
   */
  private View getAudioView() {
    View audioView = getActivity().findViewById(R.id.imageView1);
    return audioView;
  }

  private MediaController mediaController;
  private MediaPlayer mediaPlayer;
}