import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {

        String[] keywords = {"remote", "teamviewer", "anydesk", "vnc", "rdp", "logmein", "supremo", "ultravnc", "splashtop", "chrome remote", "connectwise", "remote utilities", "microsoft"};
        int remoteApps = 0;

        try {
            ProcessBuilder builder = new ProcessBuilder("wmic", "product", "get", "name");
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "CP850"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.startsWith("Name")) {
                    for (String keyword : keywords) {
                        if (line.toLowerCase().contains(keyword)) {
                            System.out.println("TÁVOLI ELÉRÉS GYANÚS: " + line);
                            remoteApps++;
                            break;
                        }
                    }
                }
            }

            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(remoteApps == 0){
            System.out.println("A gépen nem található távoli eléréses szoftver.");
        }

        checkForRemoteAccess();

    }

    public static void checkForRemoteAccess(){
        try {
            Process process = Runtime.getRuntime().exec(
                "reg query \"HKLM\\SYSTEM\\CurrentControlSet\\Control\\Terminal Server\" /v fDenyTSConnections"
            );
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("fDenyTSConnections")) {
                    String[] parts = line.trim().split("\\s+");
                    String value = parts[parts.length - 1];
                    if ("0x0".equals(value)) {
                        System.out.println("Remote Desktop BE van kapcsolva.");
                    } else {
                        System.out.println("Remote Desktop KI van kapcsolva.");
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
