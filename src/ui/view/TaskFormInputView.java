package ui.view;

import data.model.Task;
import ui.viewmodel.TaskViewModel;

import javax.swing.*;
import java.awt.*;

public class TaskFormInputView extends JDialog {

    private final JLabel dateLabel;
    private final JLabel titleLabel;
    private final JLabel priorityLabel;
    private final JTextField dateTextField;
    private final JTextField titleTextField;
    private final JComboBox priorityTextField;
    private final JButton saveButton;
    private final JButton cancelButton;

    public TaskFormInputView(JFrame parent) {
        super(parent, "Информация о задаче", true);

        GridLayout gridLayout = new GridLayout(5, 2, 10, 10);
        JPanel jp = new JPanel(gridLayout);
        jp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        dateLabel = new JLabel("Дата (dd.MM.yyyy HH:mm):");
        dateTextField = new JTextField();

        titleLabel = new JLabel("Название задачи:");
        titleTextField = new JTextField();

        priorityLabel = new JLabel("Приоритет (1-5):");
        priorityTextField = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        saveButton = new JButton("Сохранить");
        cancelButton = new JButton("Отменить");

        saveButton.addActionListener(e -> saveTask());
        cancelButton.addActionListener(e -> dispose());

        jp.add(dateLabel);
        jp.add(dateTextField);
        jp.add(titleLabel);
        jp.add(titleTextField);
        jp.add(priorityLabel);
        jp.add(priorityTextField);
        jp.add(saveButton);
        jp.add(cancelButton);

        add(jp);
        setSize(300, 250);
        setLocationRelativeTo(parent);
    }

    private void saveTask() {
        TaskViewModel taskViewModel = new TaskViewModel();
        String date = dateTextField.getText();
        String name = titleTextField.getText();
        int priority = (int) priorityTextField.getSelectedItem();
        Task task = new Task(7, name, date, priority);
        taskViewModel.addTask(task);
        dispose();
    }

}
