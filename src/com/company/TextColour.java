package com.company;

public class TextColour {
    // a help class to add colours on text printed in the terminal
    public static final String RESET = "\u001B[0m"; //add reset to end the colour command
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void inYellow(String x, boolean ln){
        if(!x.equals("")){ System.out.print(TextColour.YELLOW + x + TextColour.RESET + (ln ? "\n" : "")); }
    }

    public static void inRed(String x, boolean ln){
        if(!x.equals("")){ System.out.print(TextColour.RED + x + TextColour.RESET + (ln ? "\n" : "")); }
    }
}
