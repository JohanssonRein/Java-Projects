package assignment1;
//Johnson Zhang 261105766
public class Worker extends Unit {

    private int jobsPerformed;

    public Worker(Tile position, double hp, String faction){
        super(position,hp,2,faction);
        this.jobsPerformed = 0;
    }

    public void takeAction(Tile x) {
        if(x.equals(this.getPosition()) && x.isImproved() == false){
            x.buildImprovement();
            this.jobsPerformed++;
            if(this.jobsPerformed == 10){
                x.removeUnit(this);
            }
        }
    }

    public boolean equals(Object x){
        if(x instanceof Worker){
            Worker newX = (Worker) x;
            boolean isJobs = (newX.jobsPerformed == this.jobsPerformed);
            return (super.equals(x) && isJobs);
        }
        return false;
    }


}
