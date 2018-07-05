package cn.com.base.util

/**
 * 创建日期：2018/6/21 on 14:08
 * 描述: 单例模式，不带参数
 * 作者:wantao
 */
class Single2 private constructor()
{
    companion object {
        val instance = Single2()
    }
}
