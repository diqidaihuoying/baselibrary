package wt.cn.com.wt9.greendao

import android.content.Context
import cn.com.base.LibApplication
import wt.cn.com.wt9.bean.Work

/**
 * 创建日期：2018/6/21 on 13:55
 * 描述:
 * 作者:wantao
 */
class DaoHelper private constructor(context: Context?, name: String?) : DaoMaster.DevOpenHelper(context, name) {

    companion object {
        private val dbName = "wt.db"
        val daoHelper = DaoHelper(LibApplication.application, dbName)
        private val daoMaster=DaoMaster(daoHelper.writableDb)
    }

    fun insert(work: Work)
    {
        var newSession = daoMaster.newSession() as DaoSession
        var workDao = newSession.workDao as WorkDao
        workDao.insert(work)
    }
}
