package ui.view;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.toedter.calendar.JCalendar;
import ui.viewmodel.CalendarListener;

public class CalendarView {

    private static final Map<LocalDate, String> tooltips = new HashMap<>();

    public static void showCalendar() {
        JCalendar calendar = new JCalendar();
        JFrame frame = new JFrame("To-do List Calendar");
        CalendarListener calendarListener = new CalendarListener(calendar, frame);
        drawCalendarWindow(calendar, frame);
        setTooltips(calendar);
        calendarListener.addDoubleClickListener();
    }

    private static void drawCalendarWindow(JCalendar calendar, JFrame frame) {
        frame.add(calendar);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static void setTooltips(JCalendar calendar) {
        CustomTooltipSetView customTooltipSetView1 = new CustomTooltipSetView(calendar);
        customTooltipSetView1.setTooltips();
    }
}