package assignments.telephone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneFuncImpl implements PhoneFunc {

    private static final String DIRECTORY = "/Users/whitebear/Documents/file.txt";

    public PhoneFuncImpl() {}

    private void write(List<Phone> list) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORY, false));
            for (Phone phone : list) {
                writer.write(phone.getName() + "/" + phone.getPhoneNumber() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private List<Phone> readFileList() throws IOException {
        try {
            Reader reader = new FileReader(DIRECTORY);
            int ch;
            StringBuilder buffer = new StringBuilder("");
            while((ch = reader.read()) != -1) {
                buffer.append((char) ch);
            }

            String str = buffer.toString();
            List<Phone> list = new ArrayList<>();

            String[] data = str.split("\n");
            for(int i = 0; i < data.length; i++) {
                String[] phoneData = data[i].split("/");
                Phone phone = new Phone(phoneData[0], phoneData[1]);
                list.add(phone);
            }

            return list;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public Phone insert(String name, String phoneNumber) throws IOException {
        try {
            String purifiedPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");
            Phone phone = new Phone(name, purifiedPhoneNumber);
            List<Phone> list = readFileList();
            list.add(phone);
            write(list);

            return phone;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public List<Phone> readAllPhones() throws IOException {
        try {
            return readFileList();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public List<Phone> searchByName(String name) throws IOException {
        try {
            List<Phone> phoneList = readFileList();
            List<Phone> result = new ArrayList<>();

            for (Phone phone : phoneList) {
                if (phone.getName().contains(name)) {
                    result.add(phone);
                }
            }

            return result;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public List<Phone> searchByPhoneNumber(String phoneNumber) throws IOException {
        try {
            List<Phone> phoneList = readFileList();
            List<Phone> result = new ArrayList<>();

            for (Phone phone : phoneList) {
                if (phone.getPhoneNumber().contains(phoneNumber)) {
                    result.add(phone);
                }
            }

            return result;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void delete(Phone phone) throws IOException {
        try {
            List<Phone> phoneList = readFileList();

            for(int i = 0; i < phoneList.size(); i++) {
                String name = phoneList.get(i).getName();
                if(name.equals(phone.getName())) {
                    phoneList.remove(i);
                    write(phoneList);
                    break;
                }
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
