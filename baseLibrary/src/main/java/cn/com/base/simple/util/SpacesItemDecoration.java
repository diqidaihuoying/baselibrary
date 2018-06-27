package cn.com.base.simple.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author      : wantao
 * date        : 2017/12/15 13:43
 * description :不要修改此类,影响作品的ui显示
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        outRect.left=0;
        outRect.right=space;
        outRect.bottom=space;
//        if(parent.getChildAdapterPosition(view)==0){
//            outRect.top=space;
//        }
    }
}