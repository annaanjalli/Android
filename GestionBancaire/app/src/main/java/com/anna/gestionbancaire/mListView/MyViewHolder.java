package com.anna.gestionbancaire.mListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.anna.gestionbancaire.R;

/**
 * Created by annaanjalli on 7/7/16.
 */

public class MyViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener {



    TextView numCompteTxt;
    TextView nomClientTxt;
    TextView soldeTxt;


    MyLongClickListener longClickListener;


    public MyViewHolder(View v) {

        numCompteTxt = (TextView) v.findViewById(R.id.numCompteTxt);
        nomClientTxt = (TextView) v.findViewById(R.id.nomClientTxt);
        soldeTxt = (TextView) v.findViewById(R.id.soldeTxt);

        v.setOnClickListener((View.OnClickListener) this);
        v.setOnCreateContextMenuListener(this);


    }

    @Override
    public boolean onLongClick(View v) {
        this.longClickListener.onItemLongClick();
        return false;
    }


    public  void setLongClickListener(MyLongClickListener longClickListener)
    {

        this.longClickListener = longClickListener;

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Action : ");
        menu.add(0, 0, 0, "NEW");
        menu.add(0, 1, 0, "EDIT");
        menu.add(0, 2, 0, "DELETE");

    }
}
