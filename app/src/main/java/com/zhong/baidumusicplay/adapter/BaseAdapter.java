package com.zhong.baidumusicplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 俊峰 on 2016/10/8.
 */
public class BaseAdapter extends FragmentPagerAdapter {

    private List<Fragment> MyAllFragment;

    public BaseAdapter(FragmentManager fm, List<Fragment> MyAllFragment) {
        super(fm);
        this.MyAllFragment = MyAllFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return MyAllFragment.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (MyAllFragment != null) {
            ret = MyAllFragment.size();
        }
        return ret;
    }
}
