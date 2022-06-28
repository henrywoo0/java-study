package basic.innerclass;

class Outer {
    int outNum = 100;
    static int sNum = 200;

    Runnable getRunnable(int i) {
        int num = 100;

        class MyRunnable implements Runnable {
            int localNum = 10;
        }
    }
}

public class LocalInnerTest {

}
