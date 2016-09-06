package com.codeest.geeknews.ui.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
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
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.util.ImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/20.
 */

public class TechAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<GankItemBean> mList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    private String tech;
    private String url;
    private String date;
    private ImageView ivOrigin;

    public enum ITEM_TYPE {
        ITEM_TOP,       //图片
        ITEM_CONTENT    //内容
    }

    public TechAdapter(Context mContext, List<GankItemBean> mList,String tech) {
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.tech = tech;
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
            return new ViewHolder(inflater.inflate(R.layout.item_tech, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder) {
            if(tech.equals(TechPresenter.TECH_ANDROID)) {
                ((ViewHolder)holder).ivIcon.setImageResource(R.mipmap.ic_android);
            } else if(tech.equals(TechPresenter.TECH_IOS)) {
                ((ViewHolder)holder).ivIcon.setImageResource(R.mipmap.ic_ios);
            } else if(tech.equals(TechPresenter.TECH_WEB)) {
                ((ViewHolder)holder).ivIcon.setImageResource(R.mipmap.ic_web);
            }
            ((ViewHolder)holder).tvContent.setText(mList.get(position).getDesc());
            ((ViewHolder)holder).tvAuthor.setText(mList.get(position).getWho());
            String date = mList.get(position).getPublishedAt();
            int idx = date.indexOf(".");
            date = date.substring(0,idx).replace("T"," ");
            ((ViewHolder)holder).tvTime.setText(DateUtil.formatDateTime(date,true));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        CardView cv = (CardView) view.findViewById(R.id.cv_tech_content);
                        onItemClickListener.onItemClick(holder.getAdapterPosition(),cv);
                    }
                }
            });
        } else if(holder instanceof TopViewHolder) {
            if (url != null) {
                ivOrigin = ((TopViewHolder)holder).ivOrigin;
                Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        ((TopViewHolder)holder).ivOrigin.setImageBitmap(resource);
                        ((TopViewHolder)holder).ivBlur.setImageBitmap(ImageUtil.doBlur(resource, 50 , false));
                    }
                });
            }
            if (date != null) {
                ((TopViewHolder)holder).tvTime.setText(date);
            }
        }
    }

    public void setTopInfo(String url,String date) {
        this.url = url;
        this.date = date;
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

    public static class TopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_tech_bar_image_blur)
        ImageView ivBlur;
        @BindView(R.id.iv_tech_bar_image_origin)
        ImageView ivOrigin;
        @BindView(R.id.tv_tech_bar_time)
        TextView tvTime;

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
}
