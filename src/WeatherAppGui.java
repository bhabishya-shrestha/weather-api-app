import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WeatherAppGui extends JFrame {
    public WeatherAppGui() {
        super("Weather App");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 650);

        setLocationRelativeTo(null);

        setLayout(null);

        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {

        // searchbar component
        JTextField searchTextField = new JTextField();

        searchTextField.setBounds(15, 15, 351, 45);

        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchTextField);

        // search button component
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));

        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        searchButton.setBounds(375, 13, 47, 45);

        add(searchButton);

        // weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));

        weatherConditionImage.setBounds(0, 125, 450, 217);

        add(weatherConditionImage);

        // temperature info
        JLabel temperatureText = new JLabel("70 F");

        temperatureText.setBounds(0, 350, 450, 54);

        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));

        // center text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);

        add(temperatureText);

        // weather description
        JLabel weatherConditionDescription = new JLabel("Cloudy");

        weatherConditionDescription.setBounds(0, 405, 450, 36);

        weatherConditionDescription.setFont(new Font("Dialog", Font.PLAIN, 32));

        weatherConditionDescription.setHorizontalAlignment((SwingConstants.CENTER));

        add(weatherConditionDescription);
    }

    private ImageIcon loadImage(String resourcePath) {
        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));

            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }
}
