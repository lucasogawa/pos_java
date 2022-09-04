package com.ogawalucas.automobilesupplycontrol.supply.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogawalucas.automobilesupplycontrol.R;
import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;
import com.ogawalucas.automobilesupplycontrol.utils.DateUtils;

import java.util.List;

public class SupplyAdapter extends BaseAdapter {

    Context context;
    List<Supply> supplies;

    private static class SupplyHolder {
        public TextView tvFuelStation;
        public TextView tvDate;
        public TextView tvTypeOfFuel;
        public TextView tvKilometers;
        public TextView tvLiters;
        public TextView tvAmountPaid;
    }

    public SupplyAdapter() {

    }

    public SupplyAdapter(Context context, List<Supply> supplies) {
        this.context = context;
        this.supplies = supplies;
    }

    @Override
    public int getCount() {
        return supplies.size();
    }

    @Override
    public Object getItem(int i) {
        return supplies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SupplyHolder holder;

        if (view == null) {
            var inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_supply, viewGroup, false);
            holder = new SupplyHolder();
            holder.tvFuelStation = view.findViewById(R.id.tvFuelStation);
            holder.tvDate = view.findViewById(R.id.tvDate);
            holder.tvTypeOfFuel = view.findViewById(R.id.tvTypeOfFuel);
            holder.tvKilometers = view.findViewById(R.id.tvKilometers);
            holder.tvLiters = view.findViewById(R.id.tvLiters);
            holder.tvAmountPaid = view.findViewById(R.id.tvAmountPaid);

            view.setTag(holder);
        } else {
            holder = (SupplyHolder) view.getTag();
        }

        holder.tvFuelStation.setText(supplies.get(i).getFuelStation());
        holder.tvDate.setText(DateUtils.format(context, supplies.get(i).getDate()));
        holder.tvTypeOfFuel.setText(supplies.get(i).getTypeOfFuel());
        holder.tvKilometers.setText(String.valueOf(supplies.get(i).getKilometers()));
        holder.tvLiters.setText(String.valueOf(supplies.get(i).getLiters()));
        holder.tvAmountPaid.setText(String.valueOf(supplies.get(i).getAmountPaid()));

        return view;
    }
}
