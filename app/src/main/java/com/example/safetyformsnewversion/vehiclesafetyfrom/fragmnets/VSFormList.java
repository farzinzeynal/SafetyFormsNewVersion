package com.example.safetyformsnewversion.vehiclesafetyfrom.fragmnets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.RigSafetyFormFragment;
import com.example.safetyformsnewversion.screens.MainMenuFragmnet;
import com.example.utils.FormTypes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class VSFormList extends Fragment
{

    FloatingActionButton floatingActionButton;

    String vehicleformType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_vehiclesf_form_list, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton_rig);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] items = {"Inspection","Hand-Over"};

                new AlertDialog.Builder(getActivity())
                        .setTitle("Form type")
                        .setSingleChoiceItems(items, 0, null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                switch (selectedPosition)
                                {
                                    case 0:
                                        vehicleformType = FormTypes.INSPECTION;
                                        break;
                                    case 1:
                                        vehicleformType = FormTypes.HAND_OVER;
                                        break;
                                }

                                VehicleSafetyFormFragment fragment = new VehicleSafetyFormFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("vehicleformType", vehicleformType);
                                fragment.setArguments(bundle);

                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();


                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });




        return view;
    }
}