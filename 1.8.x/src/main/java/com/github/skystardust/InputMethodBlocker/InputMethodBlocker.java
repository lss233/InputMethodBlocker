package com.github.skystardust.InputMethodBlocker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.*;

@Mod(
        modid = InputMethodBlocker.MOD_ID,
        name = InputMethodBlocker.MOD_NAME,
        version = InputMethodBlocker.MOD_VERSION,
        acceptedMinecraftVersions = InputMethodBlocker.GAME_VERSION
)
@SideOnly(Side.CLIENT)
public class InputMethodBlocker {
    public static final String MOD_ID = "inputmethodblocker";
    public static final String MOD_NAME = "InputMethodBlocker";
    public static final String MOD_VERSION = "1.5.0";
    public static final String GAME_VERSION = "[1.8,1.8.9]";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new GameEventHandle());
        try {
            saveNativeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        NativeUtils.inactiveInputMethod("");
    }

    public void saveNativeFile() throws IOException {
        OSChecker.OSType osType = OSChecker.getOsType();
        if (osType== OSChecker.OSType.WIN_X64){
            saveTempNativeFile("InputMethodBlocker-Natives-x64.dll");
        }
        else if (osType== OSChecker.OSType.WIN_X32){
            saveTempNativeFile("InputMethodBlocker-Natives-x86.dll");
        }
    }
    private void saveTempNativeFile(String fileName) throws IOException {
        InputStream fileInputStream = getClass().getClassLoader().getResource(fileName).openStream();
        File nativeFile = File.createTempFile("InputMethodBlocker", ".dll");
        FileOutputStream out = new FileOutputStream(nativeFile);
        int i;
        byte [] buf = new byte[1024];
        while((i=fileInputStream.read(buf))!=-1) {
            out.write(buf,0,i);
        }
        fileInputStream.close();
        out.close();
        nativeFile.deleteOnExit();
        System.load(nativeFile.toString());
    }
}
