# Keystore信息

## 创建Keystore

当需要签名发布版本时，可以使用以下命令创建keystore：

```bash
keytool -genkey -v -keystore keystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias whispercove
```

## 签名配置

在app/build.gradle中配置签名：

```gradle
signingConfigs {
    release {
        storeFile file('keystore.jks')
        storePassword 'your_store_password'
        keyAlias 'whispercove'
        keyPassword 'your_key_password'
    }
}

buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        signingConfig signingConfigs.release
    }
}
```

## 注意事项

1. 请妥善保管keystore文件和密码
2. 不要将keystore文件提交到版本控制系统
3. 建议将keystore文件和密码添加到.gitignore文件中