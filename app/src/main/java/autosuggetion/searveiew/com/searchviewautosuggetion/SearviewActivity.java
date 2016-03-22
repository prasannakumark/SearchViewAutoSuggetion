package autosuggetion.searveiew.com.searchviewautosuggetion;

import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SearviewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searview);
        searchView = (SearchView)findViewById(R.id.searchView);
        setSearchViewResources();
        //Callbacks for changes to the query text.
        searchView.setOnQueryTextListener(this);
        searchListeners();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    //query hint string to be displayed in the empty query field.
    //mSearchView.setQueryHint("Search title, description and email");
    private void setSearchViewResources() {

        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        //This way of setting hint text not only sets hint text but also removes the default magnifier icon from the query field
        searchEditText.setHint("Search by ID, Title or Description");

        //setting color for search hint
        searchEditText.setHintTextColor(getResources().getColor(android.R.color.background_light));

        //setting text color for user-entered text in the query field
        searchEditText.setTextColor(getResources().getColor(android.R.color.black));
    }
    /**
     * search Listeners for auto select
     */
    private void searchListeners() {
        final SearchSuggestionsAdapter searchSuggestionsAdapter = new SearchSuggestionsAdapter(this);
        ImageView imageView = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchView.setSuggestionsAdapter(searchSuggestionsAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                searchView.setQuery(searchSuggestionsAdapter.suggestionsCursor.getString(position), false);
                searchView.clearFocus();
                return true;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
