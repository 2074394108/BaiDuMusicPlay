package com.zhong.baidumusicplay.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhong.baidumusicplay.R;
import com.zhong.baidumusicplay.adapter.PictureAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class first extends Fragment implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private int currentIndex = 1500;
    private mHandler setImage;
    private RadioGroup mGroup;
    private ViewPager mViewPager;
    private static int itemCount = 4;
    private boolean running;
    private ImageView mPerson_fm_image;
    private ImageView mYun_music_order_image;
    private ImageView mDay_music_image;

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (running) {
                        currentIndex++;
                        if (currentIndex > 3000) {
                            currentIndex = 1500;
                        }
                        Message message = setImage.obtainMessage(100);
                        message.arg1 = currentIndex;
                        setImage.sendMessage(message);
                        Thread.sleep(4000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public first() {

    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_first, container, false);
        mViewPager = (ViewPager) inflate.findViewById(R.id.MyPictureViewPager);
        List<Integer> pictureResource = new ArrayList<>();
        pictureResource.add(R.mipmap.p01);
        pictureResource.add(R.mipmap.p02);
        pictureResource.add(R.mipmap.p03);
        pictureResource.add(R.mipmap.p04);
        PictureAdapter adapter = new PictureAdapter(getContext(), pictureResource);
        mViewPager.setAdapter(adapter);
        setImage = new mHandler(mViewPager);
        mGroup = (RadioGroup) inflate.findViewById(R.id.set_item);
        LayoutInflater from = LayoutInflater.from(getContext());
        for (int i = 0; i < pictureResource.size(); i++) {
            View groupOfView = from.inflate(R.layout.circule_button, mGroup, false);
            mGroup.addView(groupOfView);
        }
        mViewPager.setOnPageChangeListener(this);
        LinearLayout person_fm = (LinearLayout) inflate.findViewById(R.id.Person_FM);
        LinearLayout yun_music_order = (LinearLayout) inflate.findViewById(R.id.Yun_MusicOrder);
        LinearLayout day_music = (LinearLayout) inflate.findViewById(R.id.Day_Music);
        mPerson_fm_image = (ImageView) inflate.findViewById(R.id.Person_FM_Image);
        mYun_music_order_image = (ImageView) inflate.findViewById(R.id.Yun_MusicOrder_Image);
        mDay_music_image = (ImageView) inflate.findViewById(R.id.Day_Music_Image);
        person_fm.setOnTouchListener(this);
        yun_music_order.setOnTouchListener(this);
        day_music.setOnTouchListener(this);
        return inflate;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int setChecked = position % mGroup.getChildCount();
        RadioButton childAt = ((RadioButton) mGroup.getChildAt(setChecked));

        //设置获取焦点
        childAt.setFocusable(true);
        childAt.setFocusableInTouchMode(true);
        childAt.requestFocus();
        childAt.requestFocusFromTouch();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                switch (id) {
                    case R.id.Person_FM:
                        mPerson_fm_image.setImageResource(R.mipmap.recommend_icn_fm_prs);
                        break;
                    case R.id.Day_Music:
                        mDay_music_image.setImageResource(R.mipmap.recommend_icn_runfm_prs);
                        break;
                    case R.id.Yun_MusicOrder:
                        mYun_music_order_image.setImageResource(R.mipmap.recommend_icn_upbill_prs);
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                switch (id) {
                    case R.id.Person_FM:
                        mPerson_fm_image.setImageResource(R.mipmap.recommend_icn_fm);
                        break;
                    case R.id.Day_Music:
                        mDay_music_image.setImageResource(R.mipmap.recommend_icn_runfm);
                        break;
                    case R.id.Yun_MusicOrder:
                        mYun_music_order_image.setImageResource(R.mipmap.recommend_icn_upbill);
                        break;
                }
                break;
        }
        return true;
    }

    private static class mHandler extends Handler{
        private ViewPager mPager;

        public mHandler(ViewPager pager) {
            mPager = pager;
        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 100:
                    int index = msg.arg1;
                    mPager.setCurrentItem(index % itemCount);
                    break;
            }
        }
    }

}
