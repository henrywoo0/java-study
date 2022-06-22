package assignments.telephone;

import java.io.IOException;
import java.util.List;

public interface PhoneFunc {
    Phone insert(String name, String phoneNumber) throws IOException;
    List<Phone> readAllPhones() throws IOException;
    List<Phone> searchByName(String name) throws IOException;
    List<Phone> searchByPhoneNumber(String phoneNumber) throws IOException;
    void delete(Phone phone) throws IOException;
}
