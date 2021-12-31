package ga;

public class Score {

    public int[] individum;
    public double fitnesss;

    public Score(int[] jedinec) {
        this.individum = jedinec;
    }

    public Score(int[] jedinec, double fitnesss) {
        this.individum = jedinec;
        this.fitnesss = fitnesss;
    }
}
