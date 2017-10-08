import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class BallHandler {

    private final int width;
    private final int height;
    private Engine engine;

    boolean keyHeld = false;

    Color[]ca = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK, Color.CYAN, Color.ORANGE};
    int currentColor = 0;
    int ballSize = 50;
    int ballX;
    int ballY;

    private LinkedList<Ball> ballPit = new LinkedList<>();


    public BallHandler(int width, int height, Engine engine){
        this.width = width;
        this.height = height;
        this.engine = engine;
        this.ballX = width/2;
        this.ballY = height/2;
    }
    public void tick(){
        if(keyHeld){
            //int x = rand.nextInt(WIDTH/2)+(WIDTH/4);

            //int y = rand.nextInt(HEIGHT/2)+(HEIGHT/4);
            int xVel = engine.rand().nextInt(10)-5;
            int yVel = engine.rand().nextInt(20)+10;
            ballPit.add(new Ball(ballX, ballY, xVel, yVel, ballSize, ca[currentColor]));
        }
        for(Iterator<Ball> iter = ballPit.iterator(); iter.hasNext();){
            Ball b = iter.next();
            b.tick();
            if(b.getX() > width + 100 || b.getX() < -100 || b.getY() > height + 100 || b.getY() < -100){
                iter.remove();
            }
        }
    }
    LinkedList<Ball>getBallPit(){
        return ballPit;
    }
     void switchColor(){
        currentColor = (currentColor + 1)% ca.length;
    }
    public void decBallSize(){
        if(ballSize > 1){
            ballSize--;
        }
    }
    public void incBallSize(){
        ballSize++;

    }
    public void setBallOrigin(int x, int y){
        this.ballX = x;
        this.ballY = y;
    }




}
