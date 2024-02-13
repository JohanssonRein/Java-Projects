package assignment1;
//Johnson Zhang 261105766
public class Warrior extends MilitaryUnit{

    public Warrior(Tile position, double hp, String faction){
        super(position,hp,1,faction,20.0,1,25);
    }


    public boolean moveTo(Tile x) {
        return super.moveTo(x);
    }


    public void receiveDamage(double damage) {
        super.receiveDamage(damage);
    }

    public boolean equals(Object x){
        if(x instanceof Warrior){
            Warrior newX = (Warrior) x;
            return super.equals(x);

        }
        return false;
    }

}
