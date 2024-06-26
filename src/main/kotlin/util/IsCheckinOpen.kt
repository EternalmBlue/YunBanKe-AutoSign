package `fun`.eternalblue.util

import `fun`.eternalblue.data.ConstDate
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException

object IsCheckinOpen
{
    fun isCheckOpen(access_secret:String, user_id:String, last_sec_update_ts_s:String, access_id:String,classId:String)
    {
        val url = ConstDate().isCheckinOpen
        val formBody = FormBody.Builder()
            .addEncoded("clazz_course_id",classId)
            .build()

        val getCheckList = Request.Builder()
                    .url(url)
                    .header("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.1.0; ONE A2001 Build/OPM7.181205.001)")
                    .header("X-scheme","https")
                    .header("X-app-id","MTANDROID")
                    .header("X-app-version","5.1.1")
                    .header("X-dpr","2.7")
                    .header("X-app-machine","ONE A2001")
                    .header("X-app-system-version","8.1.0")
                    .header("Host","api.mosoteach.cn")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Date", Utils.getTime())
                    .header("X-mssvc-signature", MakeSign.makeSign(userId = user_id, accessSecret = access_secret, method = 3, classId = classId))
                    .header("X-mssvc-sec-ts",last_sec_update_ts_s)
                    .header("X-mssvc-access-id",access_id)
                    .post(formBody)
                    .build()
        val client = OkHttpClient()
        try
        {
            val rsp = client.newCall(getCheckList).execute()
            try
            {
                val mes = rsp.body?.string()
                mes?.let { Log.info(it) }
            } catch (e: IOException) {
                println(e.message)
                e.message?.let { Log.error(it) }
            } catch (e: Exception) {
                println(e.message)
                e.message?.let { Log.error(it) }
            } finally {
                rsp.body?.close()
            }
        } catch (e: SocketTimeoutException) {
            println("Request timed out: ${e.message}")
            e.message?.let { Log.error(it) }
        } catch (e: IOException) {
            println("Network error: ${e.message}")
            e.message?.let { Log.error(it) }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            e.message?.let { Log.error(it) }
        }
    }
}