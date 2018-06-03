package com.reppernews.sokajamur;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Server on 31/05/2018.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
    Context context;
    ArrayList<HashMap<String, String>> list_data;
    public AdapterList(PesananSaya mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterList.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape;
        ImageView imghape;

        public ViewHolder(View itemView) {
            super(itemView);

            txthape = (TextView) itemView.findViewById(R.id.txthape);
            imghape = (ImageView) itemView.findViewById(R.id.imghp);
        }
    }
}
