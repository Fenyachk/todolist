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
        JFrame frame = new JFrame(NAME);
        CalendarListener calendarListener = new CalendarListener(calendar, frame);
        drawCalendarWindow(frame);
        CustomTooltipView customTooltipSetView1 = new CustomTooltipView(calendar);
        customTooltipSetView1.reloadToolTips();
        calendarListener.addListener();
    }

    private static void drawCalendarWindow(JFrame frame) {
        frame.add(calendar);
        frame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
