import javax.swing.*;
import java.awt.*;

public class Meteor extends Component {

    private int x;                  //meteor position
    private int y;
    private int speed;              //meteor falling speed

    Meteor(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void drawMeteor(Graphics g)          //paints meteor
    {
        ImageIcon mIcon = new ImageIcon("ast.png");
        Image mImage = mIcon.getImage();
        Image mResized = mImage.getScaledInstance(40,30,Image.SCALE_SMOOTH);
        mIcon = new ImageIcon(mResized);
        mIcon.paintIcon(this, g, x, y);

    }

    public void moveMeteor(){
        y += speed;
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


}
