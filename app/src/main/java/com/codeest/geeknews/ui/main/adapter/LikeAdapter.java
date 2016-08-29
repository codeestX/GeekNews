package com.codeest.geeknews.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.presenter.WechatPresenter;
import com.codeest.geeknews.ui.gank.activity.GirlDetailActivity;
import com.codeest.geeknews.ui.gank.activity.TechDetailActivity;
import com.codeest.geeknews.ui.zhihu.activity.ZhihuDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/23.
 */

public class LikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<RealmLikeBean> mList;
    private LayoutInflater inflater;

    private static final int TYPE_ARTICLE = 0;
    private static final int TYPE_GIRL = 1;

    public LikeAdapter(Context mContext,List<RealmLikeBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if(mList.get(position).getType() == Constants.TYPE_GIRL) {
            return TYPE_GIRL;
        } else {
            return TYPE_ARTICLE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ARTICLE) {
            return new ArticleViewHolder(inflater.inflate(R.layout.item_like_article, parent, false));
        } else {
            return new GirlViewHolder(inflater.inflate(R.layout.item_like_girl, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ArticleViewHolder) {
            ((ArticleViewHolder) holder).title.setText(mList.get(position).getTitle());
            switch (mList.get(position).getType()) {
                case Constants.TYPE_ZHIHU:
                    ImageLoader.load(mContext, mList.get(position).getImage(), ((ArticleViewHolder) holder).image);
                    ((ArticleViewHolder) holder).from.setText("来自 知乎");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoDailyDetail(Integer.valueOf(mList.get(holder.getAdapterPosition()).getId()));
                        }
                    });
                    break;
                case Constants.TYPE_ANDROID:
                    ((ArticleViewHolder) holder).image.setImageResource(R.mipmap.ic_android);
                    ((ArticleViewHolder) holder).from.setText("来自 Android");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoTechDetail(mList.get(holder.getAdapterPosition()).getImage(),mList.get(holder.getAdapterPosition()).getTitle()
                                    ,mList.get(holder.getAdapterPosition()).getId(), TechPresenter.TECH_ANDROID);
                        }
                    });
                    break;
                case Constants.TYPE_IOS:
                    ((ArticleViewHolder) holder).image.setImageResource(R.mipmap.ic_ios);
                    ((ArticleViewHolder) holder).from.setText("来自 iOS");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoTechDetail(mList.get(holder.getAdapterPosition()).getImage(),mList.get(holder.getAdapterPosition()).getTitle()
                                    ,mList.get(holder.getAdapterPosition()).getId(), TechPresenter.TECH_IOS);
                        }
                    });
                    break;
                case Constants.TYPE_WEB:
                    ((ArticleViewHolder) holder).image.setImageResource(R.mipmap.ic_web);
                    ((ArticleViewHolder) holder).from.setText("来自 Web");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoTechDetail(mList.get(holder.getAdapterPosition()).getImage(),mList.get(holder.getAdapterPosition()).getTitle()
                                    ,mList.get(holder.getAdapterPosition()).getId(), TechPresenter.TECH_WEB);
                        }
                    });
                    break;
                case Constants.TYPE_WECHAT:
                    ImageLoader.load(mContext, mList.get(position).getId(), ((ArticleViewHolder) holder).image);
                    ((ArticleViewHolder) holder).from.setText("来自 微信");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoTechDetail(mList.get(holder.getAdapterPosition()).getImage(),mList.get(holder.getAdapterPosition()).getTitle()
                                    ,mList.get(holder.getAdapterPosition()).getId(), WechatPresenter.TECH_WECHAT);
                        }
                    });
                    break;
            }
        } else if(holder instanceof GirlViewHolder) {
            ImageLoader.load(mContext, mList.get(position).getImage(), ((GirlViewHolder) holder).image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoGirlDetail(mList.get(holder.getAdapterPosition()).getImage(),mList.get(holder.getAdapterPosition()).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_article_image)
        ImageView image;
        @BindView(R.id.tv_article_title)
        TextView title;
        @BindView(R.id.tv_article_from)
        TextView from;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

    public static class GirlViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_girl_image)
        ImageView image;

        public GirlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void gotoDailyDetail(int id) {
        Intent intent = new Intent();
        intent.setClass(mContext, ZhihuDetailActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }

    public void gotoTechDetail(String url,String title,String id,String tech) {
        Intent intent = new Intent();
        intent.setClass(mContext, TechDetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("id",id);
        intent.putExtra("tech",tech);
        mContext.startActivity(intent);
    }

    public void gotoGirlDetail(String url,String id) {
        Intent intent = new Intent();
        intent.setClass(mContext, GirlDetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }
}
