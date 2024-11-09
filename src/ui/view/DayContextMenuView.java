//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.view;

import com.toedter.calendar.JCalendar;
import data.model.Task;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ui.viewmodel.TaskViewModel;

public class DayContextMenuView {
    private static final String ADD_ITEM_MENU = "Добавить";
    private static final String EDIT_ITEM_MENU = "Редактировать";
    private static final String REMOVE_ITEM_MENU = "Удалить";
    private final MouseEvent mouseEvent;
    private final JPopupMenu contextMenu = new JPopupMenu();
    private final LocalDate date;
    private static final TaskViewModel taskViewModel = new TaskViewModel();
    private final JCalendar calendar;
    private final JFrame frame;

    public DayContextMenuView(MouseEvent mouseEvent, JFrame frame, LocalDate date, JCalendar calendar) {
        this.mouseEvent = mouseEvent;
        this.frame = frame;
        this.date = date;
        this.calendar = calendar;
    }

    public void getContextMenu() {
        Map<Integer, Task> tasks = taskViewModel.getTaskByDate(this.date);
        if (tasks.isEmpty()) {
            this.getContextMenuWithoutTask();
        } else {
            this.getContextMenuWithTask();
        }

    }

    public void getContextMenuWithoutTask() {
        this.setAddItemMenu();
        this.addItemInContextMenu();
    }

    public void getContextMenuWithTask() {
        this.setAddItemMenu();
        this.setEditItemMenu();
        this.setRemoveItemMenu();
        this.addItemInContextMenu();
    }

    private void setAddItemMenu() {
        JMenuItem addItem = new JMenuItem("Добавить");
        addItem.addActionListener((e) -> {
            TaskFormInputView taskFormInputView = new TaskFormInputView(this.frame, this.date, this.calendar);
            taskFormInputView.showWindowInputForm();
            taskFormInputView.setVisible(true);
        });
        this.contextMenu.add(addItem);
    }

    private void setEditItemMenu() {
        JMenuItem editItem = new JMenuItem("Редактировать");
        this.contextMenu.add(editItem);
    }

    private void setRemoveItemMenu() {
        JMenuItem removeItem = new JMenuItem("Удалить");
        this.contextMenu.add(removeItem);
    }

    private void addItemInContextMenu() {
        this.contextMenu.show(this.mouseEvent.getComponent(), this.mouseEvent.getX(), this.mouseEvent.getY());
    }
}
