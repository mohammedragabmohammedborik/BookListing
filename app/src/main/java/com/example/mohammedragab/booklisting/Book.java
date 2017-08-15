package com.example.mohammedragab.booklisting;

/**
 * Created by MohammedRagab on 5/4/2017.
 */

public class Book {
    // string for title book
    private String mTitlebook;
    // string of author name
  private  String mAuthorName;
    // book constructor
    public Book(String titlebook, String authorName) {
        mTitlebook = titlebook;
        mAuthorName = authorName;
    }
    // get method for title and author
    public String getmTitlebook() {
        return mTitlebook;
    }

    public String getmAuthorName() {
        return mAuthorName;
    }
}
