package assignment1;
//Johnson Zhang 261105766
public abstract class Unit{

     private Tile position;
     private double hp;
     private int movingRange;
     private String faction;

     public Unit(Tile position,double hp,int movingRange, String faction){
          this.position = position;
          this.hp = hp;
          this.movingRange = movingRange;
          this.faction = faction;
          if(!this.position.addUnit(this) ){
               throw new IllegalArgumentException();
          }
     }

     public final Tile getPosition(){
          return this.position;
     }
     public final double getHP(){
          return this.hp;
     }
     public final String getFaction(){
          return this.faction;
     }


     public boolean moveTo(Tile x){ //this.position
          if(x == null){
               return false;
          }
          if(Tile.getDistance(this.position,x) <= this.movingRange){

               Tile temp = this.position;
               //this.position = x;
               this.position.removeUnit(this);
               temp = x;
               this.position = temp;
               return x.addUnit(this);
          }
          return false;
     }

     public void receiveDamage(double x){
          if(x<0){ //In cases where damage is negative, then simply return.
               return ; //In case do one more hp-x outside.
          }

          //

          if(this.hp - x <= 0){ //Kill the unit if hp<0.
               if(this.position.isCity() && this.hp - x*(0.9) > 0){
                    this.hp = this.hp - x*(0.9);
                    return ; //
               }
               this.position.removeUnit(this);
               return ;//In case do one more hp-x outside.

          }
          if(this.position.isCity()){//Decrease the damage due to city.
               this.hp = this.hp - x*(0.9);
               return ;//In case do one more hp-x outside.
          }
          this.hp = this.hp - x; //normal cases.

     }

     public abstract void takeAction(Tile x);

     public boolean equals(Object x){
          if(x instanceof Unit) {
               Unit unitX = (Unit) x;
               boolean isPosition = (unitX.position == this.position);
               boolean isHealth = ((unitX.hp - this.hp) * (unitX.hp - this.hp) < 0.001);
               boolean isFaction = (unitX.faction.equals(this.faction));
               return (isPosition && isHealth && isFaction);
          }
          return false;
     }
}

