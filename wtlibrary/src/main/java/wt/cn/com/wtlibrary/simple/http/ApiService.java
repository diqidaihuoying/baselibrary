package wt.cn.com.wtlibrary.simple.http;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import wt.cn.com.wtlibrary.http.HttpResult;
import wt.cn.com.wtlibrary.simple.bean.Interest;
import wt.cn.com.wtlibrary.simple.bean.WorkInfo;

/**
 * 创建日期：2018/6/7 on 14:21
 * 描述:
 * 作者:wantao
 */
public interface ApiService {

    /**
     * 所有兴趣类别
     */
    @GET("category/v1/list")
    Observable<HttpResult<List<Interest>>> getInterests(@QueryMap Map<String, Object> map);

    /**
     * 根据类别获取作品list
     */
    @GET("topic/v1/page")
    Observable<HttpResult<List<WorkInfo>>> getWorkList(@QueryMap Map<String, Object> map);
}