public class Percolation1 {

    int n;
    int[] sitesOpen;
    int[] sites;
    boolean[] sitesVisited;

    public Percolation1(int n) {
        this.n = n;
        sitesOpen = new int[n*n];
        sites = new int[n * n+1];
        for (int i = 0; i < n * n+1; i++) {
            sites[i] = i;
        }
        sitesVisited = new boolean[n * n];
        for(int i=n*n-n; i<n*n; i++) {
            weightedIsConnected(i, n*n);
           // open(n, n-1);
        }
    }

    public Percolation1(double dN) {
        this.n = (int) dN;
        sitesOpen = new int[n*n];
        sites = new int[n * n+1];
        for (int i = 0; i < n * n+1; i++) {
            sites[i] = i;
        }

        for(int i=n*n-n; i<n*n; i++) {
            QuickFindAndUnionTester2.weightedIsConnected(i, n*n, sites);
        }
    }

    //WEIGHTED UNION-FIND METHODS
    public void weightedUnion(int a, int b) { //weighted find-union union method
        if (sites[a] == a && sites[b] == b) {
            sites[a] = b;
            return;
        } else {
            weightedUnion(sites[a], sites[b]);
        }
    }

    public boolean weightedIsConnected(int a, int b) { //weighted find-union find method
        if (sites[a] == b || sites[b] == a || a == b) {
            return true;
        } else {
            if (sites[a] != a || sites[b] != b) {
                if (weightedIsConnected(sites[a], sites[b])) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    //PERCOLATION METHODS
    public void open(int i, int j) {
        if (sitesOpen[i + j * n] == 0) {
            sitesOpen[i + j * n] = 1;
            if (isOpen(i, j - 1)) {
                weightedUnion(i + j * n, i + (j - 1) * n);
            }
            if (isOpen(i - 1, j)) {
                weightedUnion(i + j * n - 1, i + j * n);
            }
            if (isOpen(i, j + 1)) {
                weightedUnion(i + j * n, i + (j + 1) * n);
            }
            if (isOpen(i + 1, j)) {
                weightedUnion(i + j * n + 1, i + j * n);
            }
        }
    }

    public boolean isOpen(int i, int j) {
        if (i >= 0 && j >= 0 && i < n && j < n) {
            if (sitesOpen[i + j * n] == 1) {
                return true;
            } else {
                return false;
            }
        } else if (i==n && j == n-1) {
            return true;
        }
        return false;
    }

    public boolean isFull(int i, int j) {
        for (int z = 0; z < n; z++) {
            if (isOpen(z, 0)) {
                if (weightedIsConnected(z, i + j * n)) {
                    return true;
                }
            }
        }
        return false;


        /* FIRST TRY AT ISFULL METHOD
        sitesVisited[i][j] = true;
        if (i == 0) {
            return true;
        } else {
            if (j == 0) {
                if(!sitesVisited[i][j + 1]) {
                    if (isFull(i, j + 1)) {
                        return true;
                    }
                }
            }
            else if (j == sites.length - 1) {
                if(!sitesVisited[i][j - 1]) {
                    if (isFull(i, j - 1)) {
                        return true;
                    }
                }
            } else {
                if (!sitesVisited[i][j - 1]) {
                    if (isFull(i, j - 1)) {
                        return true;
                    }
                }
                if (!sitesVisited[i][j + 1]) {
                    if (isFull(i, j + 1)) {
                        return true;
                    }
                }
            }
            if (isFull(i - 1, j)) {
                return true;
            }
            return false;
        }
    } */

       /* if (isOpen(i, j)) {
            if (i == 0) {
                return true;
            } else {
                if (j == 0) {
                    isFull(i, j + 1);
                }
                if (j == sites.length - 1) {
                    isFull(i, j - 1);
                } else {
                    isFull(i, j + 1);
                    isFull(i, j - 1);
                }
                isFull(i - 1, j);
                return false;
            }
        } else {
            return false;
        } */
    }

    public boolean percolates() {
       for (int z = 0; z < n; z++) {
            if (isFull(z, n - 1)) {
                return true;
            }
        }

    /*   if(isFull(n, n-1)) {
           return true;
       } */
        return false;
    }

    public int runExperiment() {
        while (percolates() == false) {
            int i = (int) (Math.random() * n);
            int j = (int) (Math.random() * n);

            if (!isOpen(i, j)) {
                open(i, j);

            }
        }
        return countOpenSites();
    }

    public int countOpenSites() {
        int counter = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (isOpen(x, y)) {
                    counter++;
                }
            }
        }
        return counter;
    }


    //Run Method
    public static void main(String[] args) {
        Percolation1 perc = new Percolation1(5);
        long startTime = System.nanoTime();
        perc.open(0, 0);
        long endTime = System.nanoTime();
        System.out.println("open method took: " + (endTime-startTime) + " nanoseconds");


        perc.open(0, 1);
        perc.open(0, 2);
        perc.open(1, 2);
        perc.open(2, 2);

        perc.open(2, 0);
        perc.open(3, 0);
        perc.open(3, 1);
        perc.open(3, 2);
        perc.open(3, 3);
        perc.open(3, 4);

        long startTime2 = System.nanoTime();
        System.out.println(perc.percolates());
        long endTime2 = System.nanoTime();
        System.out.println("percolates method took: " + (endTime2-startTime2) + " nanoseconds");

        System.out.println(perc.isFull(0, 0));
        System.out.println(perc.isFull(0, 1));
        System.out.println(perc.isFull(0, 2));
        System.out.println(perc.isFull(1, 2));
        System.out.println(perc.isFull(2, 2));

        System.out.println(perc.isFull(2, 0));
        System.out.println(perc.isFull(3, 0));
        System.out.println(perc.isFull(3, 1));
        System.out.println(perc.isFull(3, 2));
        System.out.println(perc.isFull(3, 3));
        System.out.println(perc.isFull(3, 4));
    }

}