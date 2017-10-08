import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

    private Engine engine;
    private BallHandler ballHandler;

    public MouseHandler(Engine engine){

        this.engine = engine;
        engine.addMouseListener(this);
        engine.addMouseMotionListener(this);
        this.ballHandler = engine.getBallHandler();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent){
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        ballHandler.setBallOrigin(engine.getWidth() - x,engine.getHeight() - y);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        ballHandler.setBallOrigin(engine.getWidth() - x,engine.getHeight() - y);

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
