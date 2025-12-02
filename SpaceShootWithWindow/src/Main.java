import javax.swing.*;

public class Main {
    public static void main() {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(10,10,710,610);
        obj.setTitle("Space Shoot");
        obj.add(gameplay);
        obj.setResizable(true);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}