package com.codeest.geeknews.ui.main.fragment;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.SimpleFragment;
import com.codeest.geeknews.util.AlipayUtil;
import com.codeest.geeknews.util.SnackbarUtil;

import butterknife.OnClick;

/**
 * Created by codeest on 16/8/23.
 */

public class AboutFragment extends SimpleFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initEventAndData() {
    }

    @OnClick(R.id.cv_about_award)
    void awardAuthor() {
        if (AlipayUtil.hasInstalledAlipayClient(mContext)) {
            AlipayUtil.startAlipayClient(getActivity(), Constants.KEY_ALIPAY);
        } else {
            SnackbarUtil.showShort(getActivity().getWindow().getDecorView(), "木有检测到支付宝客户端 T T");
        }
    }

}
