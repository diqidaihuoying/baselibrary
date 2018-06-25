package wt.cn.com.wtlibrary.simple.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import wt.cn.com.wtlibrary.BR;

/**
 * @作者: XJP
 * @时间: 2018/6/22 9:18
 * @描述:
 */
public class TestBean{
    public ObservableField<String> testcont = new ObservableField<>();
    public ObservableField<SubTestBen> subtest = new ObservableField<SubTestBen>();

    public static class SubTestBen{
        public ObservableField<String> text1 = new ObservableField<>();
    }
}
