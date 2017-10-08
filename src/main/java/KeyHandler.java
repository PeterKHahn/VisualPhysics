import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    Engine engine;

    public KeyHandler(Engine engine){
        this.engine = engine;
        engine.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar()=='q'){
            engine.switchColor();
        }
        if(keyEvent.getKeyChar() == 'w'){
            engine.incBallSize();
        }
        if(keyEvent.getKeyChar() == 's'){
            engine.decBallSize();
        }
        engine.keyHeld = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        engine.keyHeld = false;
    }
}
