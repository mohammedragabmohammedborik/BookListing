package com.example.mohammedragab.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MohammedRagab on 5/4/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param books is the list of book, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    /**
     * Returns a list item view that displays information about book like title of book and author name at the given position
     * in the list of book.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_list, parent, false);
        }

        // Find book at the given position in the list of books.
       Book currentbook = getItem(position);

        // Find the TextView with view Title book
        TextView titleBook = (TextView) listItemView.findViewById(R.id.text1);
        // Display title book in that TextView
        titleBook.setText(currentbook.getmTitlebook());
        // Find the TextView with view author  book
        TextView authorName = (TextView) listItemView.findViewById(R.id.text2);
        // Display author book in that TextView
        authorName.setText( currentbook.getmAuthorName());

       return  listItemView;

    }

}
