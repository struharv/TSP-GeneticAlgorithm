package ga;


import utils.Points;

import java.util.Random;


public class Pool {

    public Points points;
    public Score[] pool;
    public Score best;

    public int sel1;
    public int sel2;

    public int point;

    public int cnt;
    public int pocetGeneraci;

    public GaParams gaParams;
    private final Utils utils;
    private final Random rand;

    public Pool(Points points, GaParams params) {
        this.gaParams = params;
        this.points = points;
        rand = new Random();
        utils = new Utils();

        sel1 = -1;
        sel2 = -1;
        point = -1;
        cnt = 0;

        best = new Score(null, Double.MAX_VALUE);

        vytvorNahodne();
    }


    @SuppressWarnings("empty-statement")
    public void evoluce() {
        if (points.point.size() == 0) {
            return;
        }
        Score[] newPool = new Score[gaParams.populace];
        for (int i = 0; i < gaParams.populace / 2; i++) {
            int vybrany1 = vyber_turnajovaMetoda();
            int vybrany2;

            while ((vybrany2 = vyber_turnajovaMetoda()) == vybrany1) {}
            int[] pom;

            int point1 = rand.nextInt(points.point.size());
            int point2 = rand.nextInt(points.point.size());
            pom = utils.mutate(utils.crossover_ox(pool[vybrany1].individum,
                    pool[vybrany2].individum, point1, point2), gaParams.mutace);

            newPool[i * 2] = new Score(pom, fitness(pom));

            if (newPool[i * 2].fitnesss < best.fitnesss) {
                best = newPool[i * 2];
            }
            pom = utils.mutate(utils.crossover_ox(pool[vybrany2].individum,
                            pool[vybrany1].individum, point1, point2),
                    gaParams.mutace);
            newPool[i * 2 + 1] = new Score(pom, fitness(pom));
            if (newPool[i * 2 + 1].fitnesss < best.fitnesss) {
                best = newPool[i * 2 + 1];
            }
        }
        pool = newPool;
        pocetGeneraci++;

    }


    public void vytvorNahodne() {
        pool = new Score[gaParams.populace];
        for (int i = 0; i < gaParams.populace; i++) {
            int[] pom = utils.vytvorIndividuum(points.point.size());
            pool[i] = new Score(pom, fitness(pom));
        }
    }

    private int vyber_turnajovaMetoda() {
        double min = Double.MAX_VALUE;
        int minPos = 0;

        for (int i = 0; i
                < gaParams.selekce; i++) {
            int pom = rand.nextInt(gaParams.populace);
            if (pool[pom].fitnesss < min) {
                min = pool[pom].fitnesss;
                minPos =
                        pom;
            }

        }
        return minPos;
    }


    public double fitness(int[] ind) {
        double res = 0;
        int i;
        for (i = 0; i < ind.length - 1; i++) {
            res += Math.sqrt(sqr(points.point.get(ind[i] - 1).getX() - points.point.get(ind[i + 1] - 1).getX())
                    + sqr(points.point.get(ind[i] - 1).getY() - points.point.get(ind[i + 1] - 1).getY()));
        }
        return res;
    }

    public void reset() {
        vytvorNahodne();
        sel1 = -1;
        sel2 = -1;
        best = new Score(null, Double.MAX_VALUE);
        pocetGeneraci = 0;
    }

    private double sqr(double d) {
        return d * d;
    }
}
