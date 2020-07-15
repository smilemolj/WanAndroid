package com.yuefeng.base.net

import android.util.Log
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * <pre>
 * author  : cassie
 * email   : 834221339@qq.com
 * time    : 2018/08/28.
 * desc    : 异常处理类
 * version : 1.0
 *
 * 用法： ExceptionHandle.handleException(e).message直接把抛出的异常传过来 然后.message获取到错误类型（String)
</pre> *
 */
object ExceptionHandle {
    private const val UNAUTHORIZED = 401
    private const val FORBIDDEN = 403
    private const val NOT_FOUND = 404
    private const val REQUEST_TIMEOUT = 408
    private const val INTERNAL_SERVER_ERROR = 500
    private const val BAD_GATEWAY = 502
    private const val SERVICE_UNAVAILABLE = 503
    private const val GATEWAY_TIMEOUT = 504
    fun handle(e: Throwable): ResponeThrowable {
        val ex: ResponeThrowable
        Log.i("tag", "e.toString = $e")
        return if (e is HttpException) {
            ex = ResponeThrowable(e, ERROR.HTTP_ERROR)
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN -> ex.message = "禁止访问"
                NOT_FOUND -> ex.message = "找不到服务器"
                REQUEST_TIMEOUT -> ex.message = "请求超时"
                GATEWAY_TIMEOUT -> ex.message = "网关超时"
                INTERNAL_SERVER_ERROR -> ex.message = "服务器错误"
                BAD_GATEWAY -> ex.message = "网关错误"
                SERVICE_UNAVAILABLE -> ex.message = "服务器无响应"
                else ->  //ex.code = httpException.code();
                    ex.message = "网络错误"
            }
            ex
        } else if (e is ServerException) {
            val resultException = e
            ex = ResponeThrowable(resultException, resultException.code)
            ex.message = resultException.message
            ex
        } else if (e is JsonParseException || e is JSONException /*|| e instanceof ParseException*/) {
            ex = ResponeThrowable(e, ERROR.PARSE_ERROR)
            ex.message = "解析错误"
            ex
        } else if (e is ConnectException) {
            ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
            ex.message = "连接失败,请检查网络"
            ex
        } else if (e is SSLHandshakeException) {
            ex = ResponeThrowable(e, ERROR.SSL_ERROR)
            ex.message = "证书验证失败"
            ex
        } else if (e is SocketTimeoutException) { // 超时
            ex = ResponeThrowable(e, ERROR.TIME_OUT)
            ex.message = "请求超时"
            ex
        } else {
            ex = ResponeThrowable(e, ERROR.UNKNOWN)
            ex.message = "未知错误"
            ex
        }
    }

    /**
     * 约定异常
     */
    internal object ERROR {
        /**
         * 未知错误
         */
        const val UNKNOWN = 1000
        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1001
        /**
         * 网络错误
         */
        const val NETWORD_ERROR = 1002
        /**
         * 协议出错
         */
        const val HTTP_ERROR = 1003
        /**
         * 证书出错
         */
        const val SSL_ERROR = 1005
        /**
         * 超时
         */
        const val TIME_OUT = 408
    }

    class ResponeThrowable(throwable: Throwable?, var code: Int) : Exception(throwable) {
        override var message: String? = null

    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    internal class ServerException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }
}