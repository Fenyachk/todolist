package ui.view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import data.model.CustomTooltip;
import data.model.Task;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.Map;

public class CustomTooltipView {

    private final JCalendar calendar;
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";


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
        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth + 1);
        int daysInMonth = yearMonth.lengthOfMonth();

        for (Component component : dayChooser.getDayPanel().getComponents()) {
            if (component instanceof JButton dayButton) {
                String text = dayButton.getText();
                if (isNumeric(text)) {
                    int day = Integer.parseInt(dayButton.getText());
                    if (day >= 1 && day <= daysInMonth) {
                        CustomTooltip customTooltip = new CustomTooltip(currentYear, currentMonth, day);
                        setDayButtonToolTip(dayButton, customTooltip);
                    } else {
                        dayButton.setToolTipText(null);
                    }
                }
            }
        }
    }

    private static void setDayButtonToolTip(JButton dayButton, CustomTooltip customTooltip) {
        Map<Integer, Task> tooltip = customTooltip.getTaskByDate();
        if (tooltip != null) {
            for (Map.Entry<Integer, Task> entry : tooltip.entrySet()) {
                Task customTooltipForOutputs = entry.getValue();
                String title = customTooltipForOutputs.getTitle();
                int priority = customTooltipForOutputs.getPriority();
                Color color = getBackgroundCellDay(priority);
                dayButton.setToolTipText(title);
                dayButton.setBackground(color);
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

    private boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
