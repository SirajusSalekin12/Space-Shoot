import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Home {

    private JFrame frmCms;

    //Launch the application

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home window = new Home();
                    window.frmCms.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Create the application

    public Home() {
        initialize();
    }

    //Initialize the contents of the frame
    private void initialize() {
        frmCms = new JFrame();
        frmCms.setTitle("Spaceshoot");
        frmCms.setBounds(100, 100, 634, 416);
        frmCms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCms.getContentPane().setLayout(null);

        JButton btnLogin = new JButton("Login");
        Border emptyBorder = BorderFactory.createEmptyBorder();
    	btnLogin.setBorder(emptyBorder);
        btnLogin.setIcon(new ImageIcon("My project.jpg"));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frmCms.dispose();
                Main mainWindow = new Main();                       //executes main game
                mainWindow.main();

            }
        });
        btnLogin.setBounds(314, 150, 79, 79);
        frmCms.getContentPane().add(btnLogin);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("abstract-planet-space-purple-wallpaper-preview.jpg"));
        lblNewLabel.setBounds(0, 0, 610, 379);
        frmCms.getContentPane().add(lblNewLabel);
    }
}

