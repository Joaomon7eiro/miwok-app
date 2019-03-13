package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    MediaPlayer mMediaPlayer;
    AudioManager mAudioManager;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                default:
            }
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> mColors = new ArrayList<>();

        mAudioManager = (AudioManager) getActivity().getApplication().getSystemService(Context.AUDIO_SERVICE);

        mColors.add(new Word("weṭeṭṭi", "red", R.drawable.color_red,
                R.raw.color_red));
        mColors.add(new Word("chokokki", "green", R.drawable.color_green,
                R.raw.color_green));
        mColors.add(new Word("ṭakaakki", "brown", R.drawable.color_brown,
                R.raw.color_brown));
        mColors.add(new Word("ṭopoppi", "gray", R.drawable.color_gray,
                R.raw.color_gray));
        mColors.add(new Word("kululli", "black", R.drawable.color_black,
                R.raw.color_black));
        mColors.add(new Word("kelelli", "white", R.drawable.color_white,
                R.raw.color_white));
        mColors.add(new Word("ṭopiisә", "dusty yellow",
                R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        mColors.add(new Word("chiwiiṭә", "mustard yellow",
                R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        ListView mListView = mRootView.findViewById(R.id.list);

        WordAdapter mAdapter = new WordAdapter(getActivity(), mColors, R.color.category_colors);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int mResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);

                if (mResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), mColors.get(i).getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return mRootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
