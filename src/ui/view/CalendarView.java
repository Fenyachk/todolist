package ui.view;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;

public class CalendarView {

    public static void showCalendar() {
        JCalendar calendar = new JCalendar();
        JFrame frame = new JFrame("To-do List Calendar");
        BorderLayout borderLayout = new BorderLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(borderLayout);
        frame.add(calendar, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}
