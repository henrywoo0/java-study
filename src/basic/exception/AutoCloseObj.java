package basic.exception;

public class AutoCloseObj implements AutoCloseable {

    public AutoCloseObj () {
        System.out.println("AutoCloseObj 객체가 생성되었습니다.");
    }

    @Override
    public void close() throws Exception {
        System.out.println("리소스가 close() 되었습니다.");
    }
}
