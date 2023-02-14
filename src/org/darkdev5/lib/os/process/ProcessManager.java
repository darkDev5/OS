package org.darkdev5.lib.os.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author darkDev5
 * @version 1.0
 * @since 17
 */
public class ProcessManager {

    /**
     * Prints all the processes in the system to console screen.
     *
     * @param command The process command is different based on operating system, in Windows we use
     *                "tasklist.exe" and for linux and mac we use "ps -few" to print them.
     * @throws InterruptedException Thrown when a thread is waiting,
     *                              sleeping, or otherwise occupied, and the thread is interrupted
     * @throws IOException          Signals that an I/O exception to some sort has occurred.
     */
    public static void print(String command) throws InterruptedException, IOException {
        Process p = new ProcessBuilder(command).start();

        new Thread(() -> {
            Scanner sc = new Scanner(p.getInputStream());
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        }).start();
        p.waitFor();
    }

    /**
     * @param command The commands you want to execute in the system.The first one
     *                is the shell, and the rest of commands will be executed.
     * @return True if execution was successful and false if not.
     * @throws IOException          Signals that an I/O exception to some sort has occurred.
     * @throws InterruptedException Thrown when a thread is waiting,
     *                              sleeping, or otherwise occupied, and the thread is interrupted
     */
    public static boolean executeCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        process.waitFor();
        return true;
    }

    /**
     * @param command The commands you want to execute in the system.The first one
     *                is the shell, and the rest of commands will be executed.
     * @return The output of the command in each line.
     * @throws IOException          Signals that an I/O exception to some sort has occurred.
     * @throws InterruptedException Thrown when a thread is waiting,
     *                              sleeping, or otherwise occupied, and the thread is interrupted
     */
    public static List<String> executeCommandOutput(String[] command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> output = new ArrayList<>();

        processBuilder.command(command);

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }

        process.waitFor();
        return output;
    }
}
