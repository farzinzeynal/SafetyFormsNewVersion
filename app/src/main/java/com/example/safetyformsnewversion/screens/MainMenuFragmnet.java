package com.example.safetyformsnewversion.screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.RSFormList;
import com.example.safetyformsnewversion.strockrequestfrom.fragments.StockRequestFormFragment;
import com.example.safetyformsnewversion.vehiclesafetyfrom.fragmnets.VSFormList;

import java.io.File;


public class MainMenuFragmnet extends Fragment {

    CardView cv_rigsafety,
            cv_vehiclesafetyfom,
            cv_stockrequestform,
            cv_timesheet;

    AlertDialog.Builder builder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu_fragmnet, container, false);



        cv_rigsafety = view.findViewById(R.id.cv_rigsafety);
        cv_vehiclesafetyfom = view.findViewById(R.id.cv_vehiclesafetyfom);
        cv_stockrequestform = view.findViewById(R.id.cv_stockrequestform);
        cv_timesheet = view.findViewById(R.id.cv_timesheet);


      /*  daoSession = ((GreenDaoDemoApp)getApplication()).getDaoSession();
        db = ((GreenDaoDemoApp)getApplication()).getDb();
        DaoMaster.dropAllTables(db,true);
        DaoMaster.createAllTables(db,true);*/





        cv_rigsafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new RSFormList()).commit();
            }});


        cv_vehiclesafetyfom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new VSFormList()).commit();
            }
        });

        cv_stockrequestform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new StockRequestFormFragment()).commit();
            }
        });


        cv_timesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDialog();
            }
        });

        return view;
    }

    private void showDialog()
    {

        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.time_sheet_info);
        builder.setCancelable(true);;
        builder.setTitle("Məlumat");
        builder.setPositiveButton(
                "Ok", new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

}