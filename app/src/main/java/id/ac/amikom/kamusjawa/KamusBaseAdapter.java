package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class KamusBaseAdapter extends BaseAdapter {
    ArrayList<EntitasKamus> searchArrayList;
    LayoutInflater mInflater;

    public KamusBaseAdapter(Context context, ArrayList<EntitasKamus> result){
        searchArrayList = result;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;

        if(v==null){
            v = mInflater.inflate(R.layout.item_custom_listview,null);
            holder = new ViewHolder();

            holder.bIndo = (TextView) v.findViewById(R.id.outIndo);
            holder.bJawa = (TextView) v.findViewById(R.id.outJawa);

            v.setTag(holder);

        }else{
            holder = (ViewHolder) v.getTag();

        }
        holder.bIndo.setText(searchArrayList.get(position).getIndo());
        holder.bJawa.setText(searchArrayList.get(position).getJawa());
        return v;
    }

    class ViewHolder{
        TextView bIndo,bJawa;
    }
}
