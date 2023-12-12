import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class LoginGUI extends JFrame {

    private JTextField usernameField, firstNameField, lastNameField, genderField, ageField, signUpUsernameField, emailField, addressLine1Field, addressLine2Field, cityField, countryField, postalCodeField, phoneNumberField;
    private JPasswordField logInPasswordField, signUPasswordField, reEnterPasswordField;
    private JButton loginButton, signUpButton;
    private JLabel welcomeLabel, usernameLabel, passwordLabel;

    public LoginGUI() {
        setTitle("Westminster Shopping Center Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        initComponents();
        layoutComponents();
        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void initComponents() {
        // Initialize components
        ArrayList<User> users = WestminsterShoppingManager.getUsers();
        

        welcomeLabel = new JLabel("Welcome to Westminster Shopping Center");
        usernameLabel = new JLabel("User Name:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        logInPasswordField = new JPasswordField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        genderField = new JTextField(20);
        ageField = new JTextField(20);
        emailField = new JTextField(20);
        signUpUsernameField = new JTextField(20);
        signUPasswordField = new JPasswordField(20);
        reEnterPasswordField = new JPasswordField(20);
        addressLine1Field = new JTextField(40);
        addressLine2Field = new JTextField(40);
        cityField = new JTextField(20);
        countryField = new JTextField(20);
        postalCodeField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        // Set up action listeners
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignUpDialog();
                int lastUserIndex = users.size() - 1;
                if (lastUserIndex >= 0) {
                    User lastUser = users.get(lastUserIndex);
                    String lastUsername = lastUser.getUsername();
                    usernameField.setText(lastUsername);
                }
                logInPasswordField.setText("12345678");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for login logic
                String username = usernameField.getText();
                boolean userExists = false;
                
                //if users = null Throw a Error Message
                
                
                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        userExists = true;
                        break;
                    }
                }
                
                if (userExists) {
                    User user = WestminsterShoppingManager.getUser(username);
                    if (Arrays.equals(user.getPassword(), logInPasswordField.getPassword())) {
                        LoggingSession loggingSession = new LoggingSession(user);
                        JOptionPane.showMessageDialog(LoginGUI.this, "Login Successful!");
                        new ShoppingGUI(loggingSession);
                    } else {
                        JOptionPane.showMessageDialog(LoginGUI.this, "Invalid Password!");
                    }
                    
                    
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "User Doesn't Exist!");
                }
            }
        });
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to the frame
        add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(usernameLabel, gbc);

        gbc.gridx++;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(passwordLabel, gbc);

        gbc.gridx++;
        add(logInPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridy++;
        add(signUpButton, gbc);
    }

    private void showSignUpDialog() {
        // Sign Up Dialog
        JDialog signUpDialog = new JDialog(this, "Sign Up", true);
        signUpDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        signUpDialog.add(new JLabel("First Name:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(firstNameField, gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Last Name:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(lastNameField, gbc);

        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Gender:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(genderField, gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Age:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(ageField, gbc);
        
        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Username:"), gbc);
        
        gbc.gridy ++;
        signUpDialog.add(signUpUsernameField, gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Email:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(emailField, gbc);

        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Password:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(signUPasswordField, gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Re-Enter Password:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(reEnterPasswordField, gbc);
        
        gbc.gridx --;
        gbc.gridy ++;
        gbc.gridwidth = 2;
        signUpDialog.add(new JLabel("Address Line 1:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(addressLine1Field, gbc);

        gbc.gridy ++;
        signUpDialog.add(new JLabel("Address Line 2:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(addressLine2Field, gbc);

        gbc.gridy ++;
        gbc.gridwidth = 1;
        signUpDialog.add(new JLabel("City:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(cityField, gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Country:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(countryField, gbc);

        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Postal Code:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(postalCodeField, gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Phone Number:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(phoneNumberField, gbc);

        gbc.gridx --;
        gbc.gridy ++;
        JButton cancelButton = new JButton("Cancel");
        signUpDialog.add(cancelButton, gbc);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpDialog.setVisible(false); // Hide the dialog
                signUpDialog.dispose(); // Destroy the dialog and free resources
                JOptionPane.showMessageDialog(LoginGUI.this,
                        "Sign Up Cancelled");
            }
        });
        
        gbc.gridx = 1;
        gbc.gridy = 16;
        JButton createAccountButton = new JButton("Create Account");
        signUpDialog.add(createAccountButton, gbc);
        setRandomInputFields();
        

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for sign up logic
                User user = new User();
                try {
                    user.setFirstName(firstNameField.getText());
                    user.setLastName(lastNameField.getText());
                    user.setGender(genderField.getText());
                    user.setAge(Integer.parseInt(ageField.getText()));
                    user.setUsername(signUpUsernameField.getText());
                    user.setEmail(emailField.getText());
                    if (signUPasswordField.getPassword().length == 0) {
                        throw new IllegalArgumentException("Password Field is Empty");
                    } else if (reEnterPasswordField.getPassword().length == 0) {
                        throw new IllegalArgumentException("Re-Enter Password Field is Empty");
                    } else if (!Arrays.equals(signUPasswordField.getPassword(), reEnterPasswordField.getPassword())) {
                        throw new IllegalArgumentException("Passwords do not Match!");                    
                    } else {
                        user.setPassword(signUPasswordField.getPassword());
                    } 

                    if (addressLine1Field.getText().equals("")) {
                        throw new IllegalArgumentException("Address Line 1 Field is Empty");
                    } else if (addressLine2Field.getText().equals("")) {
                        user.setAddress(addressLine1Field.getText());
                    } else {
                        user.setAddress(addressLine1Field.getText() + ", " + addressLine2Field.getText());
                    }
                    
                    user.setCity(cityField.getText());
                    user.setCountry(countryField.getText());
                    user.setPostalCode(Integer.parseInt(postalCodeField.getText()));
                    user.setPhoneNumber(phoneNumberField.getText());

                    WestminsterShoppingManager.addUser(user);

                    JOptionPane.showMessageDialog(LoginGUI.this,
                        "Account Created!");

                    for (User u : WestminsterShoppingManager.getUsers()) {
                        System.out.println(u.getUsername());
                    }
                
        
                    // If the name is valid and account creation is successful, close the dialog
                    signUpDialog.setVisible(false);
                    signUpDialog.dispose();
                    clearInputFields();
                    
                } catch (IllegalArgumentException ex) {
                    // Show the dialog box with the error message
                    JOptionPane.showMessageDialog(signUpDialog, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpDialog.pack();
        signUpDialog.setLocationRelativeTo(this);
        signUpDialog.setVisible(true);
    }

    //Another method to set input fields with random data
    private void setRandomInputFields() {
        firstNameField.setText(getRandomName());
        lastNameField.setText(getRandomName());
        genderField.setText(getRandomGender());
        ageField.setText(getRandomAge());
        signUpUsernameField.setText(getRandomUsername());
        emailField.setText(getRandomEmail());
        signUPasswordField.setText("12345678");
        reEnterPasswordField.setText("12345678");
        addressLine1Field.setText(getRandomAddress());
        addressLine2Field.setText(getRandomAddress());
        cityField.setText(getRandomCity());
        countryField.setText(getRandomCountry());
        postalCodeField.setText(getRandomPostalCode());
        phoneNumberField.setText(getRandomPhoneNumber());
    }


    private String getRandomName() {
        String[] names = {"John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Ava", "Benjamin", "Isabella"};
        Random random = new Random();
        int index = random.nextInt(names.length);
        return names[index];
    }

    private String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        Random random = new Random();
        int index = random.nextInt(genders.length);
        return genders[index];
    }

    private String getRandomAge() {
        Random random = new Random();
        // Generate a random age between 18 and 100
        int age = random.nextInt(80 - 18 + 1) + 18; 
        return String.valueOf(age);
    }

    private String getRandomUsername() {
        String p1 = getRandomName();
        String p2 = getRandomName();
        int randomNum = (int) (Math.random() * 100);
        String username = p1 + p2 + randomNum; 
        return username;
    }

    private String getRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        Random random = new Random();
        int index = random.nextInt(domains.length);
        String username = this.getRandomUsername();
        return username + "@" + domains[index];
    }
    
    private String getRandomAddress() {
        String[] streets = {"Main Street", "Park Avenue", "Oak Street", "Elm Street", "Maple Avenue"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        String[] states = {"New York", "California", "Illinois", "Texas", "Arizona"};
        Random random = new Random();
        int streetIndex = random.nextInt(streets.length);
        int cityIndex = random.nextInt(cities.length);
        int stateIndex = random.nextInt(states.length);
        int houseNumber = random.nextInt(100) + 1;
        return houseNumber + " " + streets[streetIndex] + ", " + cities[cityIndex] + ", " + states[stateIndex];
    }

    private String getRandomCity() {
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        Random random = new Random();
        int index = random.nextInt(cities.length);
        return cities[index];
    }

    private String getRandomCountry() {
        String[] countries = {"United States", "Canada", "United Kingdom", "Germany", "France", "Australia", "Japan", "China", "India"};
        Random random = new Random();
        int index = random.nextInt(countries.length);
        return countries[index];
    }

    private String getRandomPostalCode() {
        Random random = new Random();
        int postalCode = random.nextInt(90000) + 10000;
        return String.valueOf(postalCode);
    }

    private String getRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            phoneNumber.append(digit);
        }
        return phoneNumber.toString();
    }

    private void setInputFields() {
        firstNameField.setText("Malith");
        lastNameField.setText("Amarawickrama");
        genderField.setText("Male");
        ageField.setText("23");
        signUpUsernameField.setText("malithsrineth");
        emailField.setText("malithsrineth@gmail.com");
        signUPasswordField.setText("12345678");
        reEnterPasswordField.setText("12345678");
        addressLine1Field.setText("No 1/3, Maitipe 2nd Lane");
        addressLine2Field.setText("Ambalanwatta");
        cityField.setText("Galle");
        countryField.setText("Sri Lanka");
        postalCodeField.setText("80000");
        phoneNumberField.setText("0766201619");
    }

    private void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        genderField.setText("");
        ageField.setText("");
        signUpUsernameField.setText("");
        emailField.setText("");
        signUPasswordField.setText("");
        reEnterPasswordField.setText("");
        addressLine1Field.setText("");
        addressLine2Field.setText("");
        cityField.setText("");
        countryField.setText("");
        postalCodeField.setText("");
        phoneNumberField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI();
            }
        });
    }
}
