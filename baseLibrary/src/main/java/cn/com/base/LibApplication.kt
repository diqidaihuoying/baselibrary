package cn.com.base

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import skin.support.SkinCompatManager
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.utils.Slog


/**
 * 创建日期：2018/6/6 on 15:42
 * 描述:
 * 作者:wantao
 */
open class LibApplication : Application() {

    companion object {
        lateinit var application: LibApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        //初始化友盟统计
        initum();

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                return ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        })
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                //指定为经典Footer，默认是 BallPulseFooter
                return ClassicsFooter(context).setDrawableSize(20f)
            }
        })

        //换肤
        Slog.DEBUG = true
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addInflater(SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
//                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    /**
     * 初始化友盟统计
     */
    private fun initum() {
        /**
         * 友盟初始化
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5b2b14388f4a9d3178000013", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null)
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true)
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true)
        /**
         * EScenarioType.E_UM_NORMAL 普通统计场景，如果您在埋点过程中没有使用到
        U-Game统计接口，请使用普通统计场景。
        EScenarioType.E_UM_GAME 游戏场景 ，如果您在埋点过程中需要使用到U-Game
        统计接口，则必须设置游戏场景，否则所有的U-Game统计接口不会生效。
         */
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_DUM_NORMAL)

        // 将默认Session间隔时长改为40秒。
        MobclickAgent.setSessionContinueMillis(1000*40)

    }

}
