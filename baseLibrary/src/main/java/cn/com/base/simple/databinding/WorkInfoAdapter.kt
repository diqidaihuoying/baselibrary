package cn.com.base.simple.databinding

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import cn.com.base.simple.bean.WorkInfo
import cn.com.base.simple.constant.Constant
import cn.com.base.util.DensityUtils

/**
 * 创建日期：2018/6/27 on 16:10
 * 描述: 对workinfo做处理操作
 * 作者:wantao
 */
class WorkInfoAdapter :ImageModel(), Component{

    override fun getWorkInfoAdapter(): WorkInfoAdapter? {
        return this
    }

    override fun getImageModel(): ImageModel? {
        return this
    }

    // 瀑布流的图片宽度
    val waterPullImageWidth = (DensityUtils.getScreenW() - DensityUtils.dip2px(30f)) / 2

    @BindingAdapter("videoVisibility")
    fun setVideoVisibility(view: View, type: Int) {
        if (type == Constant.TYPE_VIDEO) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("layout_height")
    fun setLayoutHeight(view: View, workInfo: WorkInfo) {
        val layoutParams = view.layoutParams
        var toInt = (workInfo.height * waterPullImageWidth / workInfo.width).toInt()
        if(toInt<(DensityUtils.dip2px(150f)))
            toInt=DensityUtils.dip2px(150f)
        layoutParams.height = toInt
        view.layoutParams = layoutParams
    }

    @BindingAdapter("voiceVisibility")
    fun setVoiceVisibility(view: View, type: Int) {
        if (type == Constant.TYPE_VOICE) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("summary")
    fun setSummaryVisibility(view: View, string: String) {
        if (TextUtils.isEmpty(string)) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            (view as TextView ).text=string
        }
    }
}

