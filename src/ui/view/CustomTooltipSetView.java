package ui.view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import ui.viewmodel.CustomTooltip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

public class CustomTooltipSetView {

    private final JCalendar calendar;
    private JFrame frame;
    private JPanel panel;
    private final JPopupMenu tooltipPopup;
    private final JLabel tooltipLabel;

    public CustomTooltipSetView(JCalendar calendar, JPopupMenu tooltipPopup, JLabel tooltipLabel) {
        this.calendar = calendar;
        this.tooltipPopup = tooltipPopup;
        this.tooltipLabel = tooltipLabel;
    }

    public void setTooltips() {
        this.setDayToolTips();
        this.calendar.getDayChooser().addPropertyChangeListener("day", evt -> {
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
                addMouseListenerToDayButton(dayButton, this.tooltipPopup, this.tooltipLabel);
            }
        }
    }

    private static void setDayButtonToolTip(JButton dayButton, int year, int month) {
        String dayText = dayButton.getText();
        CustomTooltip customTooltip = new CustomTooltip();
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

    private static void addMouseListenerToDayButton(JButton dayButton, JPopupMenu tooltipPopup, JLabel tooltipLabel) {
        dayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                String tooltipText = dayButton.getToolTipText();
                if (tooltipText != null) {
                    tooltipLabel.setText(tooltipText);
                    tooltipPopup.show(dayButton, 0, dayButton.getHeight());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tooltipPopup.setVisible(false);
            }
        });
    }

    private static Color getBackgroundCellDay(int priority) {
        switch (priority) {
            case 1:
                return Color.GREEN;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.PINK;
            case 5:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }
}
