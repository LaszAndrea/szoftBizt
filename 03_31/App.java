import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    static List<String> databaseList = new ArrayList<String>();
    static List<String> virusesTxtList = new ArrayList<>();
    static List<Integer> virusesCounted = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readInDatabase();
        declareVirusesDataList();
        lookForSignatures(databaseList.get(0), virusesTxtList);
        createReport(databaseList.get(0));
    }

    public static void declareVirusesDataList(){
        virusesTxtList.add("virus.txt");
        virusesTxtList.add("virus2.txt");
        virusesTxtList.add("random_file.bin");
    }


    public static void readInDatabase() throws FileNotFoundException{

        BufferedReader bfro = new BufferedReader(new FileReader("adatbazis.txt"));
        String st;
    
        try {
            while ((st = bfro.readLine()) != null){
                    
                databaseList = new ArrayList<String>(Arrays.asList(st.split(";")));

            }

            bfro.close();    
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    
    }

    public static void lookForSignatures(String signature, List<String> virusesList) throws FileNotFoundException{

        for(int j=0; j<virusesList.size(); j++){

            BufferedReader bfro = new BufferedReader(new FileReader(virusesList.get(j)));
            String st;
            int virusCounter = 0;
        
            try {
                while ((st = bfro.readLine()) != null){

                    int signatureLength = signature.length();
                        
                    for(int i=0; signatureLength<=st.length(); i++){

                        if((st.substring(i, signatureLength)).contains(signature)){
                            virusCounter++;
                        }
        
                        signatureLength++;
                    }

                }

                bfro.close();    

                virusesCounted.add(virusCounter);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createReport(String signature) throws IOException{

        try {
            FileWriter myWriter = new FileWriter("virusScannerReport.txt");

            myWriter.append("Looked files: "+ virusesTxtList.size() + "\n");
            for(int i=0; i<virusesTxtList.size(); i++){

                if(virusesCounted.get(i) == 0){
                    myWriter.append("The " + virusesTxtList.get(i) + ".txt file is safe!\n");
                }else{
                    myWriter.append("All virus \"" + signature + "\" signatures found in "+ virusesTxtList.get(i) + ".txt list: " + virusesCounted.get(i) + "\n");
                }
            }

            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            throw e;
        }

    }

}


