package `fun`.eternalblue.util

//import com.microsoft.playwright.BrowserType
//import com.microsoft.playwright.Playwright
//import com.microsoft.playwright.options.LoadState
import `fun`.eternalblue.data.UserLoginInfo
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException


object Login
{
    /*
    * 通过http请求登录
    * 可以获取学校，user_id
    * 姓名，手机号
    * 等基础信息
    */
    fun login(userName : String,password: String): String?
    {
        val url : String = "https://www.mosoteach.cn/web/index.php?c=passport&m=account_login"
        val formBody = FormBody.Builder()
            .addEncoded("account_name",userName)
            .addEncoded("user_pwd",password)
            .addEncoded("remember_me","N")
            .build()

        val loginRequest = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val client = OkHttpClient()

        try
        {
            val response = client.newCall(loginRequest).execute()
            if (response.isSuccessful)
            {
                val rsp = response.body!!.string()
                println(rsp)
                return rsp
            }else if (!response.isSuccessful)
            {
                println("错误响应:")
                println(response.body!!.string())
                return response.body!!.string()
            }
        } catch (e: IOException)
        {
            println(e.message)
        }
        return null
    }

    /*
    * 通过playwright模拟浏览器登录
    * 获取localstorage中存储的
    * proxyToken
    * */
//    fun getToken(): String?
//    {
//        try
//        {
//            val playwright = Playwright.create()
//            val browser = playwright.chromium().launch(BrowserType.LaunchOptions().setHeadless(true))
//            val page = browser.newPage()
//            page.navigate("https://www.mosoteach.cn/web/index.php?c=passport")
//            page.fill("input[name=account_name]",UserLoginInfo.userName)
//            page.fill("input[name=user_password]",UserLoginInfo.passWord)
//            page.click(".button-big")
//            page.waitForLoadState(LoadState.LOAD)
//            page.waitForLoadState(LoadState.NETWORKIDLE)
//            val token = page.evaluate("localStorage.getItem('proxyToken')") as String
//            println(token)
//            browser.close()
//            return token
//        }catch (e:Exception)
//        {
//            println(e.message)
//        }
//        return null
//    }
//  模拟登录用于刷精品课时长，一旦启用会使.jar文件空间大幅增加.请期待后续功能开放.
}