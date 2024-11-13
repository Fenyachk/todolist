package ui.view;

import javax.swing.*;
import java.awt.*;

public class CircleOnCellDayView extends JPanel {

    public CircleOnCellDayView(int priority) {
        Color color = getBackgroundCellDay(priority);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 3, 3);
        setLayout(layout);
        setOpaque(false);

        JPanel circle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        circle.setPreferredSize(new Dimension(10, 10));
        add(circle);
    }

    private static Color getBackgroundCellDay(int priority) {
        Color var10000;
        switch (priority) {
            case 1 -> var10000 = Color.GREEN;
            case 2 -> var10000 = Color.YELLOW;
            case 3 -> var10000 = Color.ORANGE;
            case 4 -> var10000 = Color.PINK;
            case 5 -> var10000 = Color.RED;
            default -> var10000 = Color.WHITE;
        }

        return var10000;
    }

}
