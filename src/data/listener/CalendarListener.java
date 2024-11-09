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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ui.view.DayContextMenuView;
import ui.view.TaskFormInputView;

public class CalendarListener {
    private final JCalendar calendar;
    private final JFrame frame;
    private LocalDate chosenDay;

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
                            LocalDate date = CalendarListener.this.IdentifySelectedDay(dayButton);
                            DayContextMenuView dayContextMenu = new DayContextMenuView(e, CalendarListener.this.frame, date, CalendarListener.this.calendar);
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
                            LocalDate date = CalendarListener.this.IdentifySelectedDay(dayButton);
                            CalendarListener.this.setVisibleListener(date);
                        }

                    }
                });
            }
        }

    }

    private LocalDate IdentifySelectedDay(JButton dayButton) {
        int currentMonth = this.calendar.getMonthChooser().getMonth() + 1;
        int currentYear = this.calendar.getYearChooser().getYear();
        String dayText = dayButton.getText();
        int day = Integer.parseInt(dayText);
        LocalDate date = LocalDate.of(currentYear, currentMonth, day);
        return date;
    }

    private void setVisibleListener(LocalDate date) {
        TaskFormInputView taskFormInputView = new TaskFormInputView(this.frame, date, this.calendar);
        taskFormInputView.showWindowInputForm();
        taskFormInputView.setVisible(true);
    }
}
