package data.listener;

import com.toedter.calendar.JCalendar;
import ui.view.DayContextMenuView;
import ui.view.TaskFormInputView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        for (Component component : this.calendar.getDayChooser().getDayPanel().getComponents()) {
            if (component instanceof JButton dayButton) {
                dayButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            DayContextMenuView dayContextMenu = new DayContextMenuView(e);
                            dayContextMenu.getContextMenuWithTask();
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
