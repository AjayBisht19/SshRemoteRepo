import java.io.File;
import java.io.FileNotFoundException;
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
               loginUser();
                break;
            default :
                System.out.println("Please select 1 Or 2");
                break;
        }
    }


    // register
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


    //login
    public static void loginUser() {
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Username");
        String userName= input.next();
        System.out.println("Enter password");
        String password=input.next();
        File file = new File("users.txt");
        try{
        Scanner scannerReader = new Scanner(file);
            boolean findEmail=false;
            boolean findPassword=false;
            while(scannerReader.hasNextLine()) {
                if(scannerReader.nextLine().equals(userName))
                {
                    findEmail=true;
                    if(scannerReader.nextLine().equals(password)){
                        findPassword=true;
                    }
                }
            }
            if (findEmail & findPassword){
               //lockerOptions(userName);
            }
            else if(findEmail & !(findPassword))
                System.out.println("Password incorrect");
            else
                System.out.println("User not found");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }


    }

    private static void lockerOptions(String userName) {
        String path = "database\\"+userName;
        File theDir = new File(path);
      //  System.out.println(Arrays.asList(theDir.list()));
        System.out.println("1 . List all stored credentials ");
        System.out.println("2 . Search credentials ");
        System.out.println("3 . Delete credentials ");
    }


}
