public class QuickFindAndUnionTester { //Dynamic Conductivity Methods that have common int array daikon (like the radish, short for dynamic conductivity)

    public static final int daikonLength = 10;
    static int[] daikon = new int[daikonLength];

    public static void quUnion(int child, int parent) { //quick-union union method
        if(daikon[child] == child) {
            daikon[child] = parent;
            return;
        } else {
            quUnion(daikon[child], parent);
            System.out.println(daikon);
        }
    }

    public static boolean quIsConnected(int a, int b) { //quick-union find method
        if(daikon[a] == b || daikon[b] == a || a == b) {
            return true;
        } else {
            if(daikon[a] != a && daikon[b] != b) {
                if (quIsConnected(daikon[a], daikon[b])) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void qfUnion(int a, int b) { //quick-find union method
        if (daikon[a] != daikon[b]) {
            for (int i = 0; i < daikon.length; i++) {
                if (i != a && daikon[i] == daikon[a]) {
                    daikon[i] = daikon[b];
                }
            }
            daikon[a] = daikon[b];
        }
    }

    public static boolean qfIsConnected(int a, int b) { //quick-find find method
        return daikon[a] == daikon[b];
    }

    public static void weightedUnion(int a, int b) { //weighted find-union union method
        if(daikon[a] == a && daikon[b] == b) {
            daikon[a] = b;
            return;
        } else {
            weightedUnion(daikon[a], daikon[b]);
        }
    }

    public static boolean weightedIsConnected(int a, int b) { //weighted find-union find method
        if(daikon[a] == b || daikon[b] == a || a == b) {
            return true;
        } else {
            if(daikon[a] != a && daikon[b] != b) {
                if (weightedIsConnected(daikon[a], daikon[b])) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        for(int i = 0; i<daikonLength; i++) { //initializes array with index as value
            daikon[i] = i;
        }

        //SAMPLE CODE FOR QUICKFIND
        /*qfUnion(0, 1);
        qfUnion(1, 3);
        qfUnion(2, 3);
        System.out.println(qfIsConnected(0, 2));
        System.out.println(qfIsConnected(0, 4));
        System.out.println(qfIsConnected(2, 2)); */

        //SAMPLE CODE FOR QUICK UNION
        /*
        quUnion(0, 1);
        quUnion(1, 3);
        quUnion(2, 3);
        System.out.println(quIsConnected(0, 2));
        System.out.println(quIsConnected(0, 4));
        System.out.println(quIsConnected(2, 2)); */

        //SPECIAL CASE CODE FOR QUICK UNION
        /*
        quUnion(0, 1);
        quUnion(2, 0);
        quUnion(2, 3);
        quUnion(3, 4);
        quUnion(4, 5);
        quUnion(2, 4);
        System.out.println(quIsConnected(1, 5)); */

        //SAMPLE CODE FOR WEIGHTED UNION-FIND

        weightedUnion(0, 1);
        weightedUnion(1, 3);
        weightedUnion(2, 3);
        System.out.println(weightedIsConnected(0, 2));
        System.out.println(weightedIsConnected(0, 4));
        System.out.println(weightedIsConnected(2, 2));

    }

}
