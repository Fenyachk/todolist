import com.formdev.flatlaf.FlatLightLaf;
import ui.view.CalendarView;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        try {
            FlatLightLaf theme = new FlatLightLaf();
            UIManager.setLookAndFeel(theme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CalendarView.showCalendar();
    }
}