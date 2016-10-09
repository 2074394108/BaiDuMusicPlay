package com.zhong.baidumusicplay.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by 俊峰 on 2016/10/8.
 */
public class PictureAdapter extends PagerAdapter {

    private Context mContext;
    private List<Integer> mResource;

    public PictureAdapter(Context context, List<Integer> resource) {
        mContext = context;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return 3000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View result;
        ImageView view = new ImageView(mContext);
        view.setImageResource(mResource.get(position % mResource.size()));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        result = view;
        container.addView(result);
        return result;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
