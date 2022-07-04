package assignments.criticalsection.semaphore;

public class SemaphoreMain {

    public static void main(String[] args) {
        SemaphoreThread m1 = new SemaphoreThread();

        Thread t1 = new Thread(m1);
        Thread t2 = new Thread(m1);
        Thread t3 = new Thread(m1);

        t1.start();
        t2.start();
        t3.start();
    }

}
