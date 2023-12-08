/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author personal
 */
import javafx.geometry.Point2D;
import java.util.Random;
/**
 *
 * @author personal
 */
public class Asteroid extends Character {
    
    private Point2D movement = new Point2D(0.2,0.2);
    private double rotationalMovement;
    
    public Asteroid(int x, int y){
        super(new PolygonFactory().createPolygon(),x,y);
        
        Random rnd = new Random();
        
        super.getCharacter().setRotate(rnd.nextInt(360));
        
        int numAcc = 1+rnd.nextInt(10);
        for(int i=0;i<numAcc;i++){
            accelerate();
        }
        this.rotationalMovement = 0.5-rnd.nextDouble();
        
    }
    @Override
    public void move(){
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate()+rotationalMovement);
        
    }
}
