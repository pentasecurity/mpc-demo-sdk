   
- - -
   
# Penta MPC SDK
![version](https://img.shields.io/badge/version-1.0.0-blue)
<br>
펜타시큐리티의 MPC 데모 프로그램 제작을 위한 SDK를 제공합니다.
<br>
<br>
영문으로 작성된 문서를 확인하려면 [여기를 클릭하세요](<https://github.com/pentasecurity/mpc-demo-sdk/blob/master/README.md>).
   
- - -
   
# 목차

1. [시작하며](#시작하며)

* [MPC란](#mpc란)

* [Penta MPC SDK 목적](#penta-mpc-sdk-목적)

2. [요구 사항](#요구-사항)

* [구동 조건](#구동-조건)

* [데모 제약 사항](#데모-제약-사항)

* [데모 사용 신청](#데모-사용-신청)

* [상업용 버전의 SDK 사용](#상업용-버전의-sdk-사용)

3. [SDK 사용](#sdk-사용)

* [디렉토리 구조](#디렉토리-구조)

* [인터페이스 목록](#인터페이스-목록)

* [데모 프로그램 셋팅](#데모-프로그램-셋팅)

  * [다운로드](#다운로드)

  * [빌드](#빌드)

  * [실행](#실행)

* [테스트 프로그램 사용](#테스트-프로그램-사용)

  * [Member 발급](#member-발급)
  
  * [Member 로그인](#member-로그인)
  
  * [MPC 그룹 생성 발의](#mpc-그룹-생성-발의)
  
  * [MPC 그룹 참여](#mpc-그룹-참여)
  
  * [MPC 그룹 생성](#mpc-그룹-생성)
  
  * [MPC 서명 발의](#mpc-서명-발의)
  
  * [MPC 서명 참여](#mpc-서명-참여)
  
  * [MPC 서명 생성](#mpc-서명-생성)

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

## 데모 사용 신청

데모 사용에 대한 신청은 [여기를 클릭하여 진행할 수 있습니다.](<https://mpc.pentasecurity.com:18443/join>)
<br>
신청양식에 맞게 내용을 입력하고 신청 버튼을 클릭하면 입력한 메일 주소로 메일이 발송됩니다.
<br>
메일 안내에 따라 메일 주소 인증, Customer ID 발급 확인 절차를 진행하고 나면 정상적으로 데모 프로그램을 사용할 수 있습니다.

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

Penta MPC SDK는 SDK Jar(Java Archive)와 Native module로 구성 되어 있습니다.
<br><br>
Jar 파일은 CLASSPATH 환경변수에 추가 하거나 java의 -cp 옵션에 추가 하여야 합니다.
<br>
Native Module은 각 OS의 라이브러리 PATH에 추가하거나 java의 -Djava.library.path 로 지정해야 합니다.
<br>
Native module은 각 OS를 확인 하여 해당 tar.gz을 풀어서 사용해야 합니다.
<br>
* 예제)
<pre> 
 // MPC Demo를 받습니다.
 $ cd /opt
 $ git clone https://github.com/pentasecurity/mpc-demo-sdk.git
 $ cd mpc-demo-sdk
 $ ls
 LICENSE  MPCDemo  MPCSdk   README-ko.md  README.md         build.gradle  
 docs     gradle   gradlew  gradlew.bat   settings.gradle
 
 // 사용하는 native 라이브러리를 풀어서 사용용합니다.
 $ cd MPCSdk/native/
 $ ls
 libPenta_MPC-0.2.1.0403.35ce.Windows.x64.zip    libPenta_MPC-0.2.1.0403.35ce.darwin.tar.gz
 libPenta_MPC-0.2.1.0403.35ce.linux.x64.tar.gz
 $ gzip -dc libPenta_MPC-0.2.1.0403.35ce.linux.x64.tar.gz | tar xvf -   
 libPenta_MPC.so
 libPenta_MPC_linux_x64_0.2.1.0403.35ce.so
 $ ls
 libPenta_MPC-0.2.1.0403.35ce.Windows.x64.zip   libPenta_MPC.so
 libPenta_MPC-0.2.1.0403.35ce.darwin.tar.gz     libPenta_MPC_linux_x64_0.2.1.0403.35ce.so
 libPenta_MPC-0.2.1.0403.35ce.linux.x64.tar.gz
 
 // Build 합니다.
 $ cd ../..
 $ gradle build
 Starting a Gradle Daemon (subsequent builds will be faster)
 
 BUILD SUCCESSFUL in 8s
 3 actionable tasks: 2 executed, 1 up-to-date
 
 // 사용하는 모듈을 build/release로 복사하여 바로 실행 가능한 형태로 만듭니다. 
 $ gradle copyRelease
 
 BUILD SUCCESSFUL in 1s
 3 actionable tasks: 1 executed, 2 up-to-date
 
 // build 된 모듈을 바로 가능한 형태로 복사한 디렉토리로 이동하여 실행 합니다.
 $ cd MPCDemo/build/release/
 $ ls
 MPCDemo-1.0.jar  MPCDemo.bat  MPCDemo.sh  lib
 $ ./MPCDemo.sh
   1. Login
   2. Create member
   9. Exit
 Select Menu.  (1,2,9) : 
</pre>

## 디렉토리 구조

```
┬ MPCDemo               : MPC Demo 
│   ├ bin/              : Start Script
│   ├ src/              : Demo source
│   └ build.gradle      : gradle script
├ MPCSdk                : PentaMPC SDK
│    ├ native/          : PentaMPC native modules
│    ├ mpc-sdk-1.0-SNAPSHOT-javadoc.jar   : PentaMPC javadoc
│    ├ mpc-sdk-1.0-SNAPSHOT.jar           : PentaMPC Java Archive
│    └ pcw-common-1.6.2-SNAPSHOT.jar      : PentaMPC Common Java Archive
└ docs/                 : MPCSdk Javadoc
```

## 인터페이스 목록

Penta MPC SDK를 통해 제공되는 인터페이스를 확인하려면 [여기를 클릭하세요](<https://pentasecurity.github.io/mpc-demo-sdk/>).

## 데모 프로그램 셋팅

Penta MPC SDK를 사용하는 데모 프로그램을 사용하기 위해서는 아래 내용에서 설명하는 절차가 필요합니다.

### 다운로드

다음과 같이 데모 프로그램을 다운로드 받습니다.
<br>
다운로드가 완료되면 디렉토리 및 구성 파일이 모두 존재하는지 확인합니다.

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

### 빌드

다음과 같이 데모 프로그램을 빌드합니다.
<br>
빌드가 완료되면 디렉토리 및 구성 파일이 모두 존재하는지 확인합니다.

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

### 실행

데모 프로그램을 실행하려는 경우, 데모 사용 신청을 접수하여 메일 인증을 받고 Customer ID를 발급받아야 합니다.
<br>
데모 사용 신청은 [여기를 클릭하여 진행할 수 있습니다.](<https://mpc.pentasecurity.com:18443/join>)
<br>
<br>
데모 프로그램 실행 시 -c 옵션으로 Customer ID를 입력할 수 있습니다.
<br>
데모 사용 신청을 통해 미리 발급받은 유효한 Customer ID를 입력해야 정상적인 실행이 가능합니다.
<br>
* 예제) Customer ID가 00000000-0000-0000-0000-000000000000인 경우 

```
/home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh -c 00000000-0000-0000-0000-000000000000
   1. Login
   2. Create member
   9. Exit
Select Menu.  (1,2,9) : 
```

## 테스트 프로그램 사용

데모 프로그램은 Penta MPC SDK를 이용하여 MPC 기능을 테스트해 볼수 있도록 구성되어 있습니다.
<br>
<br>
데모 프로그램은 MPC Group 생성시 각 Member에게 생성되는 Key 쌍을 HashMap으로만 관리하기 때문에 프로그램의 재시작 시 키가 보존되지 않습니다.
<br>
테스트가 아닌 실사용을 위해서는 Demo 프로그램에서 생성되는 Key 쌍을 DB등의 별도 저장소에 보관하여야 합니다. 
    
### member 발급

Penta MPC 기능을 사용하는 주체인 Member를 발급합니다.
<br>
Member가 모여서 Group을 생성하고, Group 내에서 MPC 서명을 진행할 수 있습니다.
<br>
<br>
* 예제) 아래 제공되는 예제는 Customer ID가 00000000-0000-0000-0000-000000000000이고 Member ID가 member1, member2, member3인 3개의 Member를 발급하는 예제입니다.
```
// Customer ID를 옵션으로 입력하여 데모 프로그램 실행
/home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh -c 00000000-0000-0000-0000-000000000000

// Member 발급 (member1)
   1. Login
   2. Create member
   9. Exit
Select Menu.  (1,2,9) : 2
Please enter ID : member1
Please enter Name : MEMBER1
Please enter Password:
Please enter your password again:

// Member 발급 (member2)
   1. Login
   2. Create member
   9. Exit
Select Menu.  (1,2,9) : 2
Please enter ID : member2
Please enter Name : MEMBER2
Please enter Password:
Please enter your password again:

// Member 발급 (member3)
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

### Member 로그인

발급된 Member를 MPC Server로 로그인합니다.
<br>
로그인 된 Member들이 상호간의 통신을 거쳐 Group을 생성하고, Group 내에서 MPC 서명을 진행할 수 있습니다.
<br>
<br>
* 예제) 아래 제공되는 예제는 Member ID가 member1인 Member가 로그인하는 예제입니다.
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

* 예제) -m -p 옵션으로 Member ID가 member1인 Member가 로그인하는 예제입니다.

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

###  MPC 그룹 생성 발의

MPC 그룹은 최소 2개, 최대 10개의 Member의 참여로 생성될 수 있습니다.
<br>
<br>
생성 후 통보하는 일반적인 생성방식과는 다르게 정해지지 않은 어떤 Member의 발의와, 발의자가 지정한 참여 대상 Member들의 참여로 인해 그룹이 생성됩니다.
<br>
발의자는 최초로 그룹의 생성을 제안하고, 참여 대상 Member들을 지정할 수 있으며, 참여 대상 Member들의 참여 의사를 확인하기 위한 통신의 Session ID를 발급받습니다.
<br>
<br>
발의 과정에서 발생하는 통신의 Session ID를 다른 Member의 데모 프로그램에 수작업으로 전달하였습니다.
<br>
테스트가 아닌 실사용을 위해서는 Push등을 이용하여 Application으로 바로 전달 하도록 구현 할 수 있습니다.
<br>
<br>
* 예제) 3개의 Member 참여 및 2개의 서명 참여가 필요한 (Threshold) MPC 그룹을 생성하기 위해 발의하는 예제입니다.

```
// 발의자는 member1로 가정합니다.
// 참여자는 member2, member3로 가정합니다. 

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
  1. ecdsa_256k1
  2. ecdsa_p256
  3. ed25519
Select an algorithm.  (1,2,3) : 1
[createGroup] Session ID: 27f59c84-f1f1-4fb9-a0c2-16ab6e89db89
Step: -1
```

### MPC 그룹 참여 

발의자가 아닌 참여 대상인 member는 그룹으로 참여 의사를 전달할 수 있습니다.
<br>
만약 참여 대상인 member들 중 하나라도 참여에 동의하지 않는 경우, MPC 그룹의 생성 발의는 취소됩니다.
<br>
<br>
통신 Session이 생성되면 지정된 시간까지([createGroup](https://pentasecurity.github.io/mpc-demo-sdk/com/pentasecurity/mpc/CreateGroup.html) 인터페이스의 파라미터로 지정) 완료되어야 하며 시간이 지나면 Timeout으로 통신 Session은 자동으로 취소 처리 됩니다.
<br>
데모 프로그램에서는 기본 5분으로 설정되어 있습니다.
<br>
<br>
* 예제) member2가 MPC 그룹으로 참여하려는 경우
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

### MPC 그룹 생성

MPC 그룹 생성이 완료되면 MPC Group ID, Member의 Group Index, MPC Group Public key, 개인키 쌍 및 Secret 값이 출력 됩니다.
<br>    
MPC Group의 비밀키는 MPC의 특성상 알 수 없고, 서버에도 저장되지 않습니다.
<br>
서명 시 각 개인이 받은 정보로 협업을 해서 서명이 이루어 집니다.
<br>
<br>
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
MPC Group Name       : MPC Group for testing
Owner                : member1
Threshold            : 2
Group Size           : 0
Algorithm            : ecdsa_256k1
MPC Group PubKey     : 0x02d58fb448606b5a53f6498999f9cbc3363c432551a68529a1168fe3b9a79e7728
My PubKey            : 0x0215e0c7ee5fae92c90850e64fee74ee3eca43160834c1a0c236d20ad2d408043f
My PriKey            : 0x76c2424a0592ee7ec05d0c32d17977971635fe73e0bd134ff78a6a4e06404387
My Secret            : 0x4ef8b2664bf8406867c42a6ac19e422480e62579af1e74956da3cad5d5770bed
MemberID : member1    Index: 0
MemberID : member2    Index: 1
MemberID : member3    Index: 2
================================================================================================
   1. Creation of MPC Group
   2. Signing
   3. Member List
   4. Update AccessToken
   5. My MPC Group
   9. Exit
Select Menu.  (1,2,3,4,5,9) :
```
    
### MPC 서명 발의

MPC 그룹이 생성되었으면 이제 서명을 진행 할 수 있습니다.
<br>
서명을 위한 참여는 최소한 MPC 그룹 생성 시 지정한 Threshold 만큼의 Member 참여가 필요 합니다. 
<br>
<br>
따라서 위 예제에서 member1, member2, member3로 Member 크기는 3이고, Threshold가 2이므로 서명시 Threshold는 2~3을 지정 할 수 있습니다.
<br>
Threshold에 2를 주면 member 하나가 거부를 하더라도 서명이 가능합니다.
<br>
모든 참여자의 참여를 원한다면 Threshold를 3으로 해서 모든 Member가 참여 해야만 서명이 되도록 할 수 있습니다.
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

### MPC 서명 참여

발의자가 아닌 Member는 받은 Session ID를 이용하여 Session에 참여를 합니다.
<br>
참여자는 그룹의 Member 중 아무나 참여가 가능하고, MPC Group을 생성하는 절차와 동일하게 참여 또는 거부 의사를 표현할 수 있습니다.
<br>
하지만 MPC Group 생성과 다르게 한 Member가 거부하더라도 참여 승인을 하는 Member 수가 Threshold 만큼만 있으면 서명을 위한 협업이 수행 됩니다.
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
```

### MPC 서명 생성

서명의 결과값으로는 R값, S값을 출력합니다.
<br>
<br>
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
   
   
