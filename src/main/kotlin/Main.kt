package `fun`.eternalblue

import `fun`.eternalblue.data.User
import `fun`.eternalblue.util.*

fun main()
{
    val loginRspJsonStr = Login.login("13277697010","Eternal_myx2004")
    val loginStr = DecodeJson.decodeJson(loginRspJsonStr!!,User::class) ?:return
    GetClass().getClass(loginStr.user.user_id,loginStr.user.access_secret,loginStr.user.access_id,loginStr.user.last_sec_update_ts_s)
    GetNeededCheckinClassList().getCheckList(loginStr.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id)
    IsCheckinOpen().isCheckOpen(loginStr.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id)
}