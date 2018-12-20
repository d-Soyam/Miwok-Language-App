package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> list, int colorResourceId) {
        super(context, 0, list);
        this.mColorResourceId = colorResourceId;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        Word currentWord = getItem(position);
        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getmDefaultTranlation());

       ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if (currentWord.hasImage()) {

            imageView.setImageResource(currentWord.getmImageResourceId());
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        View textCotainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textCotainer.setBackgroundColor(color);

       return listItemView;
    }

}
