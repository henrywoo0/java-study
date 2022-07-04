package assignments.criticalsection.diningphilosopher;
import java.util.concurrent.Semaphore;

public class DiningPhilosopher extends Thread{
	
	int id;
    Semaphore lstick, rstick;
    DiningPhilosopher(int id, Semaphore lstick, Semaphore rstick) {
        this.id = id;
        this.lstick = lstick;
        this.rstick = rstick;
    }
    public void run() {
        try {
            while(true) {
                lstick.acquire();
                rstick.acquire();
                eating();
                rstick.release();
                lstick.release();
                thinking();
            }
        } catch (InterruptedException e) {}
    }
    
    void eating() {
        System.out.println("[" + id + "] eat");
    }
    
    void thinking() {
        System.out.println("[" + id + "] think");
    }
}
