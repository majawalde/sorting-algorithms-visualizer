public class AlgorithmComplexity {
    private String name;
    private String bestCase;
    private String averageCase;
    private String worstCase;

    public AlgorithmComplexity(String name, String bestCase, String averageCase, String worstCase) {
        this.name = name;
        this.bestCase = bestCase;
        this.averageCase = averageCase;
        this.worstCase = worstCase;
    }

    public String getName() {
        return name;
    }

    public String getBestCase() {
        return bestCase;
    }

    public String getAverageCase() {
        return averageCase;
    }

    public String getWorstCase() {
        return worstCase;
    }

}
