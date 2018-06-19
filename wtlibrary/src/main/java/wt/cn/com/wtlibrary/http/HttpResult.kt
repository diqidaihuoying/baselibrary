package wt.cn.com.wtlibrary.http

/**
 * 创建日期：2018/6/6 on 16:01
 * 描述:
 * 作者:wantao
 */

class HttpResult<T> {

    var rt: Int = 0
    var msg: String? = null
    var data: T? = null

    var isHasNextPage: Boolean = false
    var pi: Int = 0
    var pn: Int = 0
    var ps: Int = 0

    constructor() {}

    constructor(rt: Int, msg: String) {
        this.rt = rt
        this.msg = msg
    }
}