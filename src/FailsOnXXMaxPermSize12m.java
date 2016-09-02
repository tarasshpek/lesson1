import java.util.ArrayList;
import java.util.List;

public class FailsOnXXMaxPermSize12m {

    static final List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            if (i % 1000 == 0){
                System.out.println("Iteration #" + i);
                Thread.sleep(100L);
            }
            list.add(new String(Integer.toString(i)).intern());
        }
    }

}
