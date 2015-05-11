/** 
 * COSC343 Assignment2
 * Chromosome.java
 * Purpose: Representation of a creature's chromosome, which determines
 * the creature's actions. For the variables that determine direction to move,
 * the directions are represented by integers. 
 *          0:ignore
 *          1:towards
 *          2:away 
 *          3:random
 * For the default action, 
 *          0:random
 *          1:north
 *          2:south
 *          3:east
 *          4:west         
 *
 * @author: Jazlyn Akaka
 * @version: 11/5/15
 */

import java.util.*;

public class Chromosome{
    private boolean[] eatStrawb = {true,false};
    private boolean[] eatMush = {true,false};
    private int[] strawbMove = {0,1,2,3}; //direction to move given strawberry
    private int[] mushMove = {0,1,2,3}; //direction to move given mushroom
    private int[] monsMove = {0,1,2,3}; //direction to move given monster
    private int[] creMove = {0,1,2,3}; //direction to move given a creature
    private int[] action = {0,1,2,3,4}; //default action (random, north, south, east, west)
    private Object[] chrome = new Object[13];

    /** 
     * CONSTRUCTOR: Creates an all encompasing chromosome that provides all possible values for roles
     */
    public Chromosome(){
	Random rand = new Random();
	chrome[0] = eatStrawb[rand.nextInt(eatStrawb.length)];
	chrome[1] = eatMush[rand.nextInt(eatMush.length)];
	chrome[2] = strawbMove[rand.nextInt(strawbMove.length)];
	chrome[3] = mushMove[rand.nextInt(mushMove.length)];
	chrome[4] = monsMove[rand.nextInt(monsMove.length)];
	chrome[5] = creMove[rand.nextInt(creMove.length)];
	chrome[6] = action[rand.nextInt(action.length)];
	chrome[7] = rand.nextDouble();
	chrome[8] = rand.nextDouble();
	chrome[9] = rand.nextDouble();
	chrome[10] = rand.nextDouble();
	chrome[11] = rand.nextDouble();
	chrome[12] = rand.nextDouble();
    }

    /** 
     * CONSTRUCTOR: Creates a new baby chromosome based on chromosomes of previous creatures
     */
    public Chromosome(Creature c1, Creature c2){
	Random rand = new Random();
	int crossPoint = rand.nextInt(13);

	//crossover implementation
	for (int i = 0; i<crossPoint; i++){
	    chrome[i] = c1.chromosome.chrome[i];
	}
	for (int j = crossPoint; j<chrome.length; j++){
	    chrome[j] = c2.chromosome.chrome[j];
	}

	//generate a random number that represents the location of a mutation
	//if the random number is greater than 12, there's no mutation
	//could potentially change the range in the future to make it more likely
	//or less likely that mutation will happen
	int mutation = rand.nextInt(20);
        switch(mutation){
	case 0:
	    chrome[0] = eatStrawb[rand.nextInt(eatStrawb.length)];
	    break;
	case 1:
	    chrome[1] = eatMush[rand.nextInt(eatMush.length)];
	    break;
	case 2:
	    chrome[2] = strawbMove[rand.nextInt(strawbMove.length)];
	    break;
	case 3:
	    chrome[3] = mushMove[rand.nextInt(mushMove.length)];
	    break;
	case 4:
	    chrome[4] = monsMove[rand.nextInt(monsMove.length)];
	    break;
	case 5:
	    chrome[5] = creMove[rand.nextInt(creMove.length)];
	    break;
	case 6:
	    chrome[6] = action[rand.nextInt(action.length)];
	    break;
	case 7:
	    chrome[7] = rand.nextDouble();
	    break;
	case 8:
	    chrome[8] = rand.nextDouble();
	    break;
	case 9:
	    chrome[9] = rand.nextDouble();
	    break;
	case 10:
	    chrome[10] = rand.nextDouble();
	    break;
	case 11:
	    chrome[11] = rand.nextDouble();
	    break;
	case 12:
	    chrome[12] = rand.nextDouble();
	    break;
	default:
	    break;
	}
	/**TESTING**/
	/**
		System.out.println("crossPoint:" + crossPoint);
		System.out.println("Parent 1:" + c1.chromosome);
		System.out.println("Parent 2:" + c2.chromosome);
		System.out.print("child:");
		for (int i = 0; i<13; i++){
		System.out.println(chrome[i]);
		}
	*/	
    }

    /************** GETTER METHODS *****************/
    
    /** Method gets value of eatStrawb
     *  @return boolean value of eatStrawb
     */
    public boolean getEatStrawb(){
	return (Boolean)chrome[0];
    }

    /** Method gets value of eatMush
     * @ return boolean value of eatMuch
     */
    public boolean getEatMush(){
	return (Boolean)chrome[1];
    }

    /** Method gets strawbMove
     *  @return integer representation of towards, away, random, ignore
     */
    public int getStrawbMove(){
	return (Integer)chrome[2];
    }

    /** Method gets mushMove
     *  @return int representaiton of towards, away, random, ignore
     */
    public int getMushMove(){
	return (Integer)chrome[3];
    }

    /** Method gets monsMove
     *  @return int representaiton of towards, away, random, ignore
     */
    public int getMonsMove(){
	return (Integer)chrome[4];
    }

    /** Method gets creMove
     *  @return int representaiton of towards, away, random, ignore
     */
    public int getCreMove(){
	return (Integer)chrome[5];
    }

    /** Method gets default action
     *  @return int representaiton of random, north, south, east, west
     */
    public int getAction(){
	return (Integer)chrome[6];
    }

    /** Method gets all the weights
     *  @return an int[] of all of the weights that correspond to the roles
     */
    public double[] getWeights(){
	double[] result = new double[6];
	for (int i = 0; i<result.length; i++){
	    result[i] = (Double)chrome[7+i];
	}
	return result;
    }
    
    public String toString(){
	String s = "[";
	for (int i = 0; i<chrome.length-1; i++){
	    s+= chrome[i] + ", ";
	}
	s += chrome[12]+ "]";
	return s;
    }

    public static void main (String[] args){
	/**TESTING**/
	Chromosome c = new Chromosome();
	System.out.println(c);
	System.out.println(c.getEatStrawb());
	System.out.println(c.getEatMush());
	System.out.println(c.getStrawbMove());
	System.out.println(c.getMushMove());
	System.out.println(c.getMonsMove());
	System.out.println(c.getCreMove());
	System.out.println(c.getAction());
	double[] weights = c.getWeights();
	for (int i = 0; i<weights.length; i++){
	    System.out.println(weights[i]);
	}
    }

}
