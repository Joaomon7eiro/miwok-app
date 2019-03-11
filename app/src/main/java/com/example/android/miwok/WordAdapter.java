package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mItemColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> wordArrayList, int itemColorResourceId) {
        super(context, 0, wordArrayList);
        mItemColorResourceId = itemColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mListItemView = convertView;

        if (mListItemView == null) {
            mListItemView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list, parent, false);
        }

        Word word = getItem(position);

        int mColor = ContextCompat.getColor(getContext(), mItemColorResourceId);

        LinearLayout mWords = mListItemView.findViewById(R.id.words);
        mWords.setBackgroundColor(mColor);

        TextView mMiwokWord = mListItemView.findViewById(R.id.miwok_word);
        mMiwokWord.setText(word.getMiwokTranslation());

        TextView mDefaultWord = mListItemView.findViewById(R.id.english_word);
        mDefaultWord.setText(word.getDefaultTranslation());

        ImageView mPlay = mListItemView.findViewById(R.id.play);
        mPlay.setBackgroundColor(mColor);

        ImageView mImage = mListItemView.findViewById(R.id.word_image);
        if (word.hasImage()) {
            mImage.setImageResource(word.getImageResourceId());
            mImage.setVisibility(View.VISIBLE);
        } else {
            mImage.setVisibility(View.GONE);
        }

        return mListItemView;
    }
}
