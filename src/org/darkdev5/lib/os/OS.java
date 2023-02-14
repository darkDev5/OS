package org.darkdev5.lib.os;

import org.darkdev5.lib.os.options.SystemTimeDate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author darkDev5
 * @version 1.0
 * @since 17
 */
public class OS {

    /**
     * Get the name of current operating system.
     *
     * @return Returns the name of current operating system.
     */
    public static String getName() {
        return System.getProperty("os.name");
    }

    /**
     * Get the home directory path of current login user in the system.
     *
     * @return The home directory path of current login user in the system.
     */
    public static String getHome() {
        return System.getProperty("user.home");
    }

    /**
     * Print current working directory path.
     *
     * @return Returns the current working directory path.
     */
    public static String pwd() {
        return System.getProperty("user.dir");
    }

    /**
     * Get the current login user username in the system.
     *
     * @return Returns the current login user username in the system.
     */
    public static String getUsername() {
        return System.getProperty("user.name");
    }

    /**
     * Get the system time or date.
     *
     * @param timeDate Choose to get the time or date of system.
     * @return The system time or date.
     */
    public static String getTimeDate(SystemTimeDate timeDate) {
        DateTimeFormatter dtf = null;

        switch (timeDate) {
            case Time -> dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            case Date -> dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        }

        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Get the system time or date.
     *
     * @param format The format of time or date you want to specify.
     * @return The system time or date.
     */
    public static String getTimeDate(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Copy some text to the system clipboard.
     *
     * @param text The text you want to copy.
     * @return Returns true if text was copied successful and false if not.
     */
    public static boolean copyText(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        return true;
    }

    /**
     * Gets copied text from system clipboard.
     *
     * @return Returns the latest text in the system clipboard.
     * @throws IOException                Signals that an I/O exception to some sort has occurred.
     * @throws UnsupportedFlavorException Signals that the requested data is not supported in this flavor.
     */
    public static String pasteClipboard() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        return clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor) ?
                clipboard.getData(DataFlavor.stringFlavor).toString() : "";
    }

    /**
     * Open a folder from system file explorer.
     *
     * @param path The path you want to open.
     * @return Returns specific number, 1 means successful, 0 means the path is not a folder,
     * or it doesn't exist and -1 means this class is supported on the current platform.
     * @throws IOException Signals that an I/O exception to some sort has occurred.
     */
    public static int openFolder(String path) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            Path pth = Paths.get(path);
            if (Files.exists(pth) && Files.isDirectory(pth)) {
                desktop.open(pth.toFile());
                return 1;
            }

            return 0;
        }

        return -1;
    }

    /**
     * Open a file exists in the current system.
     *
     * @param path The path you want to open.
     * @return Returns specific number, 1 means successful, 0 means the path is not a file,
     * or it doesn't exist and -1 means this class is supported on the current platform.
     * @throws IOException Signals that an I/O exception to some sort has occurred.
     */
    public static int openFile(String path) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            Path pth = Paths.get(path);
            if (Files.exists(pth) && Files.isRegularFile(pth)) {
                desktop.open(pth.toFile());
                return 1;
            }

            return 0;
        }

        return -1;
    }

    /**
     * Take a screenshot from entire screen.
     * @param savePath The path you want to save the image into.
     * @return True if taking screenshot was successful and false if not.
     * @throws AWTException Signals that an Abstract Window Toolkit exception has occurred
     * @throws IOException Signals that an I/O exception to some sort has occurred.
     */
    public static boolean takeFullScreenShot(String savePath) throws AWTException, IOException {
        BufferedImage image = new Robot()
                .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        ImageIO.write(image, "png", new File(savePath));
        return true;
    }
}
