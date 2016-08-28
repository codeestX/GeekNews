package com.codeest.geeknews.ui.wechat.fragment;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.WXItemBean;
import com.codeest.geeknews.presenter.WechatPresenter;
import com.codeest.geeknews.presenter.contract.WechatContract;

import java.util.List;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatMainFragment extends BaseFragment<WechatPresenter> implements WechatContract.View {


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showContent(List<WXItemBean> mList) {

    }

    @Override
    public void showError(String msg) {

    }
}
