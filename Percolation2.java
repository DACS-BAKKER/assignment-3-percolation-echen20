import java.util.ArrayList;

public class Percolation2 {
    int n;
    int[] sitesOpen;
    int[] sites;
    int f;

    //CONSTRUCTORS

    public Percolation2(int n, int f) {  //f == 0, quickFind; f == 1, unionFind, f == 2, weighted union find

        this.f = f;
        this.n = n;
        sitesOpen = new int[n*n];
        sites = new int[n * n+2];
        for (int i = 0; i < n * n+1; i++) { //initializing array to its index
            sites[i] = i;
        }
    }

    public Percolation2(double dN, int f) { //Constructor used specifically to help with PercolationGraphics2
        this.f = f;
        this.n = (int) dN;
        sitesOpen = new int[n*n];
        sites = new int[n * n+1];
        for (int i = 0; i < n * n+1; i++) {
            sites[i] = i;
        }

        for(int i=n*n-n; i<n*n; i++) {
            if(f == 2) { //THIS SHOWS HOW I SWITCH BETWEEN WHICH SET OF METHODS I USE -- Same in rest of code calls
                weightedIsConnected(i, n * n);
            } else if(f == 1) {
                quIsConnected(i, n*n);
            } else if(f == 0) {
                qfIsConnected(i, n*n);
            }
        }
    }

    //UNION-FIND METHODS
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

   public void quUnion(int a, int b) { //quick-union union method
        if(sites[a] == a) {
            sites[a] = b;
            return;
        } else if(sites[b] == b) {
            sites[b] = a;
            return;
        } else {
            quUnion(sites[a], b);
            quUnion(a, sites[b]);
        }
    }

    public boolean quIsConnected(int a, int b) { //quick-union find method
        if(sites[a] == b || sites[b] == a || a == b) {
            return true;
        } else {
            if(sites[a] != a && sites[b] != b) {
                if (quIsConnected(sites[a], sites[b])) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public void qfUnion(int a, int b) { //quick-find union method
        if (sites[a] != sites[b]) {
            for (int i = 0; i < sites.length; i++) {
                if (i != a && sites[i] == sites[a]) {
                    sites[i] = sites[b];
                }
            }
            sites[a] = sites[b];
        }
    }

    public  boolean qfIsConnected(int a, int b) { //quick-find find method
        return sites[a] == sites[b];
    }

    //PERCOLATION METHODS
    public void open(int i, int j) { //opens a site
        if (sitesOpen[i + j * n] == 0) {
            sitesOpen[i + j * n] = 1;

            if(f == 2) {
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
            } else if(f == 1) {
                if (isOpen(i, j - 1)) {
                    quUnion(i + j * n, i + (j - 1) * n);
                }
                if (isOpen(i - 1, j)) {
                    quUnion(i + j * n - 1, i + j * n);
                }
                if (isOpen(i, j + 1)) {
                    quUnion(i + j * n, i + (j + 1) * n);
                }
                if (isOpen(i + 1, j)) {
                    quUnion(i + j * n + 1, i + j * n);
                }
            } else if(f == 0) {
                if (isOpen(i, j - 1)) {
                    qfUnion(i + j * n, i + (j - 1) * n);
                }
                if (isOpen(i - 1, j)) {
                    qfUnion(i + j * n - 1, i + j * n);
                }
                if (isOpen(i, j + 1)) {
                    qfUnion(i + j * n, i + (j + 1) * n);
                }
                if (isOpen(i + 1, j)) {
                    qfUnion(i + j * n + 1, i + j * n);
                }
            }


        }
    }

    public boolean isOpen(int i, int j) { //checks if a site is open
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

    public boolean isFull(int i, int j) { //checks if a particular site percolates (clear path to top)
        for (int z = 0; z < n; z++) {
            if (isOpen(z, 0)) {
               if(f == 2) {
                   if (weightedIsConnected(z, i + j * n)) {
                       return true;
                   }
               } else if(f == 1) {
                   if (quIsConnected(z, i + j * n)) {
                       return true;
                   }
               } else if(f == 0) {
                   if (qfIsConnected(z, i + j * n)) {
                       return true;
                   }
               }
            }
        }
        return false;


    }

    public boolean percolates() { //checks if there is a clear path from top to bottom
        for (int z = 0; z < n; z++) {
            if (isFull(z, n - 1)) {
                return true;
            }
        }
        return false;
    }

    //Test Functions

    public int runExperiment() { //continues to open random sites until the system percolates
        while (percolates() == false) {
            int i = (int) (Math.random() * n);
            int j = (int) (Math.random() * n);

            if (!isOpen(i, j)) {
                open(i, j);

            }
        }
        return countOpenSites();
    }

    public int countOpenSites() { //counts how many sites are open
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