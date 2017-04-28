package com.github.skystardust.InputMethodBlocker;

public class NativeUtils {
    public static native void inactiveInputMethod(String windowName);
    public static native void activeInputMethod(String windowName);
}
