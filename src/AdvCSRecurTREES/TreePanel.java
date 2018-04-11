package AdvCSRecurTREES;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TreePanel extends JPanel implements ActionListener
{

        private int width, height;
        private Color bColor = Color.CYAN;
        private Color brownTreeColor = new Color(170, 80, 10);
        //private Color darkTreeColor = new Color(90, 5, 10);
        private Color darkTreeColor = new Color(122, 54, 1);
        //39, 175, 5
        private Color treeShadowColor = new Color(39, 175, 5);
        private BufferedImage pic;
        
        private Timer t;
        
  
        public TreePanel(int w, int h){
              width = w;
              height= h;
              this.setSize(width, height);
              this.setBackground(bColor);
              
          
                 try {                
                    pic = ImageIO.read(new File("mountains.jpg"));
                 } catch (IOException e) {
                      // handle exception...
                     System.out.println("Error reading file");
                 }
              
                 t = new Timer(50, this);
                 t.start();
              
        }
        
        public void paintComponent(Graphics g)
        {
            g.setColor(bColor);
            g.fillRect(0, 0, width, height);
            
            g.drawImage(pic, 0, 0, width, height, this);
            
            // draw a sun...
            g.setColor(Color.YELLOW);
            g.fillOval((int)(width * .8), (int)(height * .1), 75, 75);
            
            //draw the ground
            g.setColor(Color.GREEN);
            g.fillRect(0, (int)(height * .75), width, (int)(height * .25));
            
            // Draw all of the shadows
            drawBranchShadow((int)(width/10) + 5, (int)(height * .7), 90, -90 - 90, g);
            drawBranchShadow((int)(width/4)+ (int)(width/2) + 5, (int)(height * .8), 100, -90 - 90, g);
            drawBranchShadow((int)(width/4) + 5, (int)(height * .8), 100, -90 -90, g);
            drawBranchShadow((int)(width/5) + 5, (int)(height * .8), 105, -90 -90, g);
            drawBranchShadow((int)(width/7) + 5, (int)(height * .8), 110, -90 -90, g);
            drawBranchShadow((int)(width/8) + 5, (int)(height * .85), 120, -90 - 90, g);
            drawBranchShadow((int)(width/7) + (int)(width/2) + 5, (int)(height * .85), 130, -90 -90, g);
            drawBranchShadow((int)(width/7) + (int)(width/2) + (int)(width/5) + 5 , (int)(height * .85), 130, -90 -90, g);
            drawBranchShadow((int)(width/2) + 5, (int)(height * .9), 160, -90 - 90, g);
            
            // Draw all of the trees

            drawBranch((int)(width/10), (int)(height * .7), 90, -90, g);          
            drawBranch((int)(width/4)+ (int)(width/2), (int)(height * .8), 100, -90, g);
            drawBranch((int)(width/4), (int)(height * .8), 100, -90, g);
            drawBranch((int)(width/5), (int)(height * .8), 105, -90, g);
            drawBranch((int)(width/7), (int)(height * .8), 110, -90, g);
            drawBranch((int)(width/8), (int)(height * .85), 120, -90, g);
            drawBranch((int)(width/7) + (int)(width/2), (int)(height * .85), 130, -90, g);
            drawBranch((int)(width/7) + (int)(width/2) + (int)(width/5) , (int)(height * .85), 130, -90, g);
            drawBranch((int)(width/2), (int)(height * .9), 160, -90, g);
            
            
          
        }
        
        public void drawBranchShadow(int startX, int startY, int length, int angleDeg, Graphics g){
          
          
          double angleRad = (Math.PI * angleDeg)/180.0;
          
          int endX, endY;
          
          endX = (int)(startX + (Math.cos(angleRad) * length));
          endY = (int)(startY + (Math.sin(angleRad) * length));
          
          //find new next points....
          int newL1 = (int)( (length * 0.7) + (Math.random()* (length*0.2)));
          int newX1 = (int)(startX + (Math.cos(angleRad) * newL1));
          int newY1 = (int)(startY + (Math.sin(angleRad) * newL1));
          
          int newL2 = (int)( (length * 0.6) + (Math.random()* (length*0.2)));
          int newX2 = (int)(startX + (Math.cos(angleRad) * newL2));
          int newY2 = (int)(startY + (Math.sin(angleRad) * newL2));
          
        //Trying a tree color thing....
         
          Polygon p = new Polygon();
          
          int lowerLength = (int)(length * 0.10);
          int upperLength = (int)(length * 0.03);
          int lower1X = (int)(startX + (Math.cos(angleRad + 90) * (lowerLength/2)));
          int lower2X = (int)(startX - (Math.cos(angleRad + 90) * (lowerLength/2)));
          int lower1Y = (int)(startY + (Math.sin(angleRad + 90) * (lowerLength/2)));
          int lower2Y = (int)(startY- (Math.sin(angleRad + 90) * (lowerLength/2)));
          p.addPoint(lower1X, lower1Y);
          p.addPoint(lower2X, lower2Y);
          
          int upper4X = (int)(endX + (Math.cos(angleRad + 90) * (upperLength/2)));
          int upper3X = (int)(endX - (Math.cos(angleRad + 90) * (upperLength/2)));
          int upper4Y = (int)(endY + (Math.sin(angleRad + 90) * (upperLength/2)));
          int upper3Y = (int)(endY- (Math.sin(angleRad + 90) * (upperLength/2)));
          p.addPoint(upper3X, upper3Y);
          p.addPoint(upper4X, upper4Y);
          
          g.setColor(treeShadowColor);
          g.fillPolygon(p);
          
          
          
          if(length > 10){
            drawBranchShadow(newX1, newY1, (int)(newL1 * 0.9), angleDeg + 3 + (int)(Math.random() * 5),  g);
            drawBranchShadow(newX2, newY2, (int)(newL2 * 0.9), angleDeg - 3 - (int)(Math.random() * 5),  g);
            
            if((int) (Math.random() * 100) > 70){
              drawBranchShadow(newX2, newY2, (int)(newL2 * 0.9), angleDeg + 3 + (int)(Math.random() * 5),  g);
            }
          }
          
        }
        
        public void drawBranch(int startX, int startY, int length, int angleDeg, Graphics g)
        {
          
          double angleRad = (Math.PI * angleDeg)/180.0;
          //double lucAngle = Math.toRadians(angleDeg);
          
          //System.out.println("angleRad = " + angleRad);
          //System.out.println("lucAngle = " + lucAngle);
          
          int endX, endY;
          
          endX = (int)(startX + (Math.cos(angleRad) * length));
          endY = (int)(startY + (Math.sin(angleRad) * length));
          
          g.setColor(Color.DARK_GRAY);
          g.drawLine(startX, startY, endX, endY);
          
          
          
          
         
          
          //find new next points....
          int newL1 = (int)( (length * 0.7) + (Math.random()* (length*0.2)));
          int newX1 = (int)(startX + (Math.cos(angleRad) * newL1));
          int newY1 = (int)(startY + (Math.sin(angleRad) * newL1));
          
          int newL2 = (int)( (length * 0.6) + (Math.random()* (length*0.2)));
          int newX2 = (int)(startX + (Math.cos(angleRad) * newL2));
          int newY2 = (int)(startY + (Math.sin(angleRad) * newL2));
          
        //Trying a tree color thing....
         
          Polygon p = new Polygon();
          
          int lowerLength = (int)(length * 0.10);
          int upperLength = (int)(length * 0.03);
          int lower1X = (int)(startX + (Math.cos(angleRad + 90) * (lowerLength/2)));
          int lower2X = (int)(startX - (Math.cos(angleRad + 90) * (lowerLength/2)));
          int lower1Y = (int)(startY + (Math.sin(angleRad + 90) * (lowerLength/2)));
          int lower2Y = (int)(startY- (Math.sin(angleRad + 90) * (lowerLength/2)));
          p.addPoint(lower1X, lower1Y);
          p.addPoint(lower2X, lower2Y);
          
          int upper4X = (int)(endX + (Math.cos(angleRad + 90) * (upperLength/2)));
          int upper3X = (int)(endX - (Math.cos(angleRad + 90) * (upperLength/2)));
          int upper4Y = (int)(endY + (Math.sin(angleRad + 90) * (upperLength/2)));
          int upper3Y = (int)(endY- (Math.sin(angleRad + 90) * (upperLength/2)));
          p.addPoint(upper3X, upper3Y);
          p.addPoint(upper4X, upper4Y);
          
          g.setColor(brownTreeColor);
          g.fillPolygon(p);
          
          //dark tree color
          Polygon p2 = new Polygon();
          p2.addPoint(lower1X, lower1Y);
          p2.addPoint(startX, startY);
     
          p2.addPoint(endX, endY);
          p2.addPoint(upper4X, upper4Y);
          
          g.setColor(darkTreeColor);
          g.fillPolygon(p2);
          
          
          if(length > 10){
            drawBranch(newX1, newY1, newL1, angleDeg + 10 + (int)(Math.random() * 8),  g);
            drawBranch(newX2, newY2, newL2, angleDeg - 10 - (int)(Math.random() * 8),  g);
            
            if((int) (Math.random() * 100) > 70){
              drawBranch(newX2, newY2, newL2, angleDeg + 10 + (int)(Math.random() * 8),  g);
            }
          }
          
          
          
          
          
        }

        public void actionPerformed(ActionEvent e)
        {
          // TODO Auto-generated method stub
          this.repaint();
        }
        
        
        

}
