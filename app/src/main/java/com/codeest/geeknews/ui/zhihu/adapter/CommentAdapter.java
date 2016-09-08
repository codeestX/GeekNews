package com.codeest.geeknews.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.CommentBean;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/19.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<CommentBean.CommentsBean> mList;
    private Context mContext;

    private static final int STATE_NULL = 0;    //未知
    private static final int STATE_NONE = 1;    //无需展开
    private static final int STATE_EXPAND = 2;  //已展开
    private static final int STATE_SHRINK = 3;  //已收缩

    private static final int MAX_LINE = 2;  //起始最多显示2行

    public CommentAdapter(Context mContext,List<CommentBean.CommentsBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CommentBean.CommentsBean info = mList.get(position);
        ImageLoader.load(mContext,info.getAvatar(),holder.civFace);
        holder.tvName.setText(info.getAuthor());
        holder.tvContent.setText(info.getContent());
        holder.tvTime.setText(DateUtil.formatTime2String(info.getTime()));
        holder.tvLike.setText(String.valueOf(info.getLikes()));
        if (info.getReply_to() != null && info.getReply_to().getId() != 0) {
            holder.tvReply.setVisibility(View.VISIBLE);
            SpannableString ss = new SpannableString("@" + info.getReply_to().getAuthor() + ": " + info.getReply_to().getContent());
            ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext,R.color.comment_at)), 0,info.getReply_to().getAuthor().length() + 2 , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//            holder.tvReply.setText(String.format("@%s: %s",info.getReply_to().getAuthor(),info.getReply_to().getContent()));
            holder.tvReply.setText(ss);
            if (info.getReply_to().getExpandState() == STATE_NULL) {    //未知
                holder.tvReply.post(new Runnable() {
                    @Override
                    public void run() {
                        if (holder.tvReply.getLineCount() > MAX_LINE) {
                            holder.tvReply.setMaxLines(MAX_LINE);
                            holder.tvExpand.setVisibility(View.VISIBLE);
                            holder.tvExpand.setText("展开");
                            mList.get(holder.getAdapterPosition()).getReply_to().setExpandState(STATE_SHRINK);
                            holder.tvExpand.setOnClickListener(new OnStateClickListener(holder.getAdapterPosition(), holder.tvReply));
                        } else {
                            holder.tvExpand.setVisibility(View.GONE);
                            mList.get(holder.getAdapterPosition()).getReply_to().setExpandState(STATE_NONE);
                        }
                    }
                });
            } else if(info.getReply_to().getExpandState() == STATE_NONE) {  //无需展开
                holder.tvExpand.setVisibility(View.GONE);
            } else if(info.getReply_to().getExpandState() == STATE_EXPAND) {    //已展开
                holder.tvReply.setMaxLines(Integer.MAX_VALUE);
                holder.tvExpand.setText("收起");
                holder.tvExpand.setVisibility(View.VISIBLE);
                holder.tvExpand.setOnClickListener(new OnStateClickListener(holder.getAdapterPosition(), holder.tvReply));
            } else {    //已收缩
                holder.tvReply.setMaxLines(MAX_LINE);
                holder.tvExpand.setText("展开");
                holder.tvExpand.setVisibility(View.VISIBLE);
                holder.tvExpand.setOnClickListener(new OnStateClickListener(holder.getAdapterPosition(), holder.tvReply));
            }
        } else {
            holder.tvReply.setVisibility(View.GONE);
            holder.tvExpand.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class OnStateClickListener implements View.OnClickListener {

        TextView replyView;
        int position;

        public OnStateClickListener(int position,TextView replyView) {
            this.position = position;
            this.replyView = replyView;
        }

        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;
            if (mList.get(position).getReply_to().getExpandState() == STATE_SHRINK) {
                tv.setText("收起");
                replyView.setMaxLines(Integer.MAX_VALUE);
                replyView.setEllipsize(null);
                mList.get(position).getReply_to().setExpandState(STATE_EXPAND);
            } else {
                tv.setText("展开");
                replyView.setMaxLines(MAX_LINE);
                replyView.setEllipsize(TextUtils.TruncateAt.END);
                mList.get(position).getReply_to().setExpandState(STATE_SHRINK);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.civ_comment_face)
        CircleImageView civFace;
        @BindView(R.id.tv_comment_name)
        TextView tvName;
        @BindView(R.id.tv_comment_content)
        TextView tvContent;
        @BindView(R.id.tv_comment_time)
        TextView tvTime;
        @BindView(R.id.tv_comment_expand)
        TextView tvExpand;
        @BindView(R.id.tv_comment_like)
        TextView tvLike;
        @BindView(R.id.tv_comment_reply)
        TextView tvReply;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
