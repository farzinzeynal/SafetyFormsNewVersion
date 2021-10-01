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


public class RigDocumentsFragment extends Fragment {


    RecyclerView recyclerView;
    RigSafetyFormAdapter recycler_documents_adapter;
    List<String> list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view =  inflater.inflate(R.layout.fragment_documents, container, false);

        recyclerView = view.findViewById(R.id.recycler_doc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        list = Arrays.asList(getResources().getStringArray(R.array.documents_rig_safety));

        recycler_documents_adapter =new RigSafetyFormAdapter(getActivity(), list);
        recyclerView.setAdapter(recycler_documents_adapter);

        return  view;

    }
}