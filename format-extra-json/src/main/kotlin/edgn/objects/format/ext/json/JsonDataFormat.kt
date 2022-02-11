package edgn.objects.format.ext.json

import com.google.gson.GsonBuilder
import edgn.objects.format.api.FormatOverride
import edgn.objects.format.api.IFormat
import edgn.objects.format.api.IFormatContext
import edgn.objects.format.rules.DefaultDataFormat
import kotlin.reflect.KClass

@FormatOverride([DefaultDataFormat::class])
class JsonDataFormat : IFormat {
    override val types = setOf(Any::class)

    private val gson = GsonBuilder().create()

    override fun <T : Any> format(data: T, type: KClass<out T>, context: IFormatContext): String {
        return gson.toJson(data, type.javaObjectType)
    }

    override fun <T : Any> reduce(data: String, type: KClass<T>, context: IFormatContext): T {
        return gson.fromJson(data, type.javaObjectType)
    }
}
