import java.awt.*;

public class Ball {

    private int x;
    private int y;

    private int xVel;
    private int yVel;

    private int yAcc = -1;
    private int size;

    private Color color;

    public Ball(int x, int y, int xVel, int yVel, int size, Color color){
        this.x = x;
        this.y=y;
        this.xVel = xVel;
        this.yVel = yVel;
        this.size = size;
        this.color = color;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Color getColor(){return color;}
    public int getSize(){return size;}

    public void tick(){
        this.x += xVel;
        this.y +=yVel;

        this.yVel += yAcc;
    }
}
