package wt.cn.com.wt9.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.com.base.util.LogUtil
import skin.support.SkinCompatManager
import skin.support.content.res.SkinCompatUserThemeManager

import wt.cn.com.wt9.R


/**
 * A simple [Fragment] subclass.
 *
 */
class MessageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var inflate = inflater.inflate(R.layout.fragment_message, container, false)
        var imageView=inflate.findViewById<ImageView>(R.id.iv_test);
        imageView.setImageResource(R.mipmap.ic_test)
        return inflate
    }

}
