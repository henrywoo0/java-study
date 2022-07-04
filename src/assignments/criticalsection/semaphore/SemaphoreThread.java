package assignments.criticalsection.semaphore;
import java.util.concurrent.Semaphore;

public class SemaphoreThread implements Runnable{

	private int i = 0;
	private Semaphore sem = new Semaphore(1);

	@Override
	public void run() {

		while(i<500) {
			try {
				sem.acquire();
				for(int j=0;j<2000;j++);
				System.out.printf((Thread.currentThread()).getName() + " i = " + i);
				System.out.println();
				i++;
				sem.release();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
