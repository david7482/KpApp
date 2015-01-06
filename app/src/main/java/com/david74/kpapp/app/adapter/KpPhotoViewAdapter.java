package com.david74.kpapp.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.david74.kpapp.app.fragment.KpPhotoViewFragment;

import java.util.ArrayList;
import java.util.List;

public class KpPhotoViewAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public KpPhotoViewAdapter(Context context, FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void add(String photoUrl) {
        fragmentList.add(KpPhotoViewFragment.newInstance(photoUrl));
        notifyDataSetChanged();
    }

    public void add(List<String> photoUrlList) {
        for (String photoUrl : photoUrlList) {
            fragmentList.add(KpPhotoViewFragment.newInstance(photoUrl));
        }
        notifyDataSetChanged();
    }
}
