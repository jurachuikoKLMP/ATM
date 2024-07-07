package com.senlainc.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CleanConsoleUtil {
    public static void CleanConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
