package de.esolutions.net;

import javax.swing.*;
import java.awt.*;

public class TestFrame {

    int counter;
    BlinkerThread blinker;

    public TestFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        counter = 0;

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Font f = new Font("Sans", Font.BOLD, 32);
        JFrame frame = new JFrame("WindowTitle");
        JLabel label = new JLabel(Integer.toString(counter));
        JButton resetButton = new JButton("reset");
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton blinkButton = new JButton("blink");
        blinker = new BlinkerThread(label);
        blinker.start();

//        blinkButton.setFont(f);
        label.setFont(f);
        plusButton.setFont(f);
        minusButton.setFont(f);
        frame.add(resetButton, BorderLayout.NORTH);
        frame.add(plusButton, BorderLayout.WEST);
        frame.add(minusButton, BorderLayout.EAST);
        frame.add(label, BorderLayout.CENTER);
        frame.add(blinkButton, BorderLayout.SOUTH);

        blinkButton.setActionCommand("pressedBlinkButton");
        blinkButton.addActionListener((a) -> blinker.toggleBlink());
       // plusButton.addActionListener(a -> label.setText("" + ++counter));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class BlinkerThread extends Thread {

        private volatile boolean blinking;
        JComponent component;
        private final Color FIRST_COLOR = Color.BLACK;
        private final Color SECOND_COLOR = Color.RED;

        public BlinkerThread(JComponent comp) {
            blinking = false;
            this.component = comp;
        }

        public void toggleBlink() {
            blinking = !blinking;
        }

        public void blink() {
            if (component.getForeground() == FIRST_COLOR) {
                component.setForeground(SECOND_COLOR);
            } else {
                component.setForeground(FIRST_COLOR);
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                    if (blinking) {
                        blink();
                    }
                } catch (InterruptedException e) {
                    interrupted();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new TestFrame();
    }

}
