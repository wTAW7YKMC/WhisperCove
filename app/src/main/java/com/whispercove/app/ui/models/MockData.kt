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
            shares = 3,
            isOpened = true, // 已拆盲盒
            mood = "喜悦" // 情绪标签
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
            shares = 15,
            isOpened = true,
            mood = "兴奋"
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
            shares = 18,
            isOpened = true,
            mood = "平静"
        ),
        WhisperPost(
            id = "4",
            userId = "user4",
            userName = "匿名海螺",
            userAvatar = "https://picsum.photos/seed/user4/200/200.jpg",
            content = "", // 未拆盲盒不显示内容
            timestamp = "刚刚",
            likes = 0,
            comments = 0,
            shares = 0,
            isOpened = false, // 未拆盲盒
            mood = "" // 未拆盲盒不显示情绪标签
        ),
        WhisperPost(
            id = "5",
            userId = "user5",
            userName = "漂流瓶",
            userAvatar = "https://picsum.photos/seed/user5/200/200.jpg",
            content = "",
            timestamp = "10分钟前",
            likes = 0,
            comments = 0,
            shares = 0,
            isOpened = false,
            mood = ""
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
    
    val userPosts = listOf(
        WhisperPost(
            id = "user_post_1",
            userId = "current_user",
            userName = "WhisperCove用户",
            userAvatar = "https://picsum.photos/seed/profile/200/200.jpg",
            content = "今天的夕阳特别美，海边的风轻轻吹过，带来了远方的思念。",
            imageUrl = "https://picsum.photos/seed/userpost1/400/300.jpg",
            timestamp = "3小时前",
            likes = 24,
            comments = 5,
            shares = 2,
            isOpened = true,
            mood = "平静"
        ),
        WhisperPost(
            id = "user_post_2",
            userId = "current_user",
            userName = "WhisperCove用户",
            userAvatar = "https://picsum.photos/seed/profile/200/200.jpg",
            content = "分享一首最近很喜欢的歌，旋律总能让我想起那些美好的回忆。",
            imageUrl = "https://picsum.photos/seed/userpost2/400/300.jpg",
            timestamp = "1天前",
            likes = 18,
            comments = 3,
            shares = 1,
            isOpened = true,
            mood = "喜悦"
        ),
        WhisperPost(
            id = "user_post_3",
            userId = "current_user",
            userName = "WhisperCove用户",
            userAvatar = "https://picsum.photos/seed/profile/200/200.jpg",
            content = "咖啡店的角落，阳光透过窗户洒在书页上，这样的午后让人感到宁静。",
            imageUrl = "https://picsum.photos/seed/userpost3/400/300.jpg",
            timestamp = "2天前",
            likes = 32,
            comments = 7,
            shares = 4,
            isOpened = true,
            mood = "思考"
        ),
        WhisperPost(
            id = "user_post_4",
            userId = "current_user",
            userName = "WhisperCove用户",
            userAvatar = "https://picsum.photos/seed/profile/200/200.jpg",
            content = "期待周末的旅行，新的地方总是能带来新的灵感。",
            imageUrl = "https://picsum.photos/seed/userpost4/400/300.jpg",
            timestamp = "3天前",
            likes = 15,
            comments = 2,
            shares = 0,
            isOpened = true,
            mood = "期待"
        ),
        WhisperPost(
            id = "user_post_5",
            userId = "current_user",
            userName = "WhisperCove用户",
            userAvatar = "https://picsum.photos/seed/profile/200/200.jpg",
            content = "今天尝试了新的菜谱，虽然过程有些手忙脚乱，但结果还是很满意的。",
            imageUrl = "https://picsum.photos/seed/userpost5/400/300.jpg",
            timestamp = "4天前",
            likes = 28,
            comments = 6,
            shares = 3,
            isOpened = true,
            mood = "喜悦"
        ),
        WhisperPost(
            id = "user_post_6",
            userId = "current_user",
            userName = "WhisperCove用户",
            userAvatar = "https://picsum.photos/seed/profile/200/200.jpg",
            content = "深夜的思绪总是特别清晰，或许这就是我喜欢夜晚的原因。",
            imageUrl = "https://picsum.photos/seed/userpost6/400/300.jpg",
            timestamp = "5天前",
            likes = 21,
            comments = 4,
            shares = 2,
            isOpened = true,
            mood = "思考"
        )
    )
}