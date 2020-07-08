package data;

import CLI.Main;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Log {

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static LocalDateTime now = LocalDateTime.now();

    public static void headerLogger() {
        File dir = new File("./Data/logs");
        if(!dir.exists()){
            dir.mkdirs();
        }
        File log = new File(dir + "/" + Main.currentUser.getUsername() +  ".log");
        log.getParentFile();
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(dir + "/" + Main.currentUser.getUsername() + ".log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write("User:" + Main.currentUser.getUsername() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write("CREATED_AT:" + dtf.format(now) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write("PASSWORD:" + Main.currentUser.getPassword() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bodyLogger(String type, String event){
        File dir = new File("./Data/logs");
        if(!dir.exists()){
            dir.mkdirs();
        }
        File log = new File(dir + "/" + Main.currentUser.getUsername() + ".log");
        log.getParentFile();
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(dir + "/" + Main.currentUser.getUsername() + ".log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write(type + " " + dtf.format(now) + " " + event + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void LastLogger() {
        File dir = new File("./Data/logs");
        if(!dir.exists()){
            dir.mkdirs();
        }
        File log = new File(dir + "/" + Main.currentUser.getUsername() + ".log");
        log.getParentFile();
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sca = null;
        try {
            sca = new Scanner(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> myLog = new ArrayList<>();
        while (sca.hasNextLine()){
            myLog.add(sca.nextLine());
        }
        myLog.add(3, "DELETED_AT:" + dtf.format(now));
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(dir + "/" + Main.currentUser.getUsername() + ".log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String st : myLog){
            try {
                fileWriter.write(st + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}