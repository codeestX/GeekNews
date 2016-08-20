package com.codeest.geeknews.ui.gank.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/20.
 */

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<GankItemBean> mList;
    private OnItemClickListener onItemClickListener;

    private String tech;

    public TechAdapter(Context mContext, List<GankItemBean> mList,String tech) {
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.tech = tech;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_tech, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(tech.equals(TechPresenter.TECH_ANDROID)) {
            holder.ivIcon.setImageResource(R.mipmap.ic_android);
        } else if(tech.equals(TechPresenter.TECH_IOS)) {
            holder.ivIcon.setImageResource(R.mipmap.ic_ios);
        } else if(tech.equals(TechPresenter.TECH_WEB)) {
            holder.ivIcon.setImageResource(R.mipmap.ic_web);
        }
        holder.tvContent.setText(mList.get(position).getDesc());
        holder.tvAuthor.setText(mList.get(position).getWho());
        String date = mList.get(position).getPublishedAt();
        int idx = date.indexOf(".");
        date = date.substring(0,idx).replace("T"," ");
        holder.tvTime.setText(DateUtil.formatDateTime(date,true));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    CardView cv = (CardView) view.findViewById(R.id.cv_tech_content);
                    onItemClickListener.onItemClick(holder.getAdapterPosition(),cv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_tech_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_tech_title)
        TextView tvContent;
        @BindView(R.id.tv_tech_author)
        TextView tvAuthor;
        @BindView(R.id.tv_tech_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position,View view);
    }
}
