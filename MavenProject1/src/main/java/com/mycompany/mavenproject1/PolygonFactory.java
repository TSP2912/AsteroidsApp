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
import java.util.Random;
/**
 *
 * @author personal
 */
public class PolygonFactory {
    public Polygon createPolygon(){
        //The random class
        Random rnd = new Random();
        //size of the polygon is between 10 and 19
        double size = 10 + rnd.nextInt(10);
        
        Polygon pentagon = new Polygon();
        //Tangents ratios are calculated by resolving
        //the line from centre to the 2nd and 3rd vertices
        //into components
        double c1 = Math.cos(Math.toRadians(72));
        double c2 = Math.cos(Math.toRadians(54));
        double s1 = Math.sin(Math.toRadians(72));
        double s2 = Math.sin(Math.toRadians(54));
        //Creating a random pentagon using the tangent ratios
        pentagon.getPoints().addAll(
                0.0,size,
                size*s1,size*c1,
                size*c2,-size*s2,
                -size*c2,-size*s2,
                -size*s1,size*c1);
        //Adding randomness to the pentagons
        for(int i=0;i<pentagon.getPoints().size();i++){
            int change = rnd.nextInt(5)-2;
            pentagon.getPoints().set(i,pentagon.getPoints().get(i)+ change);
        }
        
        
        return pentagon;
    }
    
}
