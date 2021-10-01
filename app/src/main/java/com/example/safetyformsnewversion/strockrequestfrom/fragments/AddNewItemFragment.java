package com.example.safetyformsnewversion.strockrequestfrom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.RSFormList;

import java.util.List;


public class AddNewItemFragment extends Fragment
{


    Button button_add_item_to_list;
    List<String> myList2;
    AutoCompleteTextView autoCompleteTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_item, container, false);
        button_add_item_to_list = view.findViewById(R.id.button_add_item_to_list);

        button_add_item_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new StockRequestFormFragment()).commit();
            }
        });



        return view;
    }
}