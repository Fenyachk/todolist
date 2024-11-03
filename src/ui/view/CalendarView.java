package ui.view;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.toedter.calendar.JCalendar;
import ui.viewmodel.CustomTooltip;

public class CalendarView {

    private static final Map<LocalDate, String> tooltips = new HashMap<>();

    public static void showCalendar() {
        JCalendar calendar = new JCalendar();
        JFrame frame = new JFrame("To-do List Calendar");
        drawCalendarWindow(calendar, frame);
        setTooltips(calendar);
    }

    private static void drawCalendarWindow(JCalendar calendar, JFrame frame) {
        frame.add(calendar);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static void setTooltips(JCalendar calendar) {
        JPopupMenu tooltipPopup = new JPopupMenu();
        JLabel tooltipLabel = new JLabel();
        CustomTooltip customTooltipSetView = new CustomTooltip();
        CustomTooltip customTooltip = new CustomTooltip();
        CustomTooltipSetView customTooltipSetView1 = new CustomTooltipSetView(calendar, tooltipPopup, tooltipLabel);
        tooltipPopup.add(tooltipLabel);
        customTooltipSetView1.setTooltips();
    }
}