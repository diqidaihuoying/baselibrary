package cn.com.base.eventbus

/**
 * @author : TJ
 * @date : 2018/1/13 10:49
 * @description :Event发送消息
 */

class MessageEvent {
    var message: String? = null
    var objectValue: Any? = null

    constructor(message: String) {
        this.message = message
    }

    constructor(message: String, `object`: Any) {
        this.message = message
        objectValue = `object`
    }
}
