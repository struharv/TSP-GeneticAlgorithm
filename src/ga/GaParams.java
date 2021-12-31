package ga;

public class GaParams {

    public int populace;
    public int selekce;
    public double mutace;

    public void setMutace(double mutace) {
        this.mutace = mutace;
    }

    public void setPopulace(int populace) {
        this.populace = populace + (populace % 2);
    }

    public void setSelekce(int selekce) {
        this.selekce = selekce;
    }

    public double getMutace() {
        return mutace;
    }

    public int getPopulace() {
        return populace;
    }

    public int getSelekce() {
        return selekce;
    }


}
