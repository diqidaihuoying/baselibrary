package wt.cn.com.wt9.databinding

import android.databinding.DataBindingComponent
import cn.com.base.simple.databinding.WorkInfoComponent


/**
 * 创建日期：2018/6/28 on 14:06
 * 描述:
 * 作者:wantao
 */
interface Component:DataBindingComponent
{
    override fun getBaseImageModel1(): cn.com.base.simple.databinding.BaseImageModel? {
        return null
    }

    override fun getBaseImageModel2(): BaseImageModel? {
        return null
    }

    override fun getWorkInfoComponent(): WorkInfoComponent? {
        return null
    }
}

