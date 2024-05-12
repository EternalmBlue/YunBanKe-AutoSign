package `fun`.eternalblue.data

import kotlinx.serialization.Serializable

@Serializable
data class CoursesList(
    val courses: List<CoursesElement>
)
