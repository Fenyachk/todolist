package data.Listener;

import com.toedter.calendar.JCalendar;
import ui.view.TaskFormInputView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class CalendarListener {

    private static final String ADD_ITEM_MENU = "Добавить";
    private static final String EDIT_ITEM_MENU = "Редактировать";
    private static final String REMOVE_ITEM_MENU = "Удалить";

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
                            showContextMenu(e, dayButton);
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

    private void showContextMenu(MouseEvent e, JButton dayButton) {
        JPopupMenu contextMenu = new JPopupMenu();

        JMenuItem addItem = new JMenuItem(ADD_ITEM_MENU);
        contextMenu.add(addItem);

        JMenuItem editItem = new JMenuItem(EDIT_ITEM_MENU);
        contextMenu.add(editItem);

        JMenuItem removeItem = new JMenuItem(REMOVE_ITEM_MENU);
        contextMenu.add(removeItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    private void setVisibleListener() {
        TaskFormInputView taskFormInputView = new TaskFormInputView(CalendarListener.this.frame);
        taskFormInputView.setVisible(true);
    }
}
