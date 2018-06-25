package wt.cn.com.wtlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wt.cn.com.wtlibrary.R;

/**
 * @作者: XJP
 * @时间: 2018/6/23 10:22
 * @描述:
 */
public class TitleButton extends RelativeLayout {
    /**
     * 参数属性
     * tvname：当显示文字时显示的内容
     * tvcolor: 当显示文字时显示的颜色
     * tvsize:当显示文字时显示的大小
     * img:设置图片资源
     * displaystatus 显示的方式
     * air:都不显示
     * tv:显示文字
     * img：显示图片
     */

    //不显示按钮
    private static final int DISPLAY_AIR = 0;
    //显示文本按钮
    private static final int DISPLAY_TV  = 1;
    //图片按钮
    private static final int DISPLAY_IMG = 2;


    private TextView  mTvBtn;
    private ImageView mImgBtn;
    private int       mDisplaystatus;

    private int tvcolor;
    private int tvsize = 15;
    private String mName;
    private int    mImgResources;

    public TitleButton(Context context) {
        super(context);
    }

    public TitleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        tvcolor = ContextCompat.getColor(getContext(), R.color.white);
        initview(attrs);
    }


    private void initview(AttributeSet attrs) {
        inflate(getContext(), R.layout.layout_titlebutton, this);

        mTvBtn = findViewById(R.id.TvBtn);
        mImgBtn = findViewById(R.id.ImgBtn);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleButton);

        mDisplaystatus = a.getInt(R.styleable.TitleButton_displaystatus, DISPLAY_AIR);
        mName = a.getString(R.styleable.TitleButton_tvname);
        tvcolor = a.getColor(R.styleable.TitleButton_tvcolor, tvcolor);
        tvsize = (int) a.getDimension(R.styleable.TitleButton_tvsize, tvsize);
        mImgResources = a.getResourceId(R.styleable.TitleButton_img, -1);

        init();
    }

    private void init() {
        switch (mDisplaystatus) {
            case DISPLAY_AIR:
                mTvBtn.setVisibility(GONE);
                mImgBtn.setVisibility(GONE);
                break;
            case DISPLAY_TV:
                mTvBtn.setVisibility(VISIBLE);
                mImgBtn.setVisibility(GONE);
                mTvBtn.setText(mName);
                mTvBtn.setTextColor(tvcolor);
                mTvBtn.setTextSize(tvsize);
                break;
            case DISPLAY_IMG:
                mTvBtn.setVisibility(GONE);
                mImgBtn.setVisibility(VISIBLE);
                if (mImgResources != -1)
                    mImgBtn.setImageResource(mImgResources);
                break;
        }
    }

    public void setDisplaystatus(int displaystatus) {
        mDisplaystatus = displaystatus;
        init();
    }

    public void setTvcolor(int tvcolor) {
        this.tvcolor = tvcolor;
        mTvBtn.setTextColor(tvcolor);
    }

    public void setTvsize(int tvsize) {
        this.tvsize = tvsize;
        mTvBtn.setTextSize(tvsize);
    }

    public void setName(String name) {
        mName = name;
        mTvBtn.setText(mName);
    }

    public void setImgResources(int imgResources) {
        mImgResources = imgResources;
        if (mImgResources != -1)
            mImgBtn.setImageResource(mImgResources);
    }
}
