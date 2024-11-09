//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.view;

import com.toedter.calendar.JCalendar;
import data.model.Task;

import java.awt.GridLayout;
import java.text.ParseException;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import ui.viewmodel.TaskViewModel;

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
    private static final String DATE_FORMAT = "##.##.#### ##:##";
    private final JCalendar calendar;
    private static final TaskViewModel taskViewModel = new TaskViewModel();
    private final JFrame parent;
    private JFormattedTextField dateTextField;
    private final JTextField titleTextField;
    private final JComboBox priorityTextField;

    public TaskFormInputView(JFrame parent, LocalDate date, JCalendar calendar) {
        super(parent, "Информация о задаче", true);
        this.parent = parent;
        this.calendar = calendar;
        this.dateTextField = createFormattedTextField("##.##.#### ##:##");
        String dateFormatted = this.FormattedDate(date);
        this.dateTextField.setValue(dateFormatted);
        Integer[] priorityValueField = new Integer[]{1, 2, 3, 4, 5};
        this.priorityTextField = new JComboBox(priorityValueField);
        this.titleTextField = new JTextField();
    }

    public void showWindowInputForm() {
        JPanel jp = new JPanel(GRID_LAYOUT);
        jp.setBorder(BORDER_PANEL);
        JLabel dateLabel = new JLabel("Дата (dd.MM.yyyy HH:mm):");
        JLabel titleLabel = new JLabel("Название задачи:");
        JLabel priorityLabel = new JLabel("Приоритет (1-5):");
        JButton cancelButton = new JButton("Отмена");
        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener((e) -> {
            this.saveTask();
        });
        cancelButton.addActionListener((e) -> {
            this.dispose();
        });
        jp.add(dateLabel);
        jp.add(this.dateTextField);
        jp.add(titleLabel);
        jp.add(this.titleTextField);
        jp.add(priorityLabel);
        jp.add(this.priorityTextField);
        jp.add(cancelButton);
        jp.add(saveButton);
        this.add(jp);
        this.setSize(300, 250);
        this.setLocationRelativeTo(this.parent);
    }

    private void saveTask() {
        CustomTooltipView customTooltipView = new CustomTooltipView(this.calendar);
        String date = this.dateTextField.getText();
        String name = this.titleTextField.getText();
        int priority = (Integer) this.priorityTextField.getSelectedItem();
        Task task = new Task(name, date, priority);
        taskViewModel.addTask(task);
        customTooltipView.setTooltips();
        this.dispose();
    }

    private static JFormattedTextField createFormattedTextField(String mask) {
        JFormattedTextField formattedTextField = null;

        try {
            MaskFormatter dateFormatter = new MaskFormatter(mask);
            dateFormatter.setPlaceholderCharacter('_');
            formattedTextField = new JFormattedTextField(dateFormatter);
        } catch (ParseException var3) {
            ParseException e = var3;
            e.printStackTrace();
        }

        return formattedTextField;
    }

    private String FormattedDate(LocalDate date) {
        int day = date.getDayOfMonth();
        String dayWithLeadingZero = String.format("%02d", day);
        int month = date.getMonthValue();
        String monthWithLeadingZero = String.format("%02d", month);
        int year = date.getYear();
        return dayWithLeadingZero + "." + monthWithLeadingZero + "." + year + " 10:00";
    }
}
