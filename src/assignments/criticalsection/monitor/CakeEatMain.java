package assignments.criticalsection.monitor;

public class CakeEatMain {

    public static void main(String[] args) {
        CakePlate cake = new CakePlate();
        CakeEater eater = new CakeEater(cake);
        CakeMaker baker = new CakeMaker(cake);

        baker.start();
        eater.start();
    }

}