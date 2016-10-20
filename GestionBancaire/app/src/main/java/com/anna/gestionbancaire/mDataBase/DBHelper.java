package com.anna.gestionbancaire.mDataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by annaanjalli on 7/7/16.
 */

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        try
        {

            db.execSQL(Constants.CREATE_CLIENT);

        }catch (SQLException e)

        {

            e.printStackTrace();

        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Constants.DROP_TB_CLIENT);
        onCreate(db);

    }
}
