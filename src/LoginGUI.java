import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for login logic
                JOptionPane.showMessageDialog(LoginGUI.this,
                        "Login Successful!");
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

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for sign up logic
                User user = new User();
                try {
                    // Assume firstNameField is your JTextField for the first name input
                    String firstName = firstNameField.getText();
                    // Assuming 'user' is an instance of your User class
                    user.setFirstName(firstName);
                    // ... the rest of your account creation logic ...
        
                    // If the name is valid and account creation is successful, close the dialog
                    signUpDialog.setVisible(false);
                    signUpDialog.dispose();
                } catch (IllegalArgumentException ex) {
                    // Show the dialog box with the error message
                    JOptionPane.showMessageDialog(signUpDialog, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }

                //user.setFirstName(firstNameField.getText());
                user.setLastName(lastNameField.getText());
                user.setGender(genderField.getText());
                user.setAge(Integer.parseInt(ageField.getText()));
                user.setUsername(signUpUsernameField.getText());
                user.setEmail(emailField.getText());
                if (signUPasswordField.getPassword().equals(reEnterPasswordField.getPassword())) {
                    user.setPassword(signUPasswordField.getPassword());
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this,
                        "Passwords do not match!");
                }
                user.setPassword(signUPasswordField.getPassword());



                JOptionPane.showMessageDialog(LoginGUI.this,
                        "Account Created!");
                
                
                signUpDialog.setVisible(false); // Hide the dialog
                signUpDialog.dispose(); // Destroy the dialog and free resources
            }
        });

        signUpDialog.pack();
        signUpDialog.setLocationRelativeTo(this);
        signUpDialog.setVisible(true);
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
