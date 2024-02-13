package assignment1;
//Johnson Zhang 261105766
public class Tile {

    private int xCoord;
    private int yCoord;
    private boolean cityBuilt;
    private boolean improvements;
    private ListOfUnits allUnits;

    public Tile(int xCoord,int yCoord){

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.allUnits = new  ListOfUnits();
        this.cityBuilt = false;
        this.improvements = false;
    }

    public int getX(){
        return xCoord;
    }

    public int getY(){
        return yCoord;
    }

    public boolean isCity(){
        return cityBuilt;
    }

    public boolean isImproved(){
        return improvements;
    }

    public boolean buildCity(){
        cityBuilt = true;
        return cityBuilt;
    }

    public boolean buildImprovement(){
        improvements = true;
        return improvements;
    }

    public boolean addUnit(Unit x){
        if(x instanceof MilitaryUnit){
            for(int i=0; i<this.allUnits.getSize();i++){
                Unit y = this.allUnits.getUnit(i);
                if(y instanceof MilitaryUnit && !y.getFaction().equals(x.getFaction())){
                    return false;
                }
            }
        }
        this.allUnits.addUnit(x);
        return true;
    }

    public boolean removeUnit(Unit x){
        return this.allUnits.removeUnit(x);
    }

    public Unit selectWeakEnemy(String faction){
        Unit weakestEnemy = null;
        for(int i=0;i<this.allUnits.getSize();i++){
            Unit x = this.allUnits.getUnit(i);
            if(!x.getFaction().equals(faction) ){
                if(weakestEnemy == null || x.getHP() < weakestEnemy.getHP()){
                    weakestEnemy = x;
                }
            }
            else{
                return null;
            }
        }
        return weakestEnemy;
    }

    public static double getDistance(Tile a,Tile b){
        double distance = Math.sqrt((a.xCoord - b.xCoord)*(a.xCoord-b.xCoord) + (a.yCoord-b.yCoord)*(a.yCoord-b.yCoord));
        return distance;
    }









}

