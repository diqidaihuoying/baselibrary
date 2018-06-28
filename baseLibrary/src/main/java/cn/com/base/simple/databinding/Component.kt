package cn.com.base.simple.databinding

import android.databinding.DataBindingComponent

/**
 * 创建日期：2018/6/28 on 14:06
 * 描述:
 * 作者:wantao
 */
interface Component :DataBindingComponent
{
    open override fun getBaseImageModel(): BaseImageModel ?{
        return null
    }

    open override fun getWorkInfoComponent(): WorkInfoComponent? {
       return null
    }
}

