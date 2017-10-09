import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.LinkedList;
import java.util.Random;


public class Engine extends Canvas implements Runnable{

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private boolean running = false;
    private int tickCount = 0;


    private KeyHandler kh;
    private MouseHandler mh;

    private JFrame frame;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[]pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Random rand = new Random();

    private LinkedList<Ball>ballPit = new LinkedList<>();
    private BallHandler ballHandler;


    public Engine(){
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Physics Engine");
        frame.addWindowListener(new WindowHandler(frame, this));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();


        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ballHandler = new BallHandler(WIDTH, HEIGHT, this);


        kh = new KeyHandler(this);
        mh = new MouseHandler(this);

    }
    public synchronized void start(){
        running = true;
        new Thread(this).start();
    }
    public synchronized void stop(){
        running = false;
        ballHandler.saveBallShow();

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
        System.out.println("");

    }
    public void tick(){

        if(tickCount() % 60 == 0){
            ballHandler.switchColor();
        }
        tickCount++;
        ballHandler.tick();

    }
    BallHandler getBallHandler(){
        return ballHandler;
    }

    private void renderBall(Ball b, Graphics g){
        g.fillOval(WIDTH - b.getX(), HEIGHT - b.getY(), b.getSize(), b.getSize());
    }
    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());




        for(Ball b: ballHandler.getBallPit()){
            Color c = b.getColor();
            g.setColor(c);
            renderBall(b, g);
        }

        g.dispose();
        bs.show();
    }
    public int tickCount(){
        return tickCount;
    }
    Random rand(){
        return rand;
    }


    public static void main(String[]args){
        System.setProperty("sun.java2d.opengl", "true");
        Engine e = new Engine();
        e.start();
        //e.stop();

    }
}
