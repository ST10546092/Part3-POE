/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
/**
 *
 * @author Student
 */
  package RegistrationLogin;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Messaging {
    public static String myRecipientCell;
    public static int numMessagesSent = 0;
    public static String[] myMessageIDs = new String[100];
    public static String[] mySentMessages = new String[100];

    public static boolean checkMessageID(String messageID){
        return messageID.length() <= 10;
    }

    public static void main(String[] args){
        int choice;
        System.out.println("Welcome to QuickChat");
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Option 1 : Send Messages ");
            System.out.println("Option 2 : Coming Soon.");
            System.out.println("Option 3 : Quit");
            System.out.println("Option 4: Stored Messages");
            System.out.println("Enter.");
            choice = scanner.nextInt();
            if (choice == 1){
                System.out.println("Send Messages");
                message();
            } else if (choice == 2){
                System.out.println("Coming Soon");
            } else if (choice == 3){
                System.out.println("Exit.");
            } else if (choice ==4){
                StoreData.populateTestData();
                StoreData.storedMessagesMenu();
            }else{
                System.out.println("error: incorrect value");
            }
        } while(choice != 3);
    }

    public static void message(){
        Scanner scan = new Scanner(System.in);
        System.out.print("How many messages: ");
        int n = scan.nextInt();
        scan.nextLine();
        Random ran = new Random();
        System.out.print("Enter cell number (+27...): ");
        String resNo = scan.nextLine();
        if(!checkRecipientCell(resNo)){
            System.out.println("Invalid number");
            return;
        }
        for (int i =0; i<n; i++){
            int messageID = ran.nextInt(1000000000);
            System.out.print("Enter message: ");
            String msg = scan.nextLine();
            String idStr = String.valueOf(messageID);
            String hash = createMessageHash(idStr, i, msg);
            System.out.println(hash);
            myMessageIDs[numMessagesSent] = idStr;
            mySentMessages[numMessagesSent] = msg;
            numMessagesSent++;
        }
    }

    public static boolean checkRecipientCell(String recipientCell){
        if(recipientCell.startsWith("+27") && recipientCell.length() == 12){
            System.out.println("correct recipientCell");
            return true;
        } else {
            System.out.println("incorrect recipientCell");
            return false;
        }
    }

    public static String createMessageHash(String messageID, int messageNum, String message){
        String messageHash = messageID.charAt(0) + ":" + messageID.charAt(1) + ":" + Integer.toString(messageNum) + ":";
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length-1];
        messageHash = messageHash + firstWord + lastWord;
        return messageHash;
    }

    public static String sentMessage(String userAction, String message){
        if(userAction.equals("sent")){
            mySentMessages[numMessagesSent] = message;
            return "Message successfully sent.";
        }
        else if(userAction.equals("disregard")){
            return "Press 0 to delete message";
        }
        else if (userAction.equals("Store")){
            storeMessage(message);
            return "Message successfully stored";
        }
        else{
            return "Invalid request";
        }
    }

    public static void printMessages(){
        for(int i=0; i < numMessagesSent; i++){
            String messageID = myMessageIDs[i];
            int messageNum = i;
            String message = mySentMessages[i];
            String messageHash = createMessageHash(messageID, messageNum, message);
            System.out.println(messageID + " " + messageHash + " " + myRecipientCell + " " + message);
        }
    }

    public static int returnTotalMessage(){
        return numMessagesSent;
    }

    public static void storeMessage(String message){
        try {
            FileWriter file = new FileWriter("messages.json");
            file.write(message);
            file.close();
            System.out.println("Message successfully stored to messages.json");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean checkMessageLength(String message){
        if(message.length() <= 250){
            System.out.println("Message is valid");
            return true;
        } else {
            System.out.println("Please enter a message of less than 250 characters");
            return false;
        }
    }
}