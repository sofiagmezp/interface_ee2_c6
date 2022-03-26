import org.json.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class info extends JFrame{
    private JButton temperatureButton;
    private JButton lightIntensityButton;
    private JLabel information;
    private JLabel db;
    private JLabel celsius;
    private JLabel light;
    private JPanel pann;
    private JButton button1;

    public info() {
        setContentPane(pann);
        setTitle("Sensor information");
        setSize(300, 380);
        pann.setBackground(Color.lightGray);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);


        String temp = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest" );
        System.out.println(parseJSON(temp,"Temperature")+"°C");
        celsius.setText(parseJSON(temp,"Temperature")+"°C");

        String noise = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest" );
        System.out.println(parseJSON(noise,"Noise")+"dB");
        db.setText(parseJSON(noise,"Noise")+"dB");

        String brightness = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest" );
        System.out.println(parseJSON(brightness,"Brightness")+"‰");
        light.setText(parseJSON(brightness,"Brightness")+"‰");temperatureButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                if (arg0.getButton() == MouseEvent.BUTTON1){
                    System.out.println("Temp pressed!");

                    String temp = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest" );
                    System.out.println(parseJSON(temp,"Temperature")+"°C");
                    celsius.setText(parseJSON(temp,"Temperature")+"°C");
                }

            }
        });

        lightIntensityButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                if (arg0.getButton() == MouseEvent.BUTTON1){
                    System.out.println("Light pressed!");

                    String brightness = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest" );
                    System.out.println(parseJSON(brightness,"Brightness")+"‰");
                    light.setText(parseJSON(brightness,"Brightness")+"‰");
                }
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

                if (arg0.getButton() == MouseEvent.BUTTON1){
                    System.out.println("Noise pressed!");

                    String noise = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest" );
                    System.out.println(parseJSON(noise,"Noise")+"dB");
                    db.setText(parseJSON(noise,"Noise")+"dB");
                }
            }
        });
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



    //pack();
    public static void main ( String[] args){
        info myFrame2 = new info();




    }
}