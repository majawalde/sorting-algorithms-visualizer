import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortingFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ARRAY_SIZE = 60;
    public static int sortingSpeed = 50;
    JPanel complexityPanel = new JPanel();

    private static final Map<String, AlgorithmComplexity> ALGORITHMS_COMPLEXITY_MAP;

    static {
        ALGORITHMS_COMPLEXITY_MAP = new LinkedHashMap<>();
        ALGORITHMS_COMPLEXITY_MAP.put("Bubble Sort", new AlgorithmComplexity("Bubble Sort", "O(n)", "O(n^2)", "O(n^2)"));
        ALGORITHMS_COMPLEXITY_MAP.put("Insertion Sort", new AlgorithmComplexity("Insertion Sort", "O(n)", "O(n^2)", "O(n^2)"));
        ALGORITHMS_COMPLEXITY_MAP.put("Selection Sort", new AlgorithmComplexity("Selection Sort", "O(n^2)", "O(n^2)", "O(n^2)"));
        ALGORITHMS_COMPLEXITY_MAP.put("Merge Sort", new AlgorithmComplexity("Merge Sort", "O(n log(n))", "O(n log(n))", "O(n log(n))"));
        ALGORITHMS_COMPLEXITY_MAP.put("Quick Sort", new AlgorithmComplexity("Quick Sort", "O(n log(n))", "O(n log(n))", "O(n^2)"));
        ALGORITHMS_COMPLEXITY_MAP.put("Heap Sort", new AlgorithmComplexity("Heap Sort", "O(n log(n))", "O(n log(n))", "O(n log(n))"));
    }

    public SortingFrame() {
        super("Sorting Algorithms");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final int[][] array = {generateRandomArray(ARRAY_SIZE)};
        SortingPanel panel = new SortingPanel(array[0]);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        addAlgorithmButtons(buttonPanel, array, panel);
        initComplexityPanel();

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        leftPanel.add(Box.createVerticalStrut(10));

        JPanel toolbar = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetArray(array, panel));
        toolbar.add(resetButton);

        JSlider speedSlider = createSpeedSlider();
        toolbar.add(speedSlider);

        leftPanel.add(toolbar);
        leftPanel.add(complexityPanel);

        add(leftPanel, BorderLayout.EAST);
    }

    private void initComplexityPanel() {
        complexityPanel.setLayout(new GridLayout(4,1,5,5));
        complexityPanel.add(new JLabel("Name: "));
        complexityPanel.add(new JLabel("Best Case: "));
        complexityPanel.add(new JLabel("Average Case: "));
        complexityPanel.add(new JLabel("Worst Case: "));
        complexityPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addAlgorithmButtons(JPanel buttonPanel, int[][] array, SortingPanel panel) {
        for (Map.Entry<String, AlgorithmComplexity> entry : ALGORITHMS_COMPLEXITY_MAP.entrySet()) {
            String name = entry.getKey();
            AlgorithmComplexity complexity = entry.getValue();
            SortingAlgorithm algorithm = SortingAlgorithmFactory.create(name);

            JButton button = createAlgorithmButton(name, algorithm, complexity, array, panel);
            buttonPanel.add(button);
        }
    }

    private JButton createAlgorithmButton(String name, SortingAlgorithm algorithm, AlgorithmComplexity complexity, int[][] array, SortingPanel panel) {
        JButton button = new JButton(name);
        button.addActionListener(e -> handleAlgorithmButtonClick(algorithm, complexity, array, panel, (JPanel) button.getParent()));
        return button;
    }

    private void handleAlgorithmButtonClick(SortingAlgorithm algorithm, AlgorithmComplexity complexity, int[][] array, SortingPanel panel, JPanel buttonPanel) {
        for (Component comp : buttonPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(false);
            }
        }

        SwingUtilities.invokeLater(() -> {
            ((JLabel) complexityPanel.getComponent(0)).setText("Name: " + complexity.getName());
            ((JLabel) complexityPanel.getComponent(1)).setText("Best Case: " + complexity.getBestCase());
            ((JLabel) complexityPanel.getComponent(2)).setText("Average Case: " + complexity.getAverageCase());
            ((JLabel) complexityPanel.getComponent(3)).setText("Worst Case: " + complexity.getWorstCase());
            complexityPanel.revalidate();
            complexityPanel.repaint();
        });

        Thread sortingThread = new Thread(() -> {
            try {
                algorithm.sort(array[0], panel, sortingSpeed);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                for (Component comp : buttonPanel.getComponents()) {
                    if (comp instanceof JButton) {
                        comp.setEnabled(true);
                    }
                }
            });
        });
        sortingThread.start();
    }

    private JSlider createSpeedSlider() {
        JSlider speedSlider = new JSlider(10, 100, 50);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(e -> sortingSpeed = ((JSlider) e.getSource()).getValue());
        return speedSlider;
    }

    private void resetArray(int[][] array, SortingPanel panel) {
        array[0] = generateRandomArray(ARRAY_SIZE);
        panel.setArray(array[0], -1, -1, -1);

        SwingUtilities.invokeLater(() -> {
            ((JLabel) complexityPanel.getComponent(0)).setText("Name: ");
            ((JLabel) complexityPanel.getComponent(1)).setText("Best Case: ");
            ((JLabel) complexityPanel.getComponent(2)).setText("Average Case: ");
            ((JLabel) complexityPanel.getComponent(3)).setText("Worst Case: ");
            complexityPanel.revalidate();
            complexityPanel.repaint();
        });
    }

    public int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (HEIGHT - 50));
        }
        return array;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingFrame frame = new SortingFrame();
            frame.setVisible(true);
        });
    }
}
