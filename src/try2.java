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

public class try2 extends JFrame {
    private JPanel subPanel;
    private JLabel labelH;
    private JLabel labelS;
    private JToggleButton JTB1;
    private JToggleButton JTB2;
    private JLabel buzzerimg;
    private JLabel humidifier;
    private JLabel buzzer;
    private JLabel allOptions;
    private JToggleButton JTB3;
    private JLabel introTitle;
    private JPanel p1;
    private JButton reload;
    private boolean humIsTurnedON;
    private boolean buzzIsTurnedON;

    public try2() {

        setContentPane(subPanel);
        setTitle("Settings");
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        reload.setBackground(Color.white);
        p1.setBackground(Color.gray);
        p1.setBounds(0, 0, 1000, 300);
        labelS.setFont(new Font("Ariel", Font.BOLD, 20));
        introTitle.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        buzzer.setSize(100, 10);
        buzzer.setFont(new Font("Ariel", Font.BOLD, 14));
        humidifier.setFont(new Font("Ariel", Font.BOLD, 14));
        allOptions.setFont(new Font("Ariel", Font.BOLD, 14));
        subPanel.setBackground(Color.lightGray);
        humIsTurnedON = false;
        buzzIsTurnedON = false;


            JTB1.setText("OFF");
            JTB1.setForeground(Color.white);
            JTB1.setBackground(Color.darkGray);
            buzzer.setText("The buzzer is currently turned off");
            buzzer.setForeground(Color.darkGray);
            JTB2.setText("OFF");
            JTB2.setBackground(Color.darkGray);
            JTB2.setForeground(Color.white);
            humidifier.setText("The humidifier is currently turned off");
            humidifier.setForeground(Color.darkGray);
            JTB3.setText("OFF");
            JTB3.setBackground(Color.darkGray);
            JTB3.setForeground(Color.white);
            allOptions.setText("All of the options are turned off");
            allOptions.setForeground(Color.darkGray);
            checkAutomatic ();

            JTB1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JTB1.isSelected()) {
                        JTB1.setText("ON");
                        JTB1.setBackground(Color.blue);
                        buzzer.setText("The buzzer is currently turned on");
                        buzzer.setForeground(Color.blue);
                        subPanel.setBackground(Color.white);
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableSound" );
                        if (JTB2.getText().equals("ON")) {
                            JTB3.setText("ON");
                            JTB3.setBackground(Color.blue);
                            allOptions.setText("All of the options are turned on");
                            JTB1.setText("ON");
                            JTB1.setBackground(Color.blue);
                            buzzer.setText("The buzzer is currently turned on");
                            JTB2.setText("ON");
                            JTB2.setBackground(Color.blue);
                            humidifier.setText("The buzzer is currently turned on");
                            buzzer.setForeground(Color.blue);
                            humidifier.setForeground(Color.blue);
                            allOptions.setForeground(Color.blue);
                            subPanel.setBackground(Color.white);
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableHumidifier");
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableSound" );
                        }
                    } else {
                        JTB1.setText("OFF");
                        JTB1.setBackground(Color.darkGray);
                        buzzer.setText("The buzzer is currently turned off");
                        buzzer.setForeground(Color.darkGray);
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableSound");
                        if (JTB2.getText().equals("OFF")) {
                            JTB3.setText("OFF");
                            JTB3.setBackground(Color.darkGray);
                            allOptions.setText("All of the options are turned off");
                            JTB1.setText("OFF");
                            JTB1.setBackground(Color.darkGray);
                            buzzer.setText("The buzzer is currently turned off");
                            JTB2.setText("OFF");
                            JTB2.setBackground(Color.darkGray);
                            humidifier.setText("The humidifier is currently turned off");
                            humidifier.setForeground(Color.darkGray);
                            buzzer.setForeground(Color.darkGray);
                            allOptions.setForeground(Color.darkGray);
                            subPanel.setBackground(Color.gray);
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableHumidifier");
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableSound");
                        }
                    }

                }
            });

            JTB2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JTB2.isSelected()) {
                        JTB2.setText("ON");
                        JTB2.setBackground(Color.blue);
                        humidifier.setText("The humidifier is currently turned on");
                        humidifier.setForeground(Color.blue);
                        subPanel.setBackground(Color.white);
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableHumidifier");
                        if (JTB1.getText().equals("ON")) {
                            JTB3.setText("ON");
                            allOptions.setText("All of the options are turned on");
                            JTB3.setBackground(Color.blue);
                            JTB1.setText("ON");
                            JTB1.setBackground(Color.blue);
                            buzzer.setText("The buzzer is currently turned on");
                            JTB2.setText("ON");
                            JTB2.setBackground(Color.blue);
                            humidifier.setText("The humidifier is currently turned on");
                            buzzer.setForeground(Color.blue);
                            humidifier.setForeground(Color.blue);
                            allOptions.setForeground(Color.blue);
                            subPanel.setBackground(Color.white);
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableHumidifier");
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableSound" );
                        }
                    } else {
                        JTB2.setText("OFF");
                        JTB2.setBackground(Color.darkGray);
                        JTB3.setText("OFF");
                        humidifier.setText("The humidifier is currently turned off");
                        humidifier.setForeground(Color.darkGray);
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableHumidifier");
                        if (JTB2.getText().equals("OFF")) {
                            JTB3.setText("OFF");
                            JTB3.setBackground(Color.darkGray);
                            allOptions.setText("All of the options are turned off");
                            JTB1.setText("OFF");
                            JTB1.setBackground(Color.darkGray);
                            buzzer.setText("The buzzer is currently turned off");
                            JTB2.setText("OFF");
                            JTB2.setBackground(Color.darkGray);
                            humidifier.setText("The humidifier is currently turned off");
                            humidifier.setForeground(Color.darkGray);
                            buzzer.setForeground(Color.darkGray);
                            allOptions.setForeground(Color.darkGray);
                            subPanel.setBackground(Color.gray);
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableHumidifier");
                            makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableSound");
                        }
                    }
                }
            });

            JTB3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JTB3.isSelected()) {
                        JTB3.setText("ON");
                        JTB3.setBackground(Color.blue);
                        JTB1.setText("ON");
                        allOptions.setText("All of the options are turned on");
                        JTB1.setBackground(Color.blue);
                        buzzer.setText("The buzzer is currently turned on");
                        JTB2.setText("ON");
                        JTB2.setBackground(Color.blue);
                        humidifier.setText("The humidifier is currently turned on");
                        buzzer.setForeground(Color.blue);
                        humidifier.setForeground(Color.blue);
                        allOptions.setForeground(Color.blue);
                        subPanel.setBackground(Color.white);
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableHumidifier");
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/EnableSound" );

                    } else {
                        JTB3.setText("OFF");
                        JTB3.setBackground(Color.darkGray);
                        allOptions.setText("All of the options are turned off");
                        JTB1.setText("OFF");
                        JTB1.setBackground(Color.darkGray);
                        buzzer.setText("The buzzer is currently turned off");
                        JTB2.setText("OFF");
                        JTB2.setBackground(Color.darkGray);
                        humidifier.setText("The humidifier is currently turned off");
                        humidifier.setForeground(Color.darkGray);
                        buzzer.setForeground(Color.darkGray);
                        allOptions.setForeground(Color.darkGray);
                        subPanel.setBackground(Color.gray);
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableHumidifier");
                        makeGETRequest("https://studev.groept.be/api/a21ib2c06/DisableSound");
                    }

                }
            });
            setVisible(true);

        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAutomatic ();
            }
        });
    }


        //pack();
        public static void main ( String[] args){
            try2 myFrame1 = new try2();


        }

        public void checkAutomatic (){
            String temperature = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest");
            String noisiness = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest");
            String brightness = makeGETRequest("https://studev.groept.be/api/a21ib2c06/GetLatest");
            String bright = parseJSON(brightness, "Brightness");
            String noise = parseJSON(noisiness, "Noise");
            String temp = parseJSON(temperature, "Temperature");
            Float brightInt = Float.valueOf(bright);
            Float noiseFloat = Float.valueOf(noise);
            Float tempFloat = Float.valueOf(temp);

            System.out.println(brightInt);
            System.out.println(noiseFloat);
            System.out.println(tempFloat);

            if (brightInt < 400 && noiseFloat >30  && tempFloat > 21) {
                //eveything is on
                JTB3.setText("ON");
                JTB3.setBackground(Color.blue);
                JTB1.setText("ON");
                allOptions.setText("All of the options are turned on");
                JTB1.setBackground(Color.blue);
                buzzer.setText("The buzzer is currently turned on");
                JTB2.setText("ON");
                JTB2.setBackground(Color.blue);
                humidifier.setText("The humidifier is currently turned on");
                buzzer.setForeground(Color.blue);
                humidifier.setForeground(Color.blue);
                allOptions.setForeground(Color.blue);
                subPanel.setBackground(Color.white);
            } else if(brightInt < 400 && noiseFloat >30 && tempFloat < 21){
                //only the buzzer is on
                JTB1.setText("ON");
                JTB1.setBackground(Color.blue);
                buzzer.setText("The buzzer is currently turned on");
                buzzer.setForeground(Color.blue);
                subPanel.setBackground(Color.white);
                if (JTB2.getText().equals("ON")) {
                    JTB3.setText("ON");
                    JTB3.setBackground(Color.blue);
                    allOptions.setText("All of the options are turned on");
                    JTB1.setText("ON");
                    JTB1.setBackground(Color.blue);
                    buzzer.setText("The buzzer is currently turned on");
                    JTB2.setText("ON");
                    JTB2.setBackground(Color.blue);
                    humidifier.setText("The buzzer is currently turned on");
                    buzzer.setForeground(Color.blue);
                    humidifier.setForeground(Color.blue);
                    allOptions.setForeground(Color.blue);
                    subPanel.setBackground(Color.white);
                }

            }else{
                JTB3.setText("OFF");
                JTB3.setBackground(Color.darkGray);
                allOptions.setText("All of the options are turned off");
                JTB1.setText("OFF");
                JTB1.setBackground(Color.darkGray);
                buzzer.setText("The buzzer is currently turned off");
                JTB2.setText("OFF");
                JTB2.setBackground(Color.darkGray);
                humidifier.setText("The humidifier is currently turned off");
                humidifier.setForeground(Color.darkGray);
                buzzer.setForeground(Color.darkGray);
                allOptions.setForeground(Color.darkGray);
                subPanel.setBackground(Color.gray);
            }
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
