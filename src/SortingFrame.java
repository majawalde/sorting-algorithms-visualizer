import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortingFrame extends JFrame {
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static int sortingSpeed = 50;

    public SortingFrame() {
        super("Sorting Algorithms");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final int[][] array = {generateRandomArray(60)};
        SortingPanel panel = new SortingPanel(array[0]);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        Map<String, SortingAlgorithm> algorithms = new LinkedHashMap<>();
        algorithms.put("Bubble Sort", new BubbleSort());
        algorithms.put("Insertion Sort", new InsertionSort());
        algorithms.put("Selection Sort", new SelectionSort());
        algorithms.put("Merge Sort", new MergeSort());
        algorithms.put("Quick Sort", new QuickSort());
        algorithms.put("Heap Sort", new HeapSort());

        //map of algorithms and their time complexities
        Map<String, Pair<SortingAlgorithm, AlgorithmComplexity>> algorithmsComplexities = new LinkedHashMap<>();
        algorithmsComplexities.put("Bubble Sort", new Pair<>(new BubbleSort(), new AlgorithmComplexity("Bubble Sort", "O(n)", "O(n^2)", "O(n^2)")));
        algorithmsComplexities.put("Insertion Sort", new Pair<>(new InsertionSort(), new AlgorithmComplexity("Insertion Sort", "O(n)", "O(n^2)", "O(n^2)")));
        algorithmsComplexities.put("Selection Sort", new Pair<>(new SelectionSort(), new AlgorithmComplexity("Selection Sort", "O(n^2)", "O(n^2)", "O(n^2)")));
        algorithmsComplexities.put("Merge Sort", new Pair<>(new MergeSort(), new AlgorithmComplexity("Merge Sort", "O(n log(n))", "O(n log(n))", "O(n log(n))")));
        algorithmsComplexities.put("Quick Sort", new Pair<>(new QuickSort(), new AlgorithmComplexity("Quick Sort", "O(n log(n))", "O(n log(n))", "O(n^2)")));
        algorithmsComplexities.put("Heap Sort", new Pair<>(new HeapSort(), new AlgorithmComplexity("Heap Sort", "O(n log(n))", "O(n log(n))", "O(n log(n))")));


        JPanel complexityPanel = new JPanel(new GridLayout(4, 1)); // grid layout for structured display
        JLabel nameLabel = new JLabel("Name: ");
        JLabel bestCaseLabel = new JLabel("Best Case: ");
        JLabel avgCaseLabel = new JLabel("Average Case: ");
        JLabel worstCaseLabel = new JLabel("Worst Case: ");

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        complexityPanel.add(nameLabel);
        complexityPanel.add(bestCaseLabel);
        complexityPanel.add(avgCaseLabel);
        complexityPanel.add(worstCaseLabel);

        for (Map.Entry<String, Pair<SortingAlgorithm, AlgorithmComplexity>> entry : algorithmsComplexities.entrySet()) {
            JButton button = new JButton(entry.getKey());
            button.addActionListener(e -> {
                for (Component comp : buttonPanel.getComponents()) {
                    if (comp instanceof JButton) {
                        comp.setEnabled(false);
                    }
                }

                AlgorithmComplexity complexity = entry.getValue().getRight();
                SwingUtilities.invokeLater(() -> {
                    nameLabel.setText("Name: " + complexity.getName());
                    bestCaseLabel.setText("Best Case: " + complexity.getBestCase());
                    avgCaseLabel.setText("Average Case: " + complexity.getAverageCase());
                    worstCaseLabel.setText("Worst Case: " + complexity.getWorstCase());
                    complexityPanel.revalidate();
                    complexityPanel.repaint();
                });

                Thread thread = new Thread(() -> {
                    try {
                        entry.getValue().getLeft().sort(array[0], panel, sortingSpeed);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    SwingUtilities.invokeLater(() -> {
                        for (Component comp : buttonPanel.getComponents()) {
                            if (comp instanceof JButton) {
                                comp.setEnabled(true);
                            }
                        }
                    });
                });
                thread.start();
            });
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.SOUTH);
        JPanel toolbar = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            array[0] = generateRandomArray(60);
            panel.setArray(array[0], -1, -1, -1);
        });
        toolbar.add(resetButton);
        JSlider speedSlider = new JSlider(10, 100, 50); // min: 10, max: 100, initial: 50
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        speedSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            sortingSpeed = source.getValue();
        });
        toolbar.add(speedSlider);

        leftPanel.add(toolbar);
        leftPanel.add(complexityPanel);

        add(leftPanel, BorderLayout.EAST);


    }
    public int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int)(Math.random() * (HEIGHT - 50));
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
