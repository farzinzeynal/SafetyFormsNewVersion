package com.example.safetyformsnewversion.vehiclesafetyfrom.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.RigDocumentsFragment;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.DrillmanPerformFragment;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.GeneralRigConftitonFragment;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.RigSafetyInspectionFragment;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.Rig_Fragment;
import com.example.safetyformsnewversion.rigsafetyform.fragmnets.SafetyEquipFraqment;



public class VehicleSectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.vehicle_tab_text_1,
                                                      R.string.vehicle_tab_text_2,
                                                      R.string.vehicle_tab_text_3,
                                                      R.string.vehicle_tab_text_4,
                                                      R.string.vehicle_tab_text_5,
                                                      R.string.vehicle_tab_text_6};
    private final Context mContext;

    public VehicleSectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment =  new RigSafetyInspectionFragment();
                break;
            case 1:
                fragment =  new RigDocumentsFragment();
                break;
            case 2:
                fragment =  new SafetyEquipFraqment();
                break;
            case 3:
                fragment =  new GeneralRigConftitonFragment();
                break;
            case 4:
                fragment =  new DrillmanPerformFragment();
                break;
            case 5:
                fragment =  new Rig_Fragment();
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 6;
    }
}