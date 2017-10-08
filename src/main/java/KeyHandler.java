import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private Engine engine;
    private BallHandler ballHandler;

    public KeyHandler(Engine engine){
        this.engine = engine;
        engine.addKeyListener(this);
        this.ballHandler = engine.getBallHandler();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar()=='q'){
            ballHandler.switchColor();
        }
        if(keyEvent.getKeyChar() == 'w'){
            ballHandler.incBallSize();
        }
        if(keyEvent.getKeyChar() == 's'){
            ballHandler.decBallSize();
        }
        ballHandler.keyHeld = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        ballHandler.keyHeld = false;
    }
}
