package com.whispercove.app.ui.models

data class WhisperPost(
    val id: String,
    val userId: String,
    val userName: String,
    val userAvatar: String,
    val content: String,
    val imageUrl: String = "",
    val timestamp: String,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val isOpened: Boolean = false, // 是否已拆盲盒
    val mood: String = "" // 情绪标签
)

data class ExploreItem(
    val id: String,
    val title: String,
    val author: String,
    val category: String,
    val imageUrl: String,
    val description: String
)

data class Connection(
    val id: String,
    val name: String,
    val username: String,
    val bio: String,
    val avatarUrl: String,
    val isFollowing: Boolean = false,
    val isFollower: Boolean = false
)

data class UserProfile(
    val id: String,
    val name: String,
    val username: String,
    val bio: String,
    val avatarUrl: String,
    val coverImageUrl: String,
    val postsCount: Int,
    val followingCount: Int,
    val followersCount: Int
)