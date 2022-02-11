package edgn.objects.format.utils

import edgn.objects.format.api.IDataCovert

inline fun <reified T : Any> IDataCovert.reduce(data: String): T {
    return this.reduce(data, T::class)
}
