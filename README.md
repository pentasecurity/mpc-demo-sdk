   
- - -
   
# Penta MPC SDK
![version](https://img.shields.io/badge/version-1.0.0-blue)
<br>
Penta Security provides SDK for Penta MPC Demo Program.
<br>
<br>
[Please click here](<https://github.com/pentasecurity/mpc-demo-sdk/blob/master/README-ko.md>) to find the Korean version of the document.
   
- - -
   
# Contents

1. [Getting Started](#getting-started)

* [What is MPC](#what-is-mpc)

* [Purpose of Penta MPC SDK](#purpose-of-penta-mpc-sdk)

2. [Requirements](#requirements)

* [Conditions for Operations](#conditions-for-operations)

* [Restrictions on Demo](#restrictions-on-demo)

* [Registering for Demo Testing](#registering-for-demo-testing)

* [Using the Commercial Version of SDK](#using-the-commercial-version-of-sdk)

3. [Using SDK](#using-sdk)

* [Structure of Directory](#structure-of-directory)

* [Interface](#interface)

* [Demo Program Setting](#demo-program-setting)

  * [Download](#download)

  * [Build](#build)

  * [Starting the Demo Program](#starting-the-demo-program)

* [Testing Demo Program](#testing-demo-program)

  * [Create Members](#create-members)

  * [Log in for Members](#log-in-for-members)

  * [Initiation of MPC Group Formation](#initiation-of-mpc-group-formation)
  
  * [Participation of MPC Group](#participation-of-mpc-group)
  
  * [Creation of MPC Group](#creation-of-mpc-group)
  
  * [Initation of MPC Signature Approval](#initation-of-mpc-signature-approval)
  
  * [Participation of MPC Signature Generation](#participation-of-mpc-signature-generation)
  
  * [Creation of MPC Signature](#creation-of-mpc-signature)

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

## Registering for Demo Testing

[Please follow this link](<http://10.0.121.41:18080/join>) to request testing for MPC demo. 
<br>
Once the form is complete and registered, you will receive an email from us in your inbox. 
<br>
After verifying the email address, Customer ID will be issued so that you may gain access to the demo program.

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
<br>
Native Module must be added to the library PATH of each OS or java -Djava.library.path.
<br>
Native module should be used after checking each OS and the corresponding unpacking tar.gz.
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
┬ MPCDemo               : MPC Demo
│   ├ bin/              : Start Script
│   ├ src/              : Demo source
│   └ build.gradle      : gradle script
├ MPCSdk                : PentaMPC SDK
│   ├ native/           : PentaMPC native modules
│   ├ mpc-sdk-1.0-SNAPSHOT-javadoc.jar    : PentaMPC javadoc
│   ├ mpc-sdk-1.0-SNAPSHOT.jar            : PentaMPC Java Archive
│   └ pcw-common-1.6.2-SNAPSHOT.jar       : PentaMPC Common Java Archive
└ docs/                 : MPCSdk Javadoc
```

## Interface

[Click here](<https://pentasecurity.github.io/mpc-demo-sdk/>) to view the interface provided for Penta MPC SDK.

## Demo Program Setting

Please follow the following steps for Penta MPC SDK demo program testing.

### Download

Download the demo program as follows.
<br>
When the download is complete, check to make sure that the directory and configuration files exist.
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

### Build
Build the program as follows.
<br>
When the build is complete, check to make sure that the directory and configuration files exist.
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

### Starting the Demo Program
To run the demo program, you must first apply for the registration and have your email verified, so that a Customer ID is issued.
<br>
[Please click here](<https://mpc.pentasecurity.com:18443/join>) for demo registration.
<br><br>
When running the demo program, you may enter Customer ID with -c option.
<br>
A valid Customer ID that has been issued from the registration process must be entered to operate the demo.
<br>
* e.g.) When customer ID is set as [Customer ID 00000000-0000-0000-0000-000000000000]
```
/home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh -c 00000000-0000-0000-0000-000000000000
   1. Login
   2. Create member
   9. Exit
Select Menu.  (1,2,9) : 
```

## Testing Demo Program

Users are able to run and test out MPC functions with Penta MPC SDK.
<br><br>
Demo program does not retain or save the generated keys when it is rebooted.
<br>
The Key Pairs created for every Member when MPC Group is being formed, are managed in form of HashMap only.

### Create Members

“Members” are the those that play the primary role in utilizing the MPC functionalities.
<br>
Members gather to form a “Group”, and within that Group, signing of MPC takes place.
<br>
* e.g.) In the example below, Customer ID is set as [Customer ID 00000000-0000-0000-0000-000000000000] and three Member IDs are set as “member1”, “member2”, “member 3”.
```
// Run the Demo Program by entering Customer ID as an option
/home/mpc/mpc-demo-sdk/MPCDemo/build/release> ./MPCDemo.sh -c 00000000-0000-0000-0000-000000000000

// Set Member (member1)
   1. Login
   2. Create member
   9. Exit
Select Menu.  (1,2,9) : 2
Please enter ID : member1
Please enter Name : MEMBER1
Please enter Password:
Please enter your password again:

// Set Member (member2)
   1. Login
   2. Create member
   9. Exit
Select Menu.  (1,2,9) : 2
Please enter ID : member2
Please enter Name : MEMBER2
Please enter Password:
Please enter your password again:

// Set Member (member3)
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

### Log in for Members

Members log in on the MPC Server.
<br>
Logged in Members can form a Group and then later process MPC signature.
<br>
* e.g.) In the example below “member1” logs on as a Member
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
* e.g.) In the example below “member 1” logs in with -m -p option
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

### Initiation of MPC Group Formation

MPC Groups can be created with the participation of minimum 2 and maximum 10 members: 2 ≤ M ≤10.
<br><br>
Let’s say one person proposes to create a group.
<br>
Contrary to the general method of how a group created, for MPC Group formation a Member proposes the act and then chooses who the Members will be to make the new group.
<br>
The initiator proposes a new group, selects the participant Members and then receives a Session ID to confirm Members’ intentions.
<br><br>
The Session ID created during the initiation process is manually delivered to other Member’s demo program.
<br>But for the use of the commercial program (*not the demo version), a Push Alert will pop up on the Application immediately.
<br>
* e.g.) In the example below a Threshold MPC group that requires 2 signatures out of a 3 Member Group is proposed.
```
// Initiator is set as member1.
// Participants (members) are set as member2, member3. 

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

### Participation of MPC Group

Non-initiating Members can deliver their intentions of participating in the group.
<br>
If any of the selected Members do not consent to participate in the group as a Member, the proposal to create the group is cancelled.
<br>
<br>
<br>
For the demo program the time is set for 5 minutes in default.
<br>
* e.g.) In the example below member2 is participating as one of the members in the MPC Group.
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

### Creation of MPC Group

### Initation of MPC Signature Approval

### Participation of MPC Signature Generation

### Creation of MPC Signature

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
   
   
