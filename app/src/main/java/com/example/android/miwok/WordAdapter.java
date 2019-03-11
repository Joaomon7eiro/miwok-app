package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> wordArrayList) {
        super(context, 0, wordArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mListItemView = convertView;

        if (mListItemView == null) {
            mListItemView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list, parent, false);
        }

        Word word = getItem(position);

        TextView mMiwokWord = mListItemView.findViewById(R.id.miwok_word);
        mMiwokWord.setText(word.getMiwokTranslation());

        TextView mDefaultWord = mListItemView.findViewById(R.id.english_word);
        mDefaultWord.setText(word.getDefaultTranslation());

        return mListItemView;
    }
}
