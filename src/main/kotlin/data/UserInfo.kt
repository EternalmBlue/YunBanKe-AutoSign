package `fun`.eternalblue.data

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val user_id : String,
    val access_id : String,
    val access_secret : String,
    val full_name : String,
    val phone_number : String,
    val last_sec_update_ts_s : String,
    val school_name : String
)

@Serializable
data class User(
    val user : UserInfo
)
