# Penta MPC SDK
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

# Requirement

## 구동 조건

* Windows

* Unix / Linux

* Android

* iOS

## 제약 사항

* "MPC 그룹" 내 "멤버"수는 최소 2명, 최대 10명까지 지원됩니다.
* Threshold 값의 범위는 아래와 같습니다.
```
2 ≤ Threshold ≤ "MPC 그룹" 내 "멤버" 수 ≤ 10
```

# Use SDK

## 디렉토리 구조

## SDK 사용 방법

## 프로그램 테스트 방법

## 제공되는 인터페이스

# Permission

## Penta MPC SDK 데모 제공 범위

## 데모 외 정식 사용을 위한 

# Contact

## 이슈 제보

## 사용에 대한 문의

## Commercial Contact
