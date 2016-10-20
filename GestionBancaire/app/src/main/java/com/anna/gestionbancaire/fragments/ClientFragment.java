package com.anna.gestionbancaire.fragments;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.widget.Toolbar;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.anna.gestionbancaire.R;
import com.anna.gestionbancaire.mDataBase.DBAdapter;
import com.anna.gestionbancaire.mDataObject.Spacecraft;
import com.anna.gestionbancaire.mListView.CustomAdapter;

import java.util.ArrayList;

public class ClientFragment extends Fragment{

    ListView lv;

    EditText numCompteEditText;
    EditText nomClientEditText;
    EditText soldeEditText;

    Button saveBtn, retrieveBtn;

    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

    CustomAdapter adapter;
    final Boolean forUpdate = true;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View clientview =  inflater.inflate(R.layout.client_layout, null);

        //return inflater.inflate(R.layout.client_layout,null);

       // Toolbar toolbar = (Toolbar) clientview.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        lv = (ListView) clientview.findViewById(R.id.lv);
        adapter = new CustomAdapter(getActivity(), spacecrafts);

        this.getSpacecrafts();
        //lv.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) clientview.findViewById(R.id.fab);

        //Button fab = (Button) clientview.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                displayDialog(false);
            }
        });


        return clientview;
    }


    private  void  displayDialog(Boolean forUpdate)
    {

        Dialog d = new Dialog(getActivity());
        d.setTitle("SQLITE DATA");
        d.setContentView(R.layout.client_dialog);

        numCompteEditText = (EditText) d.findViewById(R.id.numCompteEditTxt);
        saveBtn = (Button) d.findViewById(R.id.saveBtn);
        retrieveBtn = (Button) d.findViewById(R.id.retrieveBtn);

        if (!forUpdate)
        {

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    save(numCompteEditText.getText().toString(), nomClientEditText.getText().toString(), Integer.parseInt(soldeEditText.getText().toString()));

                }
            });

            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getSpacecrafts();

                }
            });

        }else {


            //SET SELECTED TEXT
            numCompteEditText.setText(adapter.getSelectedItemNumCompte());
            nomClientEditText.setText(adapter.getSelectedItemNomClient());
            soldeEditText.setText(adapter.getSelectedItemSolde());

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    update(numCompteEditText.getText().toString(), nomClientEditText.getText().toString(), Integer.parseInt(soldeEditText.getText().toString()));

                }
            });

            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)

                {

                    getSpacecrafts();

                }
            });

        }

        d.show();

    }

    //SAVE


    private void save(String numCompte, String nomClient, int solde)
    {
        DBAdapter db = new DBAdapter(getActivity());
        db.openDB();
        boolean saved = db.add(numCompte, nomClient, solde);

        if (saved)
        {
            numCompteEditText.setText("");
            nomClientEditText.setText("");
            soldeEditText.setText("");
            getSpacecrafts();

        }else
        {
            Toast.makeText(getActivity(), "Unable to save", Toast.LENGTH_SHORT).show();
        }

    }


    //RETRIEVEA OR GETSPACECRAFTS

    private  void getSpacecrafts()
    {
        spacecrafts.clear();

        DBAdapter db = new DBAdapter(getActivity());
        db.openDB();
        Cursor c = db.retrieve();
        Spacecraft spacecraft = null;


        while (c.moveToNext())
        {
            int id = c.getInt(0);
            String numCompte = c.getString(1);
            String nomClient = c.getString(2);
            int solde = c.getInt(3);


            spacecraft = new Spacecraft();
            spacecraft.setId(id);
            spacecraft.setNumCompte(numCompte);
            spacecraft.setNomClient(nomClient);
            spacecraft.setSolde(solde);

            spacecrafts.add(spacecraft);
        }


        db.closeDB();
        lv.setAdapter(adapter);

    }



    //UPDATE OR EDIT

    private  void update (String newNumCompte, String newNomClient, int newSolde)
    {

        //GET ID OF SPACECRAFT
        int id = adapter.getSelectedItemID();

        //UPDATE IN DB
        DBAdapter db = new DBAdapter(getActivity());
        db.openDB();
        boolean updated = db.update(newNumCompte, newNomClient, newSolde, id);
        db.closeDB();

        if (updated)
        {
            numCompteEditText.setText(newNumCompte);
            nomClientEditText.setText(newNomClient);
            soldeEditText.setText(newSolde);

            getSpacecrafts();

        }else {
            Toast.makeText(getActivity(), "Unable to update", Toast.LENGTH_SHORT).show();

        }

    }


    //DELETE

    private  void  delete()
    {
        //GET ID
        int id = adapter.getSelectedItemID();

        //DELETE FROM DB
        DBAdapter db = new DBAdapter(getActivity());
        db.openDB();
        boolean deleted = db.delete(id);
        db.closeDB();

        if (deleted)
        {

            getSpacecrafts();
        }else {
            Toast.makeText(getActivity(), "Unable to delete", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        if (title == "NEW")
        {

            displayDialog(!forUpdate);


        }else if (title == "EDIT")
        {

            displayDialog(forUpdate);

        }else  if (title == "DELETE")
        {
            delete();

        }




        return super.onContextItemSelected(item);
    }
}
