public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String username;
    private String email;
    private char[] password;
    private String address;
    private String city;
    private String country;
    private int postalCode;
    private String phoneNumber;
    

    public User(char[] password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("[a-zA-Z]+")) {
            this.firstName = firstName;
        } else {
            System.out.println("Invalid first name");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.matches("[a-zA-Z]+")) {
            this.lastName = lastName;
        } else {
            System.out.println("Invalid last name");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public  int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        } else {
            System.out.println("Invalid age");
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            this.email = email;
        } else {
            System.out.println("Invalid email");
        }
    }

    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if (address.matches("[a-zA-Z0-9]+")) {
            this.address = address;
        } else {
            System.out.println("Invalid address");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.matches("[a-zA-Z]+")) {
            this.city = city;
        } else {
            System.out.println("Invalid city");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.matches("[a-zA-Z]+")) {
            this.country = country;
        } else {
            System.out.println("Invalid country");
        }
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        if (postalCode >= 10000 && postalCode <= 99999) {
            this.postalCode = postalCode;
        } else {
            System.out.println("Invalid postal code");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Invalid phone number");
        }
    }

    
}
