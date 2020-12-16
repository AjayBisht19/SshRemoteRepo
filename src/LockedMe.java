import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Objects;
import java.util.Scanner;

public class LockedMe {
    public static void main(String[] args) {
        welcomeScreen();
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

    private static void welcomeScreen() {
        System.out.println("==========================================");
        System.out.println("*					*");
        System.out.println("*   Welcome To LockMe.com		*");
        System.out.println("*   Your Personal Digital Locker	*");
        System.out.println("*   Developer - Ajay Bisht      *");
        System.out.println("*   Locked me application keeps your credentials safe and secure *");
        System.out.println("*					*");
        System.out.println("==========================================");
        System.out.println("Please read all the options carefully and provide input as required");
        System.out.println();
        System.out.println();
    }


    // register
    public static void registerUser(){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Username");
        String userName= input.next();
        System.out.println("Enter password");
        String password=input.next();


        FileWriter fileWriter;
        try {
            File file = new File("users.txt");
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
                   boolean b=theDir.mkdir();
                   if (b)
                   System.out.println("Directory created");
                }
                System.out.println("Data has been written successfully..");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println();
        System.out.println("************************");
        System.out.println("1 . List all stored credentials ");
        System.out.println("2 . Add, Search/Read or Delete a credential ");
        System.out.println("3 . Exit Application");
        System.out.println("************************");
        System.out.println();
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
            System.out.println();
            System.out.println();
            System.out.println("****************************************************************");
            System.out.println("  1. Create a credential file ");
            System.out.println("  2. Search/Read a credential file");
            System.out.println("  3. Delete a file");
            System.out.println("  4. Go to the main menu");
            System.out.println("****************************************************************");
            System.out.println();
            System.out.println("------------Enter a valid input------------");
            option2 =sc.nextInt();
            System.out.println();

            switch (option2){
                case 1:
                        createFile(userName);
                        break;
                case 2:
                        readCred(userName);
                        break;
                case 3:
                        deleteCred(userName);
                        break;
                case 4:
                    lockerOptions(userName);
                    break;
                default:
                    System.out.println("***************** Please enter a valid option *****************");
            }

        }while(option2<1 | option2>4);


    }

    // delete a Credential of a user
    private static void deleteCred(String userName) {
        File fileDir = new File("database\\"+userName);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of credential you want to delete--");
        String search = sc.next();
        search=search+".txt";
        boolean found = false;
        if (fileDir.isDirectory()) {
            for (String p : Objects.requireNonNull(fileDir.list())) {
                if (p.equals(search)) {
                    found = true;
                    break;
                }
            }
        }
        if(found){
        File fileDelete = new File("database\\"+userName+"\\"+search);
        boolean b=fileDelete.delete();

        if(b)
            System.out.println("Credential "+search +" deleted" );
        }
        if (!found) {
            System.out.println("This credential is not present in the directory");
        }

    }


    //create user credential file
    private static void createFile(String userName) {

        Scanner sc= new Scanner(System.in);

        try {
            System.out.println("Enter name of your credential");
            String input = sc.next();
            String path = "database\\"+userName+"\\"+input+".txt";
            File theDir = new File(path);
            //File file = new File("database\\"+userName+"\\"+input+".txt");
            FileWriter fileWriter;

            boolean flag = theDir.createNewFile();
            if (flag) {

                System.out.println("Credential file generated");
                System.out.println();
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

    // Search for the credential file and read
    private static void readCred(String userName) {
        File fileDir = new File("database\\"+userName);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of credential you want to search--");
        String search = sc.next();
        search=search+".txt";
        boolean found = false;
        if (fileDir.isDirectory()) {
                for (String p : Objects.requireNonNull(fileDir.list())) {
                    if (p.equals(search)) {
                        System.out.println("This credential is present in the directory");
                        found = true;
                        File filRead = new File("database\\"+userName+"\\"+search);
                        try{
                            Scanner scannerReader = new Scanner(filRead);

                                System.out.println("Username - "+scannerReader.nextLine());
                                System.out.println("Password - "+scannerReader.nextLine());
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }


                    }
                }
        }
                if (!found) {
                    System.out.println("This credential is not present in the directory");
                }



    }
}
