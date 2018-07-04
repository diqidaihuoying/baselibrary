package wt.cn.com.wt9.mvp.present

import cn.com.base.mvp.BaseActivityPresent
import wt.cn.com.wt9.mvp.contact.ABActivityContact


/**
 * 创建日期：2018/7/2 on 13:43
 * 描述:
 * 作者:wantao
 */
class ABActivityPresent(val mvpView : ABActivityContact.IABView) :BaseActivityPresent(mvpView), ABActivityContact.IABPresent {

    override fun requestTestContent() {
        //先进行非空判断
        mvpView.setTestContent()
    }
}
