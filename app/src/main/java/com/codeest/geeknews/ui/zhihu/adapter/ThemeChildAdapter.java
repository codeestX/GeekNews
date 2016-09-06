package com.codeest.geeknews.ui.zhihu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codeest.geeknews.R;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.ThemeChildListBean;
import com.codeest.geeknews.util.ImageUtil;
import com.codeest.geeknews.widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/24.
 */

public class ThemeChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater inflater;
    private List<ThemeChildListBean.StoriesBean> mList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    private String url;
    private String des;
    private ImageView ivOrigin;

    public enum ITEM_TYPE {
        ITEM_TOP,       //图片
        ITEM_CONTENT    //内容
    }

    public ThemeChildAdapter(Context mContext, List<ThemeChildListBean.StoriesBean> mList) {
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return ITEM_TYPE.ITEM_TOP.ordinal();
        } else {
            return ITEM_TYPE.ITEM_CONTENT.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TOP.ordinal()) {
            return new TopViewHolder(inflater.inflate(R.layout.view_tech_head, parent, false));
        } else {
            return new ViewHolder(inflater.inflate(R.layout.item_daily, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder) {
            if (mList.get(position).getImages() != null && mList.get(position).getImages().size() > 0) {
                ImageLoader.load(mContext,mList.get(position).getImages().get(0),((ViewHolder) holder).image);
            }
            ((ViewHolder) holder).title.setText(mList.get(position).getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        SquareImageView iv = (SquareImageView) view.findViewById(R.id.iv_daily_item_image);
                        onItemClickListener.onItemClick(holder.getAdapterPosition(),iv);
                    }
                }
            });
        } else if(holder instanceof TopViewHolder) {
            if (url != null) {
                ImageLoader.load(mContext, url, ((TopViewHolder)holder).ivOrigin);
                ivOrigin = ((TopViewHolder)holder).ivOrigin;
                Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        ((TopViewHolder)holder).ivBlur.setImageBitmap(ImageUtil.doBlur(resource, 50 , true));
                    }
                });
            }
            if (des != null) {
                ((TopViewHolder)holder).tvDes.setText(des);
            }
        }
    }

    public void setTopInfo(String url,String des) {
        this.url = url;
        this.des = des;
    }

    public void setTopAlpha(double alpha) {
        if(alpha <= 1 && ivOrigin != null) {
            ivOrigin.setAlpha(1 - (float) alpha);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_daily_item_title)
        TextView title;
        @BindView(R.id.iv_daily_item_image)
        SquareImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_tech_bar_image_blur)
        ImageView ivBlur;
        @BindView(R.id.iv_tech_bar_image_origin)
        ImageView ivOrigin;
        @BindView(R.id.tv_tech_bar_time)
        TextView tvDes;

        public TopViewHolder(View itemView) {
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

    public void setReadState(int position,boolean readState) {
        mList.get(position).setReadState(readState);
    }
}
