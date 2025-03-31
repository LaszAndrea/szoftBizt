import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RandomBinaryFileGenerator {
    public static void main(String[] args) {
        String filePath = "random_file.bin";
        int fileSize = 1024;
        
        try {
            generateRandomBinaryFile(filePath, fileSize);
            System.out.println("A fájl sikeresen generálva: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateRandomBinaryFile(String filePath, int size) throws IOException {
        Random random = new Random();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            for (int i = 0; i < size; i++) {
                byte randomByte = (byte) random.nextInt(256);
                fos.write(randomByte);
            }
        }
    }
}
