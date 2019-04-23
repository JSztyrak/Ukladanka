package ukladankav1;

import javax.swing.JPanel;
import java.awt.*;

public class CGraphics extends JPanel{

    CMechanics game; 

    private Color _background, _foreground, _borderCOlor; 

    private int _lenghtField; 

    private int _halfLenght; 

    private Font _font; 
    
    public CGraphics(){
        
        game = new CMechanics();
       
        _lenghtField = UkladankaV1.Width / 4;
            
        _halfLenght = _lenghtField / 2 ;

        _background = new Color(90, 210, 30);
        _foreground = new Color(0, 0, 0); 
        _borderCOlor = new Color(29, 110, 13);
        _font = new _font("TimesRoman", 1, 15);
    }
    
    @Override
    public void paint(Graphics g) {
           
        Graphics2D g2d = (Graphics2D)g;
            
        g2d.setColor(_background);
        g2d.setFont(_font);
        g2d.fillRect(0,0, getWidth(), getHeight());
        DrawFrame(g2d);
        g2d.setColor(_foreground);
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(game.GetValue(i, j) != 0)
                g2d.drawString(""+game.GetValue(i, j), _halfLenght + j * _lenghtField, _halfLenght + i * _lenghtField);
            }
        }    
    }

    private void DrawFrame(Graphics2D g2d) {
        
        g2d.setColor(_borderCOlor);
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(6));
        
        
        g2d.drawRect(0, 0, UkladankaV1.Width, UkladankaV1.Width);
        g2d.drawRect(0, UkladankaV1.Width, UkladankaV1.Width, UkladankaV1.Wysokosc-UkladankaV1.Width);
        g2d.setStroke(oldStroke);
        
        for(int i = 1; i<4; i++){
            g2d.drawLine(0, _lenghtField*i, UkladankaV1.Width, _lenghtField*i);
            g2d.drawLine(_lenghtField*i, 0, _lenghtField*i, UkladankaV1.Width);
        }
    }
}