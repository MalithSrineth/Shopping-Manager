import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
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
        passwordField = new JPasswordField(20);
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
        add(passwordField, gbc);

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
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Last Name:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Gender:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Age:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);
        
        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Username:"), gbc);
        
        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Email:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Password:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JPasswordField(20), gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Re-Enter Password:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JPasswordField(20), gbc);
        
        gbc.gridx --;
        gbc.gridy ++;
        gbc.gridwidth = 2;
        signUpDialog.add(new JLabel("Address Line 1:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(40), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JLabel("Address Line 2:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(40), gbc);

        gbc.gridy ++;
        gbc.gridwidth = 1;
        signUpDialog.add(new JLabel("City:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Country:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx --;
        gbc.gridy ++;
        signUpDialog.add(new JLabel("Postal Code:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

        gbc.gridx ++;
        gbc.gridy --;
        signUpDialog.add(new JLabel("Phone Number:"), gbc);

        gbc.gridy ++;
        signUpDialog.add(new JTextField(20), gbc);

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
                // Placeholder for creating an account logic
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
