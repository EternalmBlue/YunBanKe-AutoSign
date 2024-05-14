package `fun`.eternalblue.data

import kotlinx.serialization.Serializable

@Serializable
data class CoursesList(
    val courses: List<CoursesElement>
)
//这个类里面是精品课的列表，不是班课
@Serializable
data class CoursesElement(
    val name: String,
    val oid: String,
    val status: String
)
