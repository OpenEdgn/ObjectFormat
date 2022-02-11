package edgn.objects.format

/**
 * 数据转换异常
 */
class CovertException(msg: String, e: Exception? = null) : IllegalArgumentException(msg, e)
