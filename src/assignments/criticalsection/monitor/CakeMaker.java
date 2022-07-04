package assignments.criticalsection.monitor;

public class CakeMaker extends Thread {
    private CakePlate cake;

    public CakeMaker(CakePlate cake) {
        this.cake=cake;
    }

    public void run() {
        for(int i=0;i<50;i++) {
            cake.makeBread();
        }
    }
}