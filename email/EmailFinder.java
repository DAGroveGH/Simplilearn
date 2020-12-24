package email;

import java.util.Scanner;

public class EmailFinder {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);




        EmailList emailList = new EmailList(10);
        emailList.addEmail("generic@standard.com");
        emailList.addEmail("test@test.com");
        emailList.addEmail("xyz123@company.org");
        emailList.addEmail("hip@hop.io");
        emailList.addEmail("foo.far@woo.gov");

        emailList.printEmails();

        System.out.println("Please input an email address to search for:");
        String search = sc.nextLine();

        boolean foundMatch = emailList.foundMatch(search);
        if(foundMatch) {
            System.out.println(search + " is an email");
        } else {
            System.out.println(search + " is not an email");
        }
        sc.close();
    }
}
