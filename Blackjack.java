/**
 * Write a description of class BlackJack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class BlackJack extends JPanel
{
    Card[][] deck = new Card[5][14];
    ArrayList<Hand> hands = new ArrayList<Hand>();
    Hand dealer= new Hand();
    public int i=0;
    JButton dd;
    JButton split;
    JButton NG;
    public boolean GID=false;
    public boolean drawAGiantRectangle = false;
    public int wins;
    public int losses;
    public int money;
    public int bet=1000;
    public BlackJack()
    {
        for(int i = 1;i<=4;i++){
            for(int j = 1;j<=13;j++){
                Card card = new Card(i,j);
                deck[i][j] = card;
            }
        }
        money = 5000;
    }

    public void paintComponent(Graphics g){
        if(hands.get(i).getTotal()!=21){
            hands.get(i).checkHand();
        }
        Graphics2D g2 = (Graphics2D)g;
        if(drawAGiantRectangle = true){
            Rectangle giantRectangle = new Rectangle(0,0,2000,2000);
            g2.setColor(new Color(0,153,0));
            g2.fill(giantRectangle);
            g2.setColor(Color.BLACK);
        }
        int edge=0;
        int y=0;
        Rectangle rect= new Rectangle(0,0,80,45);
        for(int i = 0;i<hands.size();i++){
            for(int j =0;j<hands.get(i).hand.size();j++){
                if(i==this.i){
                    g2.setColor(Color.GREEN);
                }
                else{
                    g2.setColor(Color.WHITE);
                }
                g2.drawImage(hands.get(i).hand.get(j).image,j*170,i*100,null);
                edge=edge+170;
            }
            rect.translate(edge+18,y+43);
            g2.fill(rect);
            g2.setColor(Color.BLACK);
            g2.drawString("Hand: "+(i+1)+"",edge+20,y+55);
            g2.drawString("Value: "+hands.get(i).getTotal()+"",edge+20,y+80);
            if(hands.get(i).isBusted()==true){
                hands.get(i).setDone(true);
                g2.drawString("BUSTED!",edge+20,y+67);
            }
            rect.translate(-edge-18,-y-43);
            y=y+100;
            edge=0;
        }
        if(hands.get(hands.size()-1).done==false){
            try{
                g2.drawImage(ImageIO.read(new File("BACK.jpg")),0,480,null);                
            }catch(Exception e){}       
            g2.drawImage(dealer.hand.get(1).image,170,480,null);
        }else{
            GID=true;
            Rectangle rect2= new Rectangle(800,0,200,750);
            g2.setColor(Color.RED);
            g2.fill(rect2);
            Rectangle rect3=new Rectangle((dealer.hand.size()*170)+20,560,80,45);
            g2.setColor(Color.PINK);
            g2.fill(rect3);
            g2.setColor(Color.BLACK);
            g2.drawString("Value: "+dealer.getTotal()+"",(dealer.hand.size()*170)+30,580);
            for(int j =0;j<dealer.hand.size();j++){
                g2.drawImage(dealer.hand.get(j).image,j*170,480,null);
            }
            g2.setColor(Color.WHITE);
            int height=20;
            for(int i=0;i<hands.size();i++){
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
                if(hands.get(i).isBusted()==false){
                    if(dealer.isBusted()){
                        g2.drawString("Hand "+(i+1)+" wins!",810,height);
                        wins++;
                        money = money + hands.get(i).getBet();
                    }
                    else if(hands.get(i).getTotal()>dealer.getTotal()){
                        g2.drawString("Hand "+(i+1)+" wins!",810,height);
                        wins++;
                        money = money + hands.get(i).getBet();
                    }
                    else if(hands.get(i).getTotal()==dealer.getTotal()){
                        g2.drawString("Hand "+(i+1)+" pushes",810,height);
                    }
                    else{
                        g2.drawString("Hand "+(i+1)+" loses!",810,height);
                        losses++;
                        money = money - hands.get(i).getBet();
                    }
                    height=height+20;
                }else{
                    g2.drawString("Hand "+(i+1)+" loses!",810,height);
                    losses++;
                    height=height+20;
                    money = money - hands.get(i).getBet();
                }
            }
            g2.drawString("Wins: "+wins,810,height);
            height=height+20;
            g2.drawString("Losses: "+losses,810,height);
            height=height+20;
            g2.drawString("Your last bet: ",810,height);
            height=height+20;
            for(int j = 0;j<hands.size();j++){
                g2.drawString("Hand "+(j+1)+": " +hands.get(j).getBet(),810,height);
                height=height+20;
            }
            g2.drawString("Money: "+money,810,height);
            hands.get(i).betDouble=false;
        }
    }

    public void reset(){
        for(int i = 1;i<=4;i++){
            for(int j = 1;j<=13;j++){
                deck[i][j].setTaken(false);
            }
        }
        for(int k = 1;k <=4;k++){
            deck[k][1].value = 11;
        }
        dealer.hand.clear();
        dealer.sum = 0;
        hands.clear();
        i=0;
        GID=false;
        drawAGiantRectangle = false;
    }

    public Card hit(){
        Random gen = new Random();
        int Suit= gen.nextInt(4)+1;
        int Value= gen.nextInt(13)+1;
        while(deck[Suit][Value].taken==true){
            Suit = gen.nextInt(4) +1;
            Value = gen.nextInt(13) +1;
        }
        deck[Suit][Value].setTaken(true);
        return deck[Suit][Value];
    }

    public void play(int selection){
        if(hands.get(i).getTotal()!=21){
            hands.get(i).checkHand();
        }
        dealerPlay();
        paintImmediately(0,0,2000,2000);
        switch(selection)
        {
            case 1: Card cardDrawn = hit();
            hands.get(i).addCard(cardDrawn);
            paintImmediately(0,0,2000,2000);
            if(hands.get(i).isBusted()==true){
                hands.get(i).setDone(true);
            }
            break;
            case 2:
            hands.get(i).setDone(true);
            paintImmediately(0,0,2000,2000);
            i++;
            break;
            case 3: cardDrawn = hit();
            hands.get(i).addCard(cardDrawn);
            hands.get(i).setDone(true);
            paintImmediately(0,0,2000,2000);
            i++;
            break;
            case 4: 
            split(hands.get(i));
            paintImmediately(0,0,2000,2000);
            break;
        }
        if(hands.get(i).isBusted()==true){
            hands.get(i).setDone(true);
            i++;
        }
        dealerPlay();
        repaint();
        checkButtons();
    }

    public void dealerPlay(){
        while(dealer.getTotal()<17){
            dealer.addCard(hit());
            dealer.checkHand();
        }
    }

    public void compareHands(){
    }

    public void split(Hand Hand2Split){
        Hand New = new Hand();
        hands.add(New);
        New.addCard(Hand2Split.hand.get(0));
        Hand2Split.remove();
        New.addCard(hit());
        Hand2Split.addCard(hit());
    }

    public void start(){
        Hand first = new Hand();
        hands.add(first);
        hands.get(0).addCard(hit());
        hands.get(0).addCard(hit());
        dealer.addCard(hit());
        dealer.addCard(hit());
    }

    public void checkButtons(){
        NG.setVisible(true);
        if(((hands.get(i).getTotal()==10)||hands.get(i).getTotal()==11)&&(hands.get(i).hand.size()==2)){
            dd.setVisible(true);
        }
        else{
            dd.setVisible(false);
        }
        if(hands.get(i).hand.get(0).name.equals(hands.get(i).hand.get(1).name) &&(hands.get(i).hand.size()==2)){
            split.setVisible(true);
        }
        else{
            split.setVisible(false);
        }
        repaint();
    }

    public static void main(String[] args){
        BlackJack starter= new BlackJack();
        starter.reset();
        JFrame frame = new JFrame();
        frame.setSize(1000,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(starter);
        JButton hit = new JButton("HIT");
        starter.add(hit);
        JButton stay = new JButton("STAY");
        starter.add(stay);
        starter.dd = new JButton("DOUBLE DOWN");
        starter.add(starter.dd);
        starter.split = new JButton("SPLIT");
        starter.add(starter.split);
        starter.NG = new JButton("NEWGAME");
        starter.add(starter.NG);
        starter.start();
        starter.checkButtons();
        class HitButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                starter.play(1);
                starter.checkButtons();
                starter.repaint();              
            }
        } 
        ActionListener hitListener = new HitButtonListener();
        hit.addActionListener(hitListener);
        class StayButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                starter.play(2);
                starter.checkButtons();
                starter.repaint();
            }
        } 
        ActionListener stayListener = new StayButtonListener();
        stay.addActionListener(stayListener);
        class ddButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                starter.hands.get(starter.i).betDouble = true;
                starter.play(3);
                starter.checkButtons();
                starter.repaint();
            }
        } 
        ActionListener ddListener = new ddButtonListener();
        starter.dd.addActionListener(ddListener);
        class SplitButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                starter.play(4);
                starter.checkButtons();
                starter.repaint();
            }
        } 
        ActionListener SplitListener = new SplitButtonListener();
        starter.split.addActionListener(SplitListener);
        class NGButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                starter.drawAGiantRectangle = true;
                starter.repaint();
                starter.reset();
                starter.drawAGiantRectangle = false;
                starter.start();
                starter.checkButtons();
                starter.repaint();
            }
        } 
        ActionListener NGListener = new NGButtonListener();
        starter.NG.addActionListener(NGListener);
        starter.paintImmediately(0,0,2000,2000);
    }
}
