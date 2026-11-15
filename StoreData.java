package RegistrationLogin;
import java.util.Scanner;
import java.util.ArrayList;
 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class StoreData {
    public static ArrayList <String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
     public static ArrayList<String> sentRecipients = new ArrayList<>();
    public static ArrayList<String> storedRecipients = new ArrayList<>();
    public static ArrayList<String> allRecipients = new ArrayList<>();
    public static ArrayList<String> allMessages = new ArrayList<>();

    
    public static void addToSent (String message, String id, String hash, String recipient){
       sentMessages. add(message);
       messageIDs.add(id);
       messageHashes.add(hash);
       sentRecipients.add(recipient);
       allRecipients.add(recipient);
       allMessages.add(message);
    }
    public static void addToDisregarded(String message, String id, String hash, String recipient){
        disregardedMessages.add(message);
        messageIDs.add(id);
        messageHashes.add(hash);
        allRecipients.add(recipient);
        allMessages.add(message);
            
    }
    public static void addToStored(String message, String id, String hash, String recipient){
        storedMessages. add(message);
        messageIDs.add(id);
        messageHashes.add(hash);
        storedRecipients.add(recipient);
        allRecipients.add(recipient);
        allMessages.add(message);
    }
    
    public static String displayStoredSenderRecipient(){
        String out = "";
        for(int i =0; i< storedMessages.size(); i++){
            out += "Recipient:" + 
                    storedRecipients.get(i)+"| Message: " +
                    storedMessages.get(i)+ "\n";
        }
        return out;
    }
    public static String displayLongestMessage(){
        String longest = "";
        for(String m: storedMessages){
            if(m.length()> longest.length())longest =m;
           
        } 
        return longest;
    }
    public static String searchByMessageID(String searchID){
        for(int i =0; i< messageIDs.size(); i++){
            if(messageIDs.get(i).equals(searchID)){
                return "Recipient:" +
                        allRecipients.get(i)+ "\nMessage:"+
                        allMessages.get(i);
            }
        }
        return "Message ID not found.";
    }
    public static String searchByRecipient(String recipient){
        String out = "";
        for(int i=0; i< allRecipients.size(); i++){
            if(allRecipients.get(i).equals(recipient)){
                out += allMessages.get(i) + "\n";
            }
        }
        if(out.equals("")) return "No messages found for this recipient.";
        return out;
    }
    public static String deleteByHash(String hash){
        for(int i =0; i< messageHashes.size(); i++){
        if(messageHashes.get(i).equals(hash)){
            String deleted = allMessages.get(i);
            messageHashes.remove(i);
            messageIDs.remove(i);
            allMessages.remove(i);
            storedMessages.remove(deleted);
            return "Message: \" "+ deleted + "\" successfully deleted.";
                   
        }
    }
        return "Message Hash not found.";
    }
    public static String displayReport(){
        String report = "=== STORED / SENT MESSAGES REPORT ===\n";
        for(int i =0; i< allMessages.size(); i++){
            report += "Message hash:" + 
                    messageHashes.get(i)
                    + "\nRecipient: " + 
                    allRecipients.get(i) 
                    +"\nMessage:" +
                    allMessages.get(i)+ "\n\n";
        }
        return report;
    }
    public static void storedMessagesMenu(){
        Scanner scan = new Scanner (System.in);
        int choice;
        do{
            System.out.println("Stored Messages Menu");
            System.out.println("Option 1: Display Sender & Recipient");
            System.out.println("Option 2: Display Longest Message");
            System.out.println("Option 3: Search by Message ID");
            System.out.println("Option 4: Search by Recipient");
            System.out.println("Option 5: Delete by Hash");
            System.out.println("Option 6: Display Report");
            System.out.println("option 7: Back");
            System.out.println("Enter:");
            choice = scan.nextInt();
            scan.nextLine();
            if(choice == 1){
                System.out.println(displayStoredSenderRecipient());
                System.out.println("---Press ENTER to continue---");
                scan.nextLine();
            }
                else if (choice == 2){
                System.out.println("Longest Message: \n" + displayLongestMessage());
                System.out.println("---Press ENTER to continue---");
                scan.nextLine();}
            else if (choice == 3)
            { System.out.println("Enter Message ID:");
            System.out.println(searchByMessageID (scan.nextLine()));}
            else if (choice ==4)
            {System.out.println("Enter recipient:");
            System.out.println(searchByRecipient(scan.nextLine()));}
            else if (choice ==5)
            {System.out.println("Enter Message Hash:");
            System.out.println(deleteByHash(scan.nextLine()));}
            else if (choice ==6){
                System.out.println(displayReport());
                System.out.println("---Press ENTER to continue---");
                scan.nextLine();}
            }   while (choice !=7);
            
        }
    public static void populateTestData(){
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        storedRecipients.clear();
        allRecipients.clear();
        allMessages.clear();
        
        addToSent("Did you get the cake?", "2783455789",
      
        Messaging.createMessageHash("2783455789", 0, "Did you get the cake?"), "+27834557896");
             addToStored("Where are you? You are late! I have asked you to be on time.", "+2783455789",
                
       Messaging.createMessageHash("2783888456", 1, "Where are you? You are late! I have asked you to be on time."), "+27838884567");
               addToDisregarded("Yohooo, I am at your gate.", "2783448456",
        
       Messaging.createMessageHash("2783448456", 2, "Yohooo, I am at your gate."), "+27834484567");
               addToSent("It is dinner time!", "+27838884567",
                       
       Messaging.createMessageHash("+27838884567", 3, "It is dinner time!"), "+27838884567");
               addToStored("Ok I am leaving without you.", "2783888457",
             
       Messaging.createMessageHash("2783888457", 4, "Ok, I am leaving without you."),"+27838884567");
       
     System.out.println("DEBUG POPULATE RAN- stored size=" + storedMessages.size());
    }
    
}
