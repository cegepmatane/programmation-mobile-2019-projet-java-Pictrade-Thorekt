package ca.qc.cgmatane.pictrade.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class ListeFavoriAddapter extends BaseAdapter {

    // Declare Variables

    Context monContexte;
    LayoutInflater inflater;
    private List<Commerce> commerceNomListe = null;
    private ArrayList<Commerce> arraylist;

    public ListeFavoriAddapter(Context context, List<Commerce> commerceNomListe) {
        monContexte = context;
        this.commerceNomListe = commerceNomListe;
        inflater = LayoutInflater.from(monContexte);
        this.arraylist = new ArrayList<Commerce>();
        this.arraylist.addAll(commerceNomListe);
    }

    public class ViewHolder {
        TextView nom;
        TextView adresse;
    }

    @Override
    public int getCount() {
        return commerceNomListe.size();
    }

    @Override
    public Commerce getItem(int position) {
        return commerceNomListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.vue_ligne_liste_favori, null);
            // Locate the TextViews in listview_item.xml
            holder.nom = (TextView) view.findViewById(R.id.nom);
            holder.adresse = (TextView) view.findViewById(R.id.adresse);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.nom.setText(commerceNomListe.get(position).getNom());
        holder.adresse.setText(commerceNomListe.get(position).getAdresse());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        commerceNomListe.clear();
        if (charText.length() == 0) {
            commerceNomListe.addAll(arraylist);
        } else {
            for (Commerce wp : arraylist) {
                if (wp.getNom().toLowerCase(Locale.getDefault()).contains(charText)) {
                    commerceNomListe.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}