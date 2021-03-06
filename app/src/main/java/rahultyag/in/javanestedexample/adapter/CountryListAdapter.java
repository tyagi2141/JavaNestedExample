package rahultyag.in.javanestedexample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.interfaces.ISpinnerSelectedView;
import gr.escsoft.michaelprimez.searchablespinner.tools.UITools;
import rahultyag.in.javanestedexample.R;
import rahultyag.in.javanestedexample.model.Country;


public class CountryListAdapter extends ArrayAdapter<Country> implements Filterable, ISpinnerSelectedView {

    private Context mContext;
    private List<Country> mBackupStrings;
    private List<Country> mStrings;
    private StringFilter mStringFilter = new StringFilter();

    public CountryListAdapter(Context context, List<Country> strings) {
        super(context, R.layout.view_list_item);
        mContext = context;
        mStrings = strings;
        mBackupStrings = strings;
    }

    @Override
    public int getCount() {

        return mStrings == null ? 0 : mStrings.size()+1;
    }

    @Override
    public Country getItem(int position) {
       // Country area=mStrings.get(position);
       if (mStrings != null && position > 0) {
          Country area = mStrings.get(position-1);

           return area;
       }
        else{
            return null;
    }
    }

    @Override
    public long getItemId(int position) {

        if (mStrings == null && position > 0) {

           // Country area=mStrings.get(position);
            return mStrings.get(position).hashCode();
        }
        else
            return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
      //  Country area=mStrings.get(position);
        
        
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            TextView TxtVw_Terror = (TextView) view.findViewById(R.id.TxtVw_Terror);

            Log.e("jiijijijij",""+mStrings.size());
           letters.setImageDrawable(getTextDrawable(mStrings.get(position-1).getCountry()));
            dispalyName.setText(mStrings.get(position-1).getCountry());
            TxtVw_Terror.setText(mStrings.get(position-1).getTerritory());
        }
        return view;
    }

    @Override
    public View getSelectedView(int position) {
        View view = null;
       // Country area=mStrings.get(position);
        Log.e("yfyffyfyfyf",mStrings.toString());
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            TextView TxtVw_Terror = (TextView) view.findViewById(R.id.TxtVw_Terror);
           letters.setImageDrawable(getTextDrawable(mStrings.get(position-1).getCountry()));
            dispalyName.setText(mStrings.get(position-1).getCountry());
            TxtVw_Terror.setText(mStrings.get(position-1).getTerritory());
        }
        return view;
    }

    @Override
    public View getNoSelectionView() {
        View view = View.inflate(mContext, R.layout.view_list_no_selection_item, null);
        return view;
    }

    private TextDrawable getTextDrawable(String displayName) {
        TextDrawable drawable = null;
        if (!TextUtils.isEmpty(displayName)) {
            int color2 = ColorGenerator.MATERIAL.getColor(displayName);
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(UITools.dpToPx(mContext, 32))
                    .height(UITools.dpToPx(mContext, 32))
                    .textColor(Color.WHITE)
                    .toUpperCase()
                    .endConfig()
                    .round()
                    .build(displayName.substring(0, 1), color2);
        } else {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(UITools.dpToPx(mContext, 32))
                    .height(UITools.dpToPx(mContext, 32))
                    .endConfig()
                    .round()
                    .build("?", Color.GRAY);
        }
        return drawable;
    }

    @Override
    public Filter getFilter() {
        return mStringFilter;
    }

    public class StringFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = mBackupStrings.size();
                filterResults.values = mBackupStrings;
                return filterResults;
            }
            final List<Country> filterStrings = new ArrayList<>();
            for (Country text : mBackupStrings) {
                if (text.getCountry().toLowerCase().contains(constraint)) {
                    filterStrings.add(text);
                }
            }
            filterResults.count = filterStrings.size();
            filterResults.values = filterStrings;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mStrings = (ArrayList) results.values;
            Log.e("jhbjhbjhbjhb",mStrings.toString()+"\n"+results.values);
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }
    }

    private class ItemView {
        public ImageView mImageView;
        public TextView mTextView;
    }

    public enum ItemViewType {
        ITEM, NO_SELECTION_ITEM;
    }
}
