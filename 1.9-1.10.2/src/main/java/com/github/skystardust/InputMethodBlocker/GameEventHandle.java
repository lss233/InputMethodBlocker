package com.github.skystardust.InputMethodBlocker;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

@SideOnly(Side.CLIENT)
public class GameEventHandle {
    private static final boolean DEBUG = false;
    @SubscribeEvent
    public void onChatGUI(GuiOpenEvent event) throws NoSuchFieldException, IllegalAccessException {
        if (event.getGui() instanceof GuiChat){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onRepairGUI(GuiScreenEvent.MouseInputEvent event) throws NoSuchFieldException, IllegalAccessException {
        if (event.getGui() instanceof GuiRepair){
            GuiRepair guiScreen = (GuiRepair) event.getGui();
            if (DEBUG){
                for (Field f:ReflectionUtils.getPrivateObjectList(GuiRepair.class,GuiTextField.class,guiScreen)){
                    System.out.println(f);
                }
            }else {
                GuiTextField textField = (GuiTextField) ReflectionUtils.getPrivateField(guiScreen.getClass(),"field_147091_w",guiScreen);
                if (textField.isFocused()){
                    NativeUtils.activeInputMethod("");
                }else {
                    NativeUtils.inactiveInputMethod("");
                }
            }
        }
    }
    @SubscribeEvent
    public void onCreativeGUI(GuiScreenEvent.MouseInputEvent event) throws NoSuchFieldException, IllegalAccessException {
        if (event.getGui() instanceof GuiContainerCreative){
            GuiContainerCreative guiContainerCreative = (GuiContainerCreative) event.getGui();
            if (DEBUG){
                for (Field f:ReflectionUtils.getPrivateObjectList(GuiContainerCreative.class,GuiTextField.class,guiContainerCreative)){
                    System.out.println(f);
                }
            }else {
                GuiTextField searchField = (GuiTextField) ReflectionUtils.getPrivateField(guiContainerCreative.getClass(),"field_147062_A",guiContainerCreative);
                if (searchField.isFocused()){
                    NativeUtils.activeInputMethod("");
                }else {
                    NativeUtils.inactiveInputMethod("");
                }
            }
        }
    }
    @SubscribeEvent
    public void onControlGUI(GuiOpenEvent event){
        if (event.getGui() instanceof GuiControls){
            NativeUtils.inactiveInputMethod("");
        }
    }
    @SubscribeEvent
    public void onInGameGUI(GuiOpenEvent event){
        if (event.getGui()==null){
            NativeUtils.inactiveInputMethod("");
        }
    }
    @SubscribeEvent
    public void onSign(GuiOpenEvent event){
        if (event.getGui() instanceof GuiEditSign){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onCommandBlock(GuiOpenEvent event){
        if (event.getGui() instanceof GuiCommandBlock){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onCreateWorld(GuiOpenEvent event){
        if (event.getGui() instanceof GuiCreateWorld){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onEditBook(GuiOpenEvent event){
        if (event.getGui() instanceof GuiScreenBook){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onRenameWorld(GuiOpenEvent event){
        if (event.getGui() instanceof GuiWorldEdit){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onAddServer(GuiOpenEvent event){
        if (event.getGui() instanceof GuiScreenAddServer){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onDirectJoinServer(GuiOpenEvent event){
        if (event.getGui() instanceof GuiScreenServerList){
            NativeUtils.activeInputMethod("");
        }
    }
    @SubscribeEvent
    public void onMultiPlayer(GuiOpenEvent event){
        if (event.getGui() instanceof GuiMultiplayer){
            NativeUtils.inactiveInputMethod("");
        }
    }
    @SubscribeEvent
    public void onSelectWorld(GuiOpenEvent event){
        if (event.getGui() instanceof GuiWorldSelection){
            NativeUtils.inactiveInputMethod("");
        }
    }
}
