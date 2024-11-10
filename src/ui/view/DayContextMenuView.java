//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.view;

import com.toedter.calendar.JCalendar;
import data.model.Task;

import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private final LocalDateTime date;
    private static final TaskViewModel taskViewModel = new TaskViewModel();
    private final JCalendar calendar;
    private final JFrame frame;
    private final LocalDate selectDay;

    public DayContextMenuView(MouseEvent mouseEvent, JFrame frame, LocalDateTime date, JCalendar calendar) {
        this.mouseEvent = mouseEvent;
        this.frame = frame;
        this.date = date;
        this.calendar = calendar;
        this.selectDay = date.toLocalDate();
    }

    public void getContextMenu() {
        List<Task> tasks = taskViewModel.getTaskByDate(this.selectDay);
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
        JMenuItem addItem = new JMenuItem(ADD_ITEM_MENU);
        addItemMenuListener(addItem);
        this.contextMenu.add(addItem);
    }

    private void setEditItemMenu() {
        JMenuItem editItem = new JMenuItem(EDIT_ITEM_MENU);
        editItemMenuListener(editItem);
        this.contextMenu.add(editItem);
    }

    private void setRemoveItemMenu() {
        JMenuItem removeItem = new JMenuItem(REMOVE_ITEM_MENU);
        removeItemMenuListener(removeItem);
        this.contextMenu.add(removeItem);
    }

    private void addItemInContextMenu() {
        this.contextMenu.show(this.mouseEvent.getComponent(), this.mouseEvent.getX(), this.mouseEvent.getY());
    }

    private void addItemMenuListener(JMenuItem addItem) {
        addItem.addActionListener((e) -> {
            TaskFormInputView taskFormInputView = new TaskFormInputView(this.frame, this.date, this.calendar, "", 1);
            taskFormInputView.setVisible(true);
        });
    }

    private void editItemMenuListener(JMenuItem editItem) {
        editItem.addActionListener((e) -> {
            List<Task> tasks = taskViewModel.getTaskByDate(this.selectDay);
            int countTask = tasks.size();
            if (countTask > 1) {
                TaskSelectionFormView taskSelectionFormView = new TaskSelectionFormView(calendar, frame, tasks, EDIT_ITEM_MENU);
                taskSelectionFormView.setVisible(true);
            }
        });
    }

    private void removeItemMenuListener(JMenuItem removeItem) {
        CustomTooltipView customTooltipView = new CustomTooltipView(this.calendar);
        removeItem.addActionListener((e) -> {
            List<Task> tasks = taskViewModel.getTaskByDate(this.selectDay);
            int countTask = tasks.size();
            if (countTask > 1) {
                TaskSelectionFormView taskSelectionFormView = new TaskSelectionFormView(calendar, frame, tasks, REMOVE_ITEM_MENU);
                taskSelectionFormView.setVisible(true);
            } else {
                int idTask = tasks.getFirst().getId();
                taskViewModel.deleteTask(idTask);
            }
            customTooltipView.reloadToolTips();
        });
    }

}
