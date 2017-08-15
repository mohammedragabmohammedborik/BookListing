package com.example.mohammedragab.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    public static final String LOG_TAG = MainActivity.class.getName();
    // search button
    private Button search_button;
    // edit text to use for search
    private EditText editText;
    // string used to get string from edit text.
    private String queryWord = null;
    private TextView emptyTextView;
    private ListView bookListView;
    private BookAdapter bookAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_button = (Button) findViewById(R.id.next_button);
        editText = (EditText) findViewById(R.id.edit_text);
        emptyTextView = (TextView) findViewById(R.id.empty_view);

        bookListView = (ListView) findViewById(R.id.list_view);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        final ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    new GetBook().execute(BOOK_REQUEST_URL);
                } else {

                    emptyTextView.setText(R.string.NoConnect);
                    bookListView.setEmptyView(emptyTextView);
                    if(bookAdapter != null){
                    bookAdapter.clear();}


                    Log.e("this error","er");

                }
            }

        });

        new GetBook().execute(BOOK_REQUEST_URL);

    }
    //  method to get string from edit text.
    public String getBook() {
        queryWord = editText.getText().toString();
        if (queryWord == null) {
            bookListView.setEmptyView(emptyTextView);
        }
        return queryWord;
    }

    /**
     * Update the UI with the given book information.
     */
    private void updateUi(List<Book> books) {

        // Find a reference to the {@link ListView} in the layout

        // Create a new {@link ArrayAdapter} of book
        bookAdapter = new BookAdapter(this, books);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(bookAdapter);

    }

    // inner class  for  to support methods do in background.
    private class GetBook extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            String word_search = getBook();
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Book> result = QueryUtils.fetchBookName(urls[0], word_search);
            return result;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         * <p>
         * It IS okay to modify the UI within this method. We take the {@link Book} object
         * (which was returned from the doInBackground() method) and update the views on the screen.
         */
        @Override
        protected void onPostExecute(List<Book> book) {

            emptyTextView.setText(R.string.nobook);
            bookListView.setEmptyView(emptyTextView);


            // Hide loading indicator because the data has been loaded
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            if ((book != null) && !book.isEmpty()) {
                updateUi(book);
            }

        }
    }
}

