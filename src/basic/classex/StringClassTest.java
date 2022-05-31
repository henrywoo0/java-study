package basic.classex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StringClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class strClass = Class.forName("java.lang.String");

        Constructor[] cons = strClass.getConstructors(); // 모든 생성자 가져오기
        for(Constructor c : cons) {
            System.out.println(c);
        }

        System.out.println();
        Field[] fields = strClass.getFields(); // 모든 멤버 변수 가져오기
        for(Field f : fields) {
            System.out.println(f);
        }

        System.out.println();
        Method[] methods = strClass.getMethods(); // 모든 메서드 가져오기
        for(Method m : methods) {
            System.out.println(m);
        }
    }
}
