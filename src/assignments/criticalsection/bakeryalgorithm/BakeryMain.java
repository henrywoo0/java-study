package assignments.criticalsection.bakeryalgorithm;

public class BakeryMain {

    public static void main(String[] args) {

        for (int i = 0; i < BakeryThread.numberOfThreads; i++) {
            BakeryThread.choosing[i] = false;
            BakeryThread.ticket[i] = 0;
        }

        BakeryThread[] threads = new BakeryThread[BakeryThread.numberOfThreads];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new BakeryThread(i);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}