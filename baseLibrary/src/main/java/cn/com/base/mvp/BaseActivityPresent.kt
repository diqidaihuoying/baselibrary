package cn.com.base.mvp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import cn.com.base.R
import cn.com.base.util.*
import com.umeng.analytics.MobclickAgent
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest

/**
 * 创建日期：2018/7/3 on 10:52
 * 描述:
 * 作者:wantao
 */
open class BaseActivityPresent(var baseMvpView: MvpView) : BaseHttpPresent(baseMvpView),MvpPresenter {

    var activity=baseMvpView as AppCompatActivity
    var netWorkReceiver = NetWorkReceiver()

    /**
     * 默认权限添加
     */
    private val defaultPerms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate() {
        CurActivityManager.getActivityManager().pushActivity(activity)
        //设置沉浸式
        if (ThemeUtil.getInstance().isDefaultTheme)
        {
            StatusColorUtil.setRootView(activity,R.color.colorPrimary_day)
        }else
        {
            StatusColorUtil.setRootView(activity,R.color.colorPrimary_night)
        }
        //注册网络监听广播
        registerNetWorkReceiver()
    }

    override fun onResume() {
        //获取ding
        EasyPermissions.requestPermissions(
                PermissionRequest.Builder(activity, 10001, *defaultPerms)
                        .setRationale(R.string.rationale_location)
                        .setPositiveButtonText(R.string.button_allow)
                        .setNegativeButtonText(R.string.button_deny)
                        .build())

        //友盟的统计
        MobclickAgent.onResume(activity);
        MobclickAgent.onPageStart(javaClass.name)
    }

    override fun onPause() {
        //友盟的统计
        MobclickAgent.onPause(activity);
        MobclickAgent.onPageEnd(javaClass.name);
    }

    override fun onDestroy() {
        CurActivityManager.getActivityManager().popActivity(activity)
        unregisterNetWorkReceiver()
        onDestroyHttp()
    }

    override fun requiresDefaultPermission() {
        requiresPermission(defaultPerms)
    }

    override fun requiresAppointPermission( permissions: Array<String>) {
        requiresPermission(permissions)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            ToastUtils.showShort(activity.getString(R.string.returned_from_app_settings_to_activity))
        }
    }

    /**
     * 获取了权限
     */
    protected fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        // Some permissions have been granted
        // ...
    }


    /**
     * 权限否决
     */
    protected fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        // Some permissions have been denied
        if (EasyPermissions.somePermissionPermanentlyDenied(activity, list)) {
            AppSettingsDialog.Builder(activity).build().show()
        }
    }

    /**
     * 10001与BaseActivity中的请求码一致
     */
    private fun requiresPermission(permissions: Array<String>)
    {
        if (EasyPermissions.hasPermissions(activity, *permissions)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(activity,activity. getString(R.string.rationale_location),
                    10001, *permissions)
        }
    }

    /**
     * 注册网络监听
     */
    private fun registerNetWorkReceiver() {
        var filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        activity.registerReceiver(netWorkReceiver, filter);
    }

    /**
     * 取消网络监听
     */
    private fun unregisterNetWorkReceiver() {
        activity.unregisterReceiver(netWorkReceiver)
    }




    inner class NetWorkReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (!NetWorkUtil.isNetworkAvailable(context!!)) {
                baseMvpView.showNoNetwork()
            } else {
                baseMvpView.showContent()
            }
        }
    }
}
