package assignment1;
//Johnson Zhang 261105766
public class ListOfUnits{

    private  Unit [] storeUnits;
    private int sizeUnit;

    public ListOfUnits(){
        storeUnits = new Unit[10]; //Should we modify the size?
        sizeUnit = 0;
    }

    public int getSize(){
        return sizeUnit;
    }

    public Unit [] getList(){

        Unit[] output = new Unit[sizeUnit];
        int x = 0;
        for (int i = 0; i < sizeUnit; i++) {
            if (storeUnits[i] != null) { //Will the list that I am looping through has null?
                output[x] = storeUnits[i];
                x++;
            }
        }
        return output;
    }

    public Unit getUnit(int x){
        if(x<0 || x >= sizeUnit){
            throw new IndexOutOfBoundsException();
        }
        return storeUnits[x];
    }

    public void addUnit(Unit x){
        if(x == null){
            return ;
        }

        if(sizeUnit == storeUnits.length){
            Unit [] newUnits = new Unit[(storeUnits.length)*2]; //
            for(int i=0;i<sizeUnit;i++){
                newUnits[i] = storeUnits[i];
            }
            storeUnits = newUnits;

        }
        storeUnits[sizeUnit] = x;
        sizeUnit++;

    }

    public int indexOf(Unit x){

        for(int i=0;i< sizeUnit;i++){
            if(storeUnits[i].equals(x)){
                return i;
            }
        }
        return -1;
    }

    public boolean removeUnit(Unit x){

        int index = indexOf(x);
        if(index != -1){
            for(int i=index;i<sizeUnit-1;i++){
                storeUnits[i] = storeUnits[i+1];
            }
            storeUnits[sizeUnit-1] = null;
            sizeUnit--;
            return true;
        }
        return false;
    }

    public MilitaryUnit [] getArmy(){
        int count = 0;
        for(int i=0;i<storeUnits.length;i++){
            if(storeUnits[i] instanceof MilitaryUnit){
                count++;
            }
        }
        int j = 0;
        MilitaryUnit [] output = new MilitaryUnit[count];
        for(int i=0;i<storeUnits.length;i++){
            if(storeUnits[i] instanceof MilitaryUnit){
                output[j] = (MilitaryUnit) storeUnits[i];
                j++;
            }
        }
        return output;
    }


}
