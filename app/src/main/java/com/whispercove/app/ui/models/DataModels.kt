package com.whispercove.app.ui.models

/**
 * 信件数据类 - 树洞信件漂流站的核心数据结构
 * @param id 信件唯一标识
 * @param authorId 作者ID (别名，指向senderId)
 * @param authorName 作者名称 (别名，指向senderName)
 * @param senderId 发送者ID
 * @param senderName 发送者昵称
 * @param senderAvatar 发送者头像
 * @param content 信件内容
 * @param treeHoleType 投递的树洞类型
 * @param letterPaperType 信纸类型
 * @param waxSealType 火漆印类型
 * @param timestamp 发送时间戳 (字符串格式，如"2小时前")
 * @param mood 情绪标签
 * @param isSealed 是否未拆封
 * @param isRead 是否已读
 * @param isReplied 是否已回复
 * @param replyCount 回复数量
 * @param isCollected 是否已收藏
 */
data class Letter(
    val id: String,
    val senderId: String,
    val senderName: String,
    val senderAvatar: String,
    val content: String,
    val treeHoleType: TreeHoleType,
    val letterPaperType: LetterPaperType,
    val waxSealType: WaxSealType,
    val timestamp: String, // 改为字符串类型
    val mood: String = "", // 添加情绪标签
    val isSealed: Boolean = true, // 添加是否未拆封属性
    val isRead: Boolean = false,
    val isReplied: Boolean = false,
    val replyCount: Int = 0,
    val isCollected: Boolean = false,
    val imageUrl: String = "", // 添加图片URL
    val likes: Int = 0 // 添加点赞数
) {
    // 提供别名属性以兼容现有代码
    val authorId: String get() = senderId
    val authorName: String get() = senderName
}

/**
 * 树洞类型枚举
 */
enum class TreeHoleType(val displayName: String, val description: String) {
    EMO("emo树洞", "接收emo/吐槽/压力类信件"),
    FOODIE("干饭树洞", "接收美食/快乐类信件"),
    STUDY("考研树洞", "接收学业/备考类信件")
}

/**
 * 信纸类型枚举
 */
enum class LetterPaperType(val displayName: String, val patternName: String) {
    CLASSIC("经典信纸", "classic_pattern"),
    VINTAGE("复古信纸", "vintage_pattern"),
    MINIMAL("极简信纸", "minimal_pattern"),
    NATURE("自然信纸", "nature_pattern")
}

/**
 * 火漆印类型枚举
 */
enum class WaxSealType(val displayName: String, val colorName: String, val icon: String) {
    HEART("爱心火漆", "red", "♥"),
    STAR("星星火漆", "gold", "★"),
    FLOWER("花朵火漆", "pink", "✿"),
    MOON("月亮火漆", "blue", "☽"),
    BIRD("飞鸟火漆", "green", "✈")
}

/**
 * 漂流瓶数据类 - 用于漂流瓶功能
 * @param id 漂流瓶唯一标识
 * @param message 漂流瓶中的消息内容
 * @param origin 漂流瓶来源地
 * @param journeyDays 漂流天数
 * @param isPickedUp 是否已被捡起
 * @param mood 情绪标签
 */
data class DriftingBottle(
    val id: String,
    val message: String,
    val origin: String,
    val journeyDays: Int,
    val isPickedUp: Boolean,
    val mood: String
)

/**
 * 树洞数据类 - 用于树洞功能
 * @param id 树洞唯一标识
 * @param name 树洞名称
 * @param description 树洞描述
 * @param location 树洞位置
 * @param letterCount 信件数量
 * @param imageUrl 树洞图片URL
 */
data class TreeHole(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val letterCount: Int,
    val imageUrl: String
)

/**
 * 漂流瓶类型枚举
 */
enum class BottleType(val displayName: String, val colorName: String, val rotation: Float) {
    CLASSIC("经典漂流瓶", "blue", 0f),
    LUCKY("幸运漂流瓶", "green", 15f),
    WARM("温暖漂流瓶", "orange", -10f),
    PEACE("平静漂流瓶", "purple", 5f)
}

/**
 * 藏信盒数据类 - 用于收藏功能
 * @param id 藏信盒唯一标识
 * @param boxType 藏信盒类型
 * @param letters 收藏的信件列表
 * @param isPublic 是否公开
 * @param createdAt 创建时间戳
 */
data class CollectionBox(
    val id: String,
    val boxType: CollectionBoxType,
    val letters: List<CollectedLetter>,
    val isPublic: Boolean = false,
    val createdAt: Long
)

/**
 * 藏信盒类型枚举
 */
enum class CollectionBoxType(val displayName: String) {
    EMO("emo藏信盒"),
    FOODIE("干饭藏信盒"),
    STUDY("考研藏信盒"),
    SPECIAL("特别收藏")
}

/**
 * CollectionBoxType扩展函数 - 获取背景颜色
 */
fun CollectionBoxType.getBackgroundColor(): androidx.compose.ui.graphics.Color {
    return when (this) {
        CollectionBoxType.EMO -> androidx.compose.ui.graphics.Color(0xFF4CAF50).copy(alpha = 0.3f) // 绿色
        CollectionBoxType.FOODIE -> androidx.compose.ui.graphics.Color(0xFFF44336).copy(alpha = 0.3f) // 红色
        CollectionBoxType.STUDY -> androidx.compose.ui.graphics.Color(0xFF795548).copy(alpha = 0.3f) // 棕色
        CollectionBoxType.SPECIAL -> androidx.compose.ui.graphics.Color(0xFFFFD700).copy(alpha = 0.3f) // 金色
    }
}

/**
 * CollectionBoxType扩展函数 - 获取盖子颜色
 */
fun CollectionBoxType.getLidColor(): androidx.compose.ui.graphics.Color {
    return when (this) {
        CollectionBoxType.EMO -> androidx.compose.ui.graphics.Color(0xFF4CAF50).copy(alpha = 0.5f)
        CollectionBoxType.FOODIE -> androidx.compose.ui.graphics.Color(0xFFF44336).copy(alpha = 0.5f)
        CollectionBoxType.STUDY -> androidx.compose.ui.graphics.Color(0xFF795548).copy(alpha = 0.5f)
        CollectionBoxType.SPECIAL -> androidx.compose.ui.graphics.Color(0xFFFFD700).copy(alpha = 0.5f)
    }
}

/**
 * CollectionBoxType扩展函数 - 获取边框颜色
 */
fun CollectionBoxType.getBorderColor(): androidx.compose.ui.graphics.Color {
    return when (this) {
        CollectionBoxType.EMO -> androidx.compose.ui.graphics.Color(0xFF4CAF50)
        CollectionBoxType.FOODIE -> androidx.compose.ui.graphics.Color(0xFFF44336)
        CollectionBoxType.STUDY -> androidx.compose.ui.graphics.Color(0xFF795548)
        CollectionBoxType.SPECIAL -> androidx.compose.ui.graphics.Color(0xFFFFD700)
    }
}

/**
 * CollectionBoxType扩展函数 - 获取文本颜色
 */
fun CollectionBoxType.getTextColor(): androidx.compose.ui.graphics.Color {
    return androidx.compose.ui.graphics.Color(0xFF212121) // 深灰色
}

/**
 * 收藏信件数据类
 * @param id 收藏记录唯一标识
 * @param originalLetterId 原信件ID
 * @param letter 原信件数据
 * @param collectionNote 收藏备注
 * @param collectedAt 收藏时间戳
 */
data class CollectedLetter(
    val id: String,
    val originalLetterId: String,
    val letter: Letter,
    val collectionNote: String = "",
    val collectedAt: Long
)

/**
 * 用户档案数据类 - 更新以适应新功能
 * @param id 用户唯一标识
 * @param name 用户昵称
 * @param avatar 用户头像
 * @param bio 个人简介
 * @param totalLettersSent 总发送信件数
 * @param totalLettersReceived 总接收信件数
 * @param totalReplies 总回复数
 * @param collectionBoxes 用户藏信盒列表
 * @param preferredWaxSeal 偏好的火漆印类型
 * @param preferredLetterPaper 偏好的信纸类型
 * @param joinDate 加入时间戳
 */
data class UserProfile(
    val id: String,
    val name: String,
    val avatar: String,
    val bio: String = "",
    val totalLettersSent: Int = 0,
    val totalLettersReceived: Int = 0,
    val totalReplies: Int = 0,
    val collectionBoxes: List<CollectionBox> = emptyList(),
    val preferredWaxSeal: WaxSealType = WaxSealType.HEART,
    val preferredLetterPaper: LetterPaperType = LetterPaperType.CLASSIC,
    val joinDate: Long
)

/**
 * 木屋信箱数据类 - 用于收信功能
 * @param id 信箱唯一标识
 * @param userId 用户ID
 * @param pendingLetters 待取信件列表
 * @param lastCheckedTime 上次检查时间
 */
data class Mailbox(
    val id: String,
    val userId: String,
    val pendingLetters: List<Letter>,
    val lastCheckedTime: Long
)

/**
 * 情绪天气数据类 - 用于首页情绪天气牌
 * @param id 天气记录唯一标识
 * @param userId 用户ID
 * @param weatherType 天气类型
 * @param temperature 情绪温度 (-10 到 40)
 * @param description 天气描述
 * @param timestamp 记录时间戳
 */
data class MoodWeather(
    val id: String,
    val userId: String,
    val weatherType: WeatherType,
    val temperature: Int,
    val description: String,
    val timestamp: Long
)

/**
 * 天气类型枚举
 */
enum class WeatherType(val displayName: String, val icon: String) {
    SUNNY("晴朗", "☀"),
    CLOUDY("多云", "☁"),
    RAINY("下雨", "☔"),
    SNOWY("下雪", "❄"),
    RAINBOW("彩虹", "🌈")
}

/**
 * 用户连接数据类 - 用于海湾连接功能
 * @param id 连接唯一标识
 * @param userId 用户ID
 * @param connectedUserId 连接的用户ID
 * @param connectedUserName 连接的用户昵称
 * @param connectedUserAvatar 连接的用户头像
 * @param connectedUserBio 连接的用户简介
 * @param isFollowing 是否正在关注
 * @param isFollower 是否是粉丝
 * @param connectionStartTime 连接开始时间
 */// 回信数据模型
data class LetterReply(
    val id: String,
    val letterId: String,
    val replierId: String,
    val replierName: String,
    val replierAvatar: String,
    val content: String,
    val timestamp: String,
    val isPublic: Boolean = false
)

// 用户连接数据模型
data class Connection(
    val id: String,
    val userId: String,
    val connectedUserId: String,
    val connectedUserName: String,
    val connectedUserAvatar: String,
    val connectedUserBio: String = "",
    val isFollowing: Boolean = false,
    val isFollower: Boolean = false,
    val connectionStartTime: Long
)