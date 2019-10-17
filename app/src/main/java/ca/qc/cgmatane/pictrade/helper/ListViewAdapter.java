package ca.qc.cgmatane.pictrade.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class ListViewAdapter extends BaseAdapter {

    Context monContext;
    LayoutInflater inflater;
    private List<Commerce> commerceListe = null;
    private ArrayList<Commerce> arrayList;

    public ListViewAdapter(Context context, List<Commerce> commerceNomListe){
        monContext = context;
        this.commerceListe = commerceNomListe;
        inflater = LayoutInflater.from(monContext);
        this.arrayList = new ArrayList<Commerce>();
        this.arrayList = addAll(commerceNomListe);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount(){
        return commerceListe.size();
    }

    @Override
    public Commerce getItem(int position){
        return commerceListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(animalNamesList.get(position).getAnimalName());
        return view;
    }


}
