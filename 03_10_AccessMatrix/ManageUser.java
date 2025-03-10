import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ManageUser {

    public static String login(Scanner scanner){

        System.out.println("Please enter your name: ");

        String username = scanner.nextLine();

        return username;

    }
    
    public static void changePermissions(List<String> accesses, Scanner scanner) throws IOException{

        String fileAaccess = accesses.get(1);
        String fileBaccess = accesses.get(2);
        String fileCaccess = accesses.get(3);
        //String printerAccess = accesses.get(4);

        File fileA = new File("fileA.txt");
        File fileB = new File("fileB.txt");
        File fileC = new File("fileC.txt");

        extracted(fileAaccess, fileA);
        extracted(fileBaccess, fileB);
        extracted(fileCaccess, fileC);

    }

    public static void extracted(String fileAccess, File file) {
        switch(fileAccess){
            case "rw": file.setReadable(true); file.setWritable(true); break;
            case "r": file.setReadable(true); file.setWritable(false); break;
            case "w": file.setReadable(false); file.setWritable(true); break;
            default: file.setReadable(false); file.setWritable(false); break;
        }
    }

}
