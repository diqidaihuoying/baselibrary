package wt.cn.com.wt9.mvp

import cn.com.base.mvp.BasePresent
import cn.com.base.mvp.MvpView


/**
 * 创建日期：2018/7/2 on 13:43
 * 描述:
 * 作者:wantao
 */
class ABPresent(val mvpView :ABContact.IABView) :BasePresent(), ABContact.IABPresent {

    override fun requestTestContent() {
        //先进行非空判断
        mvpView.setTestContent()
    }
}
