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
 * <p>
 * Playing video is usually simpler than playing audio. Audio has a background
 * mode; there are lots of reasons why you'd want sound to continue even when
 * you're doing something else. Normally video is only interesting when you're
 * the thing the user is actually looking at.
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
    getActivity().setTitle(toString());
  }

  /**
   * @return The first video returned by File#list matching .*3gp in
   *         Environment.DIRECTORY_DCIM/Camera. (Note that this almost certainly
   *         isn't useful outside of a demo, and doesn't match most people's
   *         idea of "most recent".)
   */
  private Uri getMostRecentVideo() {
    // Note that this is a hack. I don't think there's a way to ask the camera
    // where it stores video.
    final String absolutePathToCameraDirectory = new File(Environment.DIRECTORY_DCIM,
        "Camera").getAbsolutePath();

    File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(absolutePathToCameraDirectory);

    // It's possible the camera hasn't created the directory we're looking for.
    // In a real application, you'd want to tell the user
    // that our state is unexpected.
    if (externalStoragePublicDirectory.isDirectory()) {
      for (String f : externalStoragePublicDirectory.list()) {
        // Another hack. There are lots of possible video formats.
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