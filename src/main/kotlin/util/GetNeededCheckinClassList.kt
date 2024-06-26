package `fun`.eternalblue.util

import `fun`.eternalblue.data.ConstDate
import `fun`.eternalblue.data.User
import `fun`.eternalblue.data.UserLoginInfo
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

object GetNeededCheckinClassList
{
    fun getCheckList(access_secret:String,user_id:String,last_sec_update_ts_s:String,access_id:String,classId:String) {
        val url = ConstDate().needCheckinListUrl
        val formBody = FormBody.Builder()
            .addEncoded("clazz_course_id",classId)
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
            .header("X-mssvc-signature",MakeSign.makeSign(userId = user_id, accessSecret = access_secret,classId = classId, method = 2))
            .header("X-mssvc-sec-ts",last_sec_update_ts_s)
            .header("X-mssvc-access-id",access_id)
            .post(formBody)
            .build()
        val client = OkHttpClient()

        val rsp = client.newCall(getCheckList).execute()
        println(rsp.body?.string())
    }
}

//fun main()
//{
//    println("请输入云班课账户名(手机号或邮箱账号)")
//    UserLoginInfo.userName = readln()
//    println("请输入云班课密码")
//    UserLoginInfo.passWord = readln()
//
//    val loginRspJsonStr = Login.login(UserLoginInfo.userName?:return, UserLoginInfo.passWord?:return)
//    val loginStr = DecodeJson.decodeJson(loginRspJsonStr!!, User::class)?:return
//    GetNeededCheckinClassList.getCheckList(loginStr.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id, classId = "E41E267B-D1F2-11EE-8539-1C34DA7B3F7C")
//}