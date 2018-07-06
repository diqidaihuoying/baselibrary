package cn.com.base.mvp

/**
 * 创建日期：2018/7/2 on 10:43
 * 描述:
 * 作者:wantao
 */
interface MvpView
{
    /*显示无网络视图*/
    fun showNoNetwork()

    /*显示内容视图*/
    fun showContent()

    /*显示空视图*/
    fun showEmpty()

    /*显示空视图*/
    fun showLoadingView()
}

