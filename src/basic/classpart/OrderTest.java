package basic.classpart;

public class OrderTest {
    public static void main(String[] args) {
        Order order1 = new Order();
        order1.orderNumber = 201803120001L;
        order1.ownerId = "abc123";
        order1.orderYear = 2018;
        order1.orderMonth = 3;
        order1.orderDay = 12;
        order1.ownerName = "홍길순";
        order1.orderItemNumber = "PD0345-12";
        order1.address = "서울 영등포구 여의도동 20번지";
    }
}
