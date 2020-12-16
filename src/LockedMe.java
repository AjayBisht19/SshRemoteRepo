import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("1 . Registration ");
        System.out.println("2 . Login ");
        int option = input.nextInt();
        switch(option) {
            case 1 :
                registerUser();
                break;
            case 2 :
               //loginUser();
                break;
            default :
                System.out.println("Please select 1 Or 2");
                break;
        }
    }

    public static void registerUser(){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Username");
        String userName= input.next();
        System.out.println("Enter password");
        String password=input.next();


        FileWriter fileWriter = null;
        try {
            File file = new File("users.txt");
            fileWriter = new FileWriter("users.txt",true);
            Scanner scannerReader = new Scanner(file);
            boolean find=false;
            while(scannerReader.hasNextLine()) {
               if(scannerReader.nextLine().equals(userName))
               {
                   System.out.println("User already exist");
                   find=true;
                   break;
               }
            }

            if (!find){
            fileWriter = new FileWriter("users.txt",true);
            fileWriter.write('\n');
            fileWriter.write(userName);
            fileWriter.write('\n');
            fileWriter.write(password);
                String path = "database\\"+userName;
                File theDir = new File(path);
                //System.out.println(Arrays.asList(theDir.list()));
                if (!theDir.exists()){
                   theDir.mkdir();
                   System.out.println("Directory created");
                }
                System.out.println("Data has been written successfully..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
