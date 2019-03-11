/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> mPhrases = new ArrayList<>();

        mPhrases.add(new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        mPhrases.add(new Word("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name));
        mPhrases.add(new Word("oyaaset...", "My name is...", R.raw.phrase_my_name_is));
        mPhrases.add(new Word("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        mPhrases.add(new Word("kuchi achit", "I’m feeling good.", R.raw.phrase_im_feeling_good));
        mPhrases.add(new Word("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        mPhrases.add(new Word("hәә’ әәnәm", "Yes, I’m coming.", R.raw.phrase_yes_im_coming));
        mPhrases.add(new Word("әәnәm", "I’m coming.", R.raw.phrase_im_coming));
        mPhrases.add(new Word("yoowutis", "Let’s go.", R.raw.phrase_lets_go));
        mPhrases.add(new Word("әnni'nem", "Come here.", R.raw.phrase_come_here));

        ListView listView = findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(this, mPhrases, R.color.category_phrases);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(getApplication(), mPhrases.get(i).getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}
