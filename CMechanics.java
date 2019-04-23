package ukladankav1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static ukladankav1.UkladankaV1.Window;

public final class CMechanics extends JFrame implements ActionListener {
    
    long start, stop;
    
    JButton up, down, left, right; 
    
    private int lenght; 
    
    private int[][] field; 
    
    private int[] vector; 
    
    private int X, Y; 
   
    private static JButton createButton(String text,int x, int y, int szer, int wys){
        
        JButton button = new JButton(text);
        button.setBounds(x,y,szer,wys);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.BLACK);
      
        return button;
    }

    public CMechanics(){
      
        start = System.currentTimeMillis();
       
        right = createButton("right",200,303,97,94);
        Window.add(right);
        right.addActionListener(this);
       
        left = createButton("left",3,303,98,94);
        Window.add(left);
        left.addActionListener(this);
       
        up = createButton("up",102,303,97,46);
        Window.add(up);
        up.addActionListener(this);
       
        down = createButton("down",102,349,97,48);
        Window.add(down);
        down.addActionListener(this);
       
        lenght = 4;
        field = new int [lenght][lenght];
        vector = new int [16];

         do{
            shuffle();
         }while(IsSolvable(vector)==false);        
    }
   
    private void shuffle(){
            
        Random rand = new Random();
            
        int col,row;
        int counter = 1;
        int n = 0;
        int z = 0;
           
        for(int i=0; i<lenght; i++){
            for(int j=0; j<4; j++ )
            field[i][j] = 0;
        }
            
        while(n<16){
               
            col = rand.nextInt(4);
            row = rand.nextInt(4);
               
            if(field[row][col] == 0){
                if(counter == 16){
                    field[row][col] = 0;
                    X = row;
                    Y = col;
                    break;
                }  
            else{
                field[row][col] = counter;
                ++counter;
                n++;
                }
            }  
        }
            
        for(int k = 0; k<lenght; k++){
            for(int l=0; l<lenght; l++)      
                vector[z++] = field[k][l];  
        }
    }

    public boolean IsSolvable(int[] vector){
        
        int parity = 0;
        int row = 0;
        int emptyRow = 0;
            
        for (int i = 0; i < vector.length; i++){
            
            if (i % lenght == 0)
                row++;
            
            if (vector[i] == 0){ 
              emptyRow = row;
              continue;
            }
            
            for (int j = i + 1; j < vector.length; j++){
                if (vector[i] > vector[j] && vector[j] != 0)
                    parity++;
            }
        }
    
        if (lenght % 2 == 0) {
            if (emptyRow % 2 == 0)
                return parity % 2 == 0;
           
            else
            return parity % 2 != 0;
        }
        else
        return parity % 2 == 0;
    }

    private void GoRight() {
        if( Y > 0 ) {
            field[X][Y] = field[X][Y-1];
            field[X][Y-1] = 0;
            Y--;
        } 
    }
        
    private void GoLeft() {
        if( Y < lenght -1 ){
            field[X][Y] = field[X][Y+1];
            field[X][Y+1] = 0;
            Y++;
        }
    }
        
    private void GoUp() {
        if( X < lenght -1 ){
            field[X][Y] = field[X+1][Y];
                field[X+1][Y] = 0;
                X++;
        }
    }
         
    private void GoDown() {
        if( X > 0 ){
            field[X][Y] = field[X-1][Y];
            field[X-1][Y] = 0;
            X--;
        }
    }
    
    public int GetValue(int x, int y) {
        if(X >= 0  && X <= lenght - 1 && Y >= 0 && Y <= lenght-1){
            return field[x][y];
        }
            return 0;
    }
    
    public void infoBox(){
        stop = System.currentTimeMillis();
        long czas = (stop-start)/1000;
        JOptionPane.showMessageDialog(null,"GRATULACJE!  Ukończyłeś grę w czasie: " + czas + " sekund","KONIEC GRY", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean Winning(){
        
        int counter = 1;
        
        for(int i=0; i<lenght; i++){
            for(int j=0; j<lenght; j++){
                if (field[i][j] == counter || (i == 3 && j == 3))
                    counter++;
                else
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object source = e.getSource();
        
        if(source == right){
                GoRight();
                Window.repaint();
            if(Winning() == true){
                infoBox();
                System.exit(0);
            }
        }
        else if(source == left){
                GoLeft();
                Window.repaint();
            if(Winning() == true){
                infoBox();
                System.exit(0);
            }   
        }
        else if(source == down){
                GoDown();
                Window.repaint();
            if(Winning() == true){
                infoBox();
                System.exit(0);
            }
        }
        else if(source == up){
                GoUp();
                Window.repaint();
            if(Winning() == true){
                infoBox();
                System.exit(0);
            } 
        }
    }
}