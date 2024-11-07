package ui.view;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.toedter.calendar.JCalendar;
import data.listener.CalendarListener;

public class CalendarView {

    private static final Map<LocalDate, String> tooltips = new HashMap<>();
    private static final String NAME = "To-do List Calendar";
    private static final int WIDTH_WINDOW = 400;
    private static final int HEIGHT_WINDOW = 400;
    private static final JCalendar calendar = new JCalendar();

    public static void showCalendar() {
        JFrame frame = new JFrame(NAME);
        CalendarListener calendarListener = new CalendarListener(calendar, frame);
        drawCalendarWindow(frame);
        setTooltips();
        calendarListener.addListener();
    }

    private static void drawCalendarWindow(JFrame frame) {
        frame.add(calendar);
        frame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static void setTooltips() {
        CustomTooltipView customTooltipSetView1 = new CustomTooltipView(calendar);
        customTooltipSetView1.setTooltips();
    }
}