package `fun`.eternalblue.util

import `fun`.eternalblue.data.ConstDate
import okhttp3.OkHttpClient
import okhttp3.Request

object GetClass
{
    fun getClass(user_id:String, access_secret:String, access_id:String, last_sec_update_ts_s:String)
    {
        val url = ConstDate().coursesListUrl
        val getClassInfo = Request.Builder()
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
            .header("X-mssvc-signature",MakeSign.makeSign(userId = user_id, accessSecret = access_secret, method = 1))
            .header("X-mssvc-sec-ts",last_sec_update_ts_s)
            .header("X-mssvc-access-id",access_id)
            .build()
        val client = OkHttpClient()
        try
        {
           val rsp = client.newCall(getClassInfo).execute()
            println(rsp.body?.string())
        }catch (e:Exception)
        {
            println(e.message)
        }

    }
}