package com.lh.baidu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class AlwaysMarqueeTextView extends TextView {
    public AlwaysMarqueeTextView(Context context) {
        super(context);
        initView();
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        //跑马灯缺少以下两种任何一个属性都不行
        setEllipsize(TextUtils.TruncateAt.MARQUEE);//超出文本的省略号显示方式，这里选择没有省略号
        setSingleLine(true);//因为文字只能显示一行，在一行内实现跑马灯。所以设置属性单行模式
    }

    //跑马灯属性只有在获得焦点的情况下才有动画。
    //所以，这里我们让这个textview自动获得焦点
    @Override
    public boolean isFocused() {
        return true;
    }

    //还可以设置文本中的图片，类似通知跑马灯前的小喇叭图标
    public void getLeftDrawable(Drawable leftDrawable) {
        setCompoundDrawables(leftDrawable, null, null, null);
    }
}