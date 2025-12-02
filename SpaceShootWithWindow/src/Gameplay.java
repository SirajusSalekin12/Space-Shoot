import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

    boolean play = false;               //flag for game is running or not
    private int score = 0;              //score for player
    private int health = 100;           //player life points

    private Timer timer;
    private int delay = 8;
    private int ticks = 0;              //counter for actionlistener

    Random rand = new Random();

    private int playerX = 310;          //player initial position(vertical position fixed)

    private int shoot = 0;              //number of bullets on the screen

    //bullet objects for 3 bullets on the screen at a time
    private Bullet b1 = null;
    private Bullet b2 = null;
    private Bullet b3 = null;

    //meteor objects for up to 5 meteors on the screen at a time
    private Meteor m1 = null;
    private Meteor m2 = null;
    private Meteor m3 = null;
    private Meteor m4 = null;
    private Meteor m5 = null;

    private int speed = 5;               //speed of falling of the meteors


    Gameplay(){
        addKeyListener(this);
        setFocusable(true);                         //so that this receives keyboard inputs
        setFocusTraversalKeysEnabled(false);        //so that pressing focus changing keys like TAB will not change focus
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g){

        //setting background image
        ImageIcon backgroundIcon = new ImageIcon("space.png");
        Image background = backgroundIcon.getImage();
        Image backgroundResized = background.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(backgroundResized);
        backgroundIcon.paintIcon(this, g, 0, 0);


        //setting player spaceship image
        ImageIcon shipIcon = new ImageIcon("spaceship.png");
        Image ship = shipIcon.getImage();
        Image shipResized = ship.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        shipIcon = new ImageIcon(shipResized);
        shipIcon.paintIcon(this, g, playerX, 500);


        //Initial title screen
        if(!play && health==100){
            g.setColor(Color.yellow);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            g.drawString("Spaceshoot!!", 190, 300);
        }


        if(play) {                              //if game started

            //render bullets if it exists

            if (shoot>0 && b1!=null) {
                b1.drawBullet(g);
                b1.moveBullet();
            }

            //remove bullet object if it goes out of the screen
            if (b1!=null && b1.getY() < 0) {
                shoot--;
                b1 = null;
            }

            if (shoot>0 && b2!=null) {
                b2.drawBullet(g);
                b2.moveBullet();
            }

            if (b2!=null && b2.getY() < 0) {
                shoot--;
                b2 = null;
            }

            if (shoot>0 && b3!=null) {
                b3.drawBullet(g);
                b3.moveBullet();
            }

            if (b3!=null && b3.getY() < 0) {
                shoot--;
                b3 = null;
            }

            //spawn meteor every

            if (ticks % 30 == 0) {
                if (m1 == null) {
                    m1 = createMeteor();
                } else if (m2 == null) {
                    m2 = createMeteor();
                } else if (m3 == null) {
                    m3 = createMeteor();
                } else if (m4 == null) {
                    m4 = createMeteor();
                } else if (m5 == null) {
                    m5 = createMeteor();
                }
            }

            //if meteors spawned draw and make it fall

            if (m1 != null) {
                meteorFall(m1,g);
            }
            if (m2 != null) {
                meteorFall(m2,g);
            }
            if (m3 != null) {
                meteorFall(m3,g);
            }
            if (m4 != null) {
                meteorFall(m4,g);
            }
            if (m5 != null) {
                meteorFall(m5,g);
            }

            //bullet hits meteor

            m1 = meteorGetsHit(m1, b1);
            m2 = meteorGetsHit(m2, b1);
            m3 = meteorGetsHit(m3, b1);
            m4 = meteorGetsHit(m4, b1);
            m5 = meteorGetsHit(m5, b1);

            if(b1!=null && !b1.getExist()){
                b1 = null;
            }

            m1 = meteorGetsHit(m1, b2);
            m2 = meteorGetsHit(m2, b2);
            m3 = meteorGetsHit(m3, b2);
            m4 = meteorGetsHit(m4, b2);
            m5 = meteorGetsHit(m5, b2);

            if(b2!=null && !b2.getExist()){
                b2 = null;
            }

            m1 = meteorGetsHit(m1, b3);
            m2 = meteorGetsHit(m2, b3);
            m3 = meteorGetsHit(m3, b3);
            m4 = meteorGetsHit(m4, b3);
            m5 = meteorGetsHit(m5, b3);

            if(b3!=null && !b3.getExist()){
                b3 = null;
            }

            //spaceship collision

            m1 = shipGetsHit(m1);
            m2 = shipGetsHit(m2);
            m3 = shipGetsHit(m3);
            m4 = shipGetsHit(m4);
            m5 = shipGetsHit(m5);

            //meteors hit the bottom

            m1 = meteorHitsBottom(m1);
            m2 = meteorHitsBottom(m2);
            m3 = meteorHitsBottom(m3);
            m4 = meteorHitsBottom(m4);
            m5 = meteorHitsBottom(m5);

            //score and health

            g.setColor(Color.yellow);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
            g.drawString("Score: " + score, 635, 50);
            g.drawString("Health: " + health, 635, 70);

            if(health <= 0){                                       //game over messages and reset all the variable
                g.setColor(Color.yellow);
                g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                g.drawString("Game Over! :( ", 190, 300);
                g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                g.drawString("Final Score: " + score, 190, 330);
                g.drawString("Press space to play again ", 190, 350);
                health = 100;
                score = 0;
                speed = 5;
                m1 = null;
                m2 = null;
                m3 = null;
                m4 = null;
                m5 = null;
                b1 = null;
                b2 = null;
                b3 = null;
                shoot = 0;
                play = false;
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {            //each tick happens after the timer delay
        if (play) {
            ticks++;

            if (ticks % 150 == 0) {
                speed += 5;
            }

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {               //right arrow when pressed moves rocket to the right
            if(playerX >= 590){
                playerX = 590;
            } else {
                play = true;
                playerX += 10;
            }
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT){          //left arrow when pressed moves rocket to the left
            if(playerX <= 5){
                playerX = 5;
            } else {
                play = true;
                playerX -= 10;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){                //pressing space shoots a bullet up to 3 at a time
            play = true;
            if(shoot<3){
                if(b1 == null) {
                    b1 = new Bullet(playerX + 19, 495);
                    shoot++;
                } else if (b2 == null) {
                    b2 = new Bullet(playerX + 19, 495);
                    shoot++;
                } else if (b3 == null) {
                    b3 = new Bullet(playerX + 19, 495);
                    shoot++;
                }
            }
        }

    }

    public Meteor createMeteor(){
        return new Meteor(rand.nextInt(601), 0, speed);
    }       //creates meteor object

    public void meteorFall(Meteor m, Graphics g){           //draws meteor and updates position
        m.moveMeteor();
        m.drawMeteor(g);
    }

    public Meteor meteorGetsHit(Meteor m, Bullet b){        //if player shoots a meteor, meteor and bullet objects removed, score added
        if (m != null && b!= null && new Rectangle(b.getX(), b.getY(), 10, 10).intersects(new Rectangle(m.getX(), m.getY(), 40, 30))) {
            m = null;
            b.setExist(false);
            shoot--;
            score++;
        }
        return m;
    }

    public Meteor shipGetsHit(Meteor m){                    //health decreases if meteor hits ship and meteor object removed
        if (m != null && new Rectangle(playerX, 500, 50, 50).intersects(new Rectangle(m.getX(), m.getY(), 40, 30))) {
            health -= 20;
            m = null;
        }
        return m;
    }

    public Meteor meteorHitsBottom(Meteor m){               //health decreases and meteor object removed
        if (m != null && m.getY() > 600) {
            m = null;
            health -= 10;
        }
        return m;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
