package assignment1;
//Johnson Zhang 261105766
public class Settler extends Unit {

    public Settler(Tile position, double hp, String faction){
        super(position,hp,2,faction);
    }

    public void takeAction(Tile x){
        if(x.equals(this.getPosition()) && x.isCity() == false){
            x.buildCity();
            x.removeUnit(this);
        }
    }

    public boolean equals(Object x){
        if(x instanceof Settler){
            Settler newX = (Settler) x;
            return super.equals(x); //We can just simply do this !!!
        }
        return false;
    }








}
