package `fun`.eternalblue

import `fun`.eternalblue.util.GetInfo
import `fun`.eternalblue.util.Login

fun main()
{
    println("Hello World!")
    Login.login("test","test")
    if (Login.simulatedLogin() !=null)
    {
        GetInfo.getCoursesInfo(Login.simulatedLogin()!!)
    }else
    {
        println("token获取失败。可能原因:账号密码错误或API服务不可用")
    }

}