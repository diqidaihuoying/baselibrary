package cn.com.base.simple.databinding

import android.databinding.DataBindingComponent

/**
 * 创建日期：2018/6/28 on 14:06
 * 描述:
 * 作者:wantao
 */
interface Component :DataBindingComponent
{
     override fun getWorkInfoAdapter(): WorkInfoAdapter? {
        return null
    }

     override fun getImageModel(): ImageModel? {
        return null
    }

}

