package com.full1.finaljecheon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by Full1 on 2017-07-30.
 */

public class Fragment1Home extends Fragment {
    View view;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        context = getActivity();
        SetVideoView();
        return view;
    }

    public void SetVideoView()
    {
        //홍보동영상 셋팅
        VideoView videoView = (VideoView)view.findViewById(R.id.videoview);
        videoView.setVideoURI(Uri.parse("http://www.korean-medicine-expo.kr/home/imgs/main/spot2.mp4"));
        final MediaController mediaController = new MediaController(context);
        videoView.setMediaController(mediaController);
        //videoView.start();

        TextView textView = (TextView)view.findViewById(R.id.textVIew_Day);
        textView.setText("D "+String.valueOf(new Dday().Dday(2017,9,22)));

    }

}
