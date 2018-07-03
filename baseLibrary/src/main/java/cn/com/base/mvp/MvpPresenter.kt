package cn.com.base.mvp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import cn.com.base.R
import cn.com.base.base.BaseActivity
import pub.devrel.easypermissions.EasyPermissions

/**
 * 创建日期：2018/7/2 on 10:56
 * 描述:
 * 作者:wantao
 */
interface MvpPresenter{


    fun onCreate()
    fun  onResume()
    fun  onPause()
    fun  onDestroy()

    /*获取权限*/
    fun requiresDefaultPermission()

    /*获取指定的权限*/
    fun requiresAppointPermission(permissions: Array<String>)

    /*获取权限结果回调*/
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)


}