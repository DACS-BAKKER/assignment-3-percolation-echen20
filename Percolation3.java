public class Percolation3 { //DOES NOT WORK FOR UNKNOWN REASONS
    int n;
    int[] sitesOpen;
    int[] sites;

    public Percolation3(int n) {
        this.n = n;
        sitesOpen = new int[n*n];
        sites = new int[n * n+1];
        for (int i = 0; i < n * n+1; i++) { //INITIALIZES EACH SITE TO ITS INDEX
            sites[i] = i;
        }
        for(int i=n*n-n; i<n*n; i++) { //CONNECTS BOTTOM ROW TO n*n INDEX
            QuickFindAndUnionTester2.weightedIsConnected(i, n*n, sites);
        }
    }

    public Percolation3(double dN) {
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

    //PERCOLATION METHODS
    public void open(int i, int j) {
        if (sitesOpen[i + j * n] == 0) {
            sitesOpen[i + j * n] = 1;
            if (isOpen(i, j - 1)) {
                QuickFindAndUnionTester2.weightedUnion(i + j * n, i + (j - 1) * n, sites);
            }
            if (isOpen(i - 1, j)) {
                QuickFindAndUnionTester2.weightedUnion(i + j * n - 1, i + j * n, sites);
            }
            if (isOpen(i, j + 1)) {
                QuickFindAndUnionTester2.weightedUnion(i + j * n, i + (j + 1) * n, sites);
            }
            if (isOpen(i + 1, j)) {
                QuickFindAndUnionTester2.weightedUnion(i + j * n + 1, i + j * n, sites);
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
                if (QuickFindAndUnionTester2.weightedIsConnected(z, i + j * n, sites)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean percolates() {
          if(isFull(n, n)) {
           return true;
       }
        return false;
    }

    //Test Methods

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

}