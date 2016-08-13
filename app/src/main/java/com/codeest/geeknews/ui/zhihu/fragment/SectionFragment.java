package com.codeest.geeknews.ui.zhihu.fragment;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.presenter.SectionPresenter;
import com.codeest.geeknews.presenter.contract.SectionContract;

/**
 * Created by codeest on 2016/8/11.
 */
public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionContract.View{


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    protected void initEventAndData() {

    }
}
