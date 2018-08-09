package wt.cn.com.wt9.mvp.present

import android.app.Activity
import cn.com.base.mvp.BaseFragmentPresent
import cn.com.base.mvp.MvpView
import cn.com.base.util.LogUtil
import cn.com.base.util.StatusColorUtil
import cn.com.base.util.ThemeUtil
import skin.support.SkinCompatManager
import wt.cn.com.wt9.R
import wt.cn.com.wt9.mvp.contact.MeFragmentContact

/**
 * 创建日期：2018/7/12 on 15:08
 * 描述:
 * 作者:wantao
 */
class MeFragmentPresent(var view :MvpView):BaseFragmentPresent(view),MeFragmentContact.IMeFragmentPresent  {

    companion object {
        var TAG="MeFragmentPresent"
    }

    var activity:Activity?=null
    constructor(activity:Activity,view:MvpView):this(view)
    {
        this.activity=activity
    }

    override fun switchM() {
        if (ThemeUtil.getInstance().isDefaultTheme)
        {
            LogUtil.e(TAG,"------night")
            ThemeUtil.getInstance().setNightTheme()
            StatusColorUtil.setRootView(activity!!,R.color.colorPrimary_night)
            SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // 后缀加载

        }else
        {
            LogUtil.e(TAG,"------default")
            ThemeUtil.getInstance().setDefaultTheme()
            StatusColorUtil.setRootView(activity!!, R.color.colorPrimary_day)
            SkinCompatManager.getInstance().restoreDefaultTheme()
        }
    }
}