package com.heiko.spiralbanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * SpiralAdapter
 *
 * @author Heiko
 * @date 2019/12/10
 */
public abstract class SpiralAdapter<T> extends PagerAdapter {
    private final List<T> mData;
    private final List<View> mViews;

    public SpiralAdapter(Context context, @LayoutRes int layoutRes, List<T> data) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // data集合中的图片个数在[2,3]时会存在问题，递归再次填充一遍
        if (data.size() > 1 && data.size() < 6) {
            data.addAll(data);
            data.addAll(data);
        }
        mViews = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            mViews.add(inflater.inflate(layoutRes, null, false));
        }
        mData = data;
    }

    public int getDataCount() {
        return mData.size();
    }

    @Override
    public int getCount() {
        return mData.size() <= 1 ? mData.size() : Integer.MAX_VALUE;
    }

    /**
     * 判断是否使用缓存, 如果返回的是true, 使用缓存. 不去调用instantiateItem方法创建一个新的对象
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化一个条目
     *
     * @param container
     * @param position  当前需要加载条目的索引
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        int index = position % mViews.size();
        View view = mViews.get(index);
        container.addView(view);
        convert(view, mData.get(index));
        return view;
    }

    protected abstract void convert(View view, T t);

    /**
     * 销毁一个条目
     * position 就是当前需要被销毁的条目的索引
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 把ImageView从ViewPager中移除掉
        container.removeView(mViews.get(position % mViews.size()));
    }
}
