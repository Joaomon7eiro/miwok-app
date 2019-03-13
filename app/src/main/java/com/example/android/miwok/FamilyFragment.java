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
public class FamilyFragment extends Fragment {

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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> mFamily = new ArrayList<>();

        mAudioManager = (AudioManager) getActivity().getApplication().getSystemService(Context.AUDIO_SERVICE);

        mFamily.add(new Word("әpә", "father",
                R.drawable.family_father, R.raw.family_father));
        mFamily.add(new Word("әṭa", "mother",
                R.drawable.family_mother, R.raw.family_mother));
        mFamily.add(new Word("angsi", "son",
                R.drawable.family_son, R.raw.family_son));
        mFamily.add(new Word("tune", "daughter",
                R.drawable.family_daughter, R.raw.family_daughter));
        mFamily.add(new Word("taachi", "older brother",
                R.drawable.family_older_brother, R.raw.family_older_brother));
        mFamily.add(new Word("chalitti", "younger brother",
                R.drawable.family_younger_brother, R.raw.family_younger_brother));
        mFamily.add(new Word("teṭe", "older sister",
                R.drawable.family_older_sister, R.raw.family_older_sister));
        mFamily.add(new Word("kolliti", "younger sister",
                R.drawable.family_younger_sister, R.raw.family_younger_sister));
        mFamily.add(new Word("ama", "grandmother",
                R.drawable.family_grandmother, R.raw.family_grandmother));
        mFamily.add(new Word("paapa", "grandfather",
                R.drawable.family_grandfather, R.raw.family_grandfather));

        ListView mListView = mRootView.findViewById(R.id.list);

        WordAdapter mAdapter = new WordAdapter(this, mFamily, R.color.category_family);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), mFamily.get(i).getAudioResourceId());
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
