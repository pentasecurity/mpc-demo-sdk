   
- - -
   
# Penta MPC SDK
![version](https://img.shields.io/badge/version-1.0.0-blue)
<br>
Penta Security provides SDK for Penta MPC Demo Program.
<br>
<br>
Please check the following link for a Korean version.
<br>
Document printed in Korean : <https://github.com/pentasecurity/mpc-demo-sdk/blob/master/README-ko.md>
   
- - -
   
# Contents

1. [Getting Started](#getting-started)

* [What is MPC](#what-is-mpc)

* [Purpose of Penta MPC SDK](#purpose-of-penta-mpc-sdk)

2. [Requirements](#requirements)

* [Conditions for Operations](#conditions-for-operations)

* [Restrictions on Demo](#restrictions-on-demo)

* [Using the Commercial Version of SDK](#using-the-commercial-version-of-sdk)

3. [Using SDK](#using-sdk)

* [Structure of Directory](#structure-of-directory)

* [Testing the Program](#testing-the-program)

* [Provided Interface](#provided-interface)

4. [Contact Information](#contact-information)

* [Inquiries for Questions or Concerns](#inquiries-for-questions-or-concerns)

* [Commercial Inquiry](#commercial-inquiry)

5. [Discretions](#Discretions)
   
- - -
   
# Getting Started

## What is MPC

Secure MPC is known in other words as ‘multi-party computation’, a cryptography technology that allows multiple parties to compute a common result without exposing any of the sensitive inputs to each other.
<br><br>
The MPC technology that Penta Security provides is meant to be a threshold-based signature technology, also known as the ‘Threshold Signature Scheme’ that runs on secure multi-party computation protocol.
<br><br>
These are the special traits of Penta Security’s MPC technology.

* Users rely on minimum quorum of parties to carry out the operation without revealing any of their secret value to one another.
* To generate one public key, minimum of N number of secret keys are required. Then to make generate one signature, N out of M keys are combined. (N ≤ M)
* A whole private key does not ever exist in the entire scheme of MPC.

## Purpose of Penta MPC SDK

Penta Security’s MPC SDK was created to help the testing/developing of non-commercial demo programs for the two following MPC functions.

* Create MPC Group
```
“MPC Group” is created.
“MPC Group” is a pool of MPC participants known as the “members” to gather and operate MPC functionalities.
```
* MPC Signing
```
“Signing of MPC” is processed.
“MPC Signing” requires majority consent of the “members” in the “MPC Group”.
```
   
- - -
   
# Requirements

## Conditions for Operations

Supported OS
* Windows
* Unix / Linux
* Android
* iOS

Operation Environment
* JDK 1.8 

## Restrictions on Demo

MPC SDK provided by Penta Security is intended for testing of MPC demos, and supports limited test environment as follows.
```
In MPC technology, N-of-M configuration is as follows.

N = Threshold quorum to authorize the operation
M = The entire number of “members” in the “MPC Group”

Therefore, the input of N-out-of-M quorum is mandatory to authorize MPC operation
```

* Number of “members” is set to 2 ≤ M ≤ 10. The number of “members” should be inserted during the application process.
```
2 ≤ M = ‘members’ ≤ 10
```
* Threshold value is set to 2 ≤ Threshold Value ≤ M ≤ 10.
```
2 ≤ N = Threshold ≤ M = ‘members’ ≤ 10
```
* The value for “MPC Group” and “Members” will be reset on a monthly basis.


## Using the Commercial Version of SDK

If you wish to test run without being constraint to any limitations of the demo version, and would further like to review Penta MPC SDK for commercial use, please contact the email below.

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
   
   
