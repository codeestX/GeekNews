package com.codeest.geeknews.widget;

import android.support.v7.util.DiffUtil;

import com.codeest.geeknews.model.bean.DailyListBean;

import java.util.List;

/**
 * Created by codeest on 17/1/2.
 */

public class ZhihuDiffCallback extends DiffUtil.Callback{

    private List<DailyListBean.StoriesBean> mOldDatas, mNewDatas;

    public ZhihuDiffCallback(List<DailyListBean.StoriesBean> mOldDatas, List<DailyListBean.StoriesBean> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getId() == mNewDatas.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        DailyListBean.StoriesBean beanOld = mOldDatas.get(oldItemPosition);
        DailyListBean.StoriesBean beanNew = mNewDatas.get(newItemPosition);
        if (!beanOld.getTitle().equals(beanNew.getTitle())) {
            return false;
        }
        if (beanOld.getReadState() != beanNew.getReadState()) {
            return false;
        }
        if (beanOld.getImages().size() > 0 && beanNew.getImages().size() > 0) {
            if (!beanOld.getImages().get(0).equals(beanNew.getImages().get(0))) {
                return false;
            }
        }
        return true;
    }
}
