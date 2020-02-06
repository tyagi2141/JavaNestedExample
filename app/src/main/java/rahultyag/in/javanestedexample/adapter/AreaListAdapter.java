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
import rahultyag.in.javanestedexample.model.Area;


public class AreaListAdapter extends ArrayAdapter<Area> implements Filterable, ISpinnerSelectedView {

    private Context mContext;
    private List<Area> mBackupArray;
    private List<Area> mAreaList;
    private StringFilter mSearchFilter = new StringFilter();

    public AreaListAdapter(Context context, List<Area> strings) {
        super(context, R.layout.view_list_item);
        mContext = context;
        mAreaList = strings;
        mBackupArray = strings;
    }

    @Override
    public int getCount() {

        return mAreaList == null ? 0 : mAreaList.size()+1;
    }

    @Override
    public Area getItem(int position) {
       if (mAreaList != null && position > 0) {
          Area area = mAreaList.get(position-1);

           return area;
       }
        else{
            return null;
    }
    }

    @Override
    public long getItemId(int position) {

        if (mAreaList == null && position > 0) {

            return mAreaList.get(position).hashCode();
        }
        else
            return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        
        
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            TextView TxtVw_Terror = (TextView) view.findViewById(R.id.TxtVw_Terror);

           letters.setImageDrawable(getTextDrawable(mAreaList.get(position-1).getArea()));
            dispalyName.setText(mAreaList.get(position-1).getArea());
            TxtVw_Terror.setText(mAreaList.get(position-1).getTerritory());
        }
        return view;
    }

    @Override
    public View getSelectedView(int position) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            TextView TxtVw_Terror = (TextView) view.findViewById(R.id.TxtVw_Terror);
           letters.setImageDrawable(getTextDrawable(mAreaList.get(position-1).getArea()));
            dispalyName.setText(mAreaList.get(position-1).getArea());
            TxtVw_Terror.setText(mAreaList.get(position-1).getTerritory());
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
        return mSearchFilter;
    }

    public class StringFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = mBackupArray.size();
                filterResults.values = mBackupArray;
                return filterResults;
            }
            final List<Area> filterStrings = new ArrayList<>();
            for (Area text : mBackupArray) {
                if (text.getArea().toLowerCase().contains(constraint)) {
                    filterStrings.add(text);
                }
            }
            filterResults.count = filterStrings.size();
            filterResults.values = filterStrings;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAreaList = (ArrayList) results.values;
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
