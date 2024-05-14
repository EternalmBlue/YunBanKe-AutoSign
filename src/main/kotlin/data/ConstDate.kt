package `fun`.eternalblue.data

import okhttp3.HttpUrl

data class ConstDate(
    //展示加入的云班课
    val coursesListUrl: String = "https://api.mosoteach.cn/mssvc/index.php/cc/list_joined",
    //展示该课程的历史签到记录签到的课程
    val needCheckinListUrl: String = "https://api.mosoteach.cn/mssvc/index.php/checkin/index",
    //获取当前class_id对应的课程的签到状态（签到类型和是否开启签到）
    val isCheckinOpen:String = "https://api.mosoteach.cn/mssvc/index.php/checkin/current_open"
)
