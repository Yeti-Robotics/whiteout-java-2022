package frc.robot.utils;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Contour {
    public double area;
    public int x, y, w, h;
    private static SerialPort jevois;

    public Contour(String area, String x, String y, String h, String w) {
        this.area = Double.parseDouble(area);
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.w = Integer.parseInt(w);
        this.h = Integer.parseInt(h);

        
    }

    public String toString(){
        return "area: " + area + ", " +  "x: " + x + ", " +  "y: " + y + ", " +  "h: " + h + ", " +  "w: " + w;
    }
    
}