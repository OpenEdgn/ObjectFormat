package edgn.objects.format

import edgn.objects.format.api.FormatOverride
import edgn.objects.format.api.IDataCovert
import edgn.objects.format.api.IFormat
import edgn.objects.format.api.SpiFormatContext
import java.util.ServiceLoader
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

object GlobalObjectFormat : IDataCovert {
    private val context by lazy {
        ServiceLoader.load(SpiFormatContext::class.java).findFirst().map { it.context }.orElseGet {
            ObjectFormatContext.Builder().apply {
                System.getProperties().forEach { t, u -> addConfig(t.toString(), u.toString()) }
                val formats = spiFormatLoader()
                for (item in formats) {
                    register(item)
                }
            }.build()
        }
    }

    private fun spiFormatLoader(): MutableList<IFormat> {
        val loader = ServiceLoader.load(IFormat::class.java)
        val formats = loader.toMutableList()
        formats.sortWith() { o1, o2 -> // 对覆盖进行排序
            val o1Ann = o1::class.findAnnotation<FormatOverride>()?.over?.toSet()
            val o2Ann = o2::class.findAnnotation<FormatOverride>()?.over?.toSet()
            if (o1Ann != null && o1Ann.contains(o2::class)) {
                1
            } else if (o1Ann != null) {
                0
            } else if (o2Ann != null && o2Ann.contains(o1::class)) {
                -1
            } else {
                0
            }
        }
        return formats
    }

    override fun <T : Any> format(data: T, type: KClass<out T>): String {
        return context.format(data, type)
    }

    override fun <T : Any> reduce(format: String, type: KClass<T>): T {
        return context.reduce(format, type)
    }

    override fun verify(format: String, type: KClass<Any>): Boolean {
        return context.verify(format, type)
    }

    override fun close() {
        context.close()
    }
}
