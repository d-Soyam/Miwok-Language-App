package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mcontext;

    public CategoryAdapter(Context mcontext, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mcontext = mcontext;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new NumbersFragment();
        }
        else if (i == 1){
            return new FamilyFragment();
        }
        else if (i == 2){
            return new ColorsFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int i) {
       if (i == 0){
           return  mcontext.getString(R.string.category_numbers);
       }
       else if (i == 1){
           return mcontext.getString(R.string.category_family);
       }
       else if (i == 2){
           return mcontext.getString(R.string.category_colors);
       }
       else {
           return mcontext.getString(R.string.category_phrases);
       }
    }
}

