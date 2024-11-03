import com.formdev.flatlaf.FlatLightLaf;
import data.model.Task;
import ui.view.CalendarView;
import javax.swing.UIManager;

import ui.viewmodel.TaskViewModel;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            FlatLightLaf theme = new FlatLightLaf();
            UIManager.setLookAndFeel(theme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TaskViewModel viewModel = new TaskViewModel();
        Task newTask = new Task(6, "Call the bank 2", "12.11.2024 16:45", 5);
        viewModel.addTask(newTask);
        viewModel.getAllTask();

        Map<Integer, Task> tasks = viewModel.getAllTask();
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
        }
        CalendarView calendarView = new CalendarView();
        calendarView.showCalendar();

    }
}