public class BallRecord {

    private final int tick;
    private final int x;
    private final int y;
    private final int vx;
    private final int vy;
    private int size;
    private final int c;

    public BallRecord(int tick, int x, int y, int vx, int vy, int size, int c){

        this.tick = tick;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.size = size;
        this.c = c;
    }

    @Override
    public String toString(){
        return "" + tick + " "+ x + " " + y + " " + vx + " "+ vy + " "+ size + " "+ c;
    }
}
