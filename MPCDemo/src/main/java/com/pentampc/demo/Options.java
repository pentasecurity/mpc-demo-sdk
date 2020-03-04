package com.pentampc.demo;

import com.pentampc.demo.utils.MPCConsoleUtils;

import java.util.ArrayList;
import java.util.List;

public class Options {
    private List<Option> options = new ArrayList<>();

    public void addOption(Option option) {
        options.add(option);
    }

    public void printMenu() {
        for (Option option : options) {
            System.out.println(String.format("%3d. %s", option.getNum(), option.getText()));
        }
    }

    public int InputMenu(String text) {
        boolean isFirst=true;
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append("  (");
        for (Option option : options) {
            if (false == isFirst) {
                sb.append(",");
            } else {
                isFirst=false;
            }
            sb.append(option.getNum());
        }
        sb.append(")");

        while (true) {
            String input = MPCConsoleUtils.InputString(sb.toString(), "");
            if (null != input && input.trim().length() > 0 && input.matches("[0-9]*")) {
                int menuIdx = Integer.parseInt(input, 10);

                for (Option option : options) {
                    if (menuIdx == option.getNum()) {
                        return menuIdx;
                    }
                }
            }
        }
    }
}
