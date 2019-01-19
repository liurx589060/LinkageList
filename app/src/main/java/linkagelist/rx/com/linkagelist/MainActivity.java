package linkagelist.rx.com.linkagelist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.View.OVER_SCROLL_NEVER;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recyclerview)
    RecyclerView mainRecyclerview;
    @BindView(R.id.taitou_recyclerview)
    RecyclerView taitouRecyclerview;

    private View topLayout;
    private TextView topTextView;
    private MainAdapter mainAdapter;
    private boolean mTaitouTouch;

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
            "刘备","张飞","关羽","A","1245888","@dser得分"};

    private List<String> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        dateList = new ArrayList<>();
        for(int i = 0 ; i < names.length ; i++) {
            dateList.add(names[i]);
        }

        topLayout = findViewById(R.id.top_layout);
        topTextView = topLayout.findViewById(R.id.taitou_text);
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mainAdapter = new MainAdapter(this,dateList);
        mainRecyclerview.setAdapter(mainAdapter);

        final TaiTouAdapter taiTouAdapter = new TaiTouAdapter(this,mainAdapter.getTaitouList());
        taitouRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        taitouRecyclerview.setAdapter(taiTouAdapter);
        taitouRecyclerview.setOverScrollMode(OVER_SCROLL_NEVER);

        mainRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private void setTaitou(RecyclerView recyclerView) {
                LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();//可见范围内的第一项的位置
                int taitouIndex = mainAdapter.getTaitouIndex(firstVisibleItemPosition);
                View itemView = null;
                if(taitouIndex < mainAdapter.getmTaitouPosList().size() - 1) {
                    itemView = manager.findViewByPosition((mainAdapter.getmTaitouPosList().get(taitouIndex+1)));
                }
                int topLayoutHeight = topLayout.getMeasuredHeight();
                if(itemView != null) {
                    int topDis = itemView.getTop();
                    if(topDis < topLayoutHeight) {
                        topLayout.setY(-(topLayoutHeight-topDis));
                    }else {
                        topLayout.setY(0);
                    }
                }else {
                    topLayout.setY(0);
                }

                Object value = mainAdapter.getmDateList().get(mainAdapter.getmTaitouPosList().get(taitouIndex));
                if(value instanceof TaitouModel) {
                    topTextView.setText(((TaitouModel)value).getText());
                    if(!mTaitouTouch) {
                        taiTouAdapter.setOtherIndex(((TaitouModel)value).getPosition());
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setTaitou(recyclerView);
            }
        });

        taitouRecyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    mTaitouTouch = false;
                }else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mTaitouTouch = true;
                }

                LinearLayoutManager manager = (LinearLayoutManager) taitouRecyclerview.getLayoutManager();
                View item = manager.findViewByPosition(0);
                int itemHeight = item.getHeight();
                int height = taitouRecyclerview.getHeight();
                float y = event.getY();
                if(y < 0) {
                    y = 0;
                }else if(y > height) {
                    y = height-1;
                }
                int position = (int) (y/itemHeight);
                int taitouIndex = taiTouAdapter.getDataList().get(position).getPosition();
                if(taiTouAdapter.getDataList().size() > 0 && mTaitouTouch) {
                    Log.e("yy","position=" + position + "--index=" + taitouIndex);
                    ((LinearLayoutManager)mainRecyclerview.getLayoutManager()).scrollToPositionWithOffset(taitouIndex,0);
                }
                taiTouAdapter.setOtherIndex(taitouIndex);

                return false;
            }
        });
    }

    public class TaitouModel {
        private String text = "";
        private int position;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getPosition() {
            return position;
        }

        public void setPostion(int postion) {
            this.position = postion;
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

    private class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int TAITOU_ITEM = 1;
        private final int NORMAL_ITEM = 2;

        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private List<Object> mDateList;
        private List<Integer> mTaitouPosList;

        public MainAdapter(Context context,List<String> date) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mDateList = new ArrayList<>();
            mTaitouPosList = new ArrayList<>();
            Collections.sort(date, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Utils.getPinYinFirstLetter(o1).compareTo(Utils.getPinYinFirstLetter(o2));
                }
            });
            mDateList.addAll(hanzi2pinyin(date));
        }

        public int getTaitouIndex(int firstVisibleItemPosition) {
            int index = 0;
            for(int i = 0 ; i < mTaitouPosList.size() ; i++) {
                if(mTaitouPosList.get(i) <= firstVisibleItemPosition) {
                    index = i;
                }else {
                    break;
                }
            }
            return index;
        }

        public List<Integer> getmTaitouPosList() {
            return mTaitouPosList;
        }

        public List<TaitouModel> getTaitouList() {
            List<TaitouModel> list = new ArrayList<>();
            for(int i = 0 ; i < mDateList.size() ; i++) {
                if(mDateList.get(i) instanceof TaitouModel) {
                    list.add((TaitouModel) mDateList.get(i));
                }
            }
            return list;
        }

        public List<Object> getmDateList() {
            return mDateList;
        }

        private List<Object> hanzi2pinyin(List<String> data) {
            List<String> resultList = new ArrayList<>();
            for(int i = 0 ; i < data.size() ; i++) {
                String pinyin = Utils.getPinYinFirstLetter(data.get(i));
                resultList.add(pinyin);
            }

            List<Object> list = new ArrayList<>();
            String strPre = "";
            for(int i = 0 ; i < resultList.size() ; i++) {
                if(!resultList.get(i).equals(strPre)) {
                    TaitouModel model = new TaitouModel();
                    model.setText(resultList.get(i));
                    list.add(model);
                    strPre = resultList.get(i);

                    int index = list.size()-1;
                    model.setPostion(index);
                    mTaitouPosList.add(index);
                }
                list.add(data.get(i));
            }
            return list;
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
                TaitouModel model = (TaitouModel) mDateList.get(position);
                viewHolder.textView.setText(model.getText());
                viewHolder.divide.setVisibility(View.GONE);
            }else if(holder instanceof MainNormalViewHolder) {
                MainNormalViewHolder viewHolder = (MainNormalViewHolder) holder;
                String model = (String) mDateList.get(position);
                viewHolder.textView.setText(model);
                if(mTaitouPosList.contains(position+1) || position == getItemCount() - 1) {
                    viewHolder.divide.setVisibility(View.GONE);
                }else {
                    viewHolder.divide.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mDateList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(mTaitouPosList.contains(position)) {
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

    private class TaiTouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<TaitouModel> mDataList;
        private LayoutInflater mLayoutInflater;
        private int otherIndex;
        private int preOtherIndex;

        public TaiTouAdapter(Context context,List<TaitouModel> dataList) {
            mDataList = dataList;
            mLayoutInflater = LayoutInflater.from(context);
            Log.e("yy","" + mDataList);
        }

        public List<TaitouModel> getDataList() {
            return mDataList;
        }

        public void setOtherIndex(int otherIndex) {
            this.otherIndex = otherIndex;
            if(otherIndex != preOtherIndex) {
                notifyDataSetChanged();
                preOtherIndex = otherIndex;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TaiTouViewHolder(mLayoutInflater.inflate(R.layout.layout_taitou_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TaiTouViewHolder viewHolder = (TaiTouViewHolder) holder;
            viewHolder.textView.setText(mDataList.get(position).getText());
            if(otherIndex == mDataList.get(position).getPosition()) {
                viewHolder.textView.setTextColor(Color.parseColor("#ffffff"));
                viewHolder.taiTouBgView.setVisibility(View.VISIBLE);
            }else {
                viewHolder.textView.setTextColor(Color.parseColor("#aaaaaa"));
                viewHolder.taiTouBgView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }
}
