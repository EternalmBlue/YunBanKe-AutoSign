package `fun`.eternalblue

import `fun`.eternalblue.data.ClassList
import `fun`.eternalblue.data.User
import `fun`.eternalblue.data.UserLoginInfo
import `fun`.eternalblue.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking ()
{
    if (UserLoginInfo.userName == null || UserLoginInfo.passWord == null)
    {
        println("请输入云班课账户名(手机号或邮箱账号)")
        UserLoginInfo.userName = readln()
        println("请输入云班课密码")
        UserLoginInfo.passWord = readln()
    }

    val loginRspJsonStr = Login.login(UserLoginInfo.userName?:return@runBlocking,UserLoginInfo.passWord?:return@runBlocking)
    val loginStr = DecodeJson.decodeJson(loginRspJsonStr!!,User::class)
    val classStr = GetClass.getClass(loginStr!!.user.user_id,loginStr.user.access_secret,loginStr.user.access_id,loginStr.user.last_sec_update_ts_s)
    val classList = DecodeJson.decodeJson(classStr!!,ClassList::class)
    println(classList)
    val currentClass = classList?.rows?.filter { it.term.is_current=="Y" }
    val currentClassId = currentClass?.map { it.id }
    println(currentClassId)
//    GetNeededCheckinClassList.getCheckList(loginStr!!.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id)
//    IsCheckinOpen.isCheckOpen(loginStr.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id)
//    CheckIn.checkIn(loginStr.user.last_sec_update_ts_s,loginStr.user.access_id,loginStr.user.user_id,loginStr.user.access_secret)
    val autoCheckIn = launch ()
    {
        while (isActive)
        {
            currentClassId?:return@launch
            currentClassId.forEach()
            {
                IsCheckinOpen.isCheckOpen(loginStr.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id,it)
                CheckIn.checkIn(loginStr.user.last_sec_update_ts_s,loginStr.user.access_id,loginStr.user.user_id,loginStr.user.access_secret,it)
            }
//            IsCheckinOpen.isCheckOpen(loginStr.user.access_secret,loginStr.user.user_id,loginStr.user.last_sec_update_ts_s,loginStr.user.access_id,)
//            CheckIn.checkIn(loginStr.user.last_sec_update_ts_s,loginStr.user.access_id,loginStr.user.user_id,loginStr.user.access_secret)
//            delay(1000*30)
            delay(1000*30)
        }
    }
}