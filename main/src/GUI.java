import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class GUI extends JFrame{

    final int TOTAL_HEIGHT = 540;
    final int TOTAL_WIDTH = 960;

    public GUI(){
        setTitle("SmartMessagingSystem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(TOTAL_WIDTH, TOTAL_HEIGHT);

        // Create the main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Set the menu bar
        setJMenuBar(createMenuBar());

        // Create components for the main panel
        JLabel label = new JLabel("Welcome in SmartMessagingSystem");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        loginButton.setSize(320, 100);
        loginButton.addActionListener(e->MenuLogin(mainPanel, label, buttonPanel));

        JButton closeButton = new JButton("Close");
        closeButton.setSize(320, 100);
        closeButton.addActionListener(e->System.exit(0));

        // Add components to the main panel
        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(loginButton, BorderLayout.WEST);
        buttonPanel.add(closeButton, BorderLayout.EAST);

        // Add the main panel to the frame
        add(mainPanel);

        // Set the frame to be visible
        setVisible(true);
    }

    private void MenuLogin(JPanel panel, JLabel label, JPanel panelButton){
        String lastText = label.getText();
        panel.remove(panelButton);
        label.setText("");

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10,150,10,150));
        panel.add(innerPanel);

        JTextField logInput = new JTextField(32);
        logInput.setBorder(new LineBorder(Color.BLACK, 2));
        innerPanel.add(logInput);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        innerPanel.add(buttonPanel);
        JButton login = new JButton("LoggIn");
        login.addActionListener(e->{
            label.setText("LoggedIn as ...");
            panel.remove(innerPanel);
            panel.remove(panelButton);
            JPanel contactPanel = new JPanel();
            contactPanel.setBackground(Color.lightGray);
            JLabel subLabel = new JLabel("Contacts List");
            contactPanel.add(subLabel);
            JPanel menuPanel = new JPanel();
            menuPanel.add(label);
            panel.add(menuPanel, BorderLayout.CENTER);
            panel.add(contactPanel, BorderLayout.EAST);
            SwingUtilities.updateComponentTreeUI(this);
            this.getJMenuBar().add(createSettingsMenu());
        });
        buttonPanel.add(login);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e->{
            label.setText(lastText);
            panel.remove(innerPanel);
            panel.add(panelButton, BorderLayout.SOUTH);
            SwingUtilities.updateComponentTreeUI(this);
        });
        buttonPanel.add(cancel);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createContextMenu());
        menuBar.add(createFeaturesMenu());
        return menuBar;
    }

    private JMenu createContextMenu(){
        JMenu menu = new JMenu("Contexts");
        JMenuItem day = new JMenuItem("Day");
        menu.add(day);
        JMenuItem night = new JMenuItem("Night");
        menu.add(night);
        JMenuItem wifi = new JMenuItem("Wifi");
        menu.add(wifi);
        JMenuItem cellular = new JMenuItem("Cellular");
        menu.add(cellular);
        return menu;
    }

    private JMenu createFeaturesMenu(){
        JMenu menu = new JMenu("Features");
        return menu;
    }

    private JMenu createSettingsMenu(){
        JMenu settings = new JMenu("Settings");
        JMenuItem logout = new JMenuItem("LogOut");
        settings.add(logout);
        return settings;
    }

    public static void main(String[] args){
        new GUI();
    }
}


    // Create window with title "application" that stop the program when closed
//    JFrame frame = new JFrame("Application");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                // Creating a panel that will hold the buttons
//                JPanel panel = new JPanel();
//
//                // Setup frame with the panel
//                frame.add(panel);
//                frame.setSize(200, 200);
//                frame.setVisible(true); // show the window
//
//                // Creation of a button that will be shown dynamically
//                JButton buttonTemp = new JButton("Dynamic click me");
//                buttonTemp.addActionListener((actionEvent) -> {
//                System.out.println("dynamic click");
//                });
//
//                frame.setTitle("SmartMessagingSystem");

// Make the button appear
//    case "add":
//        panel.add(buttonTemp);
//        break;

//        GUI.Main loop
//        Scanner in = new Scanner(System.in);
//        while (true) {
//            System.out.print("Enter a command: ");
//            String line = in.nextLine();
//            String[] cutLine = line.split(" ");
//
//            switch (cutLine[0]) {
//
//                // Enable dark mode
//                case "dark":
//                    frame.panel.setBackground(Color.BLACK);
//                    break;
//
//                // Disable dark mode
//                case "light":
//                    panel.setBackground(Color.WHITE);
//                    break;
//
//                // Stop the program
//                case "stop":
//                    in.close();
//                    System.exit(0);
//                    break;
//
//                // Unknown command
//                default:
//                    System.out.println("Unknown command " + cutLine[0]);
//                    break;
//            }
//
//            /*
//             * These lines update the UI
//             * Repaint holds for all graphical changes (like color changes)
//             * Revalidate holds for UI change (like button add/remove)
//             */
//            frame.repaint();
//            frame.revalidate();
//        }