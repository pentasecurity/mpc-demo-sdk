package com.pentampc.demo;

import com.pentampc.demo.utils.MPCConsoleUtils;
import com.pentasecurity.cryptowallet.utils.PcwfUtils;
import com.pentasecurity.cryptowallet.utils.StringUtils;
import com.pentasecurity.mpc.*;
import com.pentasecurity.mpc.exceptions.MPCException;
import com.pentasecurity.mpc.exceptions.SigningInitException;
import com.pentasecurity.mpc.request.SigningParam;
import com.pentasecurity.mpc.response.MPCGroupSessionResponse;
import com.pentasecurity.mpc.response.MPCSuccessResponse;
import com.pentasecurity.mpc.response.NormalResponse;

import java.io.IOException;

public class Signing {
    public int selectMenu() {
        while(true) {
            int idx = Menu.SigningMenu();
            switch (idx) {
                case 1:  // Signing Participation Request
                    Request();
                    break;
                case 2:  // Join in the Signing
                    try {
                        Join(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (MPCException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:  // Previous Menu
                    return 0;
                case 9:  // Exit
                    return 9;
            }
        }
    }
    public void Request() {
        String message = "";
        String mpcGroupId = "";
        String sThreshold = "2";
        int threshold = 2;
        String comment = "";

        while (true) {
            message = MPCConsoleUtils.InputString("Message", message);
            mpcGroupId = MPCConsoleUtils.InputString("MPC Group ID", mpcGroupId);
            sThreshold = MPCConsoleUtils.InputString("Threshold", sThreshold);
            comment = MPCConsoleUtils.InputString("comment", comment, false);

            if (StringUtils.isEmpty(sThreshold) ) {
                continue;
            } else {
                try {
                    threshold = Integer.parseInt(sThreshold, 10);
                } catch (Exception e) {
                    continue;
                }
            }
            SigningParam signingParam = null;
            try {
                signingParam = new SigningParam("0x"+ PcwfUtils.encodeHex(message.getBytes()), threshold);
                signingParam.setSessionTimeout(5);
                if (false == StringUtils.isEmpty(comment)) {
                    signingParam.setComment(comment);
                }
                try {
                    MPCSuccessResponse mpcSuccessResponse = MPCDemo.apiService.mpcSigning(MemberLogin.getAccessToken(), mpcGroupId, signingParam);
                    if (0 != mpcSuccessResponse.getCode()) {
                        System.out.println(String.format("signing Failed. [%s]", mpcSuccessResponse.getMsg()));
                        return;
                    }
                    System.out.println("[Signing] Session ID: " + mpcSuccessResponse.getSessionId());
                    Join(mpcSuccessResponse.getSessionId());
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SigningInitException e) {
                e.printStackTrace();
            }
        }
    }

    public void Join(String sessionId) throws IOException, MPCException {

        String mpcGroupId = "";
        MPCKey mpcKey = null;
        String cmd="";
        boolean isJoin=false;

        if (null == sessionId || sessionId.trim().length() < 1) {
            sessionId = MPCConsoleUtils.InputString("Please enter Session ID", sessionId);
            while (true) {
                cmd = MPCConsoleUtils.InputString("Do you join this Session? ([A]PPROVE, [R]EJECT)", "", false);
                if (false == MPCConsoleUtils.isEmpty(cmd)) {
                    cmd = cmd.trim();
                    MPCConfirm mpcConfirm;

                    if (cmd.equalsIgnoreCase("A") || cmd.equalsIgnoreCase("APPROVE")) {
                        mpcConfirm = MPCConfirm.APPROVE;
                    } else if (cmd.equalsIgnoreCase("R") || cmd.equalsIgnoreCase("REJECT")) {
                        mpcConfirm = MPCConfirm.REJECT;
                    } else {
                        System.out.println(String.format("Input only [A]PPROVE or [R]EJECT."));
                        continue;
                    }

                    NormalResponse response = MPCDemo.apiService.sessionConfirm(MemberLogin.getAccessToken(), sessionId, mpcConfirm);
                    if (0 != response.getCode()) {
                        System.out.println(String.format("sessionConfirm failed. [%s]", response.getMsg()));
                        continue;
                    }
                    if (MPCConfirm.APPROVE == mpcConfirm) {
                        isJoin = true;
                    }
                    break;
                }
                return;
            }
        }

        MPCGroupSessionResponse aa = MPCDemo.apiService.getSessionInfo(MemberLogin.getAccessToken(), sessionId);
        if (0 == aa.getCode()) {
            mpcGroupId = aa.getMPCGroupSession().getMpcGroupId();
            mpcKey = MPCDemo.getMPCKey(mpcGroupId);
            if (null == mpcKey) {
                System.out.println("Not found MPC Key");
                return;
            }
        } else {
            System.out.println(String.format("getSessionInfo ERROR: %s  [%d]", aa.getMsg(), aa.getCode()));
            return;
        }

        MPCSigning mpcSigning = new MPCSigning(mpcKey, MPCDemo.apiService);
        while (false == mpcSigning.update(MemberLogin.getAccessToken(), sessionId)) {
            if (0 != mpcSigning.getCode()) {
                System.out.println(String.format("ERROR: %s  [%d]", mpcSigning.getErrorMessage(), mpcSigning.getCode()));
                return;
            }
            MPCConsoleUtils.wait(String.format("Step: %d", mpcSigning.getStep()));
        }

        if (mpcSigning.isSuccess()) {
            MPCSign mpcSign = mpcSigning.getMPCSign();

            System.out.println(String.format("Sigr: %s", mpcSign.getSigr()));
            System.out.println(String.format("Sigs: %s", mpcSign.getSigs()));
            System.out.println(String.format("Sigrecovery: %s", mpcSign.getSigrecovery()));
        } else {
            System.out.println(String.format("ERROR: %s", mpcSigning.getErrorMessage()));
        }

    }
}
