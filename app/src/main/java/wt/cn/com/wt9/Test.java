package wt.cn.com.wt9;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import wt.cn.com.wt9.http.ApiService;
import wt.cn.com.wtlibrary.http.HttpResult;
import wt.cn.com.wtlibrary.http.ResponseCallback;

/**
 * 创建日期：2018/6/9 on 13:54
 * 描述:
 * 作者:wantao
 */
public class Test {

    public void  a()
    {
        ApiService apiService=null;
       ;
        applySchedulers(apiService.getInterests(null)).subscribe(newObserver(new ResponseCallback<HttpResult<String>>() {
            @Override
            public void onNext(HttpResult<String> stringHttpResult) {

            }
        }));
        List<String> list=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {

        }
    }

    protected <T> Observer<T> newObserver(final ResponseCallback callback) {
        return new Observer<T>() {
            @Override
            public void onError(Throwable e) {
                callback.onError(e);
//                hideTipDialog();
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(T t) {
                    callback.onNext(t);
            }
        };
    }


    /**
     * 对 Observable<HttpResult<T>> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     *
     * @param responseObservable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> applySchedulers(Observable<HttpResult<T>> responseObservable) {
        return responseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<HttpResult<T>, T>() {
                    @Override
                    public T apply(@NonNull HttpResult<T> tHttpResult) throws Exception {
                        return tHttpResult.getData();
                    }
                });

    }
}
