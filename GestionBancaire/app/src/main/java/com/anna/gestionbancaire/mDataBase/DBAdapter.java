package com.anna.gestionbancaire.mDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by annaanjalli on 7/7/16.
 */

public class DBAdapter {


    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper = new DBHelper(c);

    }


    //OPEN CON

    public void openDB()
    {
        try
        {
            db = helper.getWritableDatabase();

        }catch (SQLException e)
        {

        }
    }


    //CLOSE DB

    public void closeDB()
    {
        try
        {
            helper.close();

        }catch (SQLException e)
        {

        }
    }


    //SAVE

    public boolean add(String numCompte, String nomClient, int solde)
    {

        try
        {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NUMCOMPTE, numCompte);
            cv.put(Constants.NOMCLIENT, nomClient);
            cv.put(Constants.SOLDE, solde);

            long result = db.insert(Constants.TB_CLIENT, Constants.ROW_ID, cv);

            if (result > 0)
            {
                return  true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return  false;

    }


    //SELECT

    public Cursor retrieve()
    {

        String[] columns = {Constants.ROW_ID, Constants.NUMCOMPTE, Constants.NOMCLIENT, Constants.SOLDE};

        Cursor c = db.query(Constants.TB_CLIENT, columns,null, null, null, null, null);
        return  c;
    }


    //UPDATE/edit


    public boolean update(String newNumCompte, String newNomClient, int newSolde, int id)
    {

        try
        {
            ContentValues cv = new ContentValues();

            cv.put(Constants.NUMCOMPTE, newNumCompte);
            cv.put(Constants.NOMCLIENT, newNomClient);
            cv.put(Constants.SOLDE, newSolde);


            int result = db.update(Constants.TB_CLIENT, cv, Constants.ROW_ID+" =?", new String[]{String.valueOf(id)});
            if (result > 0)
            {

                return  true;

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return  false;

    }


    //DELETE/REMOVE

    public  boolean delete(int id)
    {

        try
        {
            int result = db.delete(Constants.TB_CLIENT, Constants.ROW_ID+" =?", new String[]{String.valueOf(id)});
            if (result > 0)
            {

                return  true;

            }
        }catch (SQLException e)
            {
            e.printStackTrace();
            }

        return  false;
    }


}
