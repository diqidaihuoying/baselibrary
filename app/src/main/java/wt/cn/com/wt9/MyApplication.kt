package wt.cn.com.wt9

import android.support.v7.app.AppCompatDelegate
import cn.com.base.LibApplication
import cn.com.base.http.HttpErrorFactory
import wt.cn.com.wt9.http.HttpError
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.SkinCompatManager
import skin.support.utils.Slog


/**
 * 创建日期：2018/6/7 on 20:03
 * 描述:
 * 作者:wantao
 */
class MyApplication : LibApplication() {
    override fun onCreate() {
        super.onCreate()
        //网络请求异常处理
        HttpErrorFactory.factory.errorResonse = HttpError()
    }
}

