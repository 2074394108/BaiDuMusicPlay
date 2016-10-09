package com.zhong.baidumusicplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhong.baidumusicplay.adapter.BaseAdapter;
import com.zhong.baidumusicplay.fragment.first;
import com.zhong.baidumusicplay.fragment.fourth;
import com.zhong.baidumusicplay.fragment.second;
import com.zhong.baidumusicplay.fragment.third;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String[] titleArray = new String[]{"个性推荐", "歌单", "主播电台", "排行榜"};
    private int increateY;
    private TextView mTextView_indicator;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.main_view_pager);
        mRadioGroup = (RadioGroup) findViewById(R.id.ViewPager_item);
        mTextView_indicator = (TextView) findViewById(R.id.indicator_line);

        List<Fragment> setPicture = new ArrayList<>();
        setPicture.add(new first());
        setPicture.add(new second());
        setPicture.add(new third());
        setPicture.add(new fourth());

        FragmentManager manager = getSupportFragmentManager();
        BaseAdapter adapter = new BaseAdapter(manager, setPicture);
        pager.setAdapter(adapter);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < setPicture.size(); i++) {
            RadioButton view = ((RadioButton) inflater.inflate(R.layout.viewpager_indicator, mRadioGroup, false));
            view.setId(i);
            view.setText(titleArray[i]);
            mRadioGroup.addView(view);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        increateY = width / 4;
        mTextView_indicator.setWidth(increateY);
        pager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        RadioButton childAt = ((RadioButton) mRadioGroup.getChildAt(position));
        float x = ViewCompat.getX(childAt);
        x = x + (childAt.getWidth() * positionOffset);
        ViewCompat.setX(mTextView_indicator, x);
        childAt.setTextColor(0xffec2626);
        int count = mRadioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i != position) {
                RadioButton otherButton = (RadioButton) mRadioGroup.getChildAt(i);
                otherButton.setTextColor(0xff4e4c4c);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
