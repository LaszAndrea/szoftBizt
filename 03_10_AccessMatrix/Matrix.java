

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matrix {


    public static void main(String[] args){

        List<String> access = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);

        try {
            access = FileManagement.readTxt(ManageUser.login(scanner));

            for(String a : access){
                System.out.print(a + " ");
            }

            ManageUser.changePermissions(access, scanner);
            FileManagement.createNewFile(scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();

    }

}

