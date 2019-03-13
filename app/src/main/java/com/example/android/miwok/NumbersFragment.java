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
public class NumbersFragment extends Fragment {

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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView =  inflater.inflate(R.layout.word_list, container, false);
        final ArrayList<Word> mNumbers = new ArrayList<>();

        mAudioManager = (AudioManager) getActivity().getApplication().getSystemService(Context.AUDIO_SERVICE);

        mNumbers.add(new Word("lutti", "one",
                R.drawable.number_one, R.raw.number_one));
        mNumbers.add(new Word("otiiko", "two",
                R.drawable.number_two, R.raw.number_two));
        mNumbers.add(new Word("tolooskosu", "three",
                R.drawable.number_three, R.raw.number_three));
        mNumbers.add(new Word("oyyisa", "four",
                R.drawable.number_four, R.raw.number_four));
        mNumbers.add(new Word("massokka", "five",
                R.drawable.number_five, R.raw.number_five));
        mNumbers.add(new Word("temmokka", "six",
                R.drawable.number_six, R.raw.number_six));
        mNumbers.add(new Word("kenekaku", "seven",
                R.drawable.number_seven, R.raw.number_seven));
        mNumbers.add(new Word("kawinta", "eight",
                R.drawable.number_eight, R.raw.number_eight));
        mNumbers.add(new Word("wo´e", "nine",
                R.drawable.number_nine, R.raw.number_nine));
        mNumbers.add(new Word("na´áacha", "ten",
                R.drawable.number_ten, R.raw.number_ten));

        ListView listView = mRootView.findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(getActivity(), mNumbers, R.color.category_numbers);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), mNumbers.get(i).getAudioResourceId());
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
