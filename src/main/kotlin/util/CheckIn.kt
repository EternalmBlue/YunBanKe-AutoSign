package `fun`.eternalblue.util

import `fun`.eternalblue.data.ConstDate
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

object CheckIn
{

    fun checkIn(last_sec_update_ts_s:String,access_id:String,user_id:String,access_secret:String,classId:String)
    {
        val url = ConstDate().checkInUrl
        val formBody = FormBody.Builder()
            .addEncoded("cc_id",classId)
//            .addEncoded("report_pos_flag","Y")
//            .addEncoded("lat","30.292238")
//            .addEncoded("lng","109.510080")
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
            .header("X-mssvc-signature", MakeSign.makeSign(userId = user_id, accessSecret = access_secret, method = 4, classId = classId))
            .header("X-mssvc-sec-ts",last_sec_update_ts_s)
            .header("X-mssvc-access-id",access_id)
            .post(formBody)
            .build()
        val client = OkHttpClient()
        val rsp = client.newCall(getCheckList).execute()
        try
        {
            val mes = rsp.body!!.string()
            println(mes)
            Log.info(mes)
        }catch (e:Exception)
        {
            println(e.message)
            e.message?.let { Log.error(it) }
        }
        finally
        {
            rsp.body?.close()
        }
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
//    CheckIn.checkIn(loginStr.user.last_sec_update_ts_s,loginStr.user.access_id,loginStr.user.user_id,loginStr.user.access_secret,"E41E267B-D1F2-11EE-8539-1C34DA7B3F7C")
//}