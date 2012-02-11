package com.restphone.audioworkshop;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * This is a a demonstration fragment with a VideoView. It finds a video in the
 * Environment.DIRECTORY_DCIM + "/Camera" directory and plays it.
 * 
 * <p>
 * 
 * Playing a video like this is extremely simple.
 */

public class BasicVideoFragment extends Fragment {
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.basic_video_player, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    loadAndPlayVideo();
  }

  /**
   * @return The first video returned by File#list matching .*3gp in
   *         Environment.DIRECTORY_DCIM/Camera. (Note that this almost certainly
   *         isn't useful outside of a demo.)
   */
  private Uri getMostRecentVideo() {
    final String absolutePathToCameraDirectory = new File(Environment.DIRECTORY_DCIM, "Camera").getAbsolutePath();
    File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(absolutePathToCameraDirectory);
    if (externalStoragePublicDirectory.isDirectory()) {
      for (String f : externalStoragePublicDirectory.list()) {
        if (f.matches(".*3gp$")) {
          File videoFile = new File(externalStoragePublicDirectory, f);
          return Uri.fromFile(videoFile);
        }
      }
    }
    return null;
  }

  /**
   * Finds the most recent video using {@link #getMostRecentVideo()}.
   * <p>
   * Loads that video into the video view.
   * <p>
   * Creates a media controller (stop, play, pause etc controls) and attaches it
   * to the video view.
   * <p>
   * Starts the video.
   */
  private void loadAndPlayVideo() {
    VideoView videoView = (VideoView) getActivity().findViewById(R.id.video_view);
    Uri u = getMostRecentVideo();
    videoView.setVideoURI(u);
    videoView.start();

    MediaController mc = new MediaController(getActivity());
    mc.setAnchorView(videoView);
    videoView.setMediaController(mc);
  }
}