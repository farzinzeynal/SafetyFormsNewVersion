package com.example.safetyformsnewversion.rigsafetyform.fragmnets;

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


public class RigSafetyFormFragment extends Fragment
{

    public TabLayout tabs;
    public ViewPager viewPager;
    TextView textView_title;

    public static String formType ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rig_safety_form, container, false);

        Bundle args = getArguments();
        formType = args.getString("formType");



        textView_title= view.findViewById(R.id.srf_title);



        textView_title.setText("Rig Safety Form "+"("+formType.toLowerCase(Locale.ROOT)+")");

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(6);


        tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        /*currentTab = tabs.getTabAt(0);
        int c = tabs.getTabCount();*/



        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*currentTab = tab;*/
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







        return view;
    }
}