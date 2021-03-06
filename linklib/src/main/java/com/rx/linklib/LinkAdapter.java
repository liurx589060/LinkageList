package com.rx.linklib;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/20.
 */

public abstract class LinkAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    private Activity mActivity;

    private AbsLinkHandle mLinkHandle;
    private RecyclerView mOtherRecyclerView;
    private RecyclerView mMainRecyclerView;
    private TaitouAdapter mTaitouAdapter;

    private List<Object> mDataList;
    private List<Integer> mHeadPosList;
    private List<HeadModel> mHeadModelList;
    private boolean mTaitouTouch;

    private MainOnScrollListener mMainScrollListener;
    private OtherOnTouchListener mOtherOnTouchListener;

    public <M extends RecyclerView.ViewHolder> LinkAdapter(Activity activity,List list
            ,RecyclerView otherRecyclerView,AbsLinkHandle<M> handle) {
        mActivity = activity;
        mLinkHandle = handle;
        mOtherRecyclerView = otherRecyclerView;
        mDataList = new ArrayList<>();
        mHeadPosList = new ArrayList<>();
        mHeadModelList = new ArrayList<>();

        vhandleData(list);

        //设置otherRecyclerView
        mTaitouAdapter = new TaitouAdapter<M>(getHeadList());
        mOtherRecyclerView.setAdapter(mTaitouAdapter);
    }

    public List<Object> getDataList() {
        return mDataList;
    }

    public void updateData(List newList) {
        vhandleData(newList);
        notifyDataSetChanged();
    }

    public List<HeadModel> getHeadList() {
        return mHeadModelList;
    }

    public HeadModel getCurrentHeadModel() {
        return mTaitouAdapter.getCurrentHeadModel();
    }

    public List<Integer> getHeadPosList() {
        return mHeadPosList;
    }

    public int getHeadIndex(int firstVisibleItemPosition) {
        int index = 0;
        for(int i = 0 ; i < mHeadPosList.size() ; i++) {
            if(mHeadPosList.get(i) <= firstVisibleItemPosition) {
                index = i;
            }else {
                break;
            }
        }
        return index;
    }

    /**
     * 自定义处理数据
     * @param list
     */
    public void vhandleData(List list) {
        mDataList.clear();
        mHeadPosList.clear();
        mHeadModelList.clear();
        for(int i = 0 ; i < list.size() ; i++) {
            Object object = isHeadData(list.get(i));
            if(object != null) {
                setHeadData(object);
            }
            mDataList.add(list.get(i));
        }

        for(int i = 0 ; i < mDataList.size() ; i++) {
            if(mDataList.get(i) instanceof HeadModel) {
                mHeadModelList.add((HeadModel) mDataList.get(i));
            }
        }

        if(mTaitouAdapter != null) {
            mTaitouAdapter.updateData();
        }
    }

    /**
     * 判断是否是抬头
     * @param object
     * @return
     */
    public abstract Object isHeadData(Object object);

    /**
     * 设置抬头
     * @return
     */
    public void setHeadData(Object object) {
        HeadModel model = new HeadModel();
        mDataList.add(model);
        model.setData(object);
        int index = mDataList.size()-1;
        model.setPosition(index);
        mHeadPosList.add(index);
    }

    @Override
    public int getItemCount(){
        return mDataList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mMainRecyclerView = recyclerView;

        mMainScrollListener = new MainOnScrollListener();
        mOtherOnTouchListener = new OtherOnTouchListener();
        mMainRecyclerView.addOnScrollListener(mMainScrollListener);
        mOtherRecyclerView.setOnTouchListener(mOtherOnTouchListener);
    }

    public MainOnScrollListener getMainScrollListener() {
        return mMainScrollListener;
    }

    public OtherOnTouchListener getOtherOnTouchListener() {
        return mOtherOnTouchListener;
    }

    private class TaitouAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
        private List<HeadModel> mOtherDataList;
        private HeadModel mHeadModel;
        private HeadModel mPreHeadModel;

        public TaitouAdapter(List<HeadModel> list) {
            mOtherDataList = list;
            if(mLinkHandle != null) {
                mLinkHandle.getOtherData(mOtherDataList);
            }
        }

        public void updateData() {
            if(mLinkHandle != null) {
                mLinkHandle.getOtherData(mOtherDataList);
            }
            notifyDataSetChanged();
        }

        public HeadModel getCurrentHeadModel() {
            return mHeadModel;
        }

        public void setOtherHeadModel(HeadModel headModel) {
            mHeadModel = headModel;
            if(mPreHeadModel != mHeadModel) {
                final int preIndex = mOtherDataList.indexOf(mPreHeadModel);
                final int currentIndex = mOtherDataList.indexOf(mHeadModel);
                notifyItemChanged(preIndex);
                notifyItemChanged(currentIndex);

                mPreHeadModel = mHeadModel;
            }
        }

        public List<HeadModel> getData() {
            return mOtherDataList;
        }

        @Override
        public T onCreateViewHolder(ViewGroup parent, int viewType) {
            if(mLinkHandle != null) {
                return (T) mLinkHandle.onOtherCreateViewHolder(parent,viewType);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(T holder, int position) {
            if(mLinkHandle != null) {
                mLinkHandle.onOtherBindViewHolder(holder,position,mOtherDataList,mHeadModel);
            }
        }

        @Override
        public int getItemCount() {
            if(mOtherDataList != null) {
                return mOtherDataList.size();
            }
            return 0;
        }
    }

    public class MainOnScrollListener extends RecyclerView.OnScrollListener {

        private void setTaitou(RecyclerView recyclerView) {
            LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();//可见范围内的第一项的位置
            int lastVisibleItemPosition = manager.findLastVisibleItemPosition();//可见范围内的最后一项的位置
            if(mLinkHandle != null) {
                mLinkHandle.onItemVisibleScroll(firstVisibleItemPosition,lastVisibleItemPosition);
            }

            int taitouIndex = getHeadIndex(firstVisibleItemPosition);
            View itemView = null;
            if(taitouIndex < getHeadPosList().size() - 1) {
                itemView = manager.findViewByPosition((getHeadPosList().get(taitouIndex+1)));
            }

            int position = getHeadPosList().get(taitouIndex);
            Object value = mDataList.get(getHeadPosList().get(taitouIndex));
            if(itemView != null) {
                int topDis = itemView.getTop();
                if(mLinkHandle != null && value instanceof HeadModel) {
                    mLinkHandle.onHeadScroll(position, (HeadModel) value,topDis,true);
                }
            }else {
                if(mLinkHandle != null) {
                    mLinkHandle.onHeadScroll(position,(HeadModel) value,0,false);
                }
            }

            if(!mTaitouTouch && value instanceof HeadModel) {
                mTaitouAdapter.setOtherHeadModel(((HeadModel)value));
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            setTaitou(recyclerView);
        }
    }

    public class OtherOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                mTaitouTouch = false;
            }else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                mTaitouTouch = true;
            }

            LinearLayoutManager manager = (LinearLayoutManager) mOtherRecyclerView.getLayoutManager();
            View item = manager.findViewByPosition(manager.findFirstVisibleItemPosition());
            int itemHeight = item.getHeight();
            int height = mOtherRecyclerView.getHeight();
            float y = event.getY();
            if(y < 0) {
                y = 0;
            }else if(y > height) {
                y = height-1;
            }
            int position = (int) (y/itemHeight);
            position = position<(mTaitouAdapter.getData().size()-1)?position:(mTaitouAdapter.getData().size()-1);
            int taitouIndex = ((HeadModel)mTaitouAdapter.getData().get(position)).getPosition();
            if(mTaitouAdapter.getData().size() > 0 && mTaitouTouch) {
                ((LinearLayoutManager)mMainRecyclerView.getLayoutManager()).scrollToPositionWithOffset(taitouIndex,0);
            }
            mTaitouAdapter.setOtherHeadModel((HeadModel) mTaitouAdapter.getData().get(position));

            return true;
        }
    }
}
