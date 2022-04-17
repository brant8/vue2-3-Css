package Demo01;

import java.sql.SQLOutput;
import java.util.Scanner;

public class DemoRegister {
    static String[] usernames = {"zhang","li","wang"};
    public static void main(String[] args) /*throws RegisterException */{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your username: ");
        String username = sc.next();
        checkUsername(username);
    }

    public static void checkUsername(String username) /* throws RegisterException */{
        for(String name : usernames){
            if(name.equals(username))  {
                try {
                    throw new RegisterException("The username has been registered...");
                } catch (RegisterException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        System.out.println("Succeed Registered..");
    }
}
