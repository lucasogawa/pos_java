package com.ogawalucas.automobilesupplycontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AutomobileAdapter extends BaseAdapter {

    Context context;
    List<Automobile> automobiles;

    private static class AutomobileHolder {
        public TextView tvNickname;
        public TextView tvTravelCar;
        public TextView tvType;
        public TextView tvBrand;
        public TextView tvModel;
        public TextView tvColor;
        public TextView tvManufactoringYear;
    }

    public AutomobileAdapter() {

    }

    public AutomobileAdapter(Context context, List<Automobile> automobiles) {
        this.context = context;
        this.automobiles = automobiles;
    }

    @Override
    public int getCount() {
        return automobiles.size();
    }

    @Override
    public Object getItem(int i) {
        return automobiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AutomobileHolder holder;

        if (view == null) {
            var inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_automobile, viewGroup, false);
            holder = new AutomobileHolder();
            holder.tvNickname = view.findViewById(R.id.tvLvNickname);
            holder.tvTravelCar = view.findViewById(R.id.tvLvTravelCar);
            holder.tvType = view.findViewById(R.id.tvLvType);
            holder.tvBrand = view.findViewById(R.id.tvLvBrand);
            holder.tvModel = view.findViewById(R.id.tvLvModel);
            holder.tvColor = view.findViewById(R.id.tvLvColor);
            holder.tvManufactoringYear = view.findViewById(R.id.tvLvManufactoringYear);

            view.setTag(holder);
        } else {
            holder = (AutomobileHolder) view.getTag();
        }

        holder.tvNickname.setText(automobiles.get(i).getNickname());
        holder.tvTravelCar.setText(automobiles.get(i).toStringTravelCal(context));
        holder.tvType.setText(EType.valueOf(automobiles.get(i).getType()).toString(context));
        holder.tvBrand.setText(automobiles.get(i).getBrand());
        holder.tvModel.setText(automobiles.get(i).getModel());
        holder.tvColor.setText(automobiles.get(i).getColor());
        holder.tvManufactoringYear.setText(automobiles.get(i).getManufactoringYear());

        return view;
    }
}
