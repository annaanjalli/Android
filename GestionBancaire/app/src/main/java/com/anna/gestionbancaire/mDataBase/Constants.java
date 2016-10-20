package com.anna.gestionbancaire.mDataBase;

/**
 * Created by annaanjalli on 7/7/16.
 */

public class Constants {

    //COLUMNS
    static  final String ROW_ID = "id";
    static  final String NUMCOMPTE = "numCompte";
    static  final String NOMCLIENT = "nomClient";
    static  final String SOLDE = "solde";



    //DB PROPERTIES

    static  final String DB_NAME = "banque_DB";
    static  final String TB_CLIENT="client";
    static  final int DB_VERSION = 2;


    //CREATE TB STMT

    static final String CREATE_CLIENT = "CREATE TABLE"  + TB_CLIENT
                                                        +  "("
                                                        +  ROW_ID
                                                        +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                        + NUMCOMPTE
                                                        + " TEXT NOT NULL, "
                                                        + NOMCLIENT
                                                        + " TEXT NOT NULL,"
                                                        + SOLDE
                                                        + "INTEGER NOT NULL);";


    //DROP TB STMT

    static  final String DROP_TB_CLIENT = "DROP TABLE IF EXISTS" + TB_CLIENT;

}
