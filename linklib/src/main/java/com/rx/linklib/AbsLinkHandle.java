package com.rx.linklib;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2019/1/20.
 */

public abstract class AbsLinkHandle<T extends RecyclerView.ViewHolder> {

    //public abstract void onOtherItemChanged(RecyclerView.ViewHolder preViewHolder, RecyclerView.ViewHolder currentViewHoder);

    public abstract void getOtherData(List<HeadModel> otherDataList);

    public abstract T onOtherCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onOtherBindViewHolder(T holder, int position,List<HeadModel> dateList,HeadModel currentModel);

    public abstract void onItemVisibleScroll(int firstVisibleItemPosition,int lastVisibleItemPosition);

    public abstract void onHeadScroll(int headPosition,HeadModel currentHeadModel,int nextHeadTopDis,boolean hasNextHead);
}
