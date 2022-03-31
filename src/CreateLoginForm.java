import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CreateLoginForm extends JFrame {

    private JButton b1;
    private JPanel newPanel;
    private JPanel newPanelError;
    private JLabel errorMessage;
    private JLabel userLabel, passLabel;
    private JLabel errorLabel;
    final JTextField textField1, textField2;
    private Object messagebox;


    public CreateLoginForm() {

        String Username = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetUsers/");
        System.out.println(parseJSON(Username, "Username"));

        String Password = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetUsers/");
        System.out.println(parseJSON(Password, "Password"));

        String Users = "https://studev.groept.be/api/a21ib2c06/GetUsers/";

        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        textField1 = new JTextField(15);    //set length of the text
        errorMessage = new JLabel();
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2
        errorLabel = new JLabel();
        errorLabel.setText("");
        textField2 = new JPasswordField(15);    //set length for the password
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel
        newPanel.add(errorMessage);

        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();        //get user entered username from the textField1
                String passValue = textField2.getText();
                String UserPass = makeGETRequest(Users + userValue + "/" + passValue);
                String Username1 = parseJSON(UserPass, "Username");
                String Password1 = parseJSON(UserPass, "Password");


                if (Username1.equals(userValue) && Password1.equals(passValue)) {
                    MainF MainF = new MainF();
                    errorMessage.setText("Login Successful");
                } else if (userValue.compareTo(Username1) != 0 && passValue.compareTo(Password1) != 0) {
                    errorMessage.setText("Username and/or Password incorrect");
                }
            }
        });

        //add action listener to button
        setTitle("LOGIN");         //set title to the login form

        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setSize(500, 150);
    }

    public String makeGETRequest(String urlName) {
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        try {
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }
            conn.disconnect();
            //System.out.println(sb);
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    public String parseJSON(String jsonString, String key) {
        String var = "";
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                var += curObject.getString(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return var;
    }
}
    //create the main class
    class LoginFormDemo
    {
        //main() method start
        public static void main(String arg[])
        {
            try
            {
                //create instance of the LoginFormDemo.CreateLoginForm
                CreateLoginForm form = new CreateLoginForm();
                form.setSize(600,100);  //set size of the frame
                form.setResizable(false);
                form.setVisible(true);
                form.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//make form visible to the user
            }
            catch(Exception e)
            {
                //handle exception
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }


