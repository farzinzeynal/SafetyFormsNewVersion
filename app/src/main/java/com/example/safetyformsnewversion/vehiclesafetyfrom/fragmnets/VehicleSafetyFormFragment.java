package com.example.safetyformsnewversion.vehiclesafetyfrom.fragmnets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.adapters.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;


public class VehicleSafetyFormFragment extends Fragment
{

    public TabLayout tabs;
    public ViewPager viewPager;
    TextView textView_title;

    public static String vehicleformType ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_safety_form, container, false);

        Bundle args = getArguments();
        vehicleformType = args.getString("vehicleformType");

        viewPager = view.findViewById(R.id.view_pager2);
        textView_title= view.findViewById(R.id.vehicle_srf_title);



        textView_title.setText("Vehicle Safety Form "+"("+vehicleformType.toLowerCase(Locale.ROOT)+")");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(6);


        tabs = view.findViewById(R.id.tabs2);
        tabs.setupWithViewPager(viewPager);

        /*currentTab = tabs.getTabAt(0);
        int c = tabs.getTabCount();*/





        return view;
    }
}