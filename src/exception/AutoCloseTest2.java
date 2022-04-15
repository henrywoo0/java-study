package exception;

public class AutoCloseTest2 {
    public static void main(String[] args) {
        AutoCloseObj obj = new AutoCloseObj();

        try (obj) {
        } catch (Exception e) {
            System.out.println("예외 발생");
        }
    }
}
