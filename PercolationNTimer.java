import edu.princeton.cs.algs4.Stopwatch;

public class PercolationNTimer { //Tests Percolation RunTime Depending on type of Find Method

    public static void main(String[] args) {

        //Weighted Union-Find
        Percolation2 one = new Percolation2(100, 2);
        Percolation2 two = new Percolation2(200, 2);

        Stopwatch stopwatch = new Stopwatch();
        System.out.println(one.runExperiment()/10000.0);
        double time = stopwatch.elapsedTime();
        System.out.println("100 Objects took " + time + " seconds to complete");

        Stopwatch stopwatch2 = new Stopwatch();
        System.out.println(two.runExperiment()/40000.0);
        double time2 = stopwatch2.elapsedTime();
        System.out.println("200 Objects took " + time2 + " seconds to complete");


        double exponent = Math.log((time2/time))/Math.log(2);
        System.out.println("The Percolation Efficiency between tests 1 and 2 for Weighted UF is N^(" + exponent + ")");
        System.out.println();

        //Quick Find

        Percolation2 three = new Percolation2(100, 0);
        Percolation2 four = new Percolation2(200, 0);

        Stopwatch stopwatch3 = new Stopwatch();
        System.out.println(three.runExperiment()/10000.0);
        double time3 = stopwatch3.elapsedTime();
        System.out.println("100 Objects took " + time3 + " seconds to complete");


        Stopwatch stopwatch4 = new Stopwatch();
        System.out.println(four.runExperiment()/40000.0);
        double time4 = stopwatch4.elapsedTime();
        System.out.println("200 Objects took " + time4 + " seconds to complete");


        double exponent2 = Math.log((time4/time3))/Math.log(2);
        System.out.println("The Percolation Efficiency between tests 3 and 4 for QuickFind is N^(" + exponent2 + ")");

    }

}
