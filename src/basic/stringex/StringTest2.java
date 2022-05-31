package basic.stringex;

public class StringTest2 {
    public static void main(String[] args) {
        String javaStr = new String("java");
        String androidStr = new String("android");
        System.out.println(javaStr);
        System.out.println("처음 문자열 주소 값 : " + System.identityHashCode(javaStr));

        javaStr = javaStr.concat(androidStr);

        System.out.println(javaStr);
        System.out.println("연결된 문자열 주소값 : " + System.identityHashCode(javaStr));
    }
}
