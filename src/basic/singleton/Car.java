package basic.singleton;

public class Car {
    private static int serial = 10000;
    private int carNum;

    public Car() {
        serial++;
        carNum = serial;
    }

    public int getCarNum() {
        return carNum;
    }
}
