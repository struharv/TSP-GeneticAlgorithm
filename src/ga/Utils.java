package ga;

import java.util.Arrays;
import java.util.Random;


public class Utils {

    Random rand;

    public Utils() {
        this.rand = new Random();
    }

    public int[] vytvorIndividuum(int length) {
        int[] res = new int[length];

        int[] pom = new int[length];
        for (int i = 0; i < length; i++) {
            pom[i] = i + 1;
        }

        for (int i = 0; i < length; i++) {
            int ix = rand.nextInt(length);
            while (pom[ix] == -1) {
                ix = (ix + 1) % length;
            }
            res[i] = (byte) pom[ix];
            pom[ix] = -1;
        }

        return res;
    }

    public int[] crossover_ox(int[] ind1, int[] ind2, int point1, int point2) {
        int[] res = new int[ind1.length];
        if (point1 > point2) {
            int pom = point1;
            point1 = point2;
            point2 = pom;
        }

        for (int i = point1; i < point2; i++) {
            res[i] = ind1[i];
        }

        int i = point2;
        int aktPos = point2;
        do {
            if (!isIn(res, ind2[i])) {
                res[aktPos++ % res.length] = ind2[i];
            }

            i = (i + 1) % res.length;

        } while (i != point2);
        return res;
    }


    private boolean isIn(int[] arr, int item) {
        for (int i : arr) {
            if (i == item) {
                return true;
            }
        }

        return false;
    }


    public int[] mutate(int[] ind, double p) {
        int[] res = new int[ind.length];
        res = Arrays.copyOf(ind, ind.length);
        for (int i = 0; i < ind.length; i++) {
            if (rand.nextDouble() < p / 2) {
                int pos = rand.nextInt(ind.length);

                int pom = res[pos];
                res[pos] = res[i];
                res[i] = pom;
            }
        }
        return res;
    }

}
