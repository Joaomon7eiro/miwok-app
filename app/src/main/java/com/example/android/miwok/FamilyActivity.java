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

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> mFamily = new ArrayList<>();

        mFamily.add(new Word("әpә",
                "father", R.drawable.family_father, R.raw.family_father));
        mFamily.add(new Word("әṭa",
                "mother", R.drawable.family_mother, R.raw.family_mother));
        mFamily.add(new Word("angsi",
                "son", R.drawable.family_son, R.raw.family_son));
        mFamily.add(new Word("tune",
                "daughter", R.drawable.family_daughter, R.raw.family_daughter));
        mFamily.add(new Word("taachi",
                "older brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        mFamily.add(new Word("chalitti",
                "younger brother", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        mFamily.add(new Word("teṭe",
                "older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        mFamily.add(new Word("kolliti",
                "younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        mFamily.add(new Word("ama",
                "grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        mFamily.add(new Word("paapa",
                "grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));

        ListView listView = findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(this, mFamily, R.color.category_family);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(getApplication(), mFamily.get(i).getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}
