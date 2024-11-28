package `fun`.eternalblue.util

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

object DecodeJson
{
    @OptIn(InternalSerializationApi::class)
    fun <T:Any> decodeJson(jsonString: String, entity: KClass<T>): T?
    {
        val json = Json{
            ignoreUnknownKeys = true
        }
        val serializer = entity.serializer()
        try
        {
            val info = json.decodeFromString(serializer,jsonString)
            //println(info)
            return info
        }catch (e:Exception)
        {
            println(e.message)
        }
        return null

    }
}