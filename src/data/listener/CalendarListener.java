//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.listener;

import com.toedter.calendar.JCalendar;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ui.view.DayContextMenuView;
import ui.view.TaskFormInputView;

public class CalendarListener {
    private final JCalendar calendar;
    private final JFrame frame;
    private LocalDateTime chosenDay;

    public CalendarListener(JCalendar calendar, JFrame frame) {
        this.calendar = calendar;
        this.frame = frame;
    }

    public void addListener() {
        this.addDoubleClickListener();
        this.addRightClickListener();
    }

    private void addRightClickListener() {
        Component[] var1 = this.calendar.getDayChooser().getDayPanel().getComponents();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            Component component = var1[var3];
            if (component instanceof final JButton dayButton) {
                dayButton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            LocalDateTime date = CalendarListener.this.IdentifySelectedDay(dayButton);
                            DayContextMenuView dayContextMenu = new DayContextMenuView(e, frame, date, calendar);
                            dayContextMenu.getContextMenu();
                        }

                    }
                });
            }
        }

    }

    private void addDoubleClickListener() {
        Component[] var1 = this.calendar.getDayChooser().getDayPanel().getComponents();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            Component component = var1[var3];
            if (component instanceof final JButton dayButton) {
                dayButton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            LocalDateTime date = CalendarListener.this.IdentifySelectedDay(dayButton);
                            CalendarListener.this.setVisibleListener(date);
                        }

                    }
                });
            }
        }

    }

    private LocalDateTime IdentifySelectedDay(JButton dayButton) {
        int currentMonth = this.calendar.getMonthChooser().getMonth() + 1;
        int currentYear = this.calendar.getYearChooser().getYear();
        String dayText = dayButton.getText();
        int day = Integer.parseInt(dayText);
        LocalDateTime date = LocalDateTime.of(currentYear, currentMonth, day, 10, 00);
        return date;
    }

    private void setVisibleListener(LocalDateTime date) {
        TaskFormInputView taskFormInputView = new TaskFormInputView(this.frame, date, this.calendar, "", 1);
        taskFormInputView.setVisible(true);
    }
}
