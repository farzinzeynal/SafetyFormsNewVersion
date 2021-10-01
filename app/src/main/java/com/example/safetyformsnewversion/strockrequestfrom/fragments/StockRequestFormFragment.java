package com.example.safetyformsnewversion.strockrequestfrom.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.RSFormList;
import com.example.safetyformsnewversion.strockrequestfrom.adapters.SrfItemsAdapter;
import com.example.safetyformsnewversion.strockrequestfrom.models.Item;

import java.util.ArrayList;
import java.util.List;


public class StockRequestFormFragment extends Fragment {

    RecyclerView recyclerView_srf_items;
    public static SrfItemsAdapter srfItemsAdapter;
    Button button_insert_items;
    Button button_submit;
    public static List<Item> itemModelList;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_request_form, container, false);


        button_insert_items = view.findViewById(R.id.button_insert_items);
        recyclerView_srf_items = view.findViewById(R.id.recycler_srf_items);
        button_submit = view.findViewById(R.id.button_submit);

        recyclerView_srf_items.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        itemModelList = new ArrayList<>();





        button_insert_items.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(itemModelList.size()<4)
                {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new AddNewItemFragment()).commit();
                }
            }
        });



        button_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }


}