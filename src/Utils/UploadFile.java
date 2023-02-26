package Utils;

import java.io.*;
import java.net.*;

public class UploadFile {

    //private String fileUploadUrl;
    //private String filePath;
    private File file;
    private URL url;

    public UploadFile(String fileUploadUrl, String filePath) throws Exception {
        // The URL of the remote file upload endpoint
        //this.fileUploadUrl = "http://localhost/myjcc/contrats";
        //this.fileUploadUrl = fileUploadUrl;

        // The file to upload
        //this.filePath = "C:/Users/Marwen/Desktop/test.txt";
        //this.filePath = filePath;
        this.file = new File(filePath);

        // Create the HTTP connection
        url = new URL(fileUploadUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set the HTTP headers
        String boundary = "===" + System.currentTimeMillis() + "===";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("User-Agent", "Java File Upload");

        // Open the output stream and write the HTTP request body
        OutputStream outputStream = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

        // Write the file part
        writer.println("--" + boundary);
        writer.println("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"");
        writer.println("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()));
        writer.println();

        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.println();

        // Write the end boundary
        writer.println("--" + boundary + "--");

        // Close the output stream and get the HTTP response
        writer.close();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("File uploaded successfully.");
            System.out.println("Destination path: " + fileUploadUrl);
            System.out.println("Filename: " + file.getName());
        } else {
            System.out.println("File upload failed with HTTP error code " + responseCode + ".");
        }

        connection.disconnect();
    }
}
