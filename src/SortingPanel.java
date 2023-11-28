import javax.swing.*;
import java.awt.*;

public class SortingPanel extends JPanel {
    private int[] array;
    private int current = -1;
    private int compare = -1;
    private int sorted = -1;

    public SortingPanel(int[] array) {
        this.array = array.clone();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int barWidth = width / array.length;
        for (int i = 0; i < array.length; i++) {
            if (i == current) {
                g.setColor(Color.RED);
            } else if (i == compare) {
                g.setColor(Color.GREEN);
            } else if (sorted >= 0 && i <= sorted) {
                g.setColor(Color.PINK);
            } else {
                g.setColor(Color.BLACK);
            }
            int x = i * barWidth;
            int y = height - array[i];
            g.fillRect(x, y, barWidth, array[i]);
        }
    }

    public void setArray(int[] array, int current, int compare, int sorted) {
        int[] clonedArray = array.clone();
        for (int i = 0; i < clonedArray.length; i++) {
            if (clonedArray[i] > getHeight()) {
                clonedArray[i] = getHeight();
            }
        }
        this.array = clonedArray;
        this.current = current;
        this.compare = compare;
        this.sorted = sorted;
        SwingUtilities.invokeLater(this::repaint);
    }
}
