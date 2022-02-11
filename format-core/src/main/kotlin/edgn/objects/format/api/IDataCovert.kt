package edgn.objects.format.api

import java.io.Closeable
import kotlin.reflect.KClass

/**
 * 数据序列化 API 抽象
 */
interface IDataCovert : Closeable {

    /**
     *
     * 将目标对象序列化成字符串
     */
    fun <T : Any> format(data: T, type: KClass<out T> = data::class): String

    /**
     * 将字符串反序列化为对象
     */
    fun <T : Any> reduce(format: String, type: KClass<T>): T

    /**
     * 数据合法性校验
     */
    fun verify(format: String, type: KClass<Any>): Boolean
}
