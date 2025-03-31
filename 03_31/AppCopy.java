import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppCopy {

    static List<String> databaseList = new ArrayList<String>();
    static List<String> virusesTxtList = new ArrayList<>();
    static List<Integer> virusesCounted = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String filePath = "random_file.bin";
        byte[] pattern = {(byte) 0xCF};

        try {
            boolean found = containsPattern(filePath, pattern);
            if (found) {
                System.out.println("A keresett mintát megtaláltuk a fájlban.");
            } else {
                System.out.println("A keresett minta nem található a fájlban.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public static boolean containsPattern(String filePath, byte[] pattern) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[pattern.length];
            int bytesRead;
            while ((bytesRead = fis.read(buffer, 0, buffer.length)) != -1) {
                if (bytesRead == pattern.length && compareArrays(buffer, pattern)) {
                    return true;
                }
                fis.skip(bytesRead - 1);
            }
        }
        return false;
    }

    private static boolean compareArrays(byte[] arr1, byte[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}


