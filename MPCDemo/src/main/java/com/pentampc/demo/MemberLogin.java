package com.pentampc.demo;

import com.pentampc.demo.utils.MPCConsoleUtils;
import com.pentasecurity.mpc.request.LoginParam;
import com.pentasecurity.mpc.response.LoginResponse;
import com.pentasecurity.mpc.response.NormalResponse;

public class MemberLogin {
    private static String accessToken;
    private static String memberId;
    private static boolean isLogin = false;

    public static boolean login(String memberId, String password) {
        try {
            if (null == memberId || memberId.trim().length() < 1) {
                memberId = MPCConsoleUtils.InputString("Member ID", "");
                if (null == memberId) {
                    System.exit(0);
                }
            }
            if (null == password || password.trim().length() < 1) {
                password = MPCConsoleUtils.InputPassword("Password");
                if (null == password) {
                    System.exit(0);
                }
            }
            LoginResponse loginResponse = MPCDemo.apiService.login(memberId, password);
            if (0 != loginResponse.getCode()) {
                System.out.println(loginResponse.getMsg());
            } else {
                accessToken = loginResponse.getAuthToken();
                MemberLogin.memberId = memberId;
                isLogin = true;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean UpdateToken() {
        try {
            NormalResponse normalResponse = MPCDemo.apiService.updateToken(accessToken);
            if (0 != normalResponse.getCode()) {
                System.out.println(normalResponse.getMsg());
            } else {
                accessToken = normalResponse.getData();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean logout() {
        accessToken = "";
        memberId = "";
        isLogin = false;
        return true;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getMemberId() {
        return memberId;
    }

    public static boolean isLogin() {
        return isLogin;
    }
}
