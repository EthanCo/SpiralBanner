package com.heiko.spiralbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Heiko
 * @date 2019/12/10
 */
public class SpiralBanner extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int curIndex = 0;

    private Runnable loopRunnable = new Runnable() {
        @Override
        public void run() {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(curIndex++);
            }
            startPlay();
        }
    };
    private int duration;
    private boolean autoPlay;

    public SpiralBanner(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public SpiralBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SpiralBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpiralBanner);

        duration = ta.getInt(R.styleable.SpiralBanner_duration, 2000);
        int pageLimit = ta.getInt(R.styleable.SpiralBanner_page_limit, -1);
        autoPlay = ta.getBoolean(R.styleable.SpiralBanner_auto_play, true);
        float marginLeft = ta.getDimension(R.styleable.SpiralBanner_inner_margin_left, 0);
        float marginRight = ta.getDimension(R.styleable.SpiralBanner_inner_margin_right, 0);
        float marginTop = ta.getDimension(R.styleable.SpiralBanner_inner_margin_top, 0);
        float marginBottom = ta.getDimension(R.styleable.SpiralBanner_inner_margin_bottom, 0);

        ta.recycle();
        mViewPager = new ViewPager(context);
        mViewPager.addOnPageChangeListener(this);

        if (pageLimit >= 0) {
            mViewPager.setOffscreenPageLimit(pageLimit);
        }

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.setMargins((int) marginLeft, (int) marginTop, (int) marginRight, (int) marginBottom);
        addView(mViewPager, lp);
    }

    private void startPlay() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(loopRunnable, duration);
    }

    public void setPageTransformer(boolean reverseDrawingOrder,
                                   @Nullable ViewPager.PageTransformer transformer) {
        mViewPager.setPageTransformer(reverseDrawingOrder, transformer);
    }

    public void setAdapter(SpiralAdapter adapter) {
        mViewPager.setAdapter(adapter);
        int dataCount = adapter.getDataCount();
        mViewPager.setCurrentItem(((Integer.MAX_VALUE / 2) / dataCount) * dataCount, false);
        if (autoPlay) {
            startPlay();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        curIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
