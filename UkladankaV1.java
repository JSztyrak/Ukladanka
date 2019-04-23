//Projekt z przedmiotu programowanie obiektowe Java 2016

package ukladankav1;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class UkladankaV1 {
    
    public static final int Width = 300;
    public static final int Hight = 400;
    public static JFrame Window;
    
    public static void main(String[] args) {
        
        Window = new JFrame("PRZESUWANKA");
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX =(dim.width-Width)/2;
        int locationY =(dim.height-Hight)/2;
        Window.setLocation(locationX, locationY);
           
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.setResizable(false);
        Window.setSize(Width+6, Hight+29);
        CGraphics Game = new CGraphics();
           
        Window.add(Game);
        Window.setVisible(true);
    }
}
