package `fun`.eternalblue.util

import `fun`.eternalblue.data.ConstDate
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

object GetNeededCheckinClassList
{
    fun getCheckList(access_secret:String,user_id:String,last_sec_update_ts_s:String,access_id:String) {
        val url = ConstDate().needCheckinListUrl
        val formBody = FormBody.Builder()
            .addEncoded("clazz_course_id","37F9113A-D142-11EE-8539-1C34DA7B3F7C")
            .addEncoded("page","1")
            .addEncoded("role_id","2")
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
            .header("X-mssvc-signature",MakeSign.makeSign(userId = user_id, accessSecret = access_secret,classId = "37F9113A-D142-11EE-8539-1C34DA7B3F7C", method = 2))
            .header("X-mssvc-sec-ts",last_sec_update_ts_s)
            .header("X-mssvc-access-id",access_id)
            .post(formBody)
            .build()
        val client = OkHttpClient()

        val rsp = client.newCall(getCheckList).execute()
        println(rsp.body?.string())
    }
}