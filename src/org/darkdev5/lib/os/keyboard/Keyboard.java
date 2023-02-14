package org.darkdev5.lib.os.keyboard;

import java.awt.im.InputContext;

/**
 * @author darkDev5
 * @version 1.0
 * @since 17
 */
public class Keyboard {

    /**
     * You can check the system keyboard layout to see if it is persian or not.
     *
     * @return True if layout is persian and false if not.
     */
    public static boolean isPersian() {
        return InputContext.getInstance().getLocale().toString().equalsIgnoreCase("fa_IR");
    }

    /**
     * You can check the system keyboard layout to see if it is english or not.
     *
     * @return True if layout is english and false if not.
     */
    public static boolean isEnglish() {
        return InputContext.getInstance().getLocale().toString().equalsIgnoreCase("en_US");
    }

    /**
     * Gets the system keyboard layout name.
     *
     * @return The name of keyboard layout.
     */
    public static String getLayout() {
        return InputContext.getInstance().getLocale().toString();
    }
}
