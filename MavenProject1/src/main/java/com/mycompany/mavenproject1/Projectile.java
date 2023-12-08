/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import javafx.scene.shape.Polygon;

/**
 *
 * @author personal
 */

public class Projectile extends Character {
    public Projectile(int x,int y){
        super(new Polygon(2,-2,2,2,-2,2,-2,-2),x,y);
    }
}

