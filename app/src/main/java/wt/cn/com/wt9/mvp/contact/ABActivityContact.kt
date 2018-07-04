package wt.cn.com.wt9.mvp.contact

import cn.com.base.mvp.MvpPresenter
import cn.com.base.mvp.MvpView

/**
 * 创建日期：2018/7/3 on 9:20
 * 描述:
 * 作者:wantao
 */
 class ABActivityContact {

     interface IABPresent
     {
         fun requestTestContent();
     }

     interface IABView :MvpView{
         fun setTestContent();
     }

}