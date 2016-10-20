package com.anna.gestionbancaire.mListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.anna.gestionbancaire.R;
import com.anna.gestionbancaire.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by annaanjalli on 7/7/16.
 */

public class CustomAdapter extends BaseAdapter {


    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    Spacecraft spacecraft;



    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
        {

            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        if (convertView == null)
        {

            convertView = inflater.inflate(R.layout.client_model, parent,false);

        }


        //BIND DATA
        MyViewHolder holder = new MyViewHolder(convertView);

        holder.numCompteTxt.setText(spacecrafts.get(position).getNumCompte());
        holder.nomClientTxt.setText(spacecrafts.get(position).getNomClient());
        holder.soldeTxt.setText(spacecrafts.get(position).getSolde());

         convertView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 Toast.makeText(c, spacecrafts.get(position).getNumCompte(), Toast.LENGTH_SHORT).show();
                 Toast.makeText(c, spacecrafts.get(position).getNomClient(), Toast.LENGTH_SHORT).show();
                 Toast.makeText(c, spacecrafts.get(position).getSolde(), Toast.LENGTH_SHORT).show();
             }
         });

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {

                spacecraft = (Spacecraft) getItem(position);

            }
        });

        return convertView;
    }


    //EXPOSE NUMCOMPTE, NOMCLIENT, SOLDE AND ID

    public int getSelectedItemID()
    {
        return  spacecraft.getId();
    }


    public String getSelectedItemNumCompte()
    {
        return  spacecraft.getNumCompte();
    }

    public String getSelectedItemNomClient()
    {
        return  spacecraft.getNomClient();
    }

    public int getSelectedItemSolde()
    {
        return  spacecraft.getSolde();
    }
}
