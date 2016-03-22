package autosuggetion.searveiew.com.searchviewautosuggetion;

import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Prasanna on 3/18/16.
 */
public class SearchSuggestionsAdapter extends SimpleCursorAdapter {
    private static final String[] mFields = {"_id", "result"};
    private static final String[] mVisible = {"result"};
    private static final int[] mViewIds = {android.R.id.icon};
    public SuggestionsCursor suggestionsCursor;

    public SearchSuggestionsAdapter(Context context) {
        super(context, android.R.layout.select_dialog_singlechoice, null, mVisible, mViewIds, 0);
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        suggestionsCursor = new SuggestionsCursor(constraint);
        return suggestionsCursor;
    }

    public class SuggestionsCursor extends AbstractCursor {
        private ArrayList<String> mResults;

        public SuggestionsCursor(CharSequence constraint) {
            mResults = new ArrayList<String>();
            mResults.clear();
            List<String> citiesList = new ArrayList<>();
            citiesList.add("TESt");
            citiesList.add("TESt");
            citiesList.add("TESt");
            citiesList.add("TESt");
            citiesList.add("TESt");
            mResults.addAll(citiesList);
            if (!TextUtils.isEmpty(constraint)) {
                String constraintString = constraint.toString().toLowerCase(Locale.ROOT);
                Iterator<String> iter = mResults.iterator();
                while (iter.hasNext()) {
                    if (!iter.next().toLowerCase(Locale.ROOT).startsWith(constraintString)) {
                        iter.remove();
                    }
                }
            }
        }

        @Override
        public int getCount() {
            return mResults.size();
        }

        @Override
        public String[] getColumnNames() {
            return mFields;
        }

        @Override
        public long getLong(int column) {
            if (column == 0) {
                return mPos;
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public String getString(int column) {
            return mResults.get(mPos);
        }

        @Override
        public short getShort(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public int getInt(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public float getFloat(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public double getDouble(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public boolean isNull(int column) {
            return false;
        }

        public String getSeletedString(int position){
            return mResults.get(mPos);
        }
    }
}
