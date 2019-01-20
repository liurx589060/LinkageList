package com.rx.linkagelist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rx.linklib.AbsLinkHandle;
import com.rx.linklib.HeadModel;
import com.rx.linklib.LinkAdapter;
import com.rx.linklib.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.OVER_SCROLL_NEVER;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recyclerview)
    RecyclerView mainRecyclerview;
    @BindView(R.id.taitou_recyclerview)
    RecyclerView taitouRecyclerview;

    private View topLayout;
    private TextView topTextView;
    private MainAdapter mainAdapter;
    private MyLinkHandle myLinkHandle;


    private String names[] = {"啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分",
            "啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","啊啊啊啊","都是肥肉","啊大哥刚才","八宝饭","孙悟空","猪八戒","我待人","贾宝玉","林黛玉","薛宝钗",
            "刘备","张飞","关羽","A","1245888","@dser得分","B","C","D","E","F","G","H","I","J","K","!","~","#","$","%","^",
            "&","*","(",")","_","-","+","=","M","N","O","P","q","R","S","T","U","V","W","X","Y","Z"};

    private List<String> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        dateList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            dateList.add(names[i]);
        }

        Collections.sort(dateList, new Comparator<String>() {

            public int compareByFirstLetter(String str1, String str2,Utils.ELetterType type) {
                return Utils.getPinYinFirstLetter(str1, type).compareTo(Utils.getPinYinFirstLetter(str2, type));
            }

            @Override
            public int compare(String o1, String o2) {
                return compareByFirstLetter(o1,o2, Utils.ELetterType.LETTER_ONLY_CHARACTER);
            }
        });

        topLayout = findViewById(R.id.top_layout);
        topTextView = topLayout.findViewById(R.id.taitou_text);
        taitouRecyclerview.setOverScrollMode(OVER_SCROLL_NEVER);
        taitouRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myLinkHandle = new MyLinkHandle();
        mainAdapter = new MainAdapter(this, dateList,taitouRecyclerview,myLinkHandle);
        mainRecyclerview.setAdapter(mainAdapter);
    }

    public class MyLinkHandle extends AbsLinkHandle<TaiTouViewHolder> {
        private LayoutInflater mLayoutInflater;

        @Override
        public void getOtherData(List<HeadModel> otherDataList) {
            mLayoutInflater = LayoutInflater.from(MainActivity.this);
        }

        @Override
        public TaiTouViewHolder onOtherCreateViewHolder(ViewGroup parent, int viewType) {
            return new TaiTouViewHolder(mLayoutInflater.inflate(R.layout.layout_taitou_item,parent,false));
        }

        @Override
        public void onOtherBindViewHolder(TaiTouViewHolder holder, int position,List<HeadModel> dateList,HeadModel currentModel) {
            holder.textView.setText((String)dateList.get(position).getData());
            if(currentModel == dateList.get(position)) {
                holder.textView.setTextColor(Color.parseColor("#ffffff"));
                holder.taiTouBgView.setVisibility(View.VISIBLE);
            }else {
                holder.textView.setTextColor(Color.parseColor("#aaaaaa"));
                holder.taiTouBgView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onItemVisibleScroll(int firstVisibleItemPosition, int lastVisibleItemPosition) {

        }

        @Override
        public void onHeadScroll(int headPosition, HeadModel currentHeadModel, int nextHeadTopDis, boolean hasNextHead) {
            int topLayoutHeight = topLayout.getMeasuredHeight();
            topTextView.setText((String)currentHeadModel.getData());
            if(!hasNextHead) {
                topLayout.setY(0);
                return;
            }

            if(nextHeadTopDis < topLayoutHeight) {
                topLayout.setY(-(topLayoutHeight-nextHeadTopDis));
            }else {
                topLayout.setY(0);
            }
        }
    }

    private class MainNormalViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public View divide;

        public MainNormalViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.normal_image);
            textView = itemView.findViewById(R.id.normal_textview);
            divide = itemView.findViewById(R.id.normal_divide);
        }
    }

    private class MainTaitouViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public View divide;

        public MainTaitouViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.taitou_text);
            divide = itemView.findViewById(R.id.taitou_divide);
        }
    }

    private class MainAdapter extends LinkAdapter<RecyclerView.ViewHolder> {
        private final int TAITOU_ITEM = 1;
        private final int NORMAL_ITEM = 2;

        private LayoutInflater mLayoutInflater;
        private String strPre = "";

        public MainAdapter(Activity activity, List<String> date, RecyclerView recyclerView, AbsLinkHandle linkHandle) {
            super(activity,date,recyclerView,linkHandle);
            mLayoutInflater = LayoutInflater.from(activity);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            switch (viewType) {
                case TAITOU_ITEM:
                    viewHolder = new MainTaitouViewHolder(mLayoutInflater.inflate(R.layout.layout_main_taitou_item,parent,false));
                    ((MainTaitouViewHolder)viewHolder).textView.setTextColor(Color.parseColor("#999999"));
                    break;
                case NORMAL_ITEM:
                    viewHolder = new MainNormalViewHolder(mLayoutInflater.inflate(R.layout.layout_main_normal_item,parent,false));
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MainTaitouViewHolder) {
                MainTaitouViewHolder viewHolder = (MainTaitouViewHolder) holder;
                HeadModel model = (HeadModel) mDataList.get(position);
                viewHolder.textView.setText((String)model.getData());
                viewHolder.divide.setVisibility(View.GONE);
            }else if(holder instanceof MainNormalViewHolder) {
                MainNormalViewHolder viewHolder = (MainNormalViewHolder) holder;
                String model = (String) mDataList.get(position);
                viewHolder.textView.setText(model);
                if(mHeadPosList.contains(position+1) || position == getItemCount() - 1) {
                    viewHolder.divide.setVisibility(View.GONE);
                }else {
                    viewHolder.divide.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public Object isHeadData(Object object) {
            String str = Utils.getPinYinFirstLetter((String) object, Utils.ELetterType.LETTER_ONLY_CHARACTER);
            if(!str.equals(strPre)) {
                strPre = str;
                return str;
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            if(mHeadPosList.contains(position)) {
                return TAITOU_ITEM;
            }
            return NORMAL_ITEM;
        }
    }

    private class TaiTouViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public View taiTouBgView;

        public TaiTouViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.taitou_textview);
            taiTouBgView = itemView.findViewById(R.id.taitou_bg);
        }
    }
}
