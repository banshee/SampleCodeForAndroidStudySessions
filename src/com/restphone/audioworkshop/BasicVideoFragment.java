package com.restphone.audioworkshop;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

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

public class BasicVideoFragment extends Fragment {
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    VideoView vv = (VideoView) getActivity().findViewById(R.id.video_view);
    Uri u = Uri.parse("rtsp://v4.cache8.c.youtube.com/CigLENy73wIaHwnhkIUPaSKmKxMYDSANFEgGUgx1c2VyX3VwbG9hZHMM/0/0/0/video.3gp");
    vv.setVideoURI(u);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.basic_video_player, container, false);
  }
}