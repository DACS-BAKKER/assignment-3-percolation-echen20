import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class PercolationGraphics2 { //GRAPHICS USING STDDRAW

    static double n = 20;

    public static void main(String[] args) throws InterruptedException {
        for (double x = 0; x < n; x++) {
            for (double y = 0; y < n; y++) {
                StdDraw.filledRectangle(x / n + 1 / (2 * n), y / n + 1 / (2 * n), 1.0 / (2 * n), 1.0 / (2 * n));
            }
        }

        Percolation2 perc = new Percolation2(n, 2);
        while (perc.percolates() == false) {
            Thread.sleep(10);
            int i = (int) (Math.random() * n);
            int j = (int) (Math.random() * n);

            if (!perc.isOpen(i, j)) {
                System.out.println(i + " " + j);

                perc.open(i, j);
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.filledRectangle(i / n + 1 / (2 * n), j / n + 1 / (2 * n), 1.0 / (2 * n), 1.0 / (2 * n));
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (perc.isFull(x, y)) {
                            StdDraw.setPenColor(Color.BLUE);
                            StdDraw.filledRectangle(x / n + 1 / (2 * n), y / n + 1 / (2 * n), 1.0 / (2 * n), 1.0 / (2 * n));
                        }

                    }
                }
            }
        }

        int counter = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (perc.isOpen(x, y)) {
                    counter++;
                }
            }
        }

        System.out.println("The Monte Carlo Simulation Percolation Threshold For This Test is: " + (double) counter / (double) (n * n));
    }
}


