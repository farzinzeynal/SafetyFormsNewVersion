package com.example.safetyformsnewversion.rigsafetyform.fragmnets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.safetyformsnewversion.R;


public class Rig_Fragment extends Fragment {


    EditText editText_inspector_name,
            editText_driver_name,
            editText_take_over,
            editText_witness_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_rig_, container, false);

        editText_driver_name = view.findViewById(R.id.editText_drillman_name);
        editText_inspector_name = view.findViewById(R.id.editText_rig_inspector_name);
        editText_take_over = view.findViewById(R.id.editText_rig_take_over);
        editText_witness_name = view.findViewById(R.id.editText_rig_witness_name);




        return  view;
    }
}