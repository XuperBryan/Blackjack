/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class Card
{
    public int suit;
    public int value;
    public boolean taken;
    public final String SUIT;
    public String name;
    public BufferedImage image;

    public Card(int suit,int value)
    {
        this.suit = suit;
        this.value = value;
        taken = false;
        if(value ==1){
            this.value = 11;
        } else if(value>=10){
            this.value = 10;
        }

        if(suit==1){
            SUIT = "SPADE";
        } else if(suit==2){
            SUIT = "CLUB";
        }else if(suit==3){
            SUIT = "DIAMOND";
        }else if(suit==4){
            SUIT = "HEART";
        }else{
            SUIT = "ERROR";
        }

        if(value ==1){
            name = "A_";
        } else if(value ==2){
            name = "2_";
        } else if(value ==3){
            name = "3_";
        }else if(value ==4){
            name = "4_";
        }else if(value ==5){
            name = "5_";
        }else if(value ==6){
            name = "6_";
        }else if(value ==7){
            name = "7_";
        }else if(value ==8){
            name = "8_";
        }else if(value ==9){
            name = "9_";
        }else if(value ==10){
            name = "10_";
        }else if(value ==11){
            name = "J_";
        }else if(value ==12){
            name = "Q_";
        }else if(value ==13){
            name = "K_";
        }else{
            name = "ERROR";
        }
        try{
            image = ImageIO.read(new File(getName()+".jpg"));
        }
        catch(Exception e){}
    }

    public void setTaken(boolean taken){
        this.taken = taken;
    }

    public String getName(){
        return name+SUIT;
    }
}
