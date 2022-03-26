import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainF extends JFrame{
    private JPanel mainPanel;
    private JButton settingsOfMachineButton;
    private JButton scheduleButton;
    private JButton dataReadingsButton;
    private JLabel label1;


    public MainF( ){
        setContentPane(mainPanel);
        setTitle("Snorring machine");
        setSize(700,500);
        setResizable(false);
        mainPanel.setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //pack();

        label1.setFont(new Font("Serif", Font.BOLD, 20));
        settingsOfMachineButton.setFont(new Font("Serif", Font.BOLD, 16));
        settingsOfMachineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {


                                               JFrame try2 = new try2();
                                               try2.setVisible(true);

            }
        });
        scheduleButton.setFont(new Font("Serif", Font.BOLD, 16));
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go onto a page that can make the activators work for a period in time
            }
        });
        dataReadingsButton.setFont(new Font("Serif", Font.BOLD, 16));
        dataReadingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get some readings updated every 10 sec from the database
                JFrame info = new info();
                info.setVisible(true);
            }
        });

    }
    public static void main ( String[] args){
        MainF myFrame = new MainF();


    }



}
