package com.codeest.geeknews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by codeest on 16/8/13.
 */

public class ScrollViewForRecycler extends ScrollView{

    private int downY;
    private static final int TOUCH_SLOP = 20;

    public ScrollViewForRecycler(Context context) {
        super(context);
    }

    public ScrollViewForRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewForRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 拦截上下滑动  不拦截点击
     * @param e
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > TOUCH_SLOP) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
