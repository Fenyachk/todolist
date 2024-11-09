//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui.view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import data.model.Task;

import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import ui.viewmodel.TaskViewModel;

public class CustomTooltipView {
    private final JCalendar calendar;
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final TaskViewModel taskViewModel = new TaskViewModel();

    public CustomTooltipView(JCalendar calendar) {
        this.calendar = calendar;
        JPopupMenu tooltipPopup = new JPopupMenu();
        JLabel tooltipLabel = new JLabel();
        tooltipPopup.add(tooltipLabel);
    }

    public void setTooltips() {
        this.setDayToolTips();
    }

    public void setTooltipsChangeDMY() {
        this.setTooltipsChangeDay();
        this.setTooltipsChangeMonth();
        this.setTooltipsChangeYear();
    }

    private void setTooltipsChangeDay() {
        this.calendar.getDayChooser().addPropertyChangeListener("day", (evt) -> {
            this.setDayToolTips();
        });
    }

    private void setTooltipsChangeMonth() {
        this.calendar.getMonthChooser().addPropertyChangeListener("month", (evt) -> {
            this.setDayToolTips();
        });
    }

    private void setTooltipsChangeYear() {
        this.calendar.getYearChooser().addPropertyChangeListener("year", (evt) -> {
            this.setDayToolTips();
        });
    }

    private void setDayToolTips() {
        JDayChooser dayChooser = this.calendar.getDayChooser();
        int currentMonth = this.calendar.getMonthChooser().getMonth() + 1;
        int currentYear = this.calendar.getYearChooser().getYear();
        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
        int daysInMonth = yearMonth.lengthOfMonth();
        Component[] var6 = dayChooser.getDayPanel().getComponents();
        int var7 = var6.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            Component component = var6[var8];
            if (component instanceof JButton dayButton) {
                String text = dayButton.getText();
                if (this.isNumeric(text)) {
                    int day = Integer.parseInt(dayButton.getText());
                    if (day >= 1 && day <= daysInMonth) {
                        LocalDate date = LocalDate.of(currentYear, currentMonth, day);
                        this.setDayButtonToolTip(dayButton, date);
                    } else {
                        dayButton.setToolTipText((String) null);
                    }
                }
            }
        }

    }

    private void setDayButtonToolTip(JButton dayButton, LocalDate date) {
        Map<Integer, Task> tooltip = taskViewModel.getTaskByDate(date);
        System.out.println(tooltip);
        StringBuilder tooltipText = new StringBuilder();
        if (tooltip != null) {
            Color color;
            for (Iterator var5 = tooltip.entrySet().iterator(); var5.hasNext(); dayButton.setBackground(color)) {
                Map.Entry<Integer, Task> entry = (Map.Entry) var5.next();
                Task customTooltipForOutputs = (Task) entry.getValue();
                String title = customTooltipForOutputs.getTitle();
                int priority = customTooltipForOutputs.getPriority();
                color = getBackgroundCellDay(priority);
                if (tooltipText.isEmpty()) {
                    tooltipText.append(title);
                } else {
                    tooltipText.append("\n").append(title);
                }
            }

            dayButton.setToolTipText(tooltipText.toString());
        }

    }

    private static Color getBackgroundCellDay(int priority) {
        Color var10000;
        switch (priority) {
            case 1 -> var10000 = Color.GREEN;
            case 2 -> var10000 = Color.YELLOW;
            case 3 -> var10000 = Color.ORANGE;
            case 4 -> var10000 = Color.PINK;
            case 5 -> var10000 = Color.RED;
            default -> var10000 = Color.WHITE;
        }

        return var10000;
    }

    private boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }
}
