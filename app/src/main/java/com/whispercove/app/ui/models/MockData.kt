package com.whispercove.app.ui.models

object MockData {
    // 信件数据
    val letters = listOf(
        Letter(
            id = "1",
            senderId = "user1",
            senderName = "贝贝",
            senderAvatar = "https://picsum.photos/seed/user1/200/200.jpg",
            content = "今天的心情像蓝天一样清澈，希望能把这份美好分享给每一个人。",
            treeHoleType = TreeHoleType.EMO,
            letterPaperType = LetterPaperType.CLASSIC,
            waxSealType = WaxSealType.HEART,
            timestamp = "5分钟前",
            mood = "快乐",
            isSealed = false
        ),
        Letter(
            id = "2",
            senderId = "user2",
            senderName = "小王",
            senderAvatar = "https://picsum.photos/seed/user2/200/200.jpg",
            content = "深夜的城市总是特别安静，这种宁静让人感到安心。",
            treeHoleType = TreeHoleType.FOODIE,
            letterPaperType = LetterPaperType.VINTAGE,
            waxSealType = WaxSealType.STAR,
            timestamp = "15分钟前",
            mood = "平静",
            isSealed = false
        ),
        Letter(
            id = "3",
            senderId = "user3",
            senderName = "摄影师李明",
            senderAvatar = "https://picsum.photos/seed/user3/200/200.jpg",
            content = "捕捉到了这组城市夜景，每一张照片背后都有一个故事。摄影不仅是记录，更是情感的传递。",
            treeHoleType = TreeHoleType.STUDY,
            letterPaperType = LetterPaperType.VINTAGE,
            waxSealType = WaxSealType.STAR,
            timestamp = "1天前",
            mood = "干饭快乐",
            isSealed = false
        ),
        Letter(
            id = "4",
            senderId = "user4",
            senderName = "匿名海螺",
            senderAvatar = "https://picsum.photos/seed/user4/200/200.jpg",
            content = "", // 未拆信件不显示内容
            treeHoleType = TreeHoleType.EMO,
            letterPaperType = LetterPaperType.CLASSIC,
            waxSealType = WaxSealType.HEART,
            timestamp = "刚刚",
            mood = "",
            isSealed = true // 未拆信件
        ),
        Letter(
            id = "5",
            senderId = "user5",
            senderName = "漂流瓶",
            senderAvatar = "https://picsum.photos/seed/user5/200/200.jpg",
            content = "",
            treeHoleType = TreeHoleType.FOODIE,
            letterPaperType = LetterPaperType.CLASSIC,
            waxSealType = WaxSealType.HEART,
            timestamp = "10分钟前",
            mood = "",
            isSealed = true
        ),
        Letter(
            id = "6",
            senderId = "user6",
            senderName = "匿名海螺",
            senderAvatar = "https://picsum.photos/seed/user6/200/200.jpg",
            content = "",
            treeHoleType = TreeHoleType.STUDY,
            letterPaperType = LetterPaperType.CLASSIC,
            waxSealType = WaxSealType.HEART,
            timestamp = "30分钟前",
            mood = "",
            isSealed = true
        ),
        Letter(
            id = "7",
            senderId = "user7",
            senderName = "漂流瓶",
            senderAvatar = "https://picsum.photos/seed/user7/200/200.jpg",
            content = "",
            treeHoleType = TreeHoleType.EMO,
            letterPaperType = LetterPaperType.CLASSIC,
            waxSealType = WaxSealType.HEART,
            timestamp = "1小时前",
            mood = "",
            isSealed = true
        ),
        Letter(
            id = "8",
            senderId = "user8",
            senderName = "匿名海螺",
            senderAvatar = "https://picsum.photos/seed/user8/200/200.jpg",
            content = "",
            treeHoleType = TreeHoleType.FOODIE,
            letterPaperType = LetterPaperType.CLASSIC,
            waxSealType = WaxSealType.HEART,
            timestamp = "2小时前",
            mood = "",
            isSealed = true
        )
    )
    
    // 树洞数据
    val treeHoles = listOf(
        TreeHole(
            id = "tree1",
            name = "老橡树",
            description = "这棵老橡树已经在这里生长了百年，见证了无数人的秘密",
            location = "城市公园深处",
            letterCount = 156,
            imageUrl = "https://picsum.photos/seed/tree1/300/200.jpg"
        ),
        TreeHole(
            id = "tree2",
            name = "樱花树",
            description = "春天开满粉色樱花，是年轻人最喜欢倾诉的地方",
            location = "大学校园",
            letterCount = 89,
            imageUrl = "https://picsum.photos/seed/tree2/300/200.jpg"
        ),
        TreeHole(
            id = "tree3",
            name = "海边古树",
            description = "海风常年吹拂，树干上刻满了海誓山盟",
            location = "海边悬崖",
            letterCount = 234,
            imageUrl = "https://picsum.photos/seed/tree3/300/200.jpg"
        )
    )
    
    // 漂流瓶数据
    val driftingBottles = listOf(
        DriftingBottle(
            id = "bottle1",
            message = "希望看到这封信的你，今天也能感受到阳光的温暖",
            origin = "遥远的南方",
            journeyDays = 15,
            isPickedUp = false,
            mood = "温暖"
        ),
        DriftingBottle(
            id = "bottle2",
            message = "我在这里留下一个愿望，希望它能漂流到需要它的人手中",
            origin = "东海之滨",
            journeyDays = 32,
            isPickedUp = true,
            mood = "期待"
        ),
        DriftingBottle(
            id = "bottle3",
            message = "有些话说不出口，但希望你能明白",
            origin = "未知",
            journeyDays = 7,
            isPickedUp = false,
            mood = "思念"
        )
    )
    
    // 连接数据
    val connections = listOf(
        Connection(
            id = "1",
            userId = "current_user",
            connectedUserId = "user1",
            connectedUserName = "贝贝",
            connectedUserAvatar = "https://picsum.photos/seed/conn1/200/200.jpg",
            connectedUserBio = "热爱生活，喜欢分享美好瞬间",
            isFollowing = true,
            isFollower = true,
            connectionStartTime = System.currentTimeMillis() - 86400000 * 30 // 30天前
        ),
        Connection(
            id = "2",
            userId = "current_user",
            connectedUserId = "user2",
            connectedUserName = "音乐人小王",
            connectedUserAvatar = "https://picsum.photos/seed/conn2/200/200.jpg",
            connectedUserBio = "独立音乐人，创作属于自己的声音",
            isFollowing = true,
            isFollower = false,
            connectionStartTime = System.currentTimeMillis() - 86400000 * 15 // 15天前
        ),
        Connection(
            id = "3",
            userId = "current_user",
            connectedUserId = "user3",
            connectedUserName = "摄影师李明",
            connectedUserAvatar = "https://picsum.photos/seed/conn3/200/200.jpg",
            connectedUserBio = "用镜头记录世界的美好",
            isFollowing = false,
            isFollower = true,
            connectionStartTime = System.currentTimeMillis() - 86400000 * 7 // 7天前
        ),
        Connection(
            id = "4",
            userId = "current_user",
            connectedUserId = "user4",
            connectedUserName = "艺术家张华",
            connectedUserAvatar = "https://picsum.photos/seed/conn4/200/200.jpg",
            connectedUserBio = "抽象艺术爱好者，探索内心世界",
            isFollowing = false,
            isFollower = false,
            connectionStartTime = System.currentTimeMillis() - 86400000 * 3 // 3天前
        )
    )
    
    // 用户档案数据
    val userProfile = UserProfile(
        id = "current_user",
        name = "WhisperCove用户",
        avatar = "https://picsum.photos/seed/profile/200/200.jpg",
        bio = "发现内心的声音，分享生活的美好。在这里，每个人都可以找到属于自己的表达方式。",
        totalLettersSent = 12,
        totalLettersReceived = 24,
        totalReplies = 18,
        collectionBoxes = listOf(
            CollectionBox(
                id = "box1",
                boxType = CollectionBoxType.SPECIAL,
                letters = listOf(
                    CollectedLetter(
                        id = "collected1",
                        originalLetterId = "1",
                        letter = letters.first(),
                        collectionNote = "特别有感触的一封信",
                        collectedAt = System.currentTimeMillis() - 86400000 * 2
                    )
                ),
                isPublic = false,
                createdAt = System.currentTimeMillis() - 86400000 * 7 // 7天前创建
            ),
            CollectionBox(
                id = "box2",
                boxType = CollectionBoxType.EMO,
                letters = listOf(
                    CollectedLetter(
                        id = "collected2",
                        originalLetterId = "2",
                        letter = letters[1],
                        collectionNote = "心情低落时的一束光",
                        collectedAt = System.currentTimeMillis() - 86400000 * 5
                    )
                ),
                isPublic = false,
                createdAt = System.currentTimeMillis() - 86400000 * 14 // 14天前创建
            ),
            CollectionBox(
                id = "box3",
                boxType = CollectionBoxType.FOODIE,
                letters = emptyList(), // 暂时为空
                isPublic = false,
                createdAt = System.currentTimeMillis() - 86400000 * 21 // 21天前创建
            )
        ),
        preferredWaxSeal = WaxSealType.HEART,
        preferredLetterPaper = LetterPaperType.CLASSIC,
        joinDate = System.currentTimeMillis() - 86400000 * 30 // 30天前加入
    )
}