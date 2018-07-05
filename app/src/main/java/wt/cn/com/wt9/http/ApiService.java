package wt.cn.com.wt9.http;


import java.util.List;
import java.util.Map;

import cn.com.base.http.HttpResult;
import wt.cn.com.wt9.bean.Interest;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import wt.cn.com.wt9.bean.WorkDetails;
import wt.cn.com.wt9.bean.Works;

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
    Observable<HttpResult<List<Works>>> getWorkList(@QueryMap Map<String, Object> map);

    /**
     * 作品详情
     */
    @GET("topic/v1/detail")
    Observable<HttpResult<WorkDetails>> getWorkDetail(@QueryMap Map<String, Object> map);

}
