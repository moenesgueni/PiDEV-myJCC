/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author dhia
 */
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationUtil {

    public static void showNotification(String title, String message) {
        

        // load an image for the icon
        Image image = Toolkit.getDefaultToolkit().createImage("http://localhost/myjcc/logos/myjcc.png");

        // create a system tray object
        SystemTray tray = SystemTray.getSystemTray();

        // create a tray icon with an image, tooltip text, and a popup menu
        TrayIcon trayIcon = new TrayIcon(image, "MyJcc");

        // add an action listener to the tray icon
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // do something when the tray icon is clicked
            }
        });

        try {
            // add the tray icon to the system tray
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }

        // show a notification with a title, message, and notification type
        trayIcon.displayMessage(title, message, MessageType.INFO);
    }
}
