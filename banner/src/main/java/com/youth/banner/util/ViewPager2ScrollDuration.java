package com.youth.banner.util;


import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.youth.banner.Banner;

/**
 * 工具类: 控制 ViewPager2 的滚动速度
 */
public class ViewPager2ScrollDuration {


    public static void setScrollDuration(Banner banner) {
        ViewPager2 viewPager2 = banner.getViewPager2();
        RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                recyclerView.post(() -> {
                    Context context = recyclerView.getContext();
                    LinearSmoothScroller scroller = new LinearSmoothScroller(context) {
                        @Override
                        protected int calculateTimeForDeceleration(int dx) {
                            return banner.getScrollTime(); // 自定义滚动时间
                        }
                    };
                    scroller.setTargetPosition(position);
                    layoutManager.startSmoothScroll(scroller);
                });
            }
        });
    }
}
