package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.boboyuwu.common.basequickadapter.BaseAdapterHelper;
import com.boboyuwu.common.basequickadapter.MultiItemTypeSupport;
import com.boboyuwu.common.basequickadapter.QuickAdapter;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.model.helper.GreenDaoHelper;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.SupportToolBarActivity;
import com.example.boboyuwu.zhihunews.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by wubo on 2017/9/2.
 */

public class AddChannelActivity extends SupportToolBarActivity<HomePageNewsPresenter> {

    private RecyclerView mRecyclerView;
    private QuickAdapter<ChannelNewsBean> mQuickAdapter;
    private ArrayList<ChannelNewsBean> mChannelList;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_channel;
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initRecyclerView();
        initChannel();
    }

    @Override
    protected void setToolBar() {
        super.setToolBar();
        setToolBarTitle("频道管理");
        setBackPress();
    }

    private void initChannel() {
        mChannelList = new ArrayList<>();
        GreenDaoHelper greenDaoHelper = NewsApplication.getAppComponent().getGreenDaoHelper();
        List<ChannelNewsBean> channel = greenDaoHelper.getChannel();
        ChannelNewsBean mineChannelTitle = new ChannelNewsBean();
        mineChannelTitle.setType(ChannelNewsBean.TYPE_TITLE);
        mineChannelTitle.setChannelName("我的频道");
        mChannelList.add(mineChannelTitle);

        for (ChannelNewsBean newsBean : channel) {
            newsBean.setChannelManagerType(ChannelNewsBean.CHANNEL_TYPE_MINE);
            newsBean.setType(ChannelNewsBean.TYPE_CHANNEL);
            mChannelList.add(newsBean);
        }

        ChannelNewsBean moreChannelTitle = new ChannelNewsBean();
        moreChannelTitle.setType(ChannelNewsBean.TYPE_TITLE);
        moreChannelTitle.setChannelName("更多频道");
        mChannelList.add(moreChannelTitle);

        ArrayList<ChannelNewsBean> moreChannelList = new ArrayList<>();
        List<String> channelName = Arrays.asList(getResources().getStringArray(R.array.news_channel_name));
        List<String> channelId = Arrays.asList(getResources().getStringArray(R.array.news_channel_id));
        for (int i = 0; i < channelName.size(); i++) {
            ChannelNewsBean channelNewsBean = new ChannelNewsBean();
            channelNewsBean.setChannelName(channelName.get(i));
            channelNewsBean.setChannelId(channelId.get(i));
            moreChannelList.add(channelNewsBean);
        }
        addDispose(Observable.fromIterable(moreChannelList).filter(new Predicate<ChannelNewsBean>() {
            @Override
            public boolean test(ChannelNewsBean channelNewsBean) throws Exception {
                boolean flag;
                if (mChannelList.contains(channelNewsBean.getChannelName()))
                    flag = false;
                else
                    flag = true;
                return flag;
            }
        }).subscribe(new Consumer<ChannelNewsBean>() {
            @Override
            public void accept(ChannelNewsBean channelNewsBean) throws Exception {
                channelNewsBean.setChannelManagerType(ChannelNewsBean.CHANNEL_TYPE_MORE);
                channelNewsBean.setType(ChannelNewsBean.TYPE_CHANNEL);
                mChannelList.add(channelNewsBean);
            }
        }));
        mQuickAdapter.replaceAll(mChannelList);
    }

    private void initRecyclerView() {
        mQuickAdapter = new QuickAdapter<ChannelNewsBean>(this, mMultiItemTypeSupport) {
            @Override
            public void onAttachedToRecyclerView(RecyclerView recyclerView) {
                super.onAttachedToRecyclerView(recyclerView);
                LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            int itemViewType = getItemViewType(position);
                            if (itemViewType == ChannelNewsBean.TYPE_TITLE) {
                                return gridLayoutManager.getSpanCount();
                            } else {
                                return 1;
                            }
                        }
                    });
                }

            }

            @Override
            protected void convert(BaseAdapterHelper helper, ChannelNewsBean item) {
                int layoutPosition = helper.getLayoutPosition();
                switch (getItemViewType(layoutPosition)) {
                    case ChannelNewsBean.TYPE_TITLE:
                        processTitle(helper, item);
                        break;
                    case ChannelNewsBean.TYPE_CHANNEL:
                        processChannel(helper, item);
                        break;
                }
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.addItemDecoration(new ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 5;
                outRect.bottom = 5;
                outRect.left = 10;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ChannelManagerCallBack(mQuickAdapter));
        touchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mQuickAdapter);

    }

    private void processChannel(BaseAdapterHelper helper, ChannelNewsBean item) {
        helper.getTextView(R.id.normal_tv).setText(item.getChannelName());
        processClick(helper, item);
        helper.itemView.setEnabled(item.getIsFixChannel() ? false : true);
    }

    private void processClick(final BaseAdapterHelper helper, final ChannelNewsBean item) {
        helper.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //将更多频道移动到我的频道
                //swapChannel(helper,item);
            }
        });
    }

    private void swapChannel(BaseAdapterHelper helper, ChannelNewsBean item) {
        List<ChannelNewsBean> data = mQuickAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            if (TextUtils.equals(data.get(i).getChannelName(), "更多频道")) {
                ChannelNewsBean channelNewsBean = data.get(i);
                data.remove(channelNewsBean);
                data.add(i - 1, channelNewsBean);
            }
        }
        mQuickAdapter.notifyDataSetChanged();
    }


    private void processTitle(BaseAdapterHelper helper, ChannelNewsBean item) {
        helper.getTextView(R.id.title_tv).setText(item.getChannelName());
    }


    private MultiItemTypeSupport<ChannelNewsBean> mMultiItemTypeSupport = new MultiItemTypeSupport<ChannelNewsBean>() {
        @Override
        public int getLayoutId(int viewType) {
            int layout = 0;
            switch (viewType) {
                case ChannelNewsBean.TYPE_TITLE:
                    layout = R.layout.item_add_channel_title;
                    break;
                case ChannelNewsBean.TYPE_CHANNEL:
                    layout = R.layout.item_add_channel_normal;
                    break;
            }
            return layout;
        }

        @Override
        public int getItemViewType(int position, ChannelNewsBean channelNewsBean) {
            return channelNewsBean.getType();
        }
    };


    private void findViews() {
        mRecyclerView = getView(R.id.recyclerview);
    }

    @Override
    protected void initInject() {
        getActivityComponent().injectActivity(this);
    }

    public static void startAddChannelActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AddChannelActivity.class);
        if (bundle != null)
            intent.putExtra(Keys.BUNDLE, bundle);
        context.startActivity(intent);
    }


    static class ChannelManagerCallBack extends ItemTouchHelper.Callback {
        private QuickAdapter <ChannelNewsBean>mQuickAdapter;

        public ChannelManagerCallBack(QuickAdapter  <ChannelNewsBean>quickAdapter){

            mQuickAdapter = quickAdapter;
        }
        @Override
        public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                    |ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT ;
            return makeMovementFlags(dragFlags,0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
            int layoutPosition = viewHolder.getLayoutPosition();
            int targetPosition = target.getLayoutPosition();
            Collections.swap(mQuickAdapter.getData(),layoutPosition,targetPosition);
            mQuickAdapter.notifyItemMoved(layoutPosition,targetPosition);
            return false;
        }

        @Override
        public void onSwiped(ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean canDropOver(RecyclerView recyclerView, ViewHolder current, ViewHolder target) {
            List<ChannelNewsBean> data = mQuickAdapter.getData();
            int currentPosition = current.getLayoutPosition();
            int targetPosition = target.getLayoutPosition();
            int moreChannelTitlePosition=0;
            for (int i = 0; i < data.size(); i++) {
                if(TextUtils.equals(data.get(i).getChannelName(),"更多频道")){
                    moreChannelTitlePosition=i;
                }
            }
            boolean moveFlag=false;

            if(data.get(currentPosition).getChannelManagerType()==ChannelNewsBean.CHANNEL_TYPE_MINE){

                if(mQuickAdapter.getItemViewType(currentPosition)!=ChannelNewsBean.TYPE_TITLE&&
                        !data.get(target.getLayoutPosition()).getIsFixChannel()&&
                        currentPosition<moreChannelTitlePosition){
                    moveFlag=true;
                }else{
                    moveFlag=false;
                }

            }


            if(data.get(currentPosition).getChannelManagerType()==ChannelNewsBean.CHANNEL_TYPE_MORE){
                if(mQuickAdapter.getItemViewType(currentPosition)!=ChannelNewsBean.TYPE_TITLE&&
                        !data.get(target.getLayoutPosition()).getIsFixChannel()&&
                        currentPosition>moreChannelTitlePosition){
                    moveFlag=true;
                }else{
                    moveFlag=false;
                }

            }
            return moveFlag;
        }


    }


}