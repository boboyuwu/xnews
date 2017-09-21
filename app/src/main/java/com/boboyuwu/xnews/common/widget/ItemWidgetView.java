package com.boboyuwu.xnews.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boboyuwu.common.widget.SwitchView;
import com.example.boboyuwu.zhihunews.R;


/**
 * Created by wubo on 2017/2/28.
 *  封装一个选项卡WidgetView
 *
 *    此控件 左边一个文字    右边一个文字一个图片通常用于导航栏
 *       leftOneText
         leftOneTextTopMargin
         leftOneTextBottomMargin
         leftOneTextLeftMargin
         leftOneTextRightMargin
         rightOneText
         rightOneTextTopMargin
         rightOneTextBottomMargin
         rightOneTextLeftMargin
         rightOneTextRightMargin
         rightOneImg
         rightOneImgTopMargin
         rightOneImgBottomMargin
         rightOneImgLeftMargin
         rightOneImgRightMargin
         padding                  这些属性看名字就知道什么意思了都是设置间距
 */

public class ItemWidgetView extends FrameLayout {

    private View mItemWidgetView;
    private TextView mLeftOneTextView;
    private TextView mRightOneTextView;
    private ImageView mRightOneImg;
    private RelativeLayout mRelativeLayout;
    private Context mContext;
    private SwitchView mSwitchView;

    public ItemWidgetView(Context context) {
        this(context,null);
        mContext = context;
    }

    public ItemWidgetView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        mContext = context;
    }

    public ItemWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context, attrs);
        addView(mItemWidgetView);
    }


    private void initView(Context context, AttributeSet attrs) {
        mItemWidgetView = LayoutInflater.from(context).inflate(R.layout.item_widget_view_layout, null);
        mLeftOneTextView =  mItemWidgetView.findViewById(R.id.leftOneTextView);
        mRightOneTextView =  mItemWidgetView.findViewById(R.id.rightOneTextView);
        mRelativeLayout = mItemWidgetView.findViewById(R.id.rl_layout);
        mRightOneImg =  mItemWidgetView.findViewById(R.id.rightOneImg);
        mSwitchView = (mItemWidgetView.findViewById(R.id.switchView));
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WidgetItemView);
        for (int i = 0; i < a.getIndexCount(); i++) {
            switch (a.getIndex(i)) {
                case R.styleable.WidgetItemView_leftOneText:
                    String leftOneText = a.getString(R.styleable.WidgetItemView_leftOneText);
                    if(!TextUtils.isEmpty(leftOneText)){
                        mLeftOneTextView.setText(leftOneText);
                    }
                    break;
                case R.styleable.WidgetItemView_rightOneText:
                    String rightOneText = a.getString(R.styleable.WidgetItemView_rightOneText);
                    if(!TextUtils.isEmpty(rightOneText)){
                        mRightOneTextView.setText(rightOneText);
                    }
                    break;
                case R.styleable.WidgetItemView_rightOneImg:
                    int rightOneImg = a.getResourceId(R.styleable.WidgetItemView_rightOneImg, 0);
                    mRightOneImg.setImageResource(rightOneImg);
                    break;
                case R.styleable.WidgetItemView_leftOneTextTopMargin:
                    RelativeLayout.LayoutParams viewParams = getInnerViewParams(mLeftOneTextView);
                    float topMargin = a.getDimension(R.styleable.WidgetItemView_leftOneTextTopMargin, viewParams.topMargin);
                    viewParams.topMargin= (int) topMargin;
                    mLeftOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_leftOneTextBottomMargin:
                    viewParams = getInnerViewParams(mLeftOneTextView);
                    float bottomMargin = a.getDimension(R.styleable.WidgetItemView_leftOneTextTopMargin, viewParams.bottomMargin);
                    viewParams.topMargin= (int) bottomMargin;
                    mLeftOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_leftOneTextLeftMargin:
                    viewParams = getInnerViewParams(mLeftOneTextView);
                    float leftMargin = a.getDimension(R.styleable.WidgetItemView_leftOneTextTopMargin, viewParams.leftMargin);
                    viewParams.topMargin= (int) leftMargin;
                    mLeftOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_leftOneTextRightMargin:
                    viewParams = getInnerViewParams(mLeftOneTextView);
                    float rightMargin = a.getDimension(R.styleable.WidgetItemView_leftOneTextTopMargin, viewParams.rightMargin);
                    viewParams.topMargin= (int) rightMargin;
                    mLeftOneTextView.setLayoutParams(viewParams);
                    break;

                case R.styleable.WidgetItemView_rightOneTextTopMargin:
                    viewParams = getInnerViewParams(mRightOneTextView);
                    topMargin = a.getDimension(R.styleable.WidgetItemView_rightOneTextTopMargin, viewParams.topMargin);
                    viewParams.topMargin= (int) topMargin;
                    mRightOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_rightOneTextBottomMargin:
                    viewParams = getInnerViewParams(mRightOneTextView);
                     bottomMargin = a.getDimension(R.styleable.WidgetItemView_rightOneTextTopMargin, viewParams.bottomMargin);
                    viewParams.topMargin= (int) bottomMargin;
                    mRightOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_rightOneTextLeftMargin:
                    viewParams = getInnerViewParams(mRightOneTextView);
                     leftMargin = a.getDimension(R.styleable.WidgetItemView_rightOneTextTopMargin, viewParams.leftMargin);
                    viewParams.topMargin= (int) leftMargin;
                    mRightOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_rightOneTextRightMargin:
                    viewParams = getInnerViewParams(mRightOneTextView);
                     rightMargin = a.getDimension(R.styleable.WidgetItemView_rightOneTextTopMargin, viewParams.rightMargin);
                    viewParams.topMargin= (int) rightMargin;
                    mRightOneTextView.setLayoutParams(viewParams);
                    break;
                case R.styleable.WidgetItemView_wpadding:
                    int padding = (int) a.getDimension(R.styleable.WidgetItemView_wpadding, 0);
                    mRelativeLayout.setPadding(padding,padding,padding,padding);
                    break;

                case R.styleable.WidgetItemView_paddingTop:
                    padding = (int) a.getDimension(R.styleable.WidgetItemView_paddingTop, 0);
                    mRelativeLayout.setPadding(0,padding,0,0);
                    break;

                case R.styleable.WidgetItemView_paddingBottom:
                     padding = (int) a.getDimension(R.styleable.WidgetItemView_paddingBottom, 0);
                     mRelativeLayout.setPadding(0,0,0,padding);
                    break;

                case R.styleable.WidgetItemView_paddingLeft:
                     padding = (int) a.getDimension(R.styleable.WidgetItemView_paddingLeft, 0);
                    mRelativeLayout.setPadding(padding,0,0,0);
                    break;

                case R.styleable.WidgetItemView_paddingRight:
                     padding = (int) a.getDimension(R.styleable.WidgetItemView_paddingRight, 0);
                    mRelativeLayout.setPadding(0,0,padding,0);
                    break;

                case R.styleable.WidgetItemView_leftOneTextColor:
                    int color = a.getColor(R.styleable.WidgetItemView_leftOneTextColor,
                            getResources().getColor(R.color.alpha_10_gray));
                    mLeftOneTextView.setTextColor(color);
                    break;
                case R.styleable.WidgetItemView_showRightOneImg:
                    boolean hasShowImgage = a.getBoolean(R.styleable.WidgetItemView_showRightOneImg, false) ;
                    mRightOneImg.setVisibility(hasShowImgage? View.VISIBLE: View.INVISIBLE);
                    break;
                case R.styleable.WidgetItemView_showSwitchView:
                    boolean hasShowSwitch= a.getBoolean(R.styleable.WidgetItemView_showSwitchView, false);
                    mSwitchView.setVisibility(hasShowSwitch? View.VISIBLE: View.INVISIBLE);
                    break;
            }
        }
        a.recycle();

    }



    private RelativeLayout.LayoutParams getInnerViewParams(View view){
        return (RelativeLayout.LayoutParams) view.getLayoutParams();
    }

    private LayoutParams getLayoutViewParams(View view){
        return (LayoutParams) view.getLayoutParams();
    }

    /**针对需求封装几个隐藏显示控件方法*/
    public void showLeftOneTextView(boolean hasShow){
       mLeftOneTextView.setVisibility(hasShow? View.VISIBLE:INVISIBLE);
    }
    public void showRightOneTextView(boolean hasShow){
        mRightOneTextView.setVisibility(hasShow? View.VISIBLE:INVISIBLE);
    }
    public void showRightOneImageView(boolean hasShow){
        mRightOneImg.setVisibility(hasShow? View.VISIBLE:INVISIBLE);
    }


    /**动态设置各控件间距  传入dp就行自动转换成像素*/
    public void setPadding(int paddingDp){
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, paddingDp, mContext.getResources().getDisplayMetrics());
        mRelativeLayout.setPadding(padding,padding,padding,padding);
    }

    /**设置几个控件的值*/
    public void setLeftOneText(String text){
        if(!TextUtils.isEmpty(text)){
            mLeftOneTextView.setText(text);
        }
    }
    public void setLeftOneTextColor(int color){
        mLeftOneTextView.setTextColor(color);
    }



    public void setRightOneText(String text){
        if(!TextUtils.isEmpty(text)){
            mRightOneTextView.setText(text);
        }
    }

    public void setRightOneTextColor(int color){
        mRightOneTextView.setTextColor(color);
    }

    public void setRightOneImgResource(int imgResource){
        mRightOneImg.setVisibility(View.VISIBLE);
        mRightOneImg.setImageResource(imgResource);
    }

    public SwitchView getSwitchView(){
        return mSwitchView;
    }
}
