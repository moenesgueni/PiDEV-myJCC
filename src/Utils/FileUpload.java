package Utils;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Noter qu'il faut lancer le serveur en premier ! ************
//Le code du serveur est en commentaire ci-dessous
public class FileUpload {

    //filePath = chemain du fichier su la machine cliente
    //destinationFolderAndNewName = dossier et nom de destination dans htdocs=> exemple : contrats\\mercedess.pdf ou profile\\351.png
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

}