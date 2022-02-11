package edgn.objects.format.rules

import edgn.objects.format.ObjectFormatContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.Serializable

internal class DefaultDataFormatTest {
    private val format = ObjectFormatContext.Builder().register(DefaultDataFormat()).build()

    @Test
    fun format() {
        val data = TestClass()
        val f = format.format(data)
        val reduce = format.reduce(f, TestClass::class)
        assertEquals(reduce, data)
    }

    data class TestClass(
        val name: String = "dragon",
        val age: Int = 12,
    ) : Serializable
}
