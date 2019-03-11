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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> mNumbers = new ArrayList<>();

        mNumbers.add(new Word("lutti", "one"));
        mNumbers.add(new Word("otiiko", "two"));
        mNumbers.add(new Word("tolooskosu", "three"));
        mNumbers.add(new Word("oyyisa", "four"));
        mNumbers.add(new Word("massokka", "five"));
        mNumbers.add(new Word("temmokka", "six"));
        mNumbers.add(new Word("kenekaku", "seven"));
        mNumbers.add(new Word("kawinta", "eight"));
        mNumbers.add(new Word("wo´e", "nine"));
        mNumbers.add(new Word("na´áacha", "ten"));

        ListView listView = findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(this, mNumbers);
        listView.setAdapter(adapter);
    }
}
