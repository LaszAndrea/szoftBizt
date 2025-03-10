import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class MatrixTester {

     @Test
     public void testReadingInTxt() throws IOException{
        
        List<String> access = new ArrayList<String>();
        List<String> expectedList = new ArrayList<>();
        expectedList.add("David");
        expectedList.add(" ");
        expectedList.add(" ");
        expectedList.add("rw");
        expectedList.add("ok");

        access = FileManagement.readTxt("David");
        Assert.assertEquals(expectedList, access);

     }

     @Test
     public void testIfUserExists() throws IOException{

        IOException e = assertThrows(IOException.class, ()-> FileManagement.readTxt("asdsadsf"));
        assertTrue(e.getMessage().contains("User does not exits"));

     }

    @Test
    public void testIfAccessChanged() throws IOException {
        
        Scanner sc = new Scanner(System.in);

        IOException thrown = assertThrows(IOException.class, () -> {
            FileManagement.addNewAccess(sc, "fileA.txt");
        });

        assertTrue(thrown.getMessage().contains("Cant write to file"));

    }

}
