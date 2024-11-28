package `fun`.eternalblue.util

import `fun`.eternalblue.data.UserLoginInfo
import java.io.File
import java.security.MessageDigest
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils
{
    fun getTime(): String
    {
        val gmtFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'XXX", Locale.ENGLISH)
        val utcTime = ZonedDateTime.now(ZoneOffset.UTC)
        val formattedTime = utcTime.format(gmtFormat)
        return if (formattedTime.endsWith("Z")) formattedTime.replace("Z", "+00:00") else formattedTime
    }
    fun getMD5(data: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(data.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }
    fun generateUserInfoFile()
    {
        val userInfoFile = File("userInfo.txt")
        if (!userInfoFile.exists())
        {
            userInfoFile.createNewFile()
            userInfoFile.writeText("""
                UserName:
                PassWord:
                    """.trimIndent())
        }
    }
    fun readUserInfoFile()
    {
        val userInfoFile = File("userInfo.txt")
        if (!userInfoFile.exists()) return
        userInfoFile.readLines().forEach ()
        {
            line ->
            if(line.startsWith("UserName"))
            {
                UserLoginInfo.userName = line.split(":")[1]
            }else if (line.startsWith("PassWord")) {
                UserLoginInfo.passWord = line.split(":")[1]
            }
        }
    }
}

