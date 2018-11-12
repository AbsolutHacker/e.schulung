package de.esolutions.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.DoubleBinaryOperator;

public class Calculator extends JFrame {

    private static final Font BUTTON_FONT = new Font("Sans", Font.BOLD, 32);
    private static final Font DISPLAY_FONT = new Font("Sans", Font.BOLD, 48);

    private double cur;
    private boolean curIsFixed;
    private int curRadixDivider;

    private double ans;
    private static final int radix = 10;
    private DoubleBinaryOperator op;

    JLabel display;

    public Calculator() {
        super("Nickulator");
        cur = 0.0;
        ans = 0.0;
        display = new JLabel("0");
        display.setFont(DISPLAY_FONT);
        display.setHorizontalTextPosition(SwingConstants.RIGHT);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(display, BorderLayout.NORTH);


        JPanel keypadContainer = new JPanel(new GridLayout(4,4));
        String[] keys = new String[] {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "+",
                "0", ",", "=", "-"
            };
        for (int i = 0; i < keys.length; i++) {
            JButton button = new JButton(keys[i]);
            button.setFont(BUTTON_FONT);
            button.addActionListener(this::parseKey);
            keypadContainer.add(button);
        }
        this.add(keypadContainer, BorderLayout.CENTER);

        JButton clearButton = new JButton("C");
        clearButton.addActionListener(this::parseKey);
        clearButton.setFont(BUTTON_FONT);
        this.add(clearButton, BorderLayout.WEST);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void parseKey(ActionEvent a) {
        String keyString = a.getActionCommand();
        System.out.println(keyString); // debug
        switch(keyString) {
            case "=":
                eval();
                break;
            case ",":
                if (curIsFixed)
                    break;
                curIsFixed = true;
                curRadixDivider = 1;
                break;
            case "C":
                ans = 0.0;
                clearCurrent();
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                eval();
                switch(keyString) {
                    case "+":
                        op = (l, r) -> l + r;
                        break;
                    case "-":
                        op = (l, r) -> l - r;
                        break;
                    case "/":
                        op = (l, r) -> l / r;
                        break;
                    case "*":
                        op = (l, r) -> l * r;
                        break;
                }
                shift();
                break;
            default:
                try {
                    int key = Integer.parseInt(keyString);
                    if (!curIsFixed) {
                        cur *= radix;
                        cur += key;
                    } else {
                        cur += (key/Math.pow(radix, curRadixDivider));
                        curRadixDivider++;
                    }
                } catch (NumberFormatException exc) {
                    break;
                }
        }
        updateDisplay();
    }
    private void shift() {
        ans = cur;
        clearCurrent();
    }
    private void eval() {
        if (op != null)
            cur = op.applyAsDouble(ans, cur);
        op = null;
    }
    private void clearCurrent() {
        cur = 0.0;
        curIsFixed = false;
        curRadixDivider = 0;
    }
    private void updateDisplay() {
        display.setText(Double.toString(cur));
    }
    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }
}