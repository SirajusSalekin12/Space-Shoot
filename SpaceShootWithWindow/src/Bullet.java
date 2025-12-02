import javax.swing.*;
import java.awt.*;

public class Bullet extends Component{

    private int x;          //bullet position
    private int y;

    private boolean exist = false;      //flag to see if bullet is destroyed or not

    Bullet(int x, int y){
        this.x = x;
        this.y = y;
        exist = true;
    }

    public void drawBullet(Graphics g)          //paints bullet
    {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 10, 10);
    }

    public void moveBullet(){
        y -= 20;
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

    public void setExist(boolean exist){
        this.exist = exist;
    }

    public boolean getExist(){
        return this.exist;
    }
}
