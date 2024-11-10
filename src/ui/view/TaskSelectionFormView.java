package ui.view;

import com.toedter.calendar.JCalendar;
import data.model.Task;
import ui.viewmodel.TaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TaskSelectionFormView extends JDialog {

    private static final String EDIT_ITEM_MENU = "Редактировать";
    private static final String REMOVE_ITEM_MENU = "Удалить";
    private static final TaskViewModel taskViewModel = new TaskViewModel();
    private final JFrame frame;
    private final JCalendar calendar;

    public TaskSelectionFormView(JCalendar calendar, JFrame frame, List<Task> tasks, String title) {

        super(frame, "Выберите задачу", true);

        this.frame = frame;
        this.calendar = calendar;
        BorderLayout borderLayout = new BorderLayout();

        setLayout(borderLayout);

        JList<Task> taskList = new JList<>(new DefaultListModel<>());
        DefaultListModel<Task> model = (DefaultListModel<Task>) taskList.getModel();
        tasks.forEach(model::addElement);
        taskList.setCellRenderer((list, task, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(task.getTitle());
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });

        JPanel buttonPanel = new JPanel();
        JButton selectButton = new JButton(title);
        JButton cancelButton = new JButton("Отмена");

        selectButton.addActionListener(e -> {
            Task selectedTask = taskList.getSelectedValue();
            if (selectedTask != null) {
                chosenEditOrDelete(title, selectedTask);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);

        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 200);
        setLocationRelativeTo(frame);
    }

    public void chosenEditOrDelete(String title, Task task) {
        switch (title) {
            case EDIT_ITEM_MENU:
                this.dispose();
                LocalDateTime dateTask = task.getDate();
                TaskFormInputView taskFormInputView = new TaskFormInputView(this.frame, dateTask, this.calendar, task);
                taskFormInputView.setVisible(true);
                break;
            case REMOVE_ITEM_MENU:
                int taskId = task.getId();
                taskViewModel.deleteTask(taskId);
                this.dispose();
                break;
            default:
                break;
        }
    }
}
