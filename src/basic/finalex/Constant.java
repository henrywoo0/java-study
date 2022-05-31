package basic.finalex;

public class Constant {
    int num = 10;
    final int NUM = 100;
    // 변수 이름은 대소문자를 구별

    public static void main(String[] args) {
        Constant cons = new Constant();
        cons.num = 50;
        // cons.NUM = 200; // 상수에 값을 대입하여 오류 발생

        System.out.println(cons.num);
        System.out.println(cons.NUM);
    }
}
