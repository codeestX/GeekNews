package com.codeest.geeknews.ui.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.GankItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/21.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder>{

    private List<GankItemBean> mList;
    private List<Integer> mHeights;
    private LayoutInflater inflater;
    private Context mContext;
    OnItemClickListener onItemClickListener;

    public GirlAdapter(Context mContext,List<GankItemBean> mList) {
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.mContext = mContext;
        mHeights = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mHeights.size() <= position) {
            mHeights.add((int) (420 + Math.random() * 220));
        }
        ViewGroup.LayoutParams lp = holder.ivGirl.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.ivGirl.setLayoutParams(lp);
        ImageLoader.load(mContext, mList.get(position).getUrl(),holder.ivGirl);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    ImageView iv = (ImageView) view.findViewById(R.id.iv_girl);
                    onItemClickListener.onItemClickListener(holder.getAdapterPosition(),iv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_girl)
        ImageView ivGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position,View view);
    }
 }
