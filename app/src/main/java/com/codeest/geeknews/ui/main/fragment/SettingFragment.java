package com.codeest.geeknews.ui.main.fragment;

import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;
import com.codeest.geeknews.base.SimpleFragment;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.NightModeEvent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by codeest on 16/8/23.
 */

public class SettingFragment extends SimpleFragment implements CompoundButton.OnCheckedChangeListener{

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox cbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    LinearLayout llSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.tv_setting_update)
    TextView tvSettingUpdate;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);
        cbSettingNight.setOnCheckedChangeListener(this);
    }

    @OnClick(R.id.ll_setting_feedback)
    void doFeedBack() {

    }

    @OnClick(R.id.ll_setting_clear)
    void doClear() {

    }

    @OnClick(R.id.ll_setting_update)
    void doUpdate() {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_setting_night:
                NightModeEvent event = new NightModeEvent();
                event.setNightMode(true);
                RxBus.getDefault().post(event);
                break;
        }
    }
}
