import javax.swing.*;
import java.awt.*;

public class PercolationGraphics { //GRAPHICS USING SWING

    static JFrame f;
    static int n = 20;
    static JButton[][] buttons;

    public static void main(String[] args) throws InterruptedException {
        f = new JFrame("GridLayout");
        f.setSize(300, 300);
        // f.setLayout("GridLayout");
        JPanel panel = new JPanel(new GridLayout(n, n, 1, 1));
        f.add(panel);
        f.setVisible(true);

        buttons = new JButton[n][n];

        Percolation2 perc = new Percolation2(n, 2);
        for (int i = 0; i < perc.sitesOpen.length; i++) {
            JButton b1 = new JButton();
            panel.add(b1);
            buttons[i / n][i % n] = b1;
            b1.setBackground(Color.BLACK);
            b1.setForeground(Color.BLACK);
            b1.setOpaque(true);
        }
        while (perc.percolates() == false) {
            int i = (int) (Math.random() * n);
            int j = (int) (Math.random() * n);

            if (!perc.isOpen(i, j)) {
                System.out.println(i + " " + j);

                perc.open(i, j);
                buttons[j][i].setBackground(Color.WHITE);
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (perc.isFull(x, y)) {
                            buttons[y][x].setBackground(Color.BLUE);
                        }
                    }
                }
               // Thread.sleep(5000);
            }
        }
        int counter = 0;
        for(int x = 0; x<n; x++) {
            for(int y = 0; y<n; y++) {
                if(perc.isOpen(x, y)) {
                    counter++;
                }
            }
        }
        System.out.println("The Monte Carlo Simulation Percolation Threshold For This Test is: " + (double) counter / (double) (n*n));
    }

}
