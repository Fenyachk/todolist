//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.view;

import com.toedter.calendar.JCalendar;
import data.listener.CalendarListener;

import javax.swing.JFrame;

public class CalendarView {
    private static final String NAME = "To-do List Calendar";
    private static final int WIDTH_WINDOW = 400;
    private static final int HEIGHT_WINDOW = 400;
    private static final JCalendar calendar = new JCalendar();

    public CalendarView() {
    }

    public static void showCalendar() {
        JFrame frame = new JFrame("To-do List Calendar");
        CalendarListener calendarListener = new CalendarListener(calendar, frame);
        drawCalendarWindow(frame);
        setTooltips();
        calendarListener.addListener();
    }

    private static void drawCalendarWindow(JFrame frame) {
        frame.add(calendar);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    private static void setTooltips() {
        CustomTooltipView customTooltipSetView1 = new CustomTooltipView(calendar);
        customTooltipSetView1.setTooltips();
        customTooltipSetView1.setTooltipsChangeDMY();
    }
}
