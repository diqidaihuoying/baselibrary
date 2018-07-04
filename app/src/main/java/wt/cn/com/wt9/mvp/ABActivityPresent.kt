package wt.cn.com.wt9.mvp

import cn.com.base.mvp.BaseActivityPresent


/**
 * 创建日期：2018/7/2 on 13:43
 * 描述:
 * 作者:wantao
 */
class ABActivityPresent(val mvpView :ABContact.IABView) :BaseActivityPresent(mvpView), ABContact.IABPresent {

    override fun requestTestContent() {
        //先进行非空判断
        mvpView.setTestContent()
    }
}
