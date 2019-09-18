/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraphic;

/**
 *
 * @author Илья
 */
/*import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.math.*;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Main extends JPanel {
         
	static boolean algo = true;
        
        Main(){
            arr = new V[nVer];
            for(int i = 0; i<nVer; i++) arr[i] = new V();
            arr[0].x = 20;
            arr[0].y = 200;
            arr[1].x = 50;
            arr[1].y = 100;
            arr[2].x = 80;
            arr[2].y = 140;
            arr[3].x = 110;
            arr[3].y = 80;
            arr[4].x = 110;
            arr[4].y = 200;
            arr[5].x = 160;
            arr[5].y = 140;
            arr[6].x = 180;
            arr[6].y = 200;
            arr[7].x = 180;
            arr[7].y = 90;
            arr[8].x = 220;
            arr[8].y = 40;
            arr[9].x = 230;
            arr[9].y = 130;
        } 
        
        class V {
            int x;
            int y;
        };
        
        V[] arr;
        
        private static int nVer = 3;
        private static int nReb;
        private static int shag = 0;
        private static Rebro[] rebra;
        
	private void drawDotted(Graphics2D g2d, double x1, double y1, double x2, double y2) {
                double len = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))/10;
                double r = (x2-x1)/len;
                double t = (y2-y1)/len;
                int i;
                int g = (int)len-1;
                for(i = 0; i<g; i+=2)
                    g2d.drawLine((int)(Math.round(x1+ i*r)),(int)(Math.round(y1+ i*t)),(int)(Math.round(x1+(i+1)*r)),(int)(Math.round(y1+(i+1)*t)));
                g2d.drawLine((int)(Math.round(x1+ i*r)),(int)(Math.round(y1+ i*t)),(int)(Math.round(x2)),(int)(Math.round(y2)));
	}

	@Override
	public void paint(Graphics g) {
                Font b = new Font("Arial", Font.BOLD, 15);
                
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
                g2d.setFont(b);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
                
                for(int i = 0; i<nReb; i++){
                    int j = 0;
                    while(true){
                        if(j<shag){
                            if((algo==false&&(rebra[i].v2==Prim.ostderevo[j][0])&&(rebra[i].v1==Prim.ostderevo[j][1]))
                                    ||(algo==true&&(rebra[i].v1==Kruskala.ostderevo[j][0])&&(rebra[i].v2==Kruskala.ostderevo[j][1]))){
                                g2d.setColor(Color.blue);
                                g2d.drawLine(arr[rebra[i].v1].x+12,arr[rebra[i].v1].y+12,arr[rebra[i].v2].x+12,arr[rebra[i].v2].y+12);
                                break;
                            }
                            j++;
                        }
                        else {
                            g2d.setColor(Color.GRAY);
                            drawDotted(g2d,arr[rebra[i].v1].x+12,arr[rebra[i].v1].y+12,arr[rebra[i].v2].x+12,arr[rebra[i].v2].y+12);
                            break;
                        }

                    }
                    g2d.setColor(Color.red);
                    g2d.drawString(Integer.toString(rebra[i].weight),
                            arr[rebra[i].v1].x+8+(arr[rebra[i].v2].x-arr[rebra[i].v1].x)/2,
                            arr[rebra[i].v1].y+8+(arr[rebra[i].v2].y-arr[rebra[i].v1].y)/2); 
                }
                
                for(int i = 0; i<nVer; i++){
                    g2d.setColor( Color.white );
                    g2d.fillOval(arr[i].x, arr[i].y, 24, 24);
                    g2d.setColor( Color.blue );
                    g2d.drawOval(arr[i].x, arr[i].y, 24, 24);
                    g2d.setColor( Color.black );
                    g2d.drawString(Integer.toString(i),arr[i].x+8,arr[i].y+18); 
                }
	}

	public static void main(String[] args) throws InterruptedException {
                Scanner s = null;
                try {
                   s = new Scanner(new File("input.txt")); // "I:\\INPUT.txt"));
                }catch (IOException e) {
                        e.printStackTrace();
                } 
                nVer = s.nextInt();
                nReb = s.nextInt();

                rebra = new Rebro[nReb];

                for(int i =0; i<nReb; i++){
                    rebra[i] = new Rebro();
                    rebra[i].v1 = s.nextInt()-1;
                }
                for(int i =0; i<nReb; i++)
                   rebra[i].v2 = s.nextInt()-1;
                for(int i =0; i<nReb; i++)
                   rebra[i].weight = s.nextInt();
                
                long start_time;
                long end_time;
                long sort_time;
                Kruskala.ss = new Sort();
                start_time = System.nanoTime();
                Kruskala.build(nVer, rebra);
                end_time = System.nanoTime();
                sort_time = end_time - start_time;
                System.out.println("Крускала: " + sort_time);
                start_time = System.nanoTime();
                Prim.build(nVer, rebra);
                end_time = System.nanoTime();
                sort_time = end_time - start_time;
                System.out.println("Прима: " + sort_time);
		
                JFrame frame = new JFrame("Візуалізація");
                frame.setSize(300, 400);
                frame.setLocationRelativeTo(null);
		Main vis = new Main();
                
                JButton butt = new JButton("Наступний крок");
                butt.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        if(shag<nVer-1)
                            shag++;
                        else shag = 0;
                        vis.repaint();
                    }
                });
                
                JLabel title = new JLabel("Алгоритм Крускала");
                JPanel text = new JPanel(new FlowLayout()); 
                text.add(title);
                
                JPanel buttonsPanel = new JPanel(new FlowLayout()); 
                buttonsPanel.add(butt);
                
                JButton buttN = new JButton("Алгоритм Пріма");
                buttN.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        if(algo==true){
                            title.setText("Алгоритм Пріма");
                            buttN.setText("Алгоритм Крускала");
                        }
                        else {
                            title.setText("Алгоритм Крускала");
                            buttN.setText("Алгоритм Пріма");
                        }
                        algo = !algo;
                        shag = 0;
                        vis.repaint();
                    }
                });
                buttonsPanel.add(buttN);

                frame.add(buttonsPanel, BorderLayout.SOUTH);
                frame.add(text, BorderLayout.NORTH);
		frame.add(vis);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*while (true) {
			vis.repaint();
			Thread.sleep(10);
		}*/
	}
        
}
