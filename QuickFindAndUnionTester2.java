public class QuickFindAndUnionTester2 { //Dynamic Conductivity Methods that have int array as parameter

    public static void quUnion(int child, int parent, int[] daikon) { //quick-union union method
        if(daikon[child] == child) {
            daikon[child] = parent;
            return;
        } else {
            quUnion(daikon[child], parent, daikon);
            System.out.println(daikon);
        }
    }

    public static boolean quIsConnected(int a, int b, int[] daikon) { //quick-union find method
        if(daikon[a] == b || daikon[b] == a || a == b) {
            return true;
        } else {
            if(daikon[a] != a && daikon[b] != b) {
                if (quIsConnected(daikon[a], daikon[b], daikon)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void qfUnion(int a, int b, int[] daikon) { //quick-find union method
        if (daikon[a] != daikon[b]) {
            for (int i = 0; i < daikon.length; i++) {
                if (i != a && daikon[i] == daikon[a]) {
                    daikon[i] = daikon[b];
                }
            }
            daikon[a] = daikon[b];
        }
    }

    public static boolean qfIsConnected(int a, int b, int[] daikon) { //quick-find find method
        return daikon[a] == daikon[b];
    }

    public static void weightedUnion(int a, int b, int[] daikon) { //weighted find-union union method
        if(daikon[a] == a && daikon[b] == b) {
            daikon[a] = b;
            return;
        } else {
            weightedUnion(daikon[a], daikon[b], daikon);
        }
    }

    public static boolean weightedIsConnected(int a, int b, int[] daikon) { //weighted find-union find method
        if(daikon[a] == b || daikon[b] == a || a == b) {
            return true;
        } else {
            if(daikon[a] != a && daikon[b] != b) {
                if (weightedIsConnected(daikon[a], daikon[b], daikon)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}
