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
public class PhrasesFragment extends Fragment {

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

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> mPhrases = new ArrayList<>();

        mAudioManager = (AudioManager) getActivity().getApplication().getSystemService(Context.AUDIO_SERVICE);

        mPhrases.add(new Word("minto wuksus", "Where are you going?",
                R.raw.phrase_where_are_you_going));
        mPhrases.add(new Word("tinnә oyaase'nә", "What is your name?",
                R.raw.phrase_what_is_your_name));
        mPhrases.add(new Word("oyaaset...", "My name is...",
                R.raw.phrase_my_name_is));
        mPhrases.add(new Word("michәksәs?", "How are you feeling?",
                R.raw.phrase_how_are_you_feeling));
        mPhrases.add(new Word("kuchi achit", "I’m feeling good.",
                R.raw.phrase_im_feeling_good));
        mPhrases.add(new Word("әәnәs'aa?", "Are you coming?",
                R.raw.phrase_are_you_coming));
        mPhrases.add(new Word("hәә’ әәnәm", "Yes, I’m coming.",
                R.raw.phrase_yes_im_coming));
        mPhrases.add(new Word("әәnәm", "I’m coming.",
                R.raw.phrase_im_coming));
        mPhrases.add(new Word("yoowutis", "Let’s go.",
                R.raw.phrase_lets_go));
        mPhrases.add(new Word("әnni'nem", "Come here.",
                R.raw.phrase_come_here));

        ListView mListView = mRootView.findViewById(R.id.list);

        WordAdapter mAdapter = new WordAdapter(getActivity(), mPhrases, R.color.category_phrases);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int mResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);

                if (mResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), mPhrases.get(i).getAudioResourceId());
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
