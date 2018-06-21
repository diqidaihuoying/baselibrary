package wt.cn.com.wtlibrary.simple.util

/**
 * 创建日期：2018/6/21 on 14:16
 * 描述: 带构造方法的单例模式
 * 作者:wantao
 */
class Single private constructor(name: String) {
    var name = name

    companion object {
        var single: Single? = null
        fun getTb(name: String): Single? {
            synchronized(Single::class)
            {
                if (single == null) {
                    single = Single(name)
                }
                return single
            }
        }
    }
}