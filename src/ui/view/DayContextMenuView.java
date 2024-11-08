package ui.view;

import data.model.Task;
import ui.viewmodel.TaskViewModel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Map;

public class DayContextMenuView {

    private static final String ADD_ITEM_MENU = "Добавить";
    private static final String EDIT_ITEM_MENU = "Редактировать";
    private static final String REMOVE_ITEM_MENU = "Удалить";
    private final MouseEvent mouseEvent;

    private final JPopupMenu contextMenu = new JPopupMenu();
    private final LocalDate date;

    public DayContextMenuView(MouseEvent mouseEvent, LocalDate date) {
        this.mouseEvent = mouseEvent;
        this.date = date;
    }

    public void getContextMenu() {
        TaskViewModel taskViewModel = new TaskViewModel();
        Map<Integer, Task> tasks = taskViewModel.getTaskByDate(this.date);
        if (tasks.isEmpty()) {
            getContextMenuWithoutTask();
        } else {
            getContextMenuWithTask();
        }
    }

    public void getContextMenuWithoutTask() {
        setAddItemMenu();
        addItemInContextMenu();
    }

    public void getContextMenuWithTask() {
        setAddItemMenu();
        setEditItemMenu();
        setRemoveItemMenu();
        addItemInContextMenu();
    }

    private void setAddItemMenu() {
        JMenuItem addItem = new JMenuItem(ADD_ITEM_MENU);
        contextMenu.add(addItem);
    }

    private void setEditItemMenu() {
        JMenuItem editItem = new JMenuItem(EDIT_ITEM_MENU);
        contextMenu.add(editItem);
    }

    private void setRemoveItemMenu() {
        JMenuItem removeItem = new JMenuItem(REMOVE_ITEM_MENU);
        contextMenu.add(removeItem);
    }

    private void addItemInContextMenu() {
        contextMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
    }
}
