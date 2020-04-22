package com.pentampc.demo;

import com.pentampc.demo.utils.MPCConsoleUtils;
import com.pentasecurity.mpc.EnumDefined;
import com.pentasecurity.mpc.MPCKey;
import com.pentasecurity.mpc.exceptions.InvalidAddressException;
import com.pentasecurity.mpc.model.MPCGroup;
import com.pentasecurity.mpc.model.MPCGroupMember;
import com.pentasecurity.mpc.model.Member;
import com.pentasecurity.mpc.network.MPCApiClient;
import com.pentasecurity.mpc.network.NetworkService;
import com.pentasecurity.mpc.request.RegisterParam;
import com.pentasecurity.mpc.response.MPCGroupsResponse;
import com.pentasecurity.mpc.response.MembersResponse;
import com.pentasecurity.mpc.response.NormalResponse;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPCDemo {
    protected static String serverUrl = "https://mpc.pentasecurity.com:8443";
    private static String memberid = null;
    private static String password = null;
    protected static Command command = null;

    public static MPCApiClient apiService = null;
    private static Map<String, MPCKey> mpcKeys = new HashMap<String, MPCKey>();

    private boolean runDemo = true;

    public static void main(String[] args) throws InterruptedException, InvalidAddressException {
        command = new Command(args);

        apiService = getApiService(command.getServer());

        if (command.isHelp()) {
            command.CommandHelpPrint();
        } else if (false == MPCConsoleUtils.isEmpty(command.getMemberId()) || false == MPCConsoleUtils.isEmpty(command.getPassword())) {
            MemberLogin.login(command.getMemberId(), command.getPassword());
        } else {
            int idx = 0;
            while (9 != idx) {
                idx = Menu.First();

                switch (idx) {
                    case 1: //login
                        if (MemberLogin.login(null, null)) {
                            idx = 9;
                            break;
                        }
                        break;
                    case 2: // create member
                        createMember();
                        break;
                    case 9:
                        return;
                }
            }
        }

        if (MemberLogin.isLogin()) {
            new MPCDemo().run();
        }
    }

    private static MPCApiClient getApiService(String serverUrl) throws InvalidAddressException {
        NetworkService networkService = new NetworkService();
        networkService.baseUrl(serverUrl);

        /* When using a proxy. */
        //java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888));
        //networkService.proxy(proxy);

        return networkService.getApiClient();
    }

    private void run() throws InterruptedException {
        int idx=0;
        while (9 != idx) {
            idx = Menu.MainMenu();

            switch (idx) {
                case 1: //Creation of MPC Group
                    CreateMPCGroup createMPCGroup = new CreateMPCGroup();
                    idx = createMPCGroup.selectMenu();
                    break;
                case 2: //Signing
                    Signing signing = new Signing();
                    idx = signing.selectMenu();
                    break;
                case 3: //Member List
                    memberList();
                    break;
                case 4: //Update AccessToken
                    MemberLogin.UpdateToken();
                    break;
                case 5: //My MPC Group
                    mpcGroupList();
                    break;
            }
        }
    }

    public static void addMPCKey(String groupId, MPCKey mpcKey) {
        mpcKeys.put(groupId, mpcKey);
    }
    public static MPCKey getMPCKey(String groupId) {
        return mpcKeys.get(groupId);
    }


    public static void memberList() {
        try {
            MembersResponse membersResponse = apiService.getMembers(MemberLogin.getAccessToken());
            if (0 != membersResponse.getCode()) {
                System.out.println(String.format("Failed to get Member List. [%s]", membersResponse.getMsg()));
                return;
            }

            List<Member> members = membersResponse.getMembers();

            System.out.println(String.format("%-15s\t%-15s", "Member ID", "Member Name"));
            System.out.println(String.format("%-15s\t%-15s", "===============", "==============="));
            for (Member member : members) {
                System.out.println(String.format("%-15s\t%-15s", member.memberId, member.memberName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mpcGroupList() {
        boolean first=true;
        MPCGroupsResponse membersResponse = apiService.selectMPCGroups(MemberLogin.getAccessToken(), MemberLogin.getMemberId());
        if (0 != membersResponse.getCode()) {
            System.out.println(String.format("Failed to get MPC Group List. [%s]", membersResponse.getMsg()));
            return;
        }

        List<MPCGroup> mpcGroups = membersResponse.getMPCGroups();


        for (MPCGroup mpcGroup : mpcGroups) {
            if (first) {
                System.out.println("================================================================================================");
                first = false;
            }
            System.out.println(String.format("%-20s : %s", "MPC Group ID", mpcGroup.getMpcGroupId()));
            System.out.println(String.format("%-20s : %s", "MPC Group Name", MPCConsoleUtils.defaultString(mpcGroup.getName(), "[notset]")));
            System.out.println(String.format("%-20s : %s", "Owner", mpcGroup.getOwnerId()));
            System.out.println(String.format("%-20s : %s", "Threshold", mpcGroup.getThreshold()));
            System.out.println(String.format("%-20s : %s", "Group Size", mpcGroup.getMpcgroupSize()));
            System.out.println(String.format("%-20s : %s", "Algorithm", mpcGroup.getAlgorithm()));
            MPCKey mpcKey = MPCDemo.getMPCKey(mpcGroup.getMpcGroupId());
            if (null == mpcKey) {
                System.out.println(String.format("MPC Key Not Found!."));
            } else {
                System.out.println(String.format("%-20s : %s", "MPC Group PubKey", mpcKey.getMasterPubKey()));
                System.out.println(String.format("%-20s : %s", "My PubKey", mpcKey.getMemberPubKey()));
                System.out.println(String.format("%-20s : %s", "My PriKey", mpcKey.getMemberPriKey()));
                System.out.println(String.format("%-20s : %s", "My Secret", mpcKey.getMemberSecret()));
                if (mpcGroup.getAlgorithm().equals(EnumDefined.Algorithm.ed25519.toString())) {
                    System.out.println(String.format("%-20s : %s", "SchemeSum", mpcKey.getSchemeSum()));
                }
            }

            for (MPCGroupMember member : mpcGroup.getMemberList()) {
                System.out.println(String.format("MemberID : %s    Index: %d", member.getMemberId(), member.getMemberIdx()));
            }
            System.out.println("================================================================================================");
        }
    }

    public static void createMember() {
        String memberId = "";
        String memberName = "";
        String memberPassword = "";
        String memberPassword2 = "";
        while (true) {
            memberId = MPCConsoleUtils.InputString("Please enter ID", memberId);
            memberName = MPCConsoleUtils.InputString("Please enter Name", memberName);
            memberPassword = MPCConsoleUtils.InputPassword("Please enter Password");
            memberPassword2 = MPCConsoleUtils.InputPassword("Please enter your password again");

            if (false == memberPassword.equals(memberPassword2)) {
                System.out.println("Password is different Please enter it again.");
                continue;
            }

            RegisterParam registerParam = new RegisterParam(memberId, memberName, memberPassword);

            /* When using MPC DEMO Service, CustomerID is required. */
            registerParam.setCustomerId(MPCDemo.command.getCustomerid());

            NormalResponse register = null;
            try {
                register = apiService.registerMember(registerParam);

                if (0 != register.getCode()) {
                    System.out.println(String.format("Member creation failed. [%s]", register.getMsg()));
                    continue;
                }

                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
