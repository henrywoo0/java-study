package assignments.criticalsection.monitor;

public class CakePlate {
	private int breadCount = 0;
	public CakePlate() {}

	public synchronized void makeBread() {
		if(breadCount>=10) {
			try {
				System.out.println("빵을 10개 이상 만들 수 없습니다.");
				wait();
			} catch(InterruptedException ire) {}
		}
		breadCount++;
		System.out.println(breadCount + "개의 빵이 남았습니다.");
		this.notifyAll();
	}

	public synchronized void eatBread() {
		if(breadCount<1) {
			try {
				System.out.println("먹을 빵이 없습니다.");
				wait();
			} catch(InterruptedException ire) {}
		}
		breadCount--;
		System.out.println(breadCount + "개의 빵이 남았습니다.");
		this.notifyAll();
	}
}
