# WhisperCove Android应用

WhisperCove是一个社交分享应用，让用户能够发现内心的声音，分享生活的美好。

## 功能特点

- **首页**: 浏览和互动用户分享的内容
- **探索**: 发现不同类别的精彩内容
- **创作**: 发布自己的作品和想法
- **连接**: 关注其他用户，建立社交网络
- **个人资料**: 管理个人信息和查看自己的作品

## 技术栈

- **开发语言**: Kotlin
- **UI框架**: Jetpack Compose
- **架构**: MVVM
- **导航**: Navigation Compose
- **图片加载**: Coil
- **最低支持版本**: Android 7.0 (API 24)
- **目标版本**: Android 13 (API 33)

## 项目结构

```
WhisperCove/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/whispercove/app/
│   │       │   ├── MainActivity.kt
│   │       │   ├── ui/
│   │       │   │   ├── components/
│   │       │   │   │   └── WhisperComponents.kt
│   │       │   │   ├── models/
│   │       │   │   │   ├── DataModels.kt
│   │       │   │   │   └── MockData.kt
│   │       │   │   ├── navigation/
│   │       │   │   │   └── Navigation.kt
│   │       │   │   ├── screens/
│   │       │   │   │   ├── HomeScreen.kt
│   │       │   │   │   ├── ExploreScreen.kt
│   │       │   │   │   ├── CreateScreen.kt
│   │       │   │   │   ├── ConnectScreen.kt
│   │       │   │   │   └── ProfileScreen.kt
│   │       │   │   └── theme/
│   │       │   │       ├── Theme.kt
│   │       │   │       └── Type.kt
│   │       ├── res/
│   │       │   ├── drawable/
│   │       │   ├── layout/
│   │       │   ├── values/
│   │       │   └── ...
│   │       └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

## 构建和安装

### 前提条件

- Android Studio Arctic Fox 或更高版本
- JDK 8 或更高版本
- Android SDK (API 24-33)

### 构建步骤

1. 克隆或下载项目到本地
2. 使用Android Studio打开项目
3. 等待Gradle同步完成
4. 连接Android设备或启动模拟器
5. 点击运行按钮或使用以下命令构建APK

#### 使用命令行构建

在Windows系统上，可以运行提供的构建脚本：

```bash
build-apk.bat
```

或者手动执行：

```bash
# 构建调试版本
gradlew assembleDebug

# 构建发布版本（需要签名配置）
gradlew assembleRelease
```

### 安装APK

调试版本APK位置：`app/build/outputs/apk/debug/app-debug.apk`

发布版本APK位置：`app/build/outputs/apk/release/app-release.apk`

## 应用截图

TODO: 添加应用截图

## 设计理念

WhisperCove的设计灵感来源于现代简约风格，采用深色主题为主，搭配明亮的强调色，营造出温馨而神秘的氛围。界面设计参考了简历网站的简洁风格，但增加了更多的交互元素和动画效果，使用户体验更加流畅自然。

## 贡献

欢迎提交问题和改进建议！

## 许可证

MIT License

## 更新日志

### v1.0.0 (2023-12-21)

- 初始版本发布
- 实现基本功能：首页、探索、创作、连接和个人资料
- 支持内容发布和互动
- 实现用户关注系统