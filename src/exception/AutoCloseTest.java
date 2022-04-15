package exception;

// AutoCloseable 인터페이스 구현한 객체 사용하면
// try-with-resource 문 사용해서 자동 close() 가능
public class AutoCloseTest {
    public static void main(String[] args) {

        try(AutoCloseObj obj = new AutoCloseObj()) {
            throw new Exception();
        } catch(Exception e) {
            System.out.println("예외 부분입니다.");
        }
    }
}
