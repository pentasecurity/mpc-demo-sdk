# Penta MPC SDK
![version](https://img.shields.io/badge/version-1.0.0-blue)
<br>
펜타시큐리티의 MPC 데모 프로그램 제작을 위한 SDK를 제공합니다.

# 시작하며

## MPC 란?

Secure Multi Party Computation의 약자로, 다수의 사용자가 각자의 비밀 값을 통해 함께 연산을 진행하는 기술입니다.
<br><br>
펜타시큐리티에서 제공하는 MPC 기술은 'Secure Multi Party Computation based Threshold Signature Scheme' 로,
<br>
Threshold 기반의 서명 관련 기술을 의미합니다.
<br><br>
펜타시큐리티의 MPC 기술은 다음과 같은 특징을 가집니다.

* 사용자 자신의 비밀값이 노출되지 않으면서 다수 사용자의 합의에 따라 연산의 진행 여부를 결정합니다.
* 한개의 공개키를 생성하기 위해 M개의 비밀키를 필요로 하며,<br>M개의 비밀키 중 N개의 비밀키를 사용하여 전자서명을 생성합니다. (N ≤ M)
* 키 생성 및 서명 과정에서 완전한 개인키가 존재하지 않습니다.

## Penta MPC SDK 목적

펜타시큐리티의 MPC SDK는 다음의 두가지 MPC 관련 기능을 사용하는 데모 프로그램을 제작, 테스트 하기위해 제공되었습니다.

* Create MPC Group
```
"MPC 그룹"을 생성합니다.
"MPC 그룹"은 MPC 참여자인 "멤버"가 모여 이루어지는 MPC 기능 사용을 위한 집단입니다.
```
* MPC Signing
```
"MPC 서명"을 진행합니다.
"MPC 서명"은 "MPC 그룹" 내 과반 수 이상의 "멤버"들의 동의로 진행됩니다.
```

# 요구 사항

## 구동 조건

지원 OS
* Windows
* Unix / Linux
* Android
* iOS

구동 환경
* JDK 1.8 

## 데모 제약 사항

펜타시큐리티에서 제공하는 MPC SDK는 MPC 기술의 데모 테스트를 목적으로 하며,
<br>
다음과 같은 제한에 따라 2-of-3 테스트 환경을 제공합니다.
* "MPC 그룹" 내 "멤버" 수는 3명으로 제한합니다.
* "MPC 그룹" 의 "Threshold" 값은 2로 제한합니다.

## 정식 버전의 SDK 사용

데모 버전의 제약 사항 외 조건에서 테스트를 진행하길 원하거나,
<br>
Penta MPC SDK 사용을 통한 Penta MPC 기술의 정식 도입을 상업적으로 검토하는 경우,
<br>
아래의 주소로 연락을 부탁드립니다.

* E-Mail
```
bni.bd@pentasecurity.com
```
* Phone Number
```
02-2125-xxxx
010-xxxx-xxxx
```

# Use SDK

- MPC SDK JAR(Java Archive)와 Native module로 구성 되어 있습니다.
- Jar 파일은 CLASSPATH 환경변수에 추가 하거나 java의 -cp 옵션에 추가 하여야 합니다.
- Native Module은 각 OS의 라이브러리 PATH에 추가하거나 java의 -Djava.library.path 로 지정해야 합니다. <br>
  Native module은 각 OS를 확인 하여 해당 tar.gz을 풀어서 사용해야 합니다.
ex)
<pre> 
 $ ls /opt/pentampc
 native                              mpc-sdk-1.0-SNAPSHOT.jar          
 mpc-sdk-1.0-SNAPSHOT-javadoc.jar    pcw-common-1.6.2-SNAPSHOT.jar
 $ ls /opt/pentampc/native
 libPenta_MPC-0.2.0.0211.e8c9.android.x86.tar.gz          libPenta_MPC-0.2.0.0211.e8c9.Darwin.tar.gz
 libPenta_MPC-0.2.0.0211.e8c9.android.x86_64.tar.gz       libPenta_MPC-0.2.0.0211.e8c9.android.arm64-v8a.tar.gz    
 libPenta_MPC-0.2.0.0211.e8c9.ios.tar.gz                  libPenta_MPC-0.2.0.0211.e8c9.android.armeabi-v7a.tar.gz  
 libPenta_MPC-0.2.0.0211.e8c9.linux.x64.tar.gz            libPenta_MPC-0.2.0.0211.e8c9.linux.x64.so
 libPenta_MPC.so
 $ java -cp /opt/pentampc/mpc-sdk-1.0-SNAPSHOT.jar:/opt/pentampc/pcw-common-1.6.2-SNAPSHOT.jar:. -Djava.library.path=/opt/pentampc/native com.mpcdemo
</pre>

## 디렉토리 구조
```
┬ MPCDemo : MPC Demo 
│   ├ bin : Start Script
│   ├ src : Demo source
│   └ build.gradle : gradle script
└ MPCSdk : PentaMPC SDK
    ├ native : PentaMPC native modules
    ├ mpc-sdk-1.0-SNAPSHOT-javadoc.jar : PentaMPC javadoc
    ├ mpc-sdk-1.0-SNAPSHOT.jar : PentaMPC Java Archive
    └ pcw-common-1.6.2-SNAPSHOT.jar : PentaMPC Common Java Archive
```

## 프로그램 테스트 방법
- Demo 프로그램은 PentaMPC SDK를 이용하여 MPC 기능을 테스트해 볼수 있도록 구성되어 있습니다.
- Demo 프로그램은 MPC Group 생성시 각 Member에게 생성되는 Key 쌍을 HashMap으로만 관리하기 때문에 프로그램의 재시작 시 키가 보존 되지 않습니다.
  실 업무에 적용 하기 위해서는 Demo 프로그램에서 생성되는 Key 쌍을 DB등의 별도 스토리지에 보관하여야 합니다.
  
- 다운로드
```
/home/mpc> git clone https://github.com/pentasecurity/mpc-demo-sdk.git
Cloning into 'mpc-demo-sdk'...
remote: Enumerating objects: 83, done.
remote: Counting objects: 100% (83/83), done.
remote: Compressing objects: 100% (67/67), done.
remote: Total 83 (delta 16), reused 49 (delta 1), pack-reused 0
Unpacking objects: 100% (83/83), done.
/home/mpc> ls
mpc-demo-sdk
/home/mpc> cd mpc-demo-sdk
/home/mpc/mpc-demo-sdk> ls
LICENSE  MPCDemo  MPCSdk  README.md  build.gradle  gradle  gradlew  gradlew.bat  settings.gradle
/home/mpc/mpc-demo-sdk> cd MPCDemo
```
- 빌드 및 테스트
```
/home/mpc/mpc-demo-sdk/MPCDemo> gradle build
BUILD SUCCESSFUL in 1s
2 actionable tasks: 2 up-to-date
/home/mpc/mpc-demo-sdk/MPCDemo> chmod +x bin/MPCDemo.sh
/home/mpc/mpc-demo-sdk/MPCDemo> gradle copyRelease
/home/mpc/mpc-demo-sdk/MPCDemo> cd build/release
/home/mpc/mpc-demo-sdk/MPCDemo/build/release> ls
MPCDemo-1.0.jar  MPCDemo.bat  MPCDemo.sh  lib
/home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh
Member ID : member1
Password : 
  1. Creation of MPC Group
  2. Signing
  3. Member List
  4. Update AccessToken
  5. My MPC Group
  9. Exit
Select Menu.  (1,2,3,4,5,9) : 
```
## 제공되는 인터페이스

인터페이스는 다음 링크를 참고한다
<br>
인터페이스 문서 : <https://pentasecurity.github.io/mpc-demo-sdk/>

# 연락처

## 이슈 제보 또는 사용에 대한 문의

Penta MPC SDK를 통한 테스트 도중 비정상적인 동작이 확인되거나 문의사항이 발생하는 경우,
<br>
아래의 주소로 연락을 부탁드립니다.

* E-Mail
```
bni.bd@pentasecurity.com
```
* Phone Number
```
02-2125-xxxx
010-xxxx-xxxx
```

## 상업적인 문의

Penta MPC SDK 사용을 통한 Penta MPC 기술의 정식 도입을 상업적으로 검토하는 경우,
<br>
아래의 주소로 연락을 부탁드립니다.

* E-Mail
```
bni.bd@pentasecurity.com
```
* Phone Number
```
02-2125-xxxx
010-xxxx-xxxx
```
