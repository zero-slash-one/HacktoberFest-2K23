package com.example.android.learn_spanish_application;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private final int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID SpanishWord

        TextView SpanishTextView =  listItemView.findViewById(R.id.SpanishWord);

        // Get the version name from the currentWord  object and
        // set this text on the name TextView
        SpanishTextView.setText(currentWord.getSpanishTranslation());

        // Find the TextView in the list_item.xml layout with the ID EnglishWord

        TextView EnglishTextView = listItemView.findViewById(R.id.EnglishWord);

        // Get the version number from the currentWord object and
        // set this text on the number TextView
        EnglishTextView.setText(currentWord.getEnglishTranslation());


        // Find the ImageView in the list_item.xml layout with the ID Images
        ImageView ImgImageView = listItemView.findViewById(R.id.images);

        if(currentWord.hasImage())
        {
            // Get the version number from the currentWord object and
            // set this Image resource on the number ImageView
            ImgImageView.setImageResource(currentWord.getImageResourceID());

            //if  visibility gone then turn back to visible
            ImgImageView.setVisibility(View.VISIBLE);
        }
        else{
            //if img source is not found than doesn't display the image( Which is in phrases Activity)
            ImgImageView.setVisibility(View.GONE);
        }

        //set theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        ////find the color  that the resource id maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        //set the background color of the text_Container view
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and 1 ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}

