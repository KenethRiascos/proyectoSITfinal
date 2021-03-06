package com.example.andresteran_i014213.projectofinal_sti.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andresteran_i014213.projectofinal_sti.Data.DataUser;
import com.example.andresteran_i014213.projectofinal_sti.LoginActivity;
import com.example.andresteran_i014213.projectofinal_sti.Models.Bus;
import com.example.andresteran_i014213.projectofinal_sti.Models.Favorites;
import com.example.andresteran_i014213.projectofinal_sti.Models.User;
import com.example.andresteran_i014213.projectofinal_sti.R;
import com.example.andresteran_i014213.projectofinal_sti.Views.Fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenet on 27/05/2017.
 */

public class BusAdapter  extends BaseAdapter {

    List<Bus> userList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public BusAdapter(Context context, List<Bus> userList) {
        this.context = context;
        this.userList = userList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return userList.size();
    }

    @Override
    public Bus getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_bus, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            viewHolder.checkFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Bus bus = (Bus) cb.getTag();
                    Toast.makeText(context, context.getString(R.string.txt_route_search)
                            +bus.getRoute()+ context.getString(R.string.txt_neighborhood_routes_search) + bus.getNeighborhood() +
                                    (cb.isChecked()? context.getString(R.string.txt_check_true) : context.getString(R.string.txt_check_false) ),
                            Toast.LENGTH_SHORT).show();
                    if (cb.isChecked()){
                        cb.setEnabled(false);
                        SearchFragment.busFavorite=bus;
                        SearchFragment.createDataFavorite();
                    }
                    bus.setCheck(cb.isChecked());
                }
            });

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Bus bus = getItem(position);
        viewHolder.route.setText(bus.getRoute());
        viewHolder.neighborhood.setText(bus.getNeighborhood());
        viewHolder.checkFavorite.setText(bus.getRoute());
        viewHolder.checkFavorite.setChecked(bus.isCheck());
        if (viewHolder.checkFavorite.isChecked()==true) viewHolder.checkFavorite.setEnabled(false);
        viewHolder.checkFavorite.setTag(bus);

        return convertView;
    }

    public class ViewHolder{
        TextView route;
        TextView neighborhood;
        CheckBox checkFavorite;

        public ViewHolder(View item) {
            route = (TextView) item.findViewById(R.id.id_item_bus_route);
            neighborhood = (TextView) item.findViewById(R.id.id_item_bus_neighborhood);
            checkFavorite = (CheckBox) item.findViewById(R.id.id_item_chb_favorite);
        }
    }

}