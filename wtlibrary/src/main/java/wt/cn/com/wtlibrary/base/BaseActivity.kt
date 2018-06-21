package wt.cn.com.wtlibrary.base

import android.Manifest
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.umeng.analytics.MobclickAgent
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import wt.cn.com.wtlibrary.R
import wt.cn.com.wtlibrary.http.HttpResult
import wt.cn.com.wtlibrary.http.ResponseCallback
import wt.cn.com.wtlibrary.util.CurActivityManager
import wt.cn.com.wtlibrary.util.SystemBarTintManager


abstract class BaseActivity : AppCompatActivity() {

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()
    /**
     * 权限添加
     */
    private val perms = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    /**
     * 布局
     */
    protected abstract val layoutId: Int

    /**
     * databinding
     */
    protected var mDataBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurActivityManager.getActivityManager().pushActivity(this)
        mDataBinding = DataBindingUtil.setContentView(this@BaseActivity, layoutId)
        mDataBinding?.root?.setFitsSystemWindows(true)
        //设置沉浸式状态栏
        setRootView()
        initView()
        initData()

    }


    @AfterPermissionGranted(1)
    private fun methodRequiresTwoPermission() {
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location),
                    1, *perms)
        }
    }

    /**
     * 获取指定的权限，需要手动调用
     */

    protected fun getAppointPermisson(permissions: Array<String>) {
        if (EasyPermissions.hasPermissions(this, *permissions)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location),
                    1, *permissions)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    protected fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        // Some permissions have been granted
        // ...
    }

    protected fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        // Some permissions have been denied
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                    .show()
        }
    }

    /**
     * 设置沉浸式
     */
    open fun setRootView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            //修改StatusBar的颜色
            val systemBarTintManager = SystemBarTintManager(this)
            systemBarTintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            systemBarTintManager.isStatusBarTintEnabled = true
        }
    }


    override fun onResume() {
        super.onResume()
        //获取ding
        EasyPermissions.requestPermissions(
                PermissionRequest.Builder(this, 1, *perms)
                        .setRationale(R.string.rationale_location)
                        .setPositiveButtonText(R.string.button_allow)
                        .setNegativeButtonText(R.string.button_deny)
                        .build())

        //友盟的统计
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(javaClass.name)
    }

    override fun onPause() {
        super.onPause()
        //友盟的统计
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(javaClass.name);
    }

    override fun onDestroy() {
        super.onDestroy()
        CurActivityManager.getActivityManager().popActivity(this)
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
            mCompositeDisposable!!.dispose()
        }
    }

    protected fun <T> newObserver(callback: ResponseCallback<T>): Observer<T> {
        return object : Observer<T> {
            override fun onError(e: Throwable) {
                callback.onError(e)
            }

            override fun onComplete() {
                callback.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable!!.add(d)
            }

            override fun onNext(t: T) {
                if (!mCompositeDisposable!!.isDisposed) {
                    callback.onNext(t)
                }
            }
        }
    }

    /**
     * 对 Observable<HttpResult></HttpResult><T>> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     *
     * @param responseObservable
     * @param <T>
     * @return
    </T></T> */
    protected fun <T> applySchedulers(responseObservable: Observable<HttpResult<T>>): Observable<T> {
        return responseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { tHttpResult -> tHttpResult.data }
    }

    protected abstract fun initView()

    protected abstract fun initData()


}

