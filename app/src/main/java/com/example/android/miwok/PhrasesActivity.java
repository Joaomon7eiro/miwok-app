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

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> mPhrases = new ArrayList<>();

        mPhrases.add(new Word("minto wuksus", "Where are you going?"));
        mPhrases.add(new Word("tinnә oyaase'nә", "What is your name?"));
        mPhrases.add(new Word("oyaaset...", "My name is..."));
        mPhrases.add(new Word("michәksәs?", "How are you feeling?"));
        mPhrases.add(new Word("kuchi achit", "I’m feeling good."));
        mPhrases.add(new Word("әәnәs'aa?", "Are you coming?"));
        mPhrases.add(new Word("hәә’ әәnәm", "Yes, I’m coming."));
        mPhrases.add(new Word("әәnәm", "I’m coming."));
        mPhrases.add(new Word("yoowutis", "Let’s go."));
        mPhrases.add(new Word("әnni'nem", "Come here."));

        ListView listView = findViewById(R.id.list);

        WordAdapter adapter = new WordAdapter(this, mPhrases, R.color.category_phrases);
        listView.setAdapter(adapter);
    }
}
