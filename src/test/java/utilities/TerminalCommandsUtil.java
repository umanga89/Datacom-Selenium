package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class TerminalCommandsUtil {

    public static void runTerminalCommand(String command, String logText) throws Exception {
        try {
            String path = System.getProperty("user.dir");
            System.out.println("Running "+command);
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd " + path + " && "+command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                System.out.println(line);
                if (line == null) {
                    break;
                }
                if (line.contains(logText)) {
                    Thread.sleep(5000);
                    break;
                }
                if (line.toLowerCase().contains("error")) {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
