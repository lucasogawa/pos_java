package com.ogawalucas.automobilesupplycontrol.supply.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogawalucas.automobilesupplycontrol.R;
import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;

import java.util.List;

public class SupplyAdapter extends BaseAdapter {

    private static final String EDIT_TEXT_PATTERN_1 = "%s - %s";
    private static final String EDIT_TEXT_PATTERN_2 = "%s - %s Km/L";

    private Context context;
    private List<Supply> supplies;

    private static class SupplyHolder {
        public TextView tvFuelStationDate;
        public TextView tvTypeOfFuelKilometersPerLiter;
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
            holder.tvFuelStationDate = view.findViewById(R.id.tvFuelStationDate);
            holder.tvTypeOfFuelKilometersPerLiter = view.findViewById(R.id.tvTypeOfFuelKilometersPerLiter);
            view.setTag(holder);
        } else {
            holder = (SupplyHolder) view.getTag();
        }

        var supply = supplies.get(i);

        holder.tvFuelStationDate.setText(String.format(EDIT_TEXT_PATTERN_1, supply.getFormattedDate(context), supply.getFuelStation()));
        holder.tvTypeOfFuelKilometersPerLiter.setText(String.format(EDIT_TEXT_PATTERN_2, supply.getTypeOfFuel(), supply.getKilometersPerLiter()));

        return view;
    }
}
