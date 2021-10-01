package com.example.safetyformsnewversion.rigsafetyform.fragmnets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.adapters.RigSafetyFormAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SafetyEquipFraqment extends Fragment
{

    RecyclerView recyclerView_safety;
    RigSafetyFormAdapter recycler_documents_adapter1;
    List<String> list2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fraqment_safety_equipment, container, false);

        recyclerView_safety = view.findViewById(R.id.recycler_safety);
        recyclerView_safety.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_safety.setHasFixedSize(true);

        list2 = new ArrayList<>();

        list2 = Arrays.asList(getResources().getStringArray(R.array.safety_equipment_rig_safety));




        recycler_documents_adapter1 =new RigSafetyFormAdapter(getActivity(), list2);
        recyclerView_safety.setAdapter(recycler_documents_adapter1);

        return  view;
    }
}