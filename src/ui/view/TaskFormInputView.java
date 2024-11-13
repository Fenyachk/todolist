//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.view;

import com.toedter.calendar.JCalendar;
import data.model.PriorityItemSelector;
import data.model.Task;

import java.awt.GridLayout;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import org.apache.hc.core5.reactor.Command;
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
    private static final String DATE_FORMATTED = "##.##.#### ##:##";
    private static final String DATE_FORMAT = "%02d";
    private static final String TIME_START = "10:00";
    private final JCalendar calendar;
    private static final TaskViewModel taskViewModel = new TaskViewModel();
    private final JFrame parent;
    private final Task task;
    private JFormattedTextField dateTextField;
    private final JTextField titleTextField;
    private final JComboBox priorityTextField;
    private final PriorityItemSelector[] priorityValueField = new PriorityItemSelector[]{
            new PriorityItemSelector(1, "Очень низкий"),
            new PriorityItemSelector(2, "Низкий"),
            new PriorityItemSelector(3, "Средний"),
            new PriorityItemSelector(4, "Повышенный"),
            new PriorityItemSelector(5, "Очень высокий")
    };

    public TaskFormInputView(JFrame parent, LocalDateTime date, JCalendar calendar, String title, int priority) {
        super(parent, NAME, true);
        this.parent = parent;
        this.calendar = calendar;
        this.dateTextField = createFormattedTextField(DATE_FORMATTED);
        String dateFormatted = this.FormattedDate(date);
        this.dateTextField.setValue(dateFormatted);
        this.priorityTextField = new JComboBox(priorityValueField);
        this.setSelectedPriority(priority);
        this.titleTextField = new JTextField();
        this.titleTextField.setText(title);
        this.task = null;
        showWindowInputForm();
    }

    public TaskFormInputView(JFrame parent, LocalDateTime date, JCalendar calendar, Task task) {
        super(parent, NAME, true);
        this.parent = parent;
        this.calendar = calendar;
        this.dateTextField = createFormattedTextField(DATE_FORMATTED);
        String dateFormatted = this.FormattedDate(date);
        this.dateTextField.setValue(dateFormatted);
        this.priorityTextField = new JComboBox(priorityValueField);
        String titleTask = task.getName();
        int priorityTask = task.getPriority();
        this.setSelectedPriority(priorityTask);
        this.titleTextField = new JTextField();
        this.titleTextField.setText(titleTask);
        this.task = task;
        showWindowInputForm();
    }

    public void showWindowInputForm() {
        JPanel jp = new JPanel(GRID_LAYOUT);
        jp.setBorder(BORDER_PANEL);
        JLabel dateLabel = new JLabel(DATE_LABEL);
        JLabel titleLabel = new JLabel(TEXT_LABEL);
        JLabel priorityLabel = new JLabel(PRIORITY_LABEL);
        JButton cancelButton = new JButton(CANCEL_BUTTON_LABEL);
        JButton saveButton = new JButton(SAVE_BUTTON_LABEL);
        CustomTooltipView customTooltipView = new CustomTooltipView(this.calendar);
        saveButton.addActionListener((e) -> {
            if (this.task == null) {
                this.saveTask();
            } else {
                this.saveTask(task);
            }
            customTooltipView.reloadToolTips();
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
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(this.parent);
    }

    private void saveTask() {
        String date = this.dateTextField.getText();
        String name = this.titleTextField.getText();
        PriorityItemSelector selectedPriority = (PriorityItemSelector) this.priorityTextField.getSelectedItem();
        int priority = selectedPriority.getPriority();
        Task task = new Task(name, date, priority);
        taskViewModel.addTask(task);
        this.dispose();
    }

    private void saveTask(Task task) {
        String date = this.dateTextField.getText();
        String name = this.titleTextField.getText();
        PriorityItemSelector selectedPriority = (PriorityItemSelector) this.priorityTextField.getSelectedItem();
        int priority = selectedPriority.getPriority();
        task.setName(name);
        task.setDate(date);
        task.setPriority(priority);
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

    private String FormattedDate(LocalDateTime date) {
        int day = date.getDayOfMonth();
        String dayWithLeadingZero = String.format(DATE_FORMAT, day);
        int month = date.getMonthValue();
        String monthWithLeadingZero = String.format(DATE_FORMAT, month);
        int year = date.getYear();
        int hour = date.getHour();
        String hourWithLeadingZero = String.format(DATE_FORMAT, hour);
        int minute = date.getMinute();
        String minuteWithLeadingZero = String.format(DATE_FORMAT, minute);
        return dayWithLeadingZero + "." + monthWithLeadingZero + "." + year + " " + hourWithLeadingZero + ":" + minuteWithLeadingZero;
    }

    private void setSelectedPriority(int priority) {
        for (PriorityItemSelector priorityItemSelector : priorityValueField) {
            if (priorityItemSelector.getPriority() == priority) {
                this.priorityTextField.setSelectedItem(priorityItemSelector);
                break;
            }
        }
    }

}
