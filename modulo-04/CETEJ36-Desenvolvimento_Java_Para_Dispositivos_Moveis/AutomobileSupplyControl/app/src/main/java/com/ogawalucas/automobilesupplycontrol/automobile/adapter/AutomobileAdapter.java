package com.ogawalucas.automobilesupplycontrol.automobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogawalucas.automobilesupplycontrol.R;

import java.util.List;

public class AutomobileAdapter extends BaseAdapter {

    private static final String EDIT_TEXT_PATTERN = "%s - %s Km/L";

    private Context context;
    private List<AutomobileAdapterView> adapterViews;

    private static class AutomobileHolder {
        public TextView tvNickname;
        public TextView tvTypeOfFuelKilometersPerLiter;
    }

    public AutomobileAdapter() {

    }

    public AutomobileAdapter(Context context, List<AutomobileAdapterView> adapterViews) {
        this.context = context;
        this.adapterViews = adapterViews;
    }

    @Override
    public int getCount() {
        return adapterViews.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterViews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        AutomobileHolder holder;

        if (view == null) {
            var inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_automobile, viewGroup, false);
            holder = new AutomobileHolder();
            holder.tvNickname = view.findViewById(R.id.tvNickname);
            holder.tvTypeOfFuelKilometersPerLiter = view.findViewById(R.id.tvTypeOfFuelKilometersPerLiter);

            view.setTag(holder);
        } else {
            holder = (AutomobileHolder) view.getTag();
        }

        holder.tvNickname.setText(getNickame(index));
        holder.tvTypeOfFuelKilometersPerLiter.setText(getTextAvgSupplies(index));

        return view;
    }

    private String getNickame(int index) {
        return this.adapterViews.get(index).getNickname();
    }

    private String getTextAvgSupplies(int index) {
        var avgSupplies = this.adapterViews.get(index).getAvgSupplies();
        var text = new StringBuilder();


        for (int i = 0; i < avgSupplies.size(); i++) {
            if (i != 0) {
                text.append("\n");
            }

            var avgSupply = avgSupplies.get(i);
            text.append(String.format(EDIT_TEXT_PATTERN, avgSupply.getTypeOfFuel(), avgSupply.getAvgKilometersPerLiter()));
        }

        return text.toString();
    }
}
