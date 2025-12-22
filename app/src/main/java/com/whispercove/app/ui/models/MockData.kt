package com.whispercove.app.ui.models

object MockData {
    val posts = listOf(
        WhisperPost(
            id = "1",
            userId = "user1",
            userName = "贝贝",
            userAvatar = "https://picsum.photos/seed/user1/200/200.jpg",
            content = "今天的天空特别蓝，心情也跟着明亮起来。生活中的小确幸，就是这些不经意的美好瞬间。",
            imageUrl = "https://picsum.photos/seed/sky1/400/300.jpg",
            timestamp = "2小时前",
            likes = 42,
            comments = 8,
            shares = 3
        ),
        WhisperPost(
            id = "2",
            userId = "user2",
            userName = "音乐人小王",
            userAvatar = "https://picsum.photos/seed/user2/200/200.jpg",
            content = "新歌创作完成！这首《午夜的低语》是我最近最满意的作品，灵感来自深夜的城市灯火。希望你们喜欢。",
            imageUrl = "https://picsum.photos/seed/music1/400/300.jpg",
            timestamp = "5小时前",
            likes = 128,
            comments = 24,
            shares = 15
        ),
        WhisperPost(
            id = "3",
            userId = "user3",
            userName = "摄影师李明",
            userAvatar = "https://picsum.photos/seed/user3/200/200.jpg",
            content = "捕捉到了这组城市夜景，每一张照片背后都有一个故事。摄影不仅是记录，更是情感的传递。",
            imageUrl = "https://picsum.photos/seed/city1/400/300.jpg",
            timestamp = "1天前",
            likes = 256,
            comments = 32,
            shares = 18
        )
    )
    
    val exploreItems = listOf(
        ExploreItem(
            id = "1",
            title = "城市夜景",
            author = "摄影师李明",
            category = "摄影",
            imageUrl = "https://picsum.photos/seed/explore1/300/300.jpg",
            description = "捕捉城市夜晚的美丽瞬间"
        ),
        ExploreItem(
            id = "2",
            title = "午夜的低语",
            author = "音乐人小王",
            category = "音乐",
            imageUrl = "https://picsum.photos/seed/explore2/300/300.jpg",
            description = "一首关于深夜城市的原创歌曲"
        ),
        ExploreItem(
            id = "3",
            title = "抽象艺术",
            author = "艺术家张华",
            category = "艺术",
            imageUrl = "https://picsum.photos/seed/explore3/300/300.jpg",
            description = "探索内心世界的抽象表达"
        ),
        ExploreItem(
            id = "4",
            title = "诗集《月光》",
            author = "作家小雨",
            category = "文学",
            imageUrl = "https://picsum.photos/seed/explore4/300/300.jpg",
            description = "关于生活与爱情的诗歌集"
        ),
        ExploreItem(
            id = "5",
            title = "咖啡时光",
            author = "生活家阿明",
            category = "生活",
            imageUrl = "https://picsum.photos/seed/explore5/300/300.jpg",
            description = "品味生活中的咖啡文化"
        ),
        ExploreItem(
            id = "6",
            title = "自然之声",
            author = "音乐人小林",
            category = "音乐",
            imageUrl = "https://picsum.photos/seed/explore6/300/300.jpg",
            description = "融合自然元素的音乐创作"
        )
    )
    
    val connections = listOf(
        Connection(
            id = "1",
            name = "贝贝",
            username = "beibei",
            bio = "热爱生活，喜欢分享美好瞬间",
            avatarUrl = "https://picsum.photos/seed/conn1/200/200.jpg",
            isFollowing = true,
            isFollower = true
        ),
        Connection(
            id = "2",
            name = "音乐人小王",
            username = "music_wang",
            bio = "独立音乐人，创作属于自己的声音",
            avatarUrl = "https://picsum.photos/seed/conn2/200/200.jpg",
            isFollowing = true,
            isFollower = false
        ),
        Connection(
            id = "3",
            name = "摄影师李明",
            username = "photographer_li",
            bio = "用镜头记录世界的美好",
            avatarUrl = "https://picsum.photos/seed/conn3/200/200.jpg",
            isFollowing = false,
            isFollower = true
        ),
        Connection(
            id = "4",
            name = "艺术家张华",
            username = "artist_zhang",
            bio = "抽象艺术爱好者，探索内心世界",
            avatarUrl = "https://picsum.photos/seed/conn4/200/200.jpg",
            isFollowing = false,
            isFollower = false
        )
    )
    
    val userProfile = UserProfile(
        id = "current_user",
        name = "WhisperCove用户",
        username = "whisper_user",
        bio = "发现内心的声音，分享生活的美好。在这里，每个人都可以找到属于自己的表达方式。",
        avatarUrl = "https://picsum.photos/seed/profile/200/200.jpg",
        coverImageUrl = "https://picsum.photos/seed/cover/800/400.jpg",
        postsCount = 12,
        followingCount = 48,
        followersCount = 126
    )
    
    val userPosts = listOf<Any>(
        "Post 1",
        "Post 2",
        "Post 3"
    )
}