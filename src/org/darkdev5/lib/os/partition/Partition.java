package org.darkdev5.lib.os.partition;

import lombok.Getter;
import lombok.Setter;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @author darkDev5
 * @version 1.0
 * @since 17
 */
public class Partition {
    private @Getter final FileSystemView fsv;
    private @Setter @Getter String driveLetter;

    public Partition(String driveLetter) {
        this.driveLetter = driveLetter;
        this.fsv = FileSystemView.getFileSystemView();
    }

    /**
     * Display a file or partition name.
     *
     * @return The file or partition name.
     */
    public String getDisplayName() {
        return fsv.getSystemDisplayName(new File(driveLetter));
    }

    /**
     * Show if this file is a partition or not.
     *
     * @return If the file is a partition it returns true and false if not.
     */
    public boolean isDrive() {
        return fsv.isDrive(new File(driveLetter));
    }

    /**
     * Show if this file is a floppy partition or not.
     *
     * @return If the file is a floppy partition it returns true and false if not.
     */
    public boolean isFloppy() {
        return fsv.isFloppyDrive(new File(driveLetter));
    }

    /**
     * Detects if a file or partition is readable or not.
     *
     * @return True if the file or partition is readable and false if not.
     */
    public boolean isReadable() {
        return new File(driveLetter).canRead();
    }

    /**
     * Detects if a file or partition is writable or not.
     *
     * @return True if the file or partition is writable and false if not.
     */
    public boolean isWritable() {
        return new File(driveLetter).canWrite();
    }

    /**
     * Gets the file or partition total size.
     *
     * @return The total size of a file or partition.
     */
    public long getTotalSpace() {
        return new File(driveLetter).getTotalSpace();
    }

    /**
     * Gets the file or partition free space size.
     *
     * @return The free space size of a file or partition.
     */
    public long getFreeSpace() {
        return new File(driveLetter).getUsableSpace();
    }

    /**
     * Gets the used size of a file or partition.
     *
     * @return The used size of a file or partition.
     */
    public long getUsedSpace() {
        return new File(driveLetter).getTotalSpace() - new File(driveLetter).getUsableSpace();
    }
}
