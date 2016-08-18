package com.codeest.geeknews.ui.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
        holder.tvLike.setText(info.getLikes());
        if (info.getReply_to() != null) {
            holder.tvReply.setVisibility(View.VISIBLE);
            holder.tvReply.setText(String.format("@%s: %s",info.getReply_to().getAuthor(),info.getReply_to().getContent()));
            if (info.getReply_to().getExpandState() == STATE_NULL) {    //未知
                if (holder.tvReply.getLineCount() > MAX_LINE) {
                    holder.tvReply.setMaxLines(MAX_LINE);
                    holder.tvExpand.setVisibility(View.VISIBLE);
                    holder.tvExpand.setText("展开");
                    info.getReply_to().setExpandState(STATE_SHRINK);
                } else {
                    holder.tvExpand.setVisibility(View.GONE);
                    info.getReply_to().setExpandState(STATE_NONE);
                }
            } else if(info.getReply_to().getExpandState() == STATE_NONE) {  //无需展开
                holder.tvExpand.setVisibility(View.GONE);
            } else if(info.getReply_to().getExpandState() == STATE_EXPAND) {    //已展开
                holder.tvReply.setMaxLines(Integer.MAX_VALUE);
                holder.tvExpand.setText("收起");
                holder.tvExpand.setVisibility(View.VISIBLE);
                holder.tvExpand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) view;
                        tv.setText("展开");
                        tv.setMaxLines(MAX_LINE);
                        mList.get(holder.getAdapterPosition()).getReply_to().setExpandState(STATE_SHRINK);
                    }
                });
            } else {    //已收缩
                holder.tvReply.setMaxLines(MAX_LINE);
                holder.tvExpand.setText("展开");
                holder.tvExpand.setVisibility(View.VISIBLE);
                holder.tvExpand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) view;
                        tv.setText("收起");
                        tv.setMaxLines(Integer.MAX_VALUE);
                        mList.get(holder.getAdapterPosition()).getReply_to().setExpandState(STATE_EXPAND);
                    }
                });
            }
        } else {
            holder.tvReply.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
