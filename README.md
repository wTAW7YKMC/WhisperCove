# WhisperCove

WhisperCove - Android应用，基于Jetpack Compose的社交分享应用

## 项目简介

WhisperCove是一款基于Android平台的社交分享应用，采用现代化的Jetpack Compose框架开发，提供简洁优雅的用户界面和流畅的交互体验。

## 功能特点

- **底部导航**：采用Material3设计风格的底部导航栏，包含5个主要功能模块
- **主页**：展示用户动态和内容流，支持卡片式布局
- **发现**：探索和发现有趣的内容，分类浏览
- **创建**：创建和发布新内容，支持多媒体
- **连接**：社交功能，关注其他用户，建立社交网络
- **个人资料**：用户个人中心，展示个人信息和发布历史

## 技术栈

- **开发语言**：Kotlin
- **UI框架**：Jetpack Compose
- **设计系统**：Material3
- **架构模式**：MVVM-like架构
- **最低支持版本**：Android 7.0 (API 24)
- **目标版本**：Android 13 (API 33)

## 项目结构

```
app/
├── src/main/
│   ├── java/com/whispercove/app/
│   │   ├── MainActivity.kt          # 主活动
│   │   ├── ui/
│   │   │   ├── components/         # 可复用UI组件
│   │   │   ├── models/            # 数据模型
│   │   │   ├── navigation/        # 导航配置
│   │   │   ├── screens/           # 各页面UI
│   │   │   └── theme/             # 主题和样式
│   │   └── ...
│   ├── res/                       # 资源文件
│   └── AndroidManifest.xml        # 应用配置
└── build.gradle                   # 构建配置
```

## 构建说明

1. 克隆项目到本地
2. 使用Android Studio打开项目
3. 确保已安装Android SDK (API 33)
4. 同步Gradle配置
5. 构建并运行应用

## APK文件

调试版本APK位于：`app/build/outputs/apk/debug/app-debug.apk`

## 已知问题

- 应用在缺少INTERNET权限时会崩溃（已在AndroidManifest.xml中添加相应权限）

## 开发计划

- [ ] 添加用户认证功能
- [ ] 实现后端API集成
- [ ] 添加推送通知
- [ ] 优化性能和内存使用
- [ ] 添加更多社交功能

## 许可证

本项目采用MIT许可证。

## 联系方式

如有问题或建议，请通过GitHub Issues联系。