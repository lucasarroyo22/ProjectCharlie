import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    private static StringValidation stringValidation = new StringValidation();

    public static void main(String args[]){

        //Creating the Frame
        JFrame frame = new JFrame("Project Charlie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("INFO");
        JMenu m2 = new JMenu("Cart");
        JMenu m3 = new JMenu("Item Inventory");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        JMenuItem m11 = new JMenuItem("About Us");
        JMenuItem m21 = new JMenuItem("View Cart");
        JMenuItem m22 = new JMenuItem("Checkout");
        m1.add(m11);
        m2.add(m21);
        m2.add(m22);
        //Menu item About Us Action Listener
        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "The creators of Project Charlie are Joe Ross and Lucas Arroyo");
            }
        });
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Username");
        JTextField tf = new JTextField(20); // accepts up to 20 characters
        JButton submit = new JButton("Submit");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(submit);

        //When Submit is pressed
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String usernameTextField = tf.getText();
                if(stringValidation.isUsernameValid(usernameTextField)){
                    System.out.println("Submitted!");
                    JOptionPane.showMessageDialog(frame, "Submitted! Welcome " + usernameTextField + "!");
                    panel.setVisible(false);

                    JPanel panel2 = new JPanel(); // the panel is not visible in output
                    JLabel label2 = new JLabel("Enter Password");
                    JTextField tf2 = new JTextField(20); // accepts up to 20 characters
                    JButton submit2 = new JButton("Submit");
                    panel2.add(label2); // Components Added using Flow Layout
                    panel2.add(tf2);
                    panel2.add(submit2);
                    //When Submit is pressed for Password
                    submit2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String passwordTextField = tf2.getText();
                            if(stringValidation.isPasswordValid(passwordTextField)){
                                System.out.println("Verified!");
                                JOptionPane.showMessageDialog(frame, "Password Verified!");
                                panel2.setVisible(false);
                            } else{
                                System.out.println("Password Invalid!");
                                JOptionPane.showMessageDialog(frame, "Password Invalid. Please Try Again");
                            }
                        }
                    });
                    frame.getContentPane().add(BorderLayout.CENTER, panel2);    //show panel2

                }else{
                    System.out.println("Username Invalid!");
                    JOptionPane.showMessageDialog(frame, "Username Invalid. Please Try Again");
                }


            }
        });

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
    }
}
