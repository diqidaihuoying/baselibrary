package wt.cn.com.wt9.databinding

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import wt.cn.com.wt9.constant.Constant
import cn.com.base.util.DensityUtils
import wt.cn.com.wt9.bean.Works

/**
 * 创建日期：2018/6/27 on 16:10
 * 描述: 对workinfo做处理操作
 * 作者:wantao
 */
class WorkInfoComponent : ImageComponent() {

    override fun getWorkInfoComponent(): WorkInfoComponent? {
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
    fun setLayoutHeight(view: View, workInfo: Works) {
        val layoutParams = view.layoutParams
        var toInt = (workInfo.height * waterPullImageWidth / workInfo.width)
        if (toInt < (DensityUtils.dip2px(150f)))
            toInt = DensityUtils.dip2px(150f)
        layoutParams.height = toInt
        layoutParams.width=waterPullImageWidth
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
            (view as TextView).text = string
        }
    }
}

