package API;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class SMSUtil {
    public static final String ACCOUNT_SID = "AC18f0474fed3312dea0aabb4161679485";
    public static final String AUTH_TOKEN = "a01089ee80f6b4fa09a37b537d962fdb";

    public static void sendSMS(String toPhoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("+216 26 360 693"),
                new PhoneNumber("+12763303738"),
                "Bonjour Youssef verifier votre email pour le code QR ")
            .create();

        System.out.println(message.getSid());
    }

    public static void sendMMS(String toPhoneNumber, BufferedImage image) throws IOException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Path tempFilePath = Paths.get(System.getProperty("user.home"), "Desktop", "temp.png");

        ImageIO.write(image, "png", tempFilePath.toFile());

        Message message = Message.creator(
                new PhoneNumber("+216 26 360 693"),
                new PhoneNumber("+12763303738"),
                "")
            .setMediaUrl(Paths.get(tempFilePath.toAbsolutePath().toString()).toUri())
            .create();

        System.out.println(message.getSid());

        Files.delete(tempFilePath);
    }

    public static void sendQRCodeMMS(String toPhoneNumber, String text, int width, int height) throws WriterException, IOException {
        BufferedImage image = generateQRCodeImage(text, width, height);
        sendMMS(toPhoneNumber, image);
    }

    private static BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        Hashtable<EncodeHintType, Object> hashtableHints = new Hashtable<>();
        hashtableHints.putAll(hints);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hashtableHints); 

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = byteMatrix.get(x, y) == 0 ? 0xFFFFFF : 0x000000;
                image.setRGB(x, y, color);
            }
        }
        System.out.println(image);
        return image;
    }

 /*   public static void envoyerQRCodeParMMS(String toPhoneNumber, BufferedImage qrCodeImage) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
        ImageIO.write(qrCodeImage, "png", os);
    } catch (IOException e) {
        e.printStackTrace();
    }
    byte[] qrCodeImageData = os.toByteArray();

    Message message = Message.creator(
            new PhoneNumber("+21626360693"),
            new PhoneNumber("+1276330 3738"),
            "")
        .setMediaUrl(MediaCreator.uploader(new ByteArrayInputStream(qrCodeImageData)))
        .create();

    System.out.println(message.getSid());*/
    }
    