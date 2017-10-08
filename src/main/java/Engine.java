import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class Engine extends Canvas implements Runnable{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public boolean running = false;
    private int tickCount = 0;

    boolean keyHeld = false;
    Color[]ca = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK, Color.CYAN, Color.ORANGE};
    int currentColor = 0;
    int ballSize = 50;

    private KeyHandler kh;

    private JFrame frame;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[]pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Random rand = new Random();

    private LinkedList<Ball>ballPit = new LinkedList<>();

    public Engine(){
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Physics Engine");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        kh = new KeyHandler(this);

    }
    public synchronized void start(){
        running = true;
        new Thread(this).start();
    }
    public synchronized void stop(){
        running = false;
    }

    public void run(){
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60;

        int ticks = 0;
        int frames = 0;

        double delta = 0;

        while(running){
            long now = System.nanoTime();
            delta +=(now - lastTime) / nsPerTick;
            lastTime = now;


            while(delta >= 1){
                ticks++;
                tick();
                render();


                delta-=1;
            }


        }

    }
    public void tick(){

        if(tickCount % 60 == 0){
            switchColor();
        }

        if(keyHeld){
            //int x = rand.nextInt(WIDTH/2)+(WIDTH/4);
            int x = WIDTH/2;
            int y = HEIGHT/2;
            //int y = rand.nextInt(HEIGHT/2)+(HEIGHT/4);
            int xVel = rand.nextInt(10)-5;
            int yVel = rand.nextInt(20)+10;
            ballPit.add(new Ball(x, y, xVel, yVel, ballSize, ca[currentColor]));
        }
        tickCount++;
        for(Iterator<Ball> iter = ballPit.iterator(); iter.hasNext();){
            Ball b = iter.next();
            b.tick();
            if(b.getX() > WIDTH + 100 || b.getX() < -100 || b.getY() > HEIGHT + 100 || b.getY() < -100){
                iter.remove();
            }
        }

    }
    public void incBallSize(){
        ballSize++;
    }
    public void decBallSize(){
        if(ballSize > 1){
            ballSize--;
        }
    }
    public void switchColor(){
        this.currentColor = (currentColor + 1)% ca.length;
    }
    public void renderBall(Ball b, Graphics g){
        g.fillOval(WIDTH - b.getX(), HEIGHT - b.getY(), b.getSize(), b.getSize());
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());




        for(Ball b: ballPit){
            Color c = b.getColor();
            g.setColor(c);
            renderBall(b, g);
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[]args){
        System.setProperty("sun.java2d.opengl", "true");
        new Engine().start();
    }
}
