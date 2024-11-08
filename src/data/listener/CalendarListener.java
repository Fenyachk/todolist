package data.listener;

import com.toedter.calendar.JCalendar;
import ui.view.DayContextMenuView;
import ui.view.TaskFormInputView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class CalendarListener {

    private final JCalendar calendar;
    private final JFrame frame;

    public CalendarListener(JCalendar calendar, JFrame frame) {
        this.calendar = calendar;
        this.frame = frame;
    }

    public void addListener() {
        addDoubleClickListener();
        addRightClickListener();
    }

    private void addRightClickListener() {
        int currentMonth = this.calendar.getMonthChooser().getMonth() + 1;
        int currentYear = this.calendar.getYearChooser().getYear();
        for (Component component : this.calendar.getDayChooser().getDayPanel().getComponents()) {
            if (component instanceof JButton dayButton) {
                dayButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            String dayText = dayButton.getText();
                            int day = Integer.parseInt(dayText);
                            LocalDate date = LocalDate.of(currentYear, currentMonth, day);
                            DayContextMenuView dayContextMenu = new DayContextMenuView(e, date);
                            dayContextMenu.getContextMenu();
                        }
                    }
                });
            }
        }
    }

    private void addDoubleClickListener() {
        for (Component component : this.calendar.getDayChooser().getDayPanel().getComponents()) {
            if (component instanceof JButton dayButton) {
                dayButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            setVisibleListener();
                        }
                    }
                });
            }
        }
    }


    private void setVisibleListener() {
        TaskFormInputView taskFormInputView = new TaskFormInputView(CalendarListener.this.frame);
        taskFormInputView.setVisible(true);
    }
}
