public class Ball {

    private int x;
    private int y;

    private int xVel;
    private int yVel;

    private int yAcc = -1;

    public Ball(int x, int y, int xVel, int yVel){
        this.x = x;
        this.y=y;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void tick(){
        this.x += xVel;
        this.y +=yVel;

        //this.yVel += yAcc;
    }
}
