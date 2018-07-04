package cn.com.base.http

/**
 * 创建日期：2018/7/4 on 11:15
 * 描述:
 * 作者:wantao
 */
class HttpErrorFactory private constructor() {

    companion object {
        var factory=HttpErrorFactory()
    }

    var errorResonse:IErrorResonse?=null

    interface IErrorResonse
    {
        fun onRtError(rt:Int)
    }

}



