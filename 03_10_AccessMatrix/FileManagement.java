import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileManagement {

    public static List<String> resourceList = new ArrayList<String>();
    
    public static List<String> readTxt(String username) throws IOException{

        BufferedReader bfro = new BufferedReader(new FileReader("new.txt"));
        String st;
        List<String> list = new ArrayList<String>();

        while ((st = bfro.readLine()) != null){
            
            if(st.startsWith(username)){
                list = new ArrayList<String>(Arrays.asList(st.split(";")));
            }
            else if((st.startsWith("name"))){
                resourceList = new ArrayList<String>(Arrays.asList(st.split(";")));
                resourceList.remove(0);
            }
        }

        bfro.close();

        if(list.size() <= 0){
            throw new IOException("User does not exits");
        }

        return list;

    }
    
    public static void addNewAccess(Scanner scanner, String filename) throws IOException{

        try {
            File file = new File(filename);
            if (!file.canWrite()) {
                throw new IOException("Cant write to file " + filename);
            }

            FileWriter myWriter = new FileWriter(filename, true);

            myWriter.append("\n");
            myWriter.append(writeToFile(scanner));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            throw e;
        }


    }

    public static String writeToFile(Scanner scanner){

        System.out.println("Whats the name of the person you want to enter into the file?");
        String username = scanner.nextLine();
        List<String> fileAccessList = new ArrayList<String>();

        for(String resource : resourceList){
            System.out.println("Access to " + resource + "?");
            fileAccessList.add(scanner.nextLine());
        }

        String appended = username.concat(";").concat(fileAccessList.get(0)).concat(";").concat(fileAccessList.get(1)).concat(";").concat(fileAccessList.get(2)).concat(";").concat(fileAccessList.get(3)).concat(";");

        return appended;

    }
    
    public static void createNewFile(Scanner scanner) throws IOException{

        System.out.println("Would you like to create a new file? [y/n]");
        String createAFile = scanner.nextLine();

        if(createAFile.equalsIgnoreCase("n")){
            return;
        }else if(createAFile.equalsIgnoreCase("y")){

            System.out.println("Whats the name of the new file?");
            String filename = scanner.nextLine();

            //create new file
            File file = new File(filename + ".txt");
            file.createNewFile();

            //write accesses to file
            FileWriter writer = new FileWriter("new2.txt");
            BufferedReader bfro = new BufferedReader(new FileReader("new.txt"));
            String st;

            while ((st = bfro.readLine()) != null){
                if(st.startsWith("name")){
                    writer.write(st + filename + ";\n");
                }else
                    writer.write(st + "r;\n");
            }
            
            writer.close();
            bfro.close();

            //rename new file to old's name
            File newFile = new File("new2.txt");
            File oldFile = new File("new.txt");
            oldFile.delete();
            System.out.println(newFile.renameTo(new File("new.txt")));

        }

    }


}
