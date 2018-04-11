package AdvCSRecurTREES;

import javax.swing.JFrame;

public class TreeMain
{

      public static void main(String[] args)
      {
        // TODO Auto-generated method stub
        
        int width = 800;
        int height = 600;
        JFrame f = new JFrame("Recursive Trees");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);
        TreePanel tp = new TreePanel( width, height-20);
        f.add(tp);
        f.setVisible(true);
        
        
        
    
      }

}
