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

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class Register extends JFrame{

    private JButton b1;
    private JPanel newPanel;
    private JPanel newPanelError;
    private JLabel errorMessage;
    private JLabel userLabel, passLabel;
    private JLabel errorLabel;
    final JTextField  textField1, textField2;
    private Object messagebox;

    //calling constructor
    public Register(){

        String Username = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetUsers/" );
        System.out.println(parseJSON(Username,"Username"));

        String Password = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetUsers" );
        System.out.println(parseJSON(Password,"Password"));

        String Register = "https://studev.groept.be/api/a21ib2c06/Register/";


        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGETRequest(Register + textField1.getText() + "/" + textField2.getText() );
                Login Login = new Login();
            }
        });     //add action listener to button
        setTitle("REGISTER");         //set title to the login form

        //create the label for the error message
        errorLabel = new JLabel();
        errorLabel.setText("");

        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setSize(500,150);
    }

    public String makeGETRequest(String urlName){
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
            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
            conn.disconnect();
            //System.out.println(sb);
            return sb.toString();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (ProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "";

    }

    public String parseJSON(String jsonString,String key){
        String var = "";
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject curObject = array.getJSONObject(i);
                var += curObject.getString(key);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return var;
    }

}