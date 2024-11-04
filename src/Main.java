import com.formdev.flatlaf.FlatLightLaf;
import ui.view.CalendarView;
import ui.view.TaskFormInputView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            FlatLightLaf theme = new FlatLightLaf();
            UIManager.setLookAndFeel(theme);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CalendarView calendarView = new CalendarView();
        calendarView.showCalendar();
    }
}