package cn.com.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import cn.com.base.R;
import skin.support.widget.SkinCompatLinearLayout;


/**
 * @作者: XJP
 * @时间: 2018/6/22 13:20
 * @描述:
 */
public class TitleBarView extends SkinCompatLinearLayout implements View.OnClickListener{
    /**
     * 参数属性
     * titlename：标题的名称
     * titlecolor: 标题的颜色
     * titlesize:标题的大小
     * leftbtnName:左边按钮的名称
     * rightbtnName:右边按钮的名称
     * leftbtnColor:左边按钮的文字颜色
     * rightbtnColor:右边按钮的文字颜色
     * leftbtnSize:左边按钮的文字大小
     * rightbtnSize:右边按钮文字的大小
     * leftimg:左按钮的图标
     * rightimg:右按钮的图标
     * leftdisplay 左按钮显示的方式
     * rightdisplay:右按钮的显示方式
     * air:都不显示
     * tv:显示文字
     * img：显示图片
     */


    //不显示按钮
    public static final int DISPLAY_AIR = 0;
    //显示文本按钮
    private static final int DISPLAY_TV  = 1;
    //图片按钮
    private static final int DISPLAY_IMG = 2;

    public static final int LEFTBUTTON = 1;

    public static final int RIGHTBUTTON = 2;

    //标题字体的大小
    private int titlesize = 18;
    //标题的名称
    private String titlename;
    //标题的颜色
    private int    titlecolor=0;
    //左边按钮的显示状态
    private int    mDisplayLeft;
    //右边按钮的显示状态
    private int    mDisplayRight;


    //左边TextView按钮的名称
    private String leftbtnName;
    //右边TextView的名称
    private String rightbtnName;
    //左边TextView的颜色
    private int leftbtnColor  = 0;
    //右边TextView的颜色
    private int rightbtnColor = 0;
    //左边TextView 按钮的大小
    private int leftbtnSize   = 16;
    //右边TextView 按钮的大小
    private int rightbtnSize  = 16;
    //左边的图片资源
    private int mLeftImgResources;
    //右边的图片资源
    private int mrightImgResources;

    private TitleButton mLeftBtn;
    private TitleButton mRightBtn;
    private TextView    mNameTv;

    private OnTitleBarClickListener titleBarClickListener;

    @Override
    public void onClick(View view) {
        if (titleBarClickListener != null) {
            if (mLeftBtn.getId() == view.getId()) {
                if (mDisplayLeft != DISPLAY_AIR)
                    titleBarClickListener.OnTitleBarButtonClick(view, LEFTBUTTON);
            } else {
                if (mDisplayRight != DISPLAY_AIR)
                    titleBarClickListener.OnTitleBarButtonClick(view, RIGHTBUTTON);
            }
        }

    }

    //按钮点击接口
    public interface OnTitleBarClickListener {
        void OnTitleBarButtonClick(View view, int direction);
    }

    public void SetTitleBarClickListener(OnTitleBarClickListener titleBarClickListener) {
        this.titleBarClickListener = titleBarClickListener;
    }


    public TitleBarView(Context context) {
        super(context);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(attrs);
    }


    /**
     * 初始化布局
     */
    private void initview(AttributeSet attrs) {
        inflate(getContext(), R.layout.layout_titlebar, this);


        mLeftBtn = findViewById(R.id.leftBtn);
        mRightBtn = findViewById(R.id.rightBtn);
        mNameTv = findViewById(R.id.titleName);


        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        //标题的参数
        titlename = a.getString(R.styleable.TitleBarView_titlename);
        titlesize = (int) a.getDimension(R.styleable.TitleBarView_titlesize, titlesize);
        titlecolor = a.getColor(R.styleable.TitleBarView_titlecolor, ContextCompat.getColor(getContext(),R.color.cus_title_color));
        //按钮显示的方式
        mDisplayLeft = a.getInt(R.styleable.TitleBarView_leftdisplay, DISPLAY_AIR);
        mDisplayRight = a.getInt(R.styleable.TitleBarView_rightdisplay, DISPLAY_AIR);

        //TextView按钮的参数
        leftbtnName = a.getString(R.styleable.TitleBarView_leftbtnName);
        rightbtnName = a.getString(R.styleable.TitleBarView_rightbtnName);
        leftbtnColor = a.getColor(R.styleable.TitleBarView_leftbtnColor, ContextCompat.getColor(getContext(),R.color.cus_title_color));
        rightbtnColor = a.getColor(R.styleable.TitleBarView_rightbtnColor, ContextCompat.getColor(getContext(),R.color.cus_title_color));
        leftbtnSize = (int) a.getDimension(R.styleable.TitleBarView_titlesize, leftbtnSize);
        rightbtnSize = (int) a.getDimension(R.styleable.TitleBarView_titlesize, rightbtnSize);

        mLeftImgResources = a.getResourceId(R.styleable.TitleBarView_leftimg, -1);
        mrightImgResources = a.getResourceId(R.styleable.TitleBarView_rightimg, -1);

        a.recycle();
        initlistiner();
        initdata();
    }

    /**
     * 设置按钮的点击监听
     */
    private void initlistiner() {
        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
    }

    /**
     * 设置数据
     */
    private void initdata() {
        mNameTv.setText(titlename);
        mNameTv.setTextColor(titlecolor);
        mNameTv.setTextSize(titlesize);

        mLeftBtn.setDisplaystatus(mDisplayLeft);
        mRightBtn.setDisplaystatus(mDisplayRight);

        mLeftBtn.setName(leftbtnName);
        mRightBtn.setName(rightbtnName);
        mLeftBtn.setTvcolor(leftbtnColor);
        mRightBtn.setTvcolor(rightbtnColor);
        mLeftBtn.setTvsize(leftbtnSize);
        mRightBtn.setTvsize(rightbtnSize);

        mLeftBtn.setImgResources(mLeftImgResources);
        mRightBtn.setImgResources(mrightImgResources);
    }

    public void setTitlesize(int titlesize) {
        this.titlesize = titlesize;
        mNameTv.setTextSize(titlesize);
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
        mNameTv.setText(titlename);
    }

    public void setTitlecolor(int titlecolor) {
        this.titlecolor = titlecolor;
        mNameTv.setTextColor(titlecolor);
    }

    public void setDisplayLeft(int displayLeft) {
        mDisplayLeft = displayLeft;
        mLeftBtn.setDisplaystatus(mDisplayLeft);
    }

    public void setDisplayRight(int displayRight) {
        mDisplayRight = displayRight;
        mRightBtn.setDisplaystatus(mDisplayRight);
    }

    public void setLeftbtnName(String leftbtnName) {
        this.leftbtnName = leftbtnName;
        mLeftBtn.setName(leftbtnName);
    }

    public void setRightbtnName(String rightbtnName) {
        this.rightbtnName = rightbtnName;
        mRightBtn.setName(rightbtnName);
    }

    public void setLeftbtnColor(int leftbtnColor) {
        this.leftbtnColor = leftbtnColor;
        mLeftBtn.setTvcolor(leftbtnColor);
    }

    public void setRightbtnColor(int rightbtnColor) {
        this.rightbtnColor = rightbtnColor;
        mRightBtn.setTvcolor(rightbtnColor);
    }

    public void setLeftbtnSize(int leftbtnSize) {
        this.leftbtnSize = leftbtnSize;
        mLeftBtn.setTvsize(leftbtnSize);
    }

    public void setRightbtnSize(int rightbtnSize) {
        this.rightbtnSize = rightbtnSize;
        mRightBtn.setTvsize(rightbtnSize);
    }

    public void setLeftImgResources(int leftImgResources) {
        mLeftImgResources = leftImgResources;
        mLeftBtn.setImgResources(mLeftImgResources);
    }

    public void setMrightImgResources(int mrightImgResources) {
        this.mrightImgResources = mrightImgResources;
        mRightBtn.setImgResources(mrightImgResources);
    }
}
