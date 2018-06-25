package cn.com.base.simple.util

/**
 * 创建日期：2018/6/21 on 14:13
 * 描述:  延迟加载的单例模式
 * 作者:wantao
 */
class Single1 private constructor()
{
    private object Holder
    {
        val instance= Single1()
    }

    companion object {
        val INSTANCE : Single1  by lazy { Holder.instance }
    }
}
