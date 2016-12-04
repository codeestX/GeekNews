package com.codeest.geeknews.ui.gold.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.model.bean.GoldManagerItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

import static com.codeest.geeknews.ui.gold.fragment.GoldMainFragment.typeStr;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldManagerAdapter extends RecyclerView.Adapter<GoldManagerAdapter.ViewHolder> {

    private RealmList<GoldManagerItemBean> mList;
    private LayoutInflater inflater;

    public GoldManagerAdapter(Context mContext, RealmList<GoldManagerItemBean> mList) {
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_gold_manager, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvType.setText(typeStr[mList.get(position).getIndex()]);
        holder.scSwitch.setChecked(mList.get(position).getIsSelect());
        holder.scSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mList.get(holder.getAdapterPosition()).setSelect(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return typeStr.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_gold_manager_type)
        TextView tvType;
        @BindView(R.id.sc_gold_manager_switch)
        SwitchCompat scSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
