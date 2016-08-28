package com.codeest.geeknews.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.SimpleFragment;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.NightModeEvent;
import com.codeest.geeknews.util.LogUtil;

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

    boolean isNull = true;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        isNull = savedInstanceState == null;
        super.onCreate(savedInstanceState);
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
                if (isNull) {   //防止夜间模式MainActivity执行reCreate后重复调用
                    NightModeEvent event = new NightModeEvent();
                    event.setNightMode(b);
                    RxBus.getDefault().post(event);
                }
                break;
            case R.id.cb_setting_image:
                break;
            case R.id.cb_setting_cache:
                break;
        }
    }
}
