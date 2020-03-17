   
- - -
   
# Penta MPC SDK
![version](https://img.shields.io/badge/version-1.0.0-blue)
<br>
펜타시큐리티의 MPC 데모 프로그램 제작을 위한 SDK를 제공합니다.
<br>
<br>
한글 문서는 다음 링크를 통해 확인해주세요.
<br>
한글 문서 : <https://github.com/pentasecurity/mpc-demo-sdk/blob/master/README-ko.md>
   
- - -
   
# 목차
1. [시작하며](#시작하며)

* [MPC란](#mpc란)

* [Penta MPC SDK 목적](#penta-mpc-sdk-목적)

2. [요구 사항](#요구-사항)

* [구동 조건](#구동-조건)

* [데모 제약 사항](#데모-제약-사항)

* [상업용 버전의 SDK 사용](#상업용-버전의-sdk-사용)

3. [Using SDK](#using-sdk)

* [Structure of Directory](#structure-of-directory)

* [Testing the Program](#testing-the-program)

* [Provided Interface](#provided-interface)

4. [Contact Information](#contact-information)

* [Inquiries for Questions or Concerns](#inquiries-for-questions-or-concerns)

* [Commercial Inquiry](#commercial-inquiry)

5. [Discretions](#Discretions)
   
- - -
   
# 시작하며

## MPC란

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

펜타시큐리티의 MPC SDK는 다음의 두가지 MPC 관련 기능을 사용하는 비상업적 데모 프로그램을 제작, 테스트 하기위해 제공되었습니다.

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
   
- - -
   
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
다음과 같은 제한에 따라 N-of-M 구성의 테스트 환경을 제공합니다.
```
MPC 기술에서, N-of-M 구성의 의미는 다음과 같습니다.

N = Threshold, MPC 연산을 위한 정족수
M = "MPC 그룹"의 "멤버" 전체 수

따라서, 전체 M 명의 "멤버" 중 N 명의 "멤버"가 MPC 연산에 참여해야 진행이 가능한 구성을 의미합니다.
```

* "MPC 그룹" 내 "멤버" 수는 2명 이상, 10명 이하로 제한합니다. "멤버" 수는 테스트 신청 과정에서 입력합니다.
```
2 ≤ M = "멤버" 수 ≤ 10
```
* "MPC 그룹" 의 "Threshold" 값은 다음과 같이 2 이상, 10 이하, "멤버" 수 이하로 제한합니다.
```
2 ≤ N = Threshold ≤ M = "멤버" 수 ≤ 10
```
* "MPC 그룹" 및 "멤버"의 사용 정보는 한달 단위로 초기화 됩니다.


## 상업용 버전의 SDK 사용

데모 버전의 제약 사항 외 조건에서 테스트를 진행하길 원하거나,
<br>
Penta MPC SDK 사용을 통한 Penta MPC 기술의 정식 도입을 상업적으로 검토하는 경우,
<br>
아래의 주소로 연락을 부탁드립니다.

* E-Mail
```
mpc@pentasecurity.com
```
   
- - -
   
# Using SDK

The SDK is composed into MPC SDK JAR (Java Archive) and Native Module.
<br><br>
JAR file must be added to the CLASSPATH environment variable or java -cp option.
<br><br>
Native Module must be added to the library PATH of each OS or java -Djava.library.path. Native module should be used after checking each OS and the corresponding unpacking tar.gz.
<br>
* e.g.)
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

## Structure of Directory
```
┬ MPCDemo            : MPC Demo 
│   ├ bin            : Start Script
│   ├ src            : Demo source
│   └ build.gradle   : gradle script
└ MPCSdk             : PentaMPC SDK
    ├ native         : PentaMPC native modules
    ├ mpc-sdk-1.0-SNAPSHOT-javadoc.jar    : PentaMPC javadoc
    ├ mpc-sdk-1.0-SNAPSHOT.jar            : PentaMPC Java Archive
    └ pcw-common-1.6.2-SNAPSHOT.jar       : PentaMPC Common Java Archive
```

## Testing the Program
Demo program is configured to test the MPC function by using Penta MPC SDK.
<br><br>
The key is not preserved when the program is rebooted. This is because the in the demo program key pairs that are generated for all members are managed only through HashMap. In order to apply it on another application, operation, etc., the key pairs that are generated on the demo program must be kept in a separate storage, like a database.
<br>  
* Download
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
* Build and Test
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
## Provided Interface

For interface, please refer to the following link.
<br>
Interface document : <https://pentasecurity.github.io/mpc-demo-sdk/>
   
- - -
   
# Contact Information

## Inquiries for Questions or Concerns

If an abnormal behavior is detected during the Penta MPC SDK testing or any questions are brought up, please contact send us an email.

* E-Mail
```
mpc@pentasecurity.com
```

## Commercial Inquiry

To review Penta MPC SDK for commercial use of Penta MPC, please send us an email.

* E-Mail
```
mpc@pentasecurity.com
```
   
- - -
   
# Discretions
Please be aware of the following discretions.
* Penta MPC SDK is provided on Github as a non-commercial test demo program.
* Testing of the demo program is available only after the full application has been submitted completely.
* Data input for MPC usage is reset periodically, and may also be reset on a irregular basis according to update/modification of the remote server.
* For commercial use or unlimited use of Penta MPC SDK, please send us an email.
```
mpc@pentasecurity.com
```
   
- - -
   
   
