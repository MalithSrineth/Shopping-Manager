import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class User implements Serializable {
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
    private ShoppingCart shoppingCart;
    private ArrayList<Purchase> purchases;


    public User() {
        this.shoppingCart = new ShoppingCart();
        this.purchases = new ArrayList<>();
    }

    public User(User other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.gender = other.gender;
        this.age = other.age;
        this.username = other.username;
        this.email = other.email;
        this.password = Arrays.copyOf(other.password, other.password.length); // copy the password array
        this.address = other.address;
        this.city = other.city;
        this.country = other.country;
        this.postalCode = other.postalCode;
        this.phoneNumber = other.phoneNumber;
        this.shoppingCart = new ShoppingCart(other.shoppingCart); // assuming ShoppingCart has a copy constructor
        this.purchases = new ArrayList<>(other.purchases); // creates a shallow copy of the purchases list
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("[a-zA-Z]+")) {
            this.firstName = firstName;
        } else if (firstName.equals("")) {
            throw new IllegalArgumentException("First Name Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid First Name");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.matches("[a-zA-Z]+")) {
            this.lastName = lastName;
        } else if (lastName.equals("")) {
            throw new IllegalArgumentException("Last Name Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Last Name");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.matches("[a-zA-Z]+")) {
            this.gender = gender;
        } else if (gender.equals("")) {
            throw new IllegalArgumentException("Gender Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Gender");
        }
    }

    public  int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        } else if (age == 0) {
            throw new IllegalArgumentException("Age Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Age");
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if (username.matches("[a-zA-Z0-9]+")) {
            //Check if username already exists
            for (User user : Database.getInstance().getUsers()) {
                if (user.getUsername().equals(username) && user != this) {
                    throw new IllegalArgumentException("Username Already Exists");
                } else if (user.getUsername().equals(username) && user == this) {
                    continue;
                }
            }
            this.username = username;
        } else {
            System.out.println("Invalid username");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            this.email = email;
        } else if (email.equals("")) {
            throw new IllegalArgumentException("Email Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        if (password.length >= 8) {
            this.password = password;
        }else if (password.length < 8) {
            throw new IllegalArgumentException("Password is too short");
        } else {
            throw new IllegalArgumentException("Invalid Password");
        }
        this.password = password;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if (address.matches("[a-zA-Z0-9 !@#$%^&*()_+=\\\\-\\\\[\\\\]{}|;:'\\\",.<>/?]+")) {
            this.address = address;
        } else if (address.equals("")) {
            throw new IllegalArgumentException("Address Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Address");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.matches("[a-zA-Z ]+")) {
            this.city = city;
        } else if (city.equals("")) {
            throw new IllegalArgumentException("City Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid City");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.matches("[a-zA-Z ]+")) {
            this.country = country;
        } else if (country.equals("")) {
            throw new IllegalArgumentException("Country Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Country");
        }
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        if (postalCode >= 10000 && postalCode <= 99999) {
            this.postalCode = postalCode;
        } else if (postalCode == 0) {
            throw new IllegalArgumentException("Postal Code Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Postal Code");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber;
        } else if (phoneNumber.equals("")) {
            throw new IllegalArgumentException("Phone Number Field is Empty");
        } else {
            throw new IllegalArgumentException("Invalid Phone Number");
        }
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    
}
