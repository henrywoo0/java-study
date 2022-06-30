package assignments.criticalsection.bakeryalgorithm;

public class BakeryThread extends Thread {

    public int thread_id;
    public static final int numberOfThreads = 100;
    public static boolean[] choosing = new boolean[numberOfThreads];
    public static int[] ticket = new int[numberOfThreads];
    public static int count=0;

    public BakeryThread(int id) {
        thread_id = id;
    }

    public void run() {
        lock(thread_id);
        System.out.printf(thread_id + " 임계영역 작업중 ");
        System.out.println("현재까지 작업 완료 수: " + count);
        for(int i=0 ; i<500 ; i++) { /* nothing */ }
        count++;
        unlock(thread_id);

    }

    public void lock(int id) {
        choosing[id] = true;										// 티켓 발급 중
        ticket[id] = findMax() + 1;									// 현재까지 발급된 번호의 다음 번호 발급
        choosing[id] = false;										// 티켓 발급 완료

        for (int j = 0; j < numberOfThreads; j++) {
            if (j == id) continue;
            while (choosing[j]) { /* nothing */ }					// 티켓 번호을 부여받는 사람이 있으면 대기


            while (ticket[j] != 0 && 								// 티켓 번호가 0번이 아니면서
                    (ticket[id] > ticket[j] || 						// 나의 티켓번호보다 작은 티켓번호가 있거나
                            (ticket[id] == ticket[j] && id > j)		// 나의 티켓번호와 같으면서 작업번호가 나보다 작은게 있으면
                    )) { /* nothing */ }					// 계속 대기

        }
    }

    private void unlock(int id) {
        ticket[id] = 0;
    }

    private int findMax() {

        int m = ticket[0];

        for (int i = 1; i < ticket.length; i++) {
            if (ticket[i] > m)
                m = ticket[i];
        }
        return m;
    }



}