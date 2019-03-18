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
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> phrases = new ArrayList<>();

        mAudioManager = (AudioManager) getActivity().getApplication().getSystemService(Context.AUDIO_SERVICE);

        phrases.add(new Word("minto wuksus", "Where are you going?",
                R.raw.phrase_where_are_you_going));
        phrases.add(new Word("tinnә oyaase'nә", "What is your name?",
                R.raw.phrase_what_is_your_name));
        phrases.add(new Word("oyaaset...", "My name is...",
                R.raw.phrase_my_name_is));
        phrases.add(new Word("michәksәs?", "How are you feeling?",
                R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("kuchi achit", "I’m feeling good.",
                R.raw.phrase_im_feeling_good));
        phrases.add(new Word("әәnәs'aa?", "Are you coming?",
                R.raw.phrase_are_you_coming));
        phrases.add(new Word("hәә’ әәnәm", "Yes, I’m coming.",
                R.raw.phrase_yes_im_coming));
        phrases.add(new Word("әәnәm", "I’m coming.",
                R.raw.phrase_im_coming));
        phrases.add(new Word("yoowutis", "Let’s go.",
                R.raw.phrase_lets_go));
        phrases.add(new Word("әnni'nem", "Come here.",
                R.raw.phrase_come_here));

        ListView listView = rootView.findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(getActivity(), phrases, R.color.category_phrases);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), phrases.get(i).getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;
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
