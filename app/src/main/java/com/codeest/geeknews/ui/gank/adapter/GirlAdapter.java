package com.codeest.geeknews.ui.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codeest.geeknews.R;
import com.codeest.geeknews.app.App;
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

    /**
     * https://github.com/oxoooo/mr-mantou-android/blob/master/app/src/main/java/ooo/oxo/mr/MainAdapter.java
     * 重写getItemViewType可以防止StaggeredGridLayoutManager快速滑动时重新排列的问题，原因目前未知
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return Math.round((float) App.SCREEN_WIDTH / (float) mList.get(position).getHeight() * 10f);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //存在记录的高度时先Layout再异步加载图片
        if (mList.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.ivGirl.getLayoutParams();
            layoutParams.height = mList.get(holder.getAdapterPosition()).getHeight();
        }

        Glide.with(mContext).load(mList.get(position).getUrl()).asBitmap().placeholder(R.mipmap.bg_drawable).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(App.SCREEN_WIDTH / 2, App.SCREEN_WIDTH / 2) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                            if (mList.get(holder.getAdapterPosition()).getHeight() <= 0) {
                                int width = resource.getWidth();
                                int height = resource.getHeight();
                                int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                                mList.get(holder.getAdapterPosition()).setHeight(realHeight);
                                ViewGroup.LayoutParams lp = holder.ivGirl.getLayoutParams();
                                lp.height = realHeight;
                            }
                            holder.ivGirl.setImageBitmap(resource);
                        }
                    }
                });
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
