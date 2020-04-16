package com.pentampc.demo;

import com.pentampc.demo.utils.MPCConsoleUtils;
import com.pentasecurity.mpc.*;
import com.pentasecurity.mpc.exceptions.MPCException;
import com.pentasecurity.mpc.request.KeygenParam;
import com.pentasecurity.mpc.response.MPCSuccessResponse;
import com.pentasecurity.mpc.response.NormalResponse;

import java.io.IOException;

public class CreateMPCGroup {
    public int selectMenu() {
        while(true) {
            int idx = Menu.MPCGroupCreationMenu();
            switch (idx) {
                case 1:  // MPC Group Participation Request
                    Request();
                    break;
                case 2:  // Join in the creation of MPC Group
                    try {
                        Join("");
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
    public EnumDefined.Algorithm selectAlgorithm() {
        while(true) {

            Options options = new Options();
            options.addOption(new Option(1, "ecdsa_256k1"));
            options.addOption(new Option(2, "ecdsa_p256"));
            options.addOption(new Option(3, "ed25519"));
            options.printMenu();

            int idx = options.InputMenu("Select an algorithm.");

            switch (idx) {
                case 1:
                    return EnumDefined.Algorithm.ecdsa_256k1;
                case 2:
                    return EnumDefined.Algorithm.ecdsa_p256;
                case 3:
                    return EnumDefined.Algorithm.ed25519;
            }
        }
    }
    public void Request() {
        String memberIds = "";
        String sthreshold = "";
        String comment = "";
        String name = "";
        EnumDefined.Algorithm algorithm = EnumDefined.Algorithm.ecdsa_256k1;

        while (true) {
            memberIds = MPCConsoleUtils.InputString("list of MemberIDs. (separator is comma(,))", memberIds);

            sthreshold = MPCConsoleUtils.InputString("number of members required for signing", sthreshold);
            name = MPCConsoleUtils.InputString("name", name, false);
            comment = MPCConsoleUtils.InputString("comment", comment, false);

            algorithm = selectAlgorithm();

            String[] arrMembers = memberIds.split(",");
            int threshold = Integer.parseInt(sthreshold, 10);

            KeygenParam keygenParam = new KeygenParam();
            keygenParam.setThreshold(threshold);
            keygenParam.setSessionTimeout(5);
            keygenParam.setComment(comment);
            keygenParam.setName(name);
            keygenParam.setAlgorithm(algorithm);

            for (String s : arrMembers) {
                keygenParam.addMembers(s.trim());
            }

            try {
                MPCSuccessResponse mpcSuccessResponse = MPCDemo.apiService.createGroup(MemberLogin.getAccessToken(), keygenParam);
                if (0 != mpcSuccessResponse.getCode()) {
                    System.out.println(String.format("MPCKeyGen Failed. [%s]", mpcSuccessResponse.getMsg()));
                    return;
                }
                System.out.println("[createGroup] Session ID: " + mpcSuccessResponse.getSessionId());
                Join(mpcSuccessResponse.getSessionId());
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Join(String sessionId) throws IOException, MPCException {
        //String sessionId = "";
        String mpcGroupId = "";
        MPCKey mpcKey = null;
        String cmd="";
        boolean isJoin=false;

        if (null == sessionId || sessionId.trim().length() < 1) {
            while (true) {
                sessionId = MPCConsoleUtils.InputString("Please enter Session ID", sessionId);
                if (false == MPCConsoleUtils.isEmpty(sessionId)) {
                    break;
                }
            }

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
                        isJoin=true;
                    }
                    break;
                }
                return;
            }

        } else {
            isJoin = true;
        }


        if (isJoin) {
            CreateGroup mpcKeygen = new CreateGroup(MPCDemo.apiService);
            int step=-100;
            while (false == mpcKeygen.update(MemberLogin.getAccessToken(), sessionId)) {
                if (0 != mpcKeygen.getErrorCode()) {
                    System.out.println(String.format("ERROR: %s  [%d]", mpcKeygen.getErrorMessage(), mpcKeygen.getErrorCode()));
                    return;
                }
                if (step != mpcKeygen.getStep()) {
                    System.out.println(String.format("Step: %d", mpcKeygen.getStep()));
                    step = mpcKeygen.getStep();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }

            if (mpcKeygen.isSuccess()) {
                mpcKey = mpcKeygen.getMPCKey();
                MPCDemo.addMPCKey(sessionId, mpcKey);

                System.out.println(String.format("MPC Group ID: %s", sessionId));
                System.out.println(String.format("Member Index: %s", mpcKey.getMemberIdx()));
                System.out.println(String.format("Group PubKey: %s", mpcKey.getMasterPubKey()));
                System.out.println(String.format("Member PriKey: %s", mpcKey.getMemberPriKey()));
                System.out.println(String.format("Member PubKey: %s", mpcKey.getMemberPubKey()));
                System.out.println(String.format("Member Secret: %s", mpcKey.getMemberSecret()));
            } else {
                System.out.println(String.format("ERROR: %s", mpcKeygen.getErrorMessage()));
            }
        }
    }
}
