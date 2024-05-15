package `fun`.eternalblue.data

import kotlinx.serialization.Serializable

@Serializable
data class ClassList(
    val rows:List<ClassInfo>
)
@Serializable
data class ClassInfo(
    val id:String,
    val term:Term,
    val course:Course
)
@Serializable
data class Term(
    val is_current :String
)

@Serializable
data class Course(
    val name:String
)
