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


public class GeneralRigConftitonFragment extends Fragment {

    RecyclerView recyclerView_general;
    RigSafetyFormAdapter recycler_documents_adapter3;
    List<String> list3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_general_rig_condition, container, false);

        recyclerView_general = view.findViewById(R.id.recycler_genrela_rig);
        recyclerView_general.setLayoutManager(new LinearLayoutManager((getActivity())));
        recyclerView_general.setHasFixedSize(true);



        list3 = new ArrayList<>();
        list3= Arrays.asList(getResources().getStringArray(R.array.general_rig_condition));


        recycler_documents_adapter3 =new RigSafetyFormAdapter(getActivity(), list3);
        recyclerView_general.setAdapter(recycler_documents_adapter3);



        return  view;
    }
}