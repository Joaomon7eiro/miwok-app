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
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list, parent, false);
        }

        Word word = getItem(position);

        int color = ContextCompat.getColor(getContext(), mItemColorResourceId);

        LinearLayout words = listItemView.findViewById(R.id.words);
        words.setBackgroundColor(color);

        TextView miwokWord = listItemView.findViewById(R.id.miwok_word);
        miwokWord.setText(word.getMiwokTranslation());

        TextView defaultWord = listItemView.findViewById(R.id.english_word);
        defaultWord.setText(word.getDefaultTranslation());

        ImageView image = listItemView.findViewById(R.id.word_image);
        if (word.hasImage()) {
            image.setImageResource(word.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
