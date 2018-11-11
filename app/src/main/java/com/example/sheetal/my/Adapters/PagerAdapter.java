package com.example.sheetal.my.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sheetal.my.Fragments.PrivacyFragment;
import com.example.sheetal.my.Fragments.TermsandConditionsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int NumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.NumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {

            case 0:
                PrivacyFragment privacyFragment = new PrivacyFragment();
                return privacyFragment;

            case 1:
                TermsandConditionsFragment termsandConditionsFragment = new TermsandConditionsFragment();
                return termsandConditionsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
