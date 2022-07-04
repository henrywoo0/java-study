package assignments.criticalsection.monitor;

public class CakeEater extends Thread {
    private CakePlate cake;

    public CakeEater(CakePlate cake) {
        this.cake=cake;
    }

    public void run() {
        for(int i=0;i<50;i++) {
            cake.eatBread();
        }
    }
}
