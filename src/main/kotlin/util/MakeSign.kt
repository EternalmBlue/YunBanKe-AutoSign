package `fun`.eternalblue.util

import `fun`.eternalblue.data.ConstDate
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object MakeSign
{
    // method: 1获取课程列表，2获取签到历史记录，3获取当前课程签到状态，4使用API签到
    fun makeSign(userId:String, accessSecret:String, method:Int, classId:String? = null): String
    {
        val time = Utils.getTime()
        when (method)
        {
            1 -> {
                val url: String = ConstDate().coursesListUrl
                val message = "${url}|${userId.uppercase()}|${time}"
                return buildSign(accessSecret = accessSecret, message = message)
            }

            2 -> {
                val url: String = ConstDate().needCheckinListUrl
                val str = Utils.getMD5("clazz_course_id=${classId}|page=1|role_id=2").uppercase()
                val message = "${url}|${userId.uppercase()}|${time}|${str}"
                return buildSign(accessSecret = accessSecret, message = message)
            }

            3 -> {
                val url: String = ConstDate().isCheckinOpen
                val str = Utils.getMD5("clazz_course_id=${classId}").uppercase()
                val message = "${url}|${userId.uppercase()}|${time}|${str}"
                return buildSign(accessSecret = accessSecret, message = message)
            }
            4 ->{
                val url: String = ConstDate().checkInUrl
                val str = Utils.getMD5("cc_id=${classId}").uppercase()
                val message = "${url}|${userId.uppercase()}|${time}|${str}"
                return buildSign(accessSecret = accessSecret, message = message)
            }

            else -> {
                val message = "签名构建失败"
            }
        }
        return "签名构建失败"
    }

        private fun buildSign(message:String, accessSecret:String): String
        {
            println(message)
            val str = message.toByteArray(Charsets.UTF_8)
            //val key = ConstDate().key.toByteArray(Charsets.UTF_8)
            val key = accessSecret.toByteArray(Charsets.UTF_8)
            val secretKey = SecretKeySpec(key,"HmacSHA1")
            val mac = Mac.getInstance("HmacSHA1")
            mac.init(secretKey)
            val digestBytes = mac.doFinal(str)
            val signByte = digestBytes.joinToString("") { "%02x".format(it) }
            //println(signByte)
            return signByte
        }
}