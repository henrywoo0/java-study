package assignments.telephone;

public class Phone implements Comparable<Phone> {
    private String name;
    private String phoneNumber;

    public Phone(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(Phone phone) {
        return phone.getName().compareTo(this.getName());
    }
}
