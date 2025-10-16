import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JTextField inputField;
    private JComboBox<String> fromUnit, toUnit;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel title = new JLabel("Temperature Converter", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        inputField = new JTextField();
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromUnit = new JComboBox<>(units);
        toUnit = new JComboBox<>(units);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);

        convertButton.addActionListener(this);

        add(title);
        add(inputField);
        add(fromUnit);
        add(toUnit);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();
            double result = convertTemperature(inputTemp, from, to);
            resultLabel.setText("Result: " + result + " " + to);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    private double convertTemperature(double value, String from, String to) {
        if (from.equals(to)) return value;

        if (from.equals("Celsius")) {
            if (to.equals("Fahrenheit")) return (value * 9/5) + 32;
            else return value + 273.15;
        } else if (from.equals("Fahrenheit")) {
            if (to.equals("Celsius")) return (value - 32) * 5/9;
            else return ((value - 32) * 5/9) + 273.15;
        } else {
            if (to.equals("Celsius")) return value - 273.15;
            else return ((value - 273.15) * 9/5) + 32;
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}
