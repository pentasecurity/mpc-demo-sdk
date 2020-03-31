   
- - -
   
# Penta MPC SDK
![version](https://img.shields.io/badge/version-1.0.0-blue)
<br>
펜타시큐리티의 MPC 데모 프로그램 제작을 위한 SDK를 제공합니다.
<br>
<br>
영문 문서는 다음 링크를 통해 확인해주세요.
<br>
영문 문서 : <https://github.com/pentasecurity/mpc-demo-sdk/blob/master/README.md>
   
- - -
   
# 목차
1. [시작하며](#시작하며)

* [MPC란](#mpc란)

* [Penta MPC SDK 목적](#penta-mpc-sdk-목적)

2. [요구 사항](#요구-사항)

* [구동 조건](#구동-조건)

* [데모 제약 사항](#데모-제약-사항)

* [상업용 버전의 SDK 사용](#상업용-버전의-sdk-사용)

3. [SDK 사용](#sdk-사용)

* [디렉토리 구조](#디렉토리-구조)

* [프로그램 테스트 방법](#프로그램-테스트-방법)

* [제공되는 인터페이스](#제공되는-인터페이스)

4. [연락처](#연락처)

* [이슈 제보 또는 사용에 대한 문의](#이슈-제보-또는-사용에-대한-문의)

* [상업적인 문의](#상업적인-문의)

5. [주의사항](#주의사항)
   
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
   
# SDK 사용

MPC SDK JAR(Java Archive)와 Native module로 구성 되어 있습니다.
<br><br>
Jar 파일은 CLASSPATH 환경변수에 추가 하거나 java의 -cp 옵션에 추가 하여야 합니다.
<br><br>
Native Module은 각 OS의 라이브러리 PATH에 추가하거나 java의 -Djava.library.path 로 지정해야 합니다.
<br>
Native module은 각 OS를 확인 하여 해당 tar.gz을 풀어서 사용해야 합니다.
<br>
* 예제)
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
┬ MPCDemo             : MPC Demo 
│   ├ bin             : Start Script
│   ├ src             : Demo source
│   └ build.gradle    : gradle script
└ MPCSdk              : PentaMPC SDK
    ├ native          : PentaMPC native modules
    ├ mpc-sdk-1.0-SNAPSHOT-javadoc.jar  : PentaMPC javadoc
    ├ mpc-sdk-1.0-SNAPSHOT.jar          : PentaMPC Java Archive
    └ pcw-common-1.6.2-SNAPSHOT.jar     : PentaMPC Common Java Archive
```

## 프로그램 테스트 방법

Demo 프로그램은 PentaMPC SDK를 이용하여 MPC 기능을 테스트해 볼수 있도록 구성되어 있습니다.

Demo 프로그램은 MPC Group 생성시 각 Member에게 생성되는 Key 쌍을 HashMap으로만 관리하기 때문에
프로그램의 재시작 시 키가 보존 되지 않습니다.

실 업무에 적용 하기 위해서는 Demo 프로그램에서 생성되는 Key 쌍을 DB등의 별도 스토리지에 보관하여야 합니다.

* 다운로드
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
* 빌드 및 테스트
    * 빌드
    ```
    /home/mpc/mpc-demo-sdk/MPCDemo> gradle build
    BUILD SUCCESSFUL in 1s
    2 actionable tasks: 2 up-to-date
    /home/mpc/mpc-demo-sdk/MPCDemo> chmod +x bin/MPCDemo.sh
    /home/mpc/mpc-demo-sdk/MPCDemo> gradle copyRelease
    /home/mpc/mpc-demo-sdk/MPCDemo> cd build/release
    /home/mpc/mpc-demo-sdk/MPCDemo/build/release> ls
    MPCDemo-1.0.jar  MPCDemo.bat  MPCDemo.sh  lib
    ```
    * Demo 실행을 위한 CustomerID 받기
    
    http://localhost:18080/join 에 접속 해서 가입을 하면 CustomerID를 메일로 받을 수 있습니다.

    * 실행 및 Member 계정 생성
    
    데모프로그램 실행 시 -c 옵션에 메일로 받은 CustomerID를 주어야 합니다. 
    
    아래 예제는 CustomerID가 00000000-0000-0000-0000-000000000000이고, member1, member2, member3 의 3개의 Member로 테스트 하는 예제 입니다. 
    
    * member 생성
    ```
    /home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh -c 00000000-0000-0000-0000-000000000000
      1. Login
      2. Create member
      9. Exit
    Select Menu.  (1,2,9) : 2
    Please enter ID : member1
    Please enter Name : MEMBER1
    Please enter Password:
    Please enter your password again:
      1. Login
      2. Create member
      9. Exit
    Select Menu.  (1,2,9) : 2
    Please enter ID : member2
    Please enter Name : MEMBER2
    Please enter Password:
    Please enter your password again:
      1. Login
      2. Create member
      9. Exit
    Select Menu.  (1,2,9) : 2
    Please enter ID : member3
    Please enter Name : MEMBER3
    Please enter Password:
    Please enter your password again:
      1. Login
      2. Create member
      9. Exit
    Select Menu.  (1,2,9) :
    ```
    * PentaMPC Server Login
    ```
    Please enter your password again:
      1. Login
      2. Create member
      9. Exit
    Select Menu.  (1,2,9) : 1
    Member ID : member1
    Password:
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) :
    ```
    * 로그인은  -m과 -p로 할 수도 있습니다.
    ```
    /home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh -m member1 -p password -c 00000000-0000-0000-0000-000000000000
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) :    
    ```
    *  MPC Group 생성 발의

    MPC Group은 2 ~ 10의 Member의 참여로 생성합니다. 아래 예는 3명이 참여하고, 서명시 2명의 참여가 필요한(이 후 <b>Threshold</b>) MPC Group를 생성하는 예입니다.
    ```
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) : 1
      1. MPC Group Participation Request
      2. Join in the creation of MPC Group
      8. Previous Menu
      9. Exit
    Select Menu.  (1,2,8,9) : 1
    list of MemberIDs. (separator is comma(,)) : member2,member3
    number of members required for signing : 2
    name : MPC Group for testing
    comment : testing...
    [createGroup] Session ID: 27f59c84-f1f1-4fb9-a0c2-16ab6e89db89
    Step: -1
    ```
    참여 member는 발의자인 member1과 member2, member3의 3개의 member가 참여 하고, 서명시 최소 2member가 참여해야하는 MPC Group을 발의 했습니다.
    발의를 하게되면 Session ID를 받을 수 있습니다. 
    
    여기서는 이 session id를 다른 참여자의 MPCDemo 에 수작업으로 전달 하였습니다. 실 적용 시 Push등을 이용하여 App 에게 바로 전달 하도록 구현 할 수 있습니다.  

    * MPC Group 생성에 참여 
    
    참여자는 member2와 member3이며 방식은 동일 하기 때문에 본 예제는 member2 만 설명 합니다.
    ```
    Please enter your password again:
      1. Login
      2. Create member
      9. Exit
    Select Menu.  (1,2,9) : 1
    Member ID : member2
    Password:
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) : 1
      1. MPC Group Participation Request
      2. Join in the creation of MPC Group
      8. Previous Menu
      9. Exit
    Select Menu.  (1,2,8,9) : 2
    Please enter Session ID : 27f59c84-f1f1-4fb9-a0c2-16ab6e89db89
    ```
    Session 이 생성되면 지정된 시간([createGroup](https://pentasecurity.github.io/mpc-demo-sdk/com/pentasecurity/mpc/CreateGroup.html)의 파라미터로 지정) 
    까지 완료되어야 하며 시간이 지나면 Timeout으로 Session은 자동으로 취소 처리 됩니다.(MPCDemo 에서는 5분으로 설정되어 있음)
    
    참여자는 MPC Group 생성에 거부 할 수 있으며 거부 시 MPC Group 생성은 취소 됩니다.
    생성이 완료되면 MPC Group ID, Member의 Group Index, MPC Group Public key, 개인키 쌍 및 Secret 값이 출력 됩니다.
    
    MPC Group의 비밀키는 MPC의 특성상 알 수 없고, 서버에도 저장되지 않습니다. 서명 시 각 개인이 받은 정보로 협업을 해서 서명이 이루어 집니다.
    
    MPC Group 생성은 발의(Step -1) 부터 완료(Step 3)까지 5단계의 Step을 진행하게 됩니다. 
    ```
    Do you join this Session? ([A]PPROVE, [R]EJECT) : a
    Step: -1
    Step: 0
    Step: 1
    Step: 2
    Step: 3
    MPC Group ID: 27f59c84-f1f1-4fb9-a0c2-16ab6e89db89
    Member Index: 1
    Group PubKey: 0x02d58fb448606b5a53f6498999f9cbc3363c432551a68529a1168fe3b9a79e7728
    Member PriKey: 0x76c2424a0592ee7ec05d0c32d17977971635fe73e0bd134ff78a6a4e06404387
    Member PubKey: 0x0215e0c7ee5fae92c90850e64fee74ee3eca43160834c1a0c236d20ad2d408043f
    Member Secret: 0x4ef8b2664bf8406867c42a6ac19e422480e62579af1e74956da3cad5d5770bed
      1. MPC Group Participation Request
      2. Join in the creation of MPC Group
      8. Previous Menu
      9. Exit
    Select Menu.  (1,2,8,9) : 8
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) : 5
    ================================================================================================
    MPC Group ID         : 27f59c84-f1f1-4fb9-a0c2-16ab6e89db89
    MPC Group Name       : dd
    Owner                : member1
    Threshold            : 2
    Group Size           : 0
    MPC Group PubKey     : 0x02d58fb448606b5a53f6498999f9cbc3363c432551a68529a1168fe3b9a79e7728
    My PubKey            : 0x0215e0c7ee5fae92c90850e64fee74ee3eca43160834c1a0c236d20ad2d408043f
    My PriKey            : 0x76c2424a0592ee7ec05d0c32d17977971635fe73e0bd134ff78a6a4e06404387
    My Secret            : 0x4ef8b2664bf8406867c42a6ac19e422480e62579af1e74956da3cad5d5770bed
    MemberID : member1    Index: 0
    MemberID : member2    Index: 0
    MemberID : member3    Index: 1
    ================================================================================================
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) :
    ```
    * 서명 발의
    
    MPC Group이 생성되었으면 이제 서명을 진행 할 수 있습니다.
    서명을 위한 참여는 최소한 MPC Group 생성 시 지정한 Threshold 만큼의 Member 참여가 필요 합니다. 
    
    따라서 위 예저에서 member1, member2, member3로 Member 크기는 3이고, Threshold가 2이므로 서명시 Threshold는 2~3을 지정 할 수 있습니다.
    
    Threshold에 2를 주면 member 하나가 거부를 하더라도 서명이 가능합니다. 모든 참여자의 참여를 원한다면 Threshold를 3으로 해서 모든 Member가 참여 해야만 서명이 되도록 할 수 있습니다.  
    ```
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) : 2
      1. Signing Participation Request
      2. Join in the Signing
      8. Previous Menu
      9. Exit
    Select Menu.  (1,2,8,9) : 1
    Message : Message to sign.
    MPC Group ID : 27f59c84-f1f1-4fb9-a0c2-16ab6e89db89
    Threshold : [2]
    comment : comment..
    [Signing] Session ID: a0dd096f-eb1b-460d-ba2a-cfae2015faeb
    Step: -1
    ```
    member3는 받은 Session ID를 이용하여 Session에 참여를 합니다.

    참여자는 Group의 Member 중 아무나 참여가 가능하고, MPC Group 생성과 같이 거부할 수 있습니다.
    하지만 MPC Group 생성과 다르게 한 Member가 거부하더라도 참여 승인을 하는 Member 수가 Threshold 만큼만 있으면 서명을 위한 협업이 수행 됩니다.
    
    서명은 발의(Step -1) 부터 완료(Step 5)까지 7 단계의 Step을 진행하게 됩니다. 
    
    ```    
      1. Creation of MPC Group
      2. Signing
      3. Member List
      4. Update AccessToken
      5. My MPC Group
      9. Exit
    Select Menu.  (1,2,3,4,5,9) : 2
      1. Signing Participation Request
      2. Join in the Signing
      8. Previous Menu
      9. Exit
    Select Menu.  (1,2,8,9) : 2
    Please enter Session ID : a0dd096f-eb1b-460d-ba2a-cfae2015faeb
    Do you join this Session? ([A]PPROVE, [R]EJECT) : a
    Step: -1
    Step: 0
    Step: 1
    Step: 2
    Step: 3
    Step: 4
    Step: 5
    Sigr: 0x18d6f655b73e9d5daeec915ff0e377321b280de4d2b13b9ab0e2ce6ca041ec78
    Sigs: 0x349962293acc3ec8f266044c22779e865f3ee8b8f57f9545ad6f19ef76e7ab3d
    Sigrecovery: 0
      1. Signing Participation Request
      2. Join in the Signing
      8. Previous Menu
      9. Exit
    Select Menu.  (1,2,8,9) :
    ```

## 제공되는 인터페이스

인터페이스는 다음 링크를 통해 확인할 수 있습니다.
<br>
인터페이스 문서 : <https://pentasecurity.github.io/mpc-demo-sdk/>
   
- - -
   
# 연락처

## 이슈 제보 또는 사용에 대한 문의

Penta MPC SDK를 통한 테스트 도중 비정상적인 동작이 확인되거나 문의사항이 발생하는 경우,
<br>
아래의 주소로 연락을 부탁드립니다.

* E-Mail
```
mpc@pentasecurity.com
```

## 상업적인 문의

Penta MPC SDK 사용을 통한 Penta MPC 기술의 정식 도입을 상업적으로 검토하는 경우,
<br>
아래의 주소로 연락을 부탁드립니다.

* E-Mail
```
mpc@pentasecurity.com
```
   
- - -
   
# 주의사항
다음의 주의사항을 꼭 숙지해 주시기 바랍니다.
* 깃헙을 통해 제공되는 Penta MPC SDK는 비상업적 데모 프로그램의 테스트를 위한 것입니다.
* 데모 프로그램의 테스트는 정상적인 신청 작성을 접수해야 진행할 수 있습니다.
* MPC 사용 데이터는 주기적으로 초기화 되며, 원격 서버의 업데이트/수정에 따라 비정기적으로 초기화 될 수 있습니다.
* Penta MPC SDK의 상업적인 사용 또는 제약 없는 사용을 위해서는 아래의 주소로 연락을 부탁드립니다.
```
mpc@pentasecurity.com
```
   
- - -
   
   
