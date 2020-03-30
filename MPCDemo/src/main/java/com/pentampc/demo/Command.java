package com.pentampc.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Main Class args parse
 */
public class Command {

    private Map<String, String> map = new HashMap<>();
    private boolean isHelp=false;

    public Command(String[] args) {
        CommandParse(args);
    }

    private void CommandParse(String[] cmd) {
        map.put("server", MPCDemo.serverUrl);

        for (int i=0; i<cmd.length; i=i+2) {
            if (cmd[i].equalsIgnoreCase("-s") || cmd[i].equalsIgnoreCase("--server")) {
                map.put("server", cmd[i+1]);
            } else if (cmd[i].equalsIgnoreCase("-h") || cmd[i].equalsIgnoreCase("--help")) {
                isHelp = true;
                break;
            } else if (cmd[i].equalsIgnoreCase("-m") || cmd[i].equalsIgnoreCase("--memberid")) {
                map.put("memberId", cmd[i+1]);
            } else if (cmd[i].equalsIgnoreCase("-p") || cmd[i].equalsIgnoreCase("--password")) {
                map.put("password", cmd[i+1]);
            } else if (cmd[i].equalsIgnoreCase("-c") || cmd[i].equalsIgnoreCase("--customerid")) {
                map.put("customerid", cmd[i+1]);
            }
        }
    }

    public void CommandHelpPrint() {
        System.out.println("usage: MPCDemo");
        System.out.println("  -h,--help                 Help.");
        System.out.println("  -c,--customerid <arg>     Customer ID.");
        System.out.println("  -m,--memberid <arg>       MemberId.");
        System.out.println("  -p,--password <arg>       Member password.");
        System.out.println("  -s,--server <arg>         Penta MPC Server URL.(default: http://10.0.121.41:8080)");
        System.out.println("");
    }
    public String getServer() {
        return map.get("server");
    }
    public String getMemberId() {
        return map.get("memberId");
    }
    public String getPassword() {
        return map.get("password");
    }
    public String getCustomerid() {
        return map.get("customerid");
    }
    public boolean isHelp() {
        return isHelp;
    }
}
