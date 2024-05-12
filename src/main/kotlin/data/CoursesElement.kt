package `fun`.eternalblue.data

import kotlinx.serialization.*
import kotlinx.*

@Serializable
data class CoursesElement(
    val name: String,
    val oid: String,
    val status: String
)

