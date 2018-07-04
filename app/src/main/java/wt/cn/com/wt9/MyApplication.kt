package wt.cn.com.wt9

import cn.com.base.LibApplication
import cn.com.base.http.HttpErrorFactory
import wt.cn.com.wt9.http.HttpError


/**
 * 创建日期：2018/6/7 on 20:03
 * 描述:
 * 作者:wantao
 */
class MyApplication : LibApplication()
{
    override fun onCreate() {
        super.onCreate()
        HttpErrorFactory.factory.errorResonse=HttpError()
    }
}

