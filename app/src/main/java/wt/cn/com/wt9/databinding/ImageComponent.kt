package wt.cn.com.wt9.databinding

/**
 * 创建日期：2018/6/28 on 14:56
 * 描述: 图片加载基类
 * 作者:wantao
 */
 open class ImageComponent :BaseImageModel(),Component
{
    override fun getBaseImageModel2(): BaseImageModel? {
        return this
    }
}
