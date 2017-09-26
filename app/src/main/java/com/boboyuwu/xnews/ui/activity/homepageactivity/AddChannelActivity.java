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
import com.boboyuwu.common.util.RxBus;
import com.boboyuwu.common.util.RxBusEventKeys;
import com.boboyuwu.common.util.SizeUtils;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.common.utils.ChannelTypeUtil;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.LoadingAndRetryActivity;
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
 * 频道管理界面
 */

public class AddChannelActivity extends LoadingAndRetryActivity<HomePageNewsPresenter> {

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
        setToolBarRight1Text("确定");
        enableBackPress();
    }


    @Override
    public void onBackClick() {
        backAndUpdateChannel();
    }

    @Override
    protected void onRight1Click() {
        super.onRight1Click();
        backAndUpdateChannel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backAndUpdateChannel();
    }

    private void backAndUpdateChannel(){
        //跟新首页的所有频道到数据库
        List<ChannelNewsBean> channels = mQuickAdapter.getData();
        int moreChannelPosition = getMoreChannelPosition(channels);
        List<ChannelNewsBean> mineChannels = channels.subList(1, moreChannelPosition);
        mGreenDaoHelper.setChannelList(mineChannels);
        RxBus.get().post(RxBusEventKeys.UPDATE_CHANNEL,true);
        finish();
    }


    //初始化频道
    private void initChannel() {
        mChannelList = new ArrayList();
        final List<ChannelNewsBean> channel = mGreenDaoHelper.getChannelList();
        //mine title
        ChannelNewsBean mineChannelTitle = new ChannelNewsBean();
        mineChannelTitle.setType(ChannelNewsBean.TYPE_TITLE);
        mineChannelTitle.setChannelName("我的频道");
        mChannelList.add(mineChannelTitle);

        for (ChannelNewsBean newsBean : channel) {
            newsBean.setChannelManagerType(ChannelNewsBean.CHANNEL_TYPE_MINE);
            newsBean.setType(ChannelNewsBean.TYPE_CHANNEL);
            mChannelList.add(newsBean);
        }

        //more title
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
            channelNewsBean.setChannelType(ChannelTypeUtil.getChannelType(channelId.get(i)));
            moreChannelList.add(channelNewsBean);
        }
        //过滤频道
        addDispose(Observable.fromIterable(moreChannelList).filter(new Predicate<ChannelNewsBean>() {
            @Override
            public boolean test(ChannelNewsBean channelNewsBean) throws Exception {
                boolean hasMoreContainMineDate=false;
                for (ChannelNewsBean newsBean : channel) {
                    if(TextUtils.equals(newsBean.getChannelName(),channelNewsBean.getChannelName())){
                        hasMoreContainMineDate=true;
                    }
                }
                return !hasMoreContainMineDate;
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
                        processChannelClick(helper, item);
                        break;
                }
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.addItemDecoration(new ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = SizeUtils.dp2px(5);
                outRect.left = SizeUtils.dp2px(10);
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ChannelManagerCallBack(mQuickAdapter));
        touchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mQuickAdapter);

    }

    private void processChannelClick(final BaseAdapterHelper helper, final ChannelNewsBean item) {
        helper.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                swapChannel(helper,item);
            }
        });

    }

    private void processChannel(BaseAdapterHelper helper, ChannelNewsBean item) {
        helper.getTextView(R.id.normal_tv).setText(item.getChannelName());
        helper.itemView.setEnabled(item.getIsFixChannel() ? false : true);
    }

    private void swapChannel(BaseAdapterHelper helper, ChannelNewsBean item) {
        List<ChannelNewsBean> data = mQuickAdapter.getData();
        if(item.getChannelManagerType() == ChannelNewsBean.CHANNEL_TYPE_MORE){
            //移动到我的频道
            int moreChannelPosition = getMoreChannelPosition(data);
            data.remove(item);
            data.add(moreChannelPosition,item);
            item.setChannelManagerType(ChannelNewsBean.CHANNEL_TYPE_MINE);
        }else if(item.getChannelManagerType() == ChannelNewsBean.CHANNEL_TYPE_MINE){
            //移动到更多频道
            data.remove(item);
            data.add(data.size(),item);
            item.setChannelManagerType(ChannelNewsBean.CHANNEL_TYPE_MORE);
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
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static int getMoreChannelPosition(List<ChannelNewsBean> list){
        int position=0;
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.equals(list.get(i).getChannelName(), "更多频道")) {
                position = i;
            }
        }
        return position;
    }

    /**
     *   自己实现的一套CallBack
     * */
   public static class ChannelManagerCallBack extends ItemTouchHelper.Callback {
        private static final String TAG = "ChannelManagerCallBack";
        private QuickAdapter<ChannelNewsBean> mQuickAdapter;

        public ChannelManagerCallBack(QuickAdapter<ChannelNewsBean> quickAdapter) {
            mQuickAdapter = quickAdapter;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
            int adapterPosition = viewHolder.getAdapterPosition();
            ChannelNewsBean channelNewsBean = mQuickAdapter.getData().get(adapterPosition);
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                    | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            return (channelNewsBean.getIsFixChannel()||
                    channelNewsBean.getChannelManagerType()!=ChannelNewsBean.CHANNEL_TYPE_MINE)
                    ?makeMovementFlags(0, 0):makeMovementFlags(dragFlags, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
            int layoutPosition = viewHolder.getLayoutPosition();
            int targetPosition = target.getLayoutPosition();
            Collections.swap(mQuickAdapter.getData(), layoutPosition, targetPosition);
            mQuickAdapter.notifyItemMoved(layoutPosition, targetPosition);
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
            int moreChannelTitlePosition = getMoreChannelPosition(data);
            boolean moveFlag = false;
            if (data.get(currentPosition).getChannelManagerType() == ChannelNewsBean.CHANNEL_TYPE_MINE) {
                if ( !data.get(currentPosition).getIsFixChannel() &&
                     !data.get(targetPosition).getIsFixChannel() &&
                     targetPosition < moreChannelTitlePosition && targetPosition > 0) {
                    moveFlag = true;
                } else {
                    moveFlag = false;
                }
            }
        /*    if (data.get(currentPosition).getChannelManagerType() == ChannelNewsBean.CHANNEL_TYPE_MORE) {
                if (targetPosition > moreChannelTitlePosition) {
                    moveFlag = true;
                } else {
                    moveFlag = false;
                }
            }*/
            return moveFlag;
        }
    }


}