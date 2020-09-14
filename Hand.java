
/**
 * Write a description of class Hand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Hand
{
    ArrayList<Card> hand;
    public boolean done = false;
    public int sum =0;
    public boolean busted = false;
    public boolean hasAce = false;
    public boolean betDouble = false;
    public int bet = 1000;
    public Hand()
    {
        hand = new ArrayList<Card>();
    }

    public void addCard(Card card){
        hand.add(card);
    }

    /**
     * Only use for spliting
     */
    public void remove(){
        hand.remove(0);
    }

    public void setDone(boolean doneness){
        done = doneness;
    }

    public void printCards(){

    }

    public void printTotal(){
        sum=0;
        for(int i = 0;i<hand.size();i++){
            sum += hand.get(i).value;
        }
    }

    public int getTotal(){
        sum=0;
        for(int i = 0;i<hand.size();i++){
            sum += hand.get(i).value;
        }
        return sum;
    }

    public void checkHand(){
        if(getTotal()>21){
            for(int i=0;i<hand.size();i++){
                if(hand.get(i).value==11){
                    hand.get(i).value=1;
                }
            }
        }
    }

    public boolean isBusted(){
        if(getTotal()>21){
            for(int i=0;i<hand.size();i++){
                if(hand.get(i).value==11){
                    hand.get(i).value=1;
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }
    
    public int getBet(){
        if(betDouble){
            return 2000;
        }
        return bet;
    }
}

