package com.codeest.geeknews.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by codeest on 2017/3/20.
 *
 * deal with issue #68
 * a bug of RecyclerView
 * Ref: https://code.google.com/p/android/issues/detail?id=231717
 */

public class TouchSwipeRefreshLayout extends SwipeRefreshLayout {

    public TouchSwipeRefreshLayout(Context context) {
        super(context);
    }

    public TouchSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return isRefreshing() || super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isRefreshing() || super.onTouchEvent(ev);
    }
}
