package com.boboyuwu.common.basequickadapter;

import android.content.Context;

import java.io.Serializable;
import java.util.List;


/**
 * 支持多类型和单类型Item的快速创建：
 *
 * ********* 单一类型使用：*********
 *  mQuickAdapter = new QuickAdapter<Person>(this, R.layout.single_item) {
 *     @Override
 *     protected void convert(BaseAdapterHelper helper, Person item) {
 *          helper.setText(R.id.tv_username, item.getUsername())
 *                .setText(R.id.tv_age, item.getAge() + "岁")
 *                .setText(R.id.tv_desc, item.getDesc());
 *          ImageLoader.getInstance().displayImage(item.getAvatar(), helper.getImageView(R.id.iv_avatar));
 *      }
 *   };
 *
 * ********* 多类型使用：***********
 *
 *  MultiItemTypeSupport<News> multiItemTypeSupport = new MultiItemTypeSupport<News>() {
 *       @Override
 *       public int getLayoutId(int viewType) {
 *           if (viewType == News.ITEM_TYPE_TEXT) {
 *              return R.layout.multi_item_text;
 *           } else if (viewType == News.ITEM_TYPE_BUTTON) {
 *               return R.layout.multi_item_button;
 *           } else if (viewType == News.ITEM_TYPE_IMAGE) {
 *               return R.layout.multi_item_image;
 *           }
 *            //默认返回是文本
 *            return News.ITEM_TYPE_TEXT;
 *        }
 *
 *        @Override
 *        public int getItemViewType(int position, News news) {
 *             return news.getItemType();
 *        }
 *      };
 *
 *  mQuickAdapter = new QuickAdapter<News>(this, multiItemTypeSupport) {
 *           @Override
 *           protected void convert(final BaseAdapterHelper helper, News item) {
 *                 switch (helper.getItemViewType()) {
 *                         case News.ITEM_TYPE_TEXT:
 *                              helper.setText(R.id.tv_text, item.getText());
 *                               break;
 *                          case News.ITEM_TYPE_BUTTON:
 *                               helper.setText(R.id.btn_click, item.getButton());
 *                               helper.setOnClickListener(R.id.btn_click, new View.OnClickListener() {
 *                                              @Override
 *                                              public void onClick(View v) {
 *                                                 Toast.makeText(MultiItemActivity.this, "你点击了按钮" + helper.getAdapterPosition(), Toast.LENGTH_SHORT).show();
 *                                   }
 *                                });
 *                              break;
 *                          case News.ITEM_TYPE_IMAGE:
 *                          ImageLoader.getInstance().displayImage(item.getImage(), helper.getImageView(R.id.iv_image));
 *                          break;
 *                   }
 *            }
 *   };
 *
 * Created by wubo on 16/6/1.
 */

public abstract class QuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> implements Serializable {

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public QuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public QuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    /**
     * Create a multi-view-type QuickAdapter
     *
     * @param context              The context
     * @param multiItemTypeSupport multiitemtypesupport
     */
    protected QuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,MultiItemTypeSupport) but with
     * some initialization data
     *
     * @param context
     * @param multiItemTypeSupport
     * @param data
     */
    protected QuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        super(context, multiItemTypeSupport, data);
    }

}
