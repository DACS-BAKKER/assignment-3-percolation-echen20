import edu.princeton.cs.algs4.Stopwatch;

public class PercolationTester {

    public static final int monteCarloTests = 100;
    public static final int ngrid = 20;

    public static void main(String[] args) {

       // long startTime = System.nanoTime();

        Stopwatch stopwatch = new Stopwatch();

        double totalPercThreshold = 0;

        for (int x = 0; x < monteCarloTests; x++) {
            Percolation1 perc = new Percolation1(ngrid);
            totalPercThreshold += perc.runExperiment();
        }

        //long endTime = System.nanoTime();
        //long totalTime = endTime-startTime;
        double totalTime = stopwatch.elapsedTime();

        System.out.println("The average Percolation Threshold over " + monteCarloTests + " tests is " + totalPercThreshold/(ngrid*ngrid*monteCarloTests));
        System.out.println("The simulation took " + totalTime + " seconds");


    }

}


