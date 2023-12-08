/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author personal
 */

import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;

/**
 *
 * @author personal
 */
public abstract class Character {
    protected Polygon character;
    protected Point2D movement;
    protected boolean alive = true;
    
    public Character(Polygon polygon, int x, int y){
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.movement = new Point2D(0,0);
    }
    public Polygon getCharacter(){
        return character;
    }
    public void turnLeft(){
        this.character.setRotate(this.character.getRotate()-5);
    }
    public void turnRight(){
        this.character.setRotate(this.character.getRotate()+5);
    }
    public void move(){
        this.character.setTranslateX(this.character.getTranslateX()+movement.getX());
        this.character.setTranslateY(this.character.getTranslateY()+movement.getY());
        
        Bounds charBounds = this.character.getBoundsInParent();
        
        if(charBounds.getMinX()<0){
            this.character.setTranslateX(this.character.getTranslateX()+AsteroidsApp.WIDTH);
        }
        if(charBounds.getMinY()<0){
            this.character.setTranslateY(this.character.getTranslateY()+AsteroidsApp.HEIGHT);
        }
        //- could be used rather than %
        if(this.character.getTranslateX()>AsteroidsApp.WIDTH){
            this.character.setTranslateX(this.character.getTranslateX()-AsteroidsApp.WIDTH);
        }
        if(this.character.getTranslateY()>AsteroidsApp.HEIGHT){
            this.character.setTranslateY(this.character.getTranslateY()-AsteroidsApp.HEIGHT);
        }
    }
    public void accelerate(){
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        changeX*=0.05;
        changeY*=0.05;
        this.movement = this.movement.add(changeX,changeY);
    }   
    public boolean collide(Character other){
        Shape collisionArea = Shape.intersect(this.character,other.character );
        return collisionArea.getBoundsInLocal().getWidth() != -1;
        
    }
    public Point2D getMovement(){
        return this.movement;
    }
    public void setMovement(Point2D movement){
        this.movement = movement;
    }
    public boolean isAlive(){
        return this.alive;
    }
    public void setAlive(boolean b){
        this.alive = b;
    }
}

