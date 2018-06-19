package wt.cn.com.wt9;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        ViewPager viewPager = null;
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return false;
            }
        });

        AppBarLayout appBarLayout=null;
        final TextView textView=null;
        int c=100;
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, View.SCALE_Y,  verticalOffset/100);
                animator1.start();
            }
        });
    }
}
