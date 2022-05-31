package basic.singleton;

public class CarFactoryTest {
    public static void main(String[] args) {
        CarFactory factory = CarFactory.getInstance();
        Car sonata = factory.createCar();
        Car benz = factory.createCar();
        System.out.println(sonata.getCarNum());
        System.out.println(benz.getCarNum());
    }
}
