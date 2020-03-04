package com.pentampc.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Console;
import java.io.IOException;

public class MPCConsoleUtils {
    private static Console console = System.console();

    public static boolean isEmpty(String s) {
        if (null == s || s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static String defaultString(String s) {
        return defaultString(s, "");
    }

    public static String defaultString(String s, String def) {
        if (isEmpty(s)) {
            return def;
        }
        return s;
    }

    public static String InputString(String msg, String def) {
        return InputString(msg, def, true);
    }
    public static String InputString(String msg, String def, boolean notEmpty) {
        while (true) {
            String message = "";
            if (null == def || def.length() < 1) {
                message = console.readLine(String.format("%s : ", msg));
            } else {
                message = console.readLine(String.format("%s : [%s] ", msg, defaultString(def)));
            }
            if (null == message) {
                System.exit(0);
            }
            message = message.trim();
            if (message.length() == 0 && false == isEmpty(def)) {
                return def;
            } else if (false == notEmpty || message.length() > 0) {
                return message;
            }
        }
    }

    public static void wait(String msg) {
        System.out.println(msg);
        console.readLine("Press [enter] to proceed...");
    }

    public static String InputPassword(String msg) {
        while (true) {
            char[] message = console.readPassword(msg + ": ");
            if (null != message) {
                String password = new String(message);
                if (null != password && password.trim().length() > 0) {
                    return new String(message);
                }
            } else {
                System.exit(0);
            }
        }
    }

    public static int InputMenu(String msg, int[] ch) {
        while (true) {
            String input = InputString(msg, "");
            if (null == input) {
                System.exit(0);
            }

            int in = Integer.parseInt(input, 10);

            for (int i : ch) {
                if (in == i) {
                    return in;
                }
            }
        }
    }

    public static String ObjectToJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
