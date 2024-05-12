package `fun`.eternalblue.util

import `fun`.eternalblue.data.CoursesList
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request


object GetInfo
{
    fun getCoursesInfo(token: String)
    {
        val client = OkHttpClient()
        val url = "https://coreapi-proxy.mosoteach.cn/index.php/online-courses/joined"
        val coursesRequest = Request.Builder()
            .url(url)
            .get()
            .header("X-token",token)
            .build()

        try
        {
            val response = client.newCall(coursesRequest).execute()
            if (response.isSuccessful)
            {
                val str = response.body!!.string()
                encodeJson(str)
            }
        }catch (e:Exception)
        {
            println(e.message)
        }
    }

    private fun encodeJson(jsonString:String): CoursesList?
    {
        val json = Json{
            ignoreUnknownKeys = true
        }
        try
        {
            val subjects = json.decodeFromString<CoursesList>(jsonString)
            subjects.courses.forEach ()
            {
                println("-------------")
                println("Course Name: ${it.name}")
                println("Course OID: ${it.oid}")
                println("Course Status: ${it.status}")
            }
            return subjects
        }catch (e:Exception)
        {
            println(e.message)
            println("json转换失败")
        }
        return null
    }
}