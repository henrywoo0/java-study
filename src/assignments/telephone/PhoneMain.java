package assignments.telephone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PhoneMain {

    private final PhoneFunc phoneFunc;
    private final Scanner scanner;

    public PhoneMain() {
        this.phoneFunc = new PhoneFuncImpl();
        this.scanner = new Scanner(System.in);
    }

    private String readString() {
        String input = scanner.next();
        scanner.nextLine();
        return input;
    }

    private int readInt() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private void showList(List<Phone> list) {
        System.out.println("==================");
        list.sort(Comparator.reverseOrder());
        for (Phone phone : list) {
            System.out.print(phone.getName());
            System.out.print(" ");
            System.out.print(phone.getPhoneNumber());
            System.out.println();
        }
        System.out.println("==================");
    }

    private void showOrderedList(List<Phone> list) {
        System.out.println("==================");
        list.sort(Comparator.reverseOrder());
        for(int i = 0; i < list.size(); i++) {
            Phone phone = list.get(i);
            System.out.print(i + 1);
            System.out.print(" ");
            System.out.print(phone.getName());
            System.out.print(" ");
            System.out.print(phone.getPhoneNumber());
            System.out.println();
        }
        System.out.println("==================");
    }

    public void insertPhone() {
        try {
            System.out.print("이름을 입력해주세요 : ");
            String name = readString();
            System.out.print("전화번호를 입력해주세요(숫자만) : ");
            String phoneNumber = readString();
            phoneFunc.insert(name, phoneNumber);
            System.out.println("전화번호 등록이 완료되었습니다.");
        } catch (Exception e) {
            System.out.println("등록 작업 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    public void searchPhoneByName() {
        try {
            System.out.print("검색할 이름을 입력해주세요 : ");
            String name = readString();
            List<Phone> phoneList = phoneFunc.searchByName(name);
            if(phoneList.size() == 0) {
                System.out.println("이름에 해당하는 전화번호가 없습니다.");
                return;
            }
            showList(phoneList);
            System.out.println("검색이 완료되었습니다.");
        } catch (Exception e) {
            System.out.println("검색 작업 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    public void searchPhoneByNumber() {
        try {
            System.out.print("검색할 전화번호를 입력해주세요 (숫자만) : ");
            String phoneNumber = readString();
            List<Phone> phoneList = phoneFunc.searchByPhoneNumber(phoneNumber);
            if(phoneList.size() == 0) {
                System.out.println("해당하는 전화번호가 없습니다.");
                return;
            }
            showList(phoneList);
            System.out.println("검색이 완료되었습니다.");
        } catch (Exception e) {
            System.out.println("검색 작업 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    public void deletePhone() {
        try {
            System.out.print("삭제할 전화번호의 이름을 입력해주세요 : ");
            String name = readString();

            List<Phone> phoneList = phoneFunc.readAllPhones();
            List<Phone> result = new ArrayList<>();

            for (Phone phone : phoneList) {
                if(phone.getName().equals(name)) {
                    result.add(phone);
                }
            }

            int size = result.size();
            if(size == 1) {
                phoneFunc.delete(result.get(0));
                System.out.println(name + "의 전화번호 삭제가 완료되었습니다.");
            } else if (size == 0) {
                System.out.println(name + "에 해당하는 이름의 전화번호가 없습니다.");
            } else {
                while(true) {
                    showOrderedList(result);
                    System.out.print("위 중 몇 번째 전화번호를 삭제할까요? : ");
                    int input = readInt();
                    if(input > result.size()) {
                        System.out.println("잘못된 입력입니다.\n");
                    } else {
                        phoneFunc.delete(result.get(input - 1));
                        System.out.println(name + "의 전화번호 삭제가 완료되었습니다.");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("삭제 작업 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    public void execute() {
        System.out.println("전화번호부 프로그램을 실행합니다. 아래 선택지중 하나를 골라서 숫자를 적어주세요.");

        try {
            while(true) {
                System.out.println("\n[1] 전화번호 등록  [2] 이름 검색  [3] 전화번호 검색  [4] 전화번호 삭제  [5] 종료");
                System.out.print(">> ");
                int input = readInt();
                switch (input) {
                    case 1:
                        insertPhone();
                        break;
                    case 2:
                        searchPhoneByName();
                        break;
                    case 3:
                        searchPhoneByNumber();
                        break;
                    case 4:
                        deletePhone();
                        break;
                    case 5:
                        System.out.println("전화번호부 프로그램을 종료합니다.");
                        this.scanner.close();
                        return;
                    default:
                        System.out.println("입력이 잘못되었습니다. 다시 숫자를 입력해주세요.");
                }
            }
        } catch (Exception e) {
            System.out.println("입력이 잘못되었습니다.");
        }
    }

    public static void main(String[] args) {

        try {
            PhoneMain main = new PhoneMain();
            main.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
