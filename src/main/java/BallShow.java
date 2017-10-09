import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class BallShow {

    private LinkedList<BallRecord>record = new LinkedList<>();
    private BufferedWriter bufferedWriter;

    public BallShow(File file) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void addBall(BallRecord br){
        record.add(br);
    }
    public void saveShow(){
        for(BallRecord br: record){
            try {
                bufferedWriter.write(br.toString()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
