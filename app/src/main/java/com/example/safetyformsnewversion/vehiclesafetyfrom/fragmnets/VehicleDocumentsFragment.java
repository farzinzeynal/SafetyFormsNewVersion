package com.example.safetyformsnewversion.vehiclesafetyfrom.fragmnets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.adapters.RigSafetyFormAdapter;
import com.example.safetyformsnewversion.vehiclesafetyfrom.adapters.VehicleSafetyFormAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VehicleDocumentsFragment extends Fragment {


    RecyclerView recyclerView;
    RigSafetyFormAdapter recycler_documents_adapter;
    List<String> list;


    RecyclerView recyclerView_general;
    VehicleSafetyFormAdapter vehicleSafetyFormAdapter3;
    List<String> list3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_general_vehicle_condition, container, false);

        recyclerView_general = view.findViewById(R.id.recycler_genrela_rig);
        recyclerView_general.setLayoutManager(new LinearLayoutManager((getActivity())));
        recyclerView_general.setHasFixedSize(true);

        list3 = new ArrayList<>();
        list3= Arrays.asList(getResources().getStringArray(R.array.vehicle_general_condition));

        Log.i("GeneralRig","SetRecycler");
        vehicleSafetyFormAdapter3 =new VehicleSafetyFormAdapter(getActivity(), list3);
        recyclerView_general.setAdapter(vehicleSafetyFormAdapter3);



        return  view;
    }
}