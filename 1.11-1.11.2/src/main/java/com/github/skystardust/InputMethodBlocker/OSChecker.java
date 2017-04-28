package com.github.skystardust.InputMethodBlocker;

public class OSChecker {
    public static OSType getOsType(){
        String typeName = System.getProperty("os.arch");
        if (typeName.contains("64")){
            return OSType.WIN_X64;
        }
        return OSType.WIN_X32;
    }
    public enum OSType{
        WIN_X32,WIN_X64
    }
}
