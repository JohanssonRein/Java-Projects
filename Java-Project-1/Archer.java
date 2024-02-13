package assignment1;
//Johnson Zhang 261105766
public class Archer extends MilitaryUnit{

    private int availableArrows;

    public Archer(Tile position, double hp, String faction){
        super(position,hp,2,faction,15.0,2,0);
        this.availableArrows = 5;
    }
    public void takeAction(Tile x) {
        if(this.availableArrows == 0){
            this.availableArrows = 5;
            return ;
        }
        x.selectWeakEnemy(this.getFaction());
        this.availableArrows--;

    }

    @Override
    public boolean equals(Object x){
        if(x instanceof Archer){
            Archer newX = (Archer) x;
            boolean isArrows = (newX.availableArrows == this.availableArrows);
            return (super.equals(x) && isArrows);
        }
        return false;
    }



}


