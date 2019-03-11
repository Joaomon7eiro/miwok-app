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

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> mNumbers = new ArrayList<>();

        mNumbers.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        mNumbers.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        mNumbers.add(new Word("tolooskosu", "three", R.drawable.number_three, R.raw.number_three));
        mNumbers.add(new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        mNumbers.add(new Word("massokka", "five", R.drawable.number_five, R.raw.number_five));
        mNumbers.add(new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        mNumbers.add(new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        mNumbers.add(new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        mNumbers.add(new Word("wo´e", "nine", R.drawable.number_nine, R.raw.number_nine));
        mNumbers.add(new Word("na´áacha", "ten", R.drawable.number_ten, R.raw.number_ten));

        ListView listView = findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(this, mNumbers, R.color.category_numbers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(getApplication(), mNumbers.get(i).getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}
