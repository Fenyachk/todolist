package ui.view;

import data.model.Task;
import ui.viewmodel.TaskViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class TaskFormInputView extends JDialog {

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 250;
    private static final GridLayout GRID_LAYOUT = new GridLayout(5, 2, 10, 10);
    private static final Border BORDER_PANEL = BorderFactory.createEmptyBorder(20, 20, 20, 20);

    private static final String NAME = "Информация о задаче";
    private static final String DATE_LABEL = "Дата (dd.MM.yyyy HH:mm):";
    private static final String TEXT_LABEL = "Название задачи:";
    private static final String PRIORITY_LABEL = "Приоритет (1-5):";
    private static final String SAVE_BUTTON_LABEL = "Сохранить";
    private static final String CANCEL_BUTTON_LABEL = "Отмена";

    private JFormattedTextField dateTextField;
    private final JTextField titleTextField;
    private final JComboBox priorityTextField;

    public TaskFormInputView(JFrame parent) {
        super(parent, NAME, true);

        JPanel jp = new JPanel(GRID_LAYOUT);
        jp.setBorder(BORDER_PANEL);

        JLabel dateLabel = new JLabel(DATE_LABEL);
        JFormattedTextField dateTextField = createFormattedTextField("##.##.#### ##:##");

        JLabel titleLabel = new JLabel(TEXT_LABEL);
        titleTextField = new JTextField();

        JLabel priorityLabel = new JLabel(PRIORITY_LABEL);
        Integer[] priorityValueField = new Integer[]{1, 2, 3, 4, 5};
        priorityTextField = new JComboBox<>(priorityValueField);

        JButton cancelButton = new JButton(CANCEL_BUTTON_LABEL);
        JButton saveButton = new JButton(SAVE_BUTTON_LABEL);

        saveButton.addActionListener(e -> saveTask());
        cancelButton.addActionListener(e -> dispose());

        jp.add(dateLabel);
        jp.add(dateTextField);
        jp.add(titleLabel);
        jp.add(titleTextField);
        jp.add(priorityLabel);
        jp.add(priorityTextField);
        jp.add(cancelButton);
        jp.add(saveButton);

        add(jp);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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

    private static JFormattedTextField createFormattedTextField(String mask) {
        JFormattedTextField formattedTextField = null;
        try {
            MaskFormatter dateFormatter = new MaskFormatter(mask);
            dateFormatter.setPlaceholderCharacter('_');
            formattedTextField = new JFormattedTextField(dateFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTextField;
    }
}
