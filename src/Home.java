import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Home extends JFrame  {

    private JButton blogin;
    private JButton bregister;
    private JPanel newPanel;

    public Home(){

        blogin = new JButton("LOGIN");
        bregister = new JButton("REGISTER");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(blogin);
        newPanel.add(bregister);
        add(newPanel, BorderLayout.CENTER);

        blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                Login login = new Login();
            }
        });

        bregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent r) {
                Register register = new Register();
            }
        });

        setSize(300,100);  //set size of the frame
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    public static void main(String[] args) {
        Home newHome = new Home();

    }
}