public class IterationResult {
    private int iteration;
    private int h1Cost;
    private int h2Cost;
    private double h1Time;
    private double h2Time;

    public IterationResult(int iteration, int h1Cost, int h2Cost, double h1Time, double h2Time){
        this.iteration = iteration;
        this.h1Cost = h1Cost;
        this.h2Cost = h2Cost;
        this.h1Time = h1Time;
        this.h2Time = h2Time;
    }

    public int getIteration() {
        return iteration;
    }

    public int getH2Cost() {
        return h2Cost;
    }

    public int getH1Cost() {
        return h1Cost;
    }

    public double getH1Time() {
        return h1Time;
    }

    public double getH2Time() {
        return h2Time;
    }
}
