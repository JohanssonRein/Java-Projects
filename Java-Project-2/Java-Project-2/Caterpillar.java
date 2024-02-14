package assignment2;

import java.awt.Color;
import java.util.Random;
import java.util.Stack;

import assignment2.food.*;

public class Caterpillar {
   // All the fields have been declared public for testing purposes
   public Segment head;
   public Segment tail;
   public int length;
   public EvolutionStage stage;

   public Stack<Position> positionsPreviouslyOccupied;
   public int goal;
   public int turnsNeededToDigest;


   public static Random randNumGenerator = new Random(1);


   // Creates a Caterpillar with one Segment. It is up to students to decide how to implement this.
   public Caterpillar(Position p, Color c, int goal) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       Segment newseg =new Segment(p,c);
       this.head = newseg;
       this.tail = this.head;
	   this.head.next = null;
       this.length = 1;
       this.goal = goal;
       this.stage = EvolutionStage.FEEDING_STAGE;
       this.positionsPreviouslyOccupied=new Stack<Position>();
   }


   public EvolutionStage getEvolutionStage() {
       return this.stage;
   }

   public Position getHeadPosition() {
       return this.head.position;
   }

   public int getLength() {
       return this.length;
   }


   // returns the color of the segment in position p. Returns null if such segment does not exist
   public Color getSegmentColor(Position p) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       Segment temp = this.head;
       while(temp!= null){
           if(temp.position.equals(p)){
               return temp.color;
           }
           else{
               temp = temp.next;
           }
       }
       return null;

   }


   // shift all Segments to the previous Position while maintaining the old color
   public void move(Position p) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       if(Position.getDistance(this.head.position,p) > 1){
		throw new IllegalArgumentException();
	   }
	   
	   
	   Segment temp;
       Position temp1;
       Position temp2;
       
	   if(Position.getDistance(p, this.head.position) == 1){
           temp = this.head;
           while(temp !=null){
               if(p.equals(temp.position)){
                   this.stage=EvolutionStage.ENTANGLED;
               }
               temp = temp.next;
           }
           
		   if(this.stage != EvolutionStage.ENTANGLED){
               temp = this.head;
               temp1 = head.position;
               if(this.turnsNeededToDigest != 0){
                   Color color = GameColors.SEGMENT_COLORS[(randNumGenerator.nextInt())%5];
                   Segment b = new Segment(tail.position,color);
                   this.tail.next = b;
                   this.tail = b;
                   this.length += 1;
                   this.turnsNeededToDigest -= 1;
                   if(this.turnsNeededToDigest == 0){
                       this.stage = EvolutionStage.FEEDING_STAGE;
                   }
                   if(this.length >= this.goal){
                       this.stage = EvolutionStage.BUTTERFLY;

                   }
               }
               else {
                   this.positionsPreviouslyOccupied.push(tail.position);
               };
               while(temp.next!= null){

                   temp2 = temp.next.position;
                   temp.next.position = temp1;
                   temp1 = temp2;
                   temp = temp.next;
               }
               System.out.println("Reached");
               this.head.position = p;

           }

       }
       
	   
	   
	   
   }



   // a segment of the fruit's color is added at the end
   public void eat(Fruit f) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       Position p = this.positionsPreviouslyOccupied.pop();
       Segment temp = new Segment(p,f.getColor());
       
	   this.tail.next = temp;
       this.tail = temp;
       this.length++;
       
	   if(this.length>=this.goal){
           this.stage=EvolutionStage.BUTTERFLY;

       }

	   positionsPreviouslyOccupied.push(this.tail.position);

   }

   // the caterpillar moves one step backwards because of sourness
   public void eat(Pickle p) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       if(this.length == 1){
           Position temp1 = this.positionsPreviouslyOccupied.pop();
           this.positionsPreviouslyOccupied.push(head.position);
           head.position=temp1;
           return;
       }
       
	   else{
	   Position temp1 = this.head.position;
       Position temp2 = this.positionsPreviouslyOccupied.pop();
       
       Segment temp = this.head;
       
	   while(temp.next!=null){
           temp.position = temp.next.position;
           temp = temp.next;
       }
       
	   this.tail.position=temp2;
       this.positionsPreviouslyOccupied.push(temp1);
	   
	   }
   }


   // all the caterpillar's colors shuffles around
   public void eat(Lollipop lolly) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       
       Color[] colorarray = new Color[this.length];
       Segment a = this.head;
       
	   int b=0;
       while(a!=null){
           colorarray[b] = a.color;
           b++;
           a = a.next;
       }
       
	   for(int i=this.length-1 ; i>0 ; i--){
           int j=randNumGenerator.nextInt(i+1);
           
		   Color temp = colorarray[j];
           colorarray[j] = colorarray[i];
           colorarray[i] = temp;
       }
       
	   a=this.head;
       b=0;
       while(a != null){

           a.color=colorarray[b];
           a=a.next;
           b++;
       }

       
   }

       
   

   // brain freeze!!
   // It reverses and its (new) head turns blue
   public void eat(IceCream gelato) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       Segment[] body = new Segment[this.length];
       Segment temp = this.head;
       int b=0;
       while(temp != null){
           body[b] = temp;
           temp = temp.next;
           b++;
       }
       
	   while(b>=2){
           body[b-1].next = body[b-2];
           b--;
       }
       this.head.next=null;

       this.tail.color = GameColors.BLUE;
       temp = this.tail;
       this.tail = this.head;
       this.head = temp;
       
	   this.positionsPreviouslyOccupied.clear();
   }

   // the caterpillar embodies a slide of Swiss cheese loosing half of its segments.
   public void eat(SwissCheese cheese) {
       /*
        * TODO: ADD YOUR CODE HERE
        */
       Position[] positions=new Position[this.length];
       Segment[] body = new Segment[this.length];
       

       Segment temp = this.head;
       int b = 0;
       while(temp != null){
           
		   body[b] = new Segment(temp.position,temp.color);
           positions[b] = new Position(temp.position.getX(),temp.position.getY());
           temp = temp.next;
           b++;
       }

     if(this.length>1){
         temp=this.head;
         b=1;

       for(int i=2;i<this.length;i+=2){

           temp.next=body[i];

           temp=temp.next;
           }

       temp=this.head;
       b=1;
       while(temp.next!=null){
           temp.next.position=positions[b];
           this.positionsPreviouslyOccupied.push(positions[this.length-b]);
           temp=temp.next;
           b++;

       }

       if(this.length==2){
           this.positionsPreviouslyOccupied.push(this.tail.position) ;
       }
         this.tail=temp;
         this.length=b;
         this.tail.next=null;
     }
     else{}


   }


   public void eat(Cake cake) {
       /*
        * TODO: ADD YOUR CODE HERE
        */

       this.stage = EvolutionStage.GROWING_STAGE;
       int energy = cake.getEnergyProvided();

       for(int i=0;i<energy;i++){
           Segment temp = this.head;
           Position p = this.positionsPreviouslyOccupied.pop();
           while(temp != null ){
               if(temp.position.equals(p)){
                   this.turnsNeededToDigest = energy - i;
                   return;

               }
               temp = temp.next;
           }
           
		   Color color=GameColors.SEGMENT_COLORS[randNumGenerator.nextInt(5)];
           temp = new Segment(p,color);
           this.tail.next = temp;
           this.tail=temp;
           this.length++;
           if(this.length>=this.goal){
               this.stage=EvolutionStage.BUTTERFLY;
           }
       }
       this.stage=EvolutionStage.FEEDING_STAGE;
       

   }


   // This nested class was declared public for testing purposes
   public class Segment {
       private Position position;
       private Color color;
       private Segment next;

       public Segment(Position p, Color c) {
           this.position = p;
           this.color = c;
       }

   }


   public String toString() {
       Segment s = this.head;
       String gus = "";
       while (s!=null) {
           String coloredPosition = GameColors.colorToANSIColor(s.color) +
                   s.position.toString() + GameColors.colorToANSIColor(Color.WHITE);
           gus = coloredPosition + " " + gus;
           s = s.next;
       }
       return gus;
   }
}



