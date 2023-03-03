/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author youssef
 */
public class FileUpload {
       public static void uploadFile(String filePath, String destinationFolderAndNewName) throws Exception {
        Socket soc;
        File file = null;
        soc = new Socket("localhost", 4000);
        System.out.println("Client is running. ");

        try {
            System.out.println("Reading file from disk. ");
            file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Send file name and extension first
            DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
            dos.writeUTF(destinationFolderAndNewName);

            // Send file data
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            baos.write(bytes);
            baos.flush();
            bytes = baos.toByteArray();

            OutputStream out = soc.getOutputStream();
            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);

            System.out.println("File sent to server. ");
            dos.close();
            out.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            soc.close();
        }
        soc.close();
    }


//Créer un simple nouveau projet java dont la Classe Main est :
/*
package servermyjcc;
import java.net.*;
import java.io.*;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
public class ServerMyJCC {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4000);
        System.out.println("Server is running. ");
        while (true) {
            Socket soc = null;
            try {
                soc = ss.accept();
                System.out.println("Client connected. ");
                InputStream in = soc.getInputStream();
                DataInputStream dis = new DataInputStream(in);
                String destinationFolderAndNewName = dis.readUTF();
                int len = dis.readInt();
                byte[] data = new byte[len];
                dis.readFully(data);
                System.out.println("Image received from client. ");
                FileOutputStream fos = new FileOutputStream("C:\\xampp\\htdocs\\myjcc\\" + destinationFolderAndNewName);
                fos.write(data);
                fos.close();
                dis.close();
                System.out.println("Image saved to server. ");
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                soc.close();
            }
        }
    }*/
    
    
}
