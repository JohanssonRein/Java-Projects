package assignment1;
//Johnson Zhang 261105766
public abstract class MilitaryUnit extends Unit{

    private double attackDamage;
    private int attackRange;
    private int armor;

    public MilitaryUnit(Tile position, double hp,int movingRange, String faction,double attackDamage,int attackRange,int armor){
        super(position,hp,movingRange,faction);
        this.attackRange = attackRange;
        this.attackDamage = attackDamage;
        this.armor = armor;
    }

    @Override
    public void takeAction(Tile x) {
        double distance = Tile.getDistance(this.getPosition(), x);
        if (distance <= this.attackRange) {
            Unit enemy = x.selectWeakEnemy(this.getFaction());

            if (enemy != null) {
                double damage = this.attackDamage;
                if (this.getPosition().isImproved()) {
                    damage *= 1.05;
                }
                enemy.receiveDamage(damage);
            }
        }
    }

    @Override
    public void receiveDamage(double damage) {
        double damageMultiplier = 100.0 / (100.0 + armor);
        damage *= damageMultiplier;
        super.receiveDamage(damage);
    }

}
