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
                System.out.println("User found");
               lockerOptions(userName);
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
        Scanner input= new Scanner(System.in);
        String path = "database\\"+userName;
        File theDir = new File(path);

        System.out.println("1 . List all stored credentials ");
        System.out.println("2 . Add, Delete or Search a credential ");
        System.out.println("3 . Exit Application");
        int option = input.nextInt();
        switch(option){
            case 1:
                for (String s:theDir.list()) {
                    System.out.println(s);
                }
                break;
            case 2:
                businessFunctions(userName);
                break;
            case 3:
                System.exit(0);
        }
    }


    // Business functions ( case 2 of users)
    private static void businessFunctions(String userName) {
        Scanner sc= new Scanner(System.in);

        int option2;
        do{
            System.out.println("****************************************************************");
            System.out.println("  1. Create a file ");
            System.out.println("  2. Search a file");
            System.out.println("  3. Delete a file");
            System.out.println("  4. Go to the main menu");
            System.out.println("****************************************************************");

            System.out.println("------------Enter a valid input------------");
            option2 =sc.nextInt();

            switch (option2){
                case 1:
                        createFile(userName);break;

                case 2:

                case 3:

                case 4:
                    break;
                default:
                    System.out.println("***************** Please enter a valid option *****************");
            }

        }while(option2<1 | option2>4);


    }

    private static void createFile(String userName) {

        Scanner sc= new Scanner(System.in);

        try {
            System.out.println("Enter name of your credential");
            String input = sc.next();
            String path = "database\\"+userName+"\\"+input+".txt";
            File theDir = new File(path);
            //File file = new File("database\\"+userName+"\\"+input+".txt");
            FileWriter fileWriter = null;

            boolean flag = theDir.createNewFile();
            if (flag) {

                System.out.println("Credential file generated");

                System.out.println("Enter username of credential");
                String credUserName= sc.next();
                System.out.println("Enter password of your credential");
                String credPassword= sc.next();
                fileWriter = new FileWriter(theDir,true);
                fileWriter.write(credUserName);
                fileWriter.write('\n');
                fileWriter.write(credPassword);
                System.out.println("Data has been written successfully..");
                fileWriter.close();
            } else {
                System.out.println("This credential already exist");
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
    }


}
