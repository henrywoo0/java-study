package basic.exception;

public class PasswordTest {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordException {
        if (password == null) {
            throw new PasswordException("비밀번호는 null일 수 없습니다.");
        }
        else if (password.matches("[a-zA-Z]+")) {
            throw new PasswordException("비밀번호는 문자, 숫자가 포함되어야 합니다.");
        }
        else if (password.length() <= 5) {
            throw new PasswordException("비밀번호는 6자 이상이어야 합니다.");
        }
        this.password = password;
    }

    public static void main(String[] args) {

        String password = null;

        PasswordTest test = new PasswordTest();
        try {
            test.setPassword(password);
        } catch (PasswordException e) {
            System.out.println(e.getMessage());
        }

        password = "abcdefg";
        try {
            test.setPassword(password);
        } catch (PasswordException e) {
            System.out.println(e.getMessage());
        }

        password = "ab123";
        try {
            test.setPassword(password);
        } catch (PasswordException e) {
            System.out.println(e.getMessage());
        }
    }
}
