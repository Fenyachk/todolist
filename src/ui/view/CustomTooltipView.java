package ui.view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import data.model.CustomTooltip;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CustomTooltipView {

    private final JCalendar calendar;
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private static final CustomTooltip customTooltip = new CustomTooltip();

    public CustomTooltipView(JCalendar calendar) {
        this.calendar = calendar;
        JPopupMenu tooltipPopup = new JPopupMenu();
        JLabel tooltipLabel = new JLabel();
        tooltipPopup.add(tooltipLabel);
    }

    public void setTooltips() {
        this.setDayToolTips();
        this.calendar.getDayChooser().addPropertyChangeListener(DAY, evt -> {
            this.setDayToolTips();
        });
        this.calendar.getMonthChooser().addPropertyChangeListener(MONTH, evt -> {
            this.setDayToolTips();
        });
        this.calendar.getYearChooser().addPropertyChangeListener(YEAR, evt -> {
            this.setDayToolTips();
        });
    }

    private void setDayToolTips() {
        JDayChooser dayChooser = this.calendar.getDayChooser();
        int currentMonth = this.calendar.getMonthChooser().getMonth();
        int currentYear = this.calendar.getYearChooser().getYear();

        for (Component component : dayChooser.getDayPanel().getComponents()) {
            if (component instanceof JButton dayButton) {
                setDayButtonToolTip(dayButton, currentYear, currentMonth);
            }
        }
    }

    private static void setDayButtonToolTip(JButton dayButton, int year, int month) {
        String dayText = dayButton.getText();
        if (dayText.matches("\\d+")) {
            int day = Integer.parseInt(dayText);
            LocalDate date = LocalDate.of(year, month + 1, day);
            List<CustomTooltip> tooltip = customTooltip.getTasksByDate(date);
            if (tooltip != null) {
                for (CustomTooltip customTooltipForOutputs : tooltip) {
                    String title = customTooltipForOutputs.getTitle();
                    int priority = customTooltipForOutputs.getPriority();
                    Color color = getBackgroundCellDay(priority);
                    dayButton.setToolTipText(title);
                    dayButton.setBackground(color);
                }
            } else {
                dayButton.setToolTipText(null);
            }
        }
    }

    private static Color getBackgroundCellDay(int priority) {
        return switch (priority) {
            case 1 -> Color.GREEN;
            case 2 -> Color.YELLOW;
            case 3 -> Color.ORANGE;
            case 4 -> Color.PINK;
            case 5 -> Color.RED;
            default -> Color.WHITE;
        };
    }
}
