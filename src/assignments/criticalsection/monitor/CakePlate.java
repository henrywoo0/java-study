package assignments.criticalsection.monitor;

public class CakePlate {
	private int breadCount = 0;
	public CakePlate() {}

	public synchronized void makeBread() {
		if(breadCount>=10) {
			try {
				System.out.println("���� ���´�.");
				wait();								// ���
			} catch(InterruptedException ire) {}
		}
		breadCount++;
		System.out.println("���� 1�� �� ���� ��: " + breadCount + "��");
		this.notifyAll();							// ��� ����
	}

	public synchronized void eatBread() {
		if(breadCount<1) {
			try {
				System.out.println("���� ���ڶ� ��ٸ�");
				wait();
			} catch(InterruptedException ire) {}
		}
		breadCount--;
		System.out.println("���� 1�� ���� ��: " + breadCount + "��");
		this.notifyAll();
	}
}
