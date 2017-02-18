package com.codeest.geeknews.ui.gold.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.GoldListBean;
import com.codeest.geeknews.ui.gank.activity.TechDetailActivity;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GoldListBean> mList;
    private Context mContext;
    private LayoutInflater inflater;

    private String mType;
    private boolean mHotFlag = true;
    private OnHotCloseListener onHotCloseListener;

    public enum ITEM_TYPE {
        ITEM_TITLE,     //标题
        ITEM_HOT,       //热门
        ITEM_CONTENT    //内容
    }

    public GoldListAdapter(Context mContext, List<GoldListBean> mList, String typeStr) {
        this.mList = mList;
        this.mContext = mContext;
        this.mType = typeStr;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (!mHotFlag) {
            return ITEM_TYPE.ITEM_CONTENT.ordinal();
        } else {
            if(position == 0) {
                return ITEM_TYPE.ITEM_TITLE.ordinal();
            } else if (position > 0 && position <= 3){
                return ITEM_TYPE.ITEM_HOT.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TITLE.ordinal()) {
            return new TitleViewHolder(inflater.inflate(R.layout.item_gold_title, parent, false));
        } else if(viewType == ITEM_TYPE.ITEM_HOT.ordinal()) {
            return new HotViewHolder(inflater.inflate(R.layout.item_gold_hot, parent, false));
        }
        return new ContentViewHolder(inflater.inflate(R.layout.item_gold, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoldListBean bean = mList.get(0);
        if (position > 0) {
            bean = mList.get(position -1);
        }
        if (holder instanceof ContentViewHolder) {
            if (bean.getScreenshot() != null && bean.getScreenshot().getUrl() != null) {
                ImageLoader.load(mContext, bean.getScreenshot().getUrl(), ((ContentViewHolder) holder).ivImg);
            } else {
                ((ContentViewHolder) holder).ivImg.setImageResource(R.mipmap.ic_launcher);
            }
            ((ContentViewHolder) holder).tvTitle.setText(bean.getTitle());
            ((ContentViewHolder) holder).tvInfo.setText(getItemInfoStr(bean.getCollectionCount(),
                    bean.getCommentsCount(),
                    bean.getUser().getUsername(),
                    DateUtil.formatDate2String(DateUtil.subStandardTime(bean.getCreatedAt()))));
            holder.itemView.setOnClickListener(new MyOnClickListener(--position));
        } else if (holder instanceof HotViewHolder) {
            if (bean.getScreenshot() != null && bean.getScreenshot().getUrl() != null) {
                ImageLoader.load(mContext, bean.getScreenshot().getUrl(), ((HotViewHolder) holder).ivImg);
            } else {
                ((HotViewHolder) holder).ivImg.setImageResource(R.mipmap.ic_launcher);
            }
            ((HotViewHolder) holder).tvTitle.setText(bean.getTitle());
            ((HotViewHolder) holder).tvLike.setText(String.valueOf(bean.getCollectionCount()));
            ((HotViewHolder) holder).tvAuthor.setText(String.valueOf(bean.getUser().getUsername()));
            ((HotViewHolder) holder).tvTime.setText(DateUtil.formatDate2String(DateUtil.subStandardTime(bean.getCreatedAt())));
            holder.itemView.setOnClickListener(new MyOnClickListener(--position));
        } else {
            ((TitleViewHolder) holder).tvTitle.setText(String.format("%s 热门", mType));
            ((TitleViewHolder) holder).btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHotFlag = false;
                    for (int i = 0; i< 4 ;i++) {
                        mList.remove(0);
                    }
                    notifyItemRangeRemoved(0, 4);
                    if (onHotCloseListener != null) {
                        onHotCloseListener.onClose();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_gold_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_gold_item_info)
        TextView tvInfo;
        @BindView(R.id.iv_gold_item_img)
        SquareImageView ivImg;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class HotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_gold_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_gold_item_like)
        TextView tvLike;
        @BindView(R.id.tv_gold_item_author)
        TextView tvAuthor;
        @BindView(R.id.tv_gold_item_time)
        TextView tvTime;
        @BindView(R.id.iv_gold_item_img)
        SquareImageView ivImg;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_gold_hot_title)
        TextView tvTitle;
        @BindView(R.id.btn_gold_hot_close)
        AppCompatButton btnClose;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private class MyOnClickListener implements View.OnClickListener {

        private int position;

        public MyOnClickListener(int position) {
            this.position = position;
            if (position < 0) {
                this.position = 0;
            }
        }

        @Override
        public void onClick(View view) {
            String imgUrl = null;
            if (mList.get(position).getScreenshot() != null && mList.get(position).getScreenshot().getUrl() != null)
                imgUrl = mList.get(position).getScreenshot().getUrl();
            TechDetailActivity.launch(new TechDetailActivity.Builder()
                    .setContext(mContext)
                    .setId(mList.get(position).getObjectId())
                    .setTitle(mList.get(position).getTitle())
                    .setUrl(mList.get(position).getUrl())
                    .setImgUrl(imgUrl)
                    .setType(Constants.TYPE_GOLD));
        }
    }

    private String getItemInfoStr(int likeNum, int cmtNum, String author, String time) {
        StringBuilder sb = new StringBuilder(String.valueOf(likeNum));
        sb.append("人收藏 · ");
        sb.append(cmtNum);
        sb.append("条评论 · ");
        sb.append(author);
        sb.append(" · ");
        sb.append(time);
        return sb.toString();
    }

    public void updateData(List<GoldListBean> list) {
        mList = list;
    }

    public void setHotFlag(boolean hotFlag) {
        this.mHotFlag = hotFlag;
    }

    public boolean getHotFlag() {
        return mHotFlag;
    }

    public interface OnHotCloseListener {
        void onClose();
    }

    public void setOnHotCloseListener(OnHotCloseListener onHotCloseListener) {
        this.onHotCloseListener = onHotCloseListener;
    }
}
