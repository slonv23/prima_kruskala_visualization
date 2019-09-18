/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraphic;

import java.util.Arrays;

/**
 *
 * @author Илья
 */
public class Kruskala {
    
    public static int[] marks;

    public static int[][] ostderevo;
    public static int reber = 0;
    public static int nVer; 
    public static Sort ss;
    
    private static void slitie(int v, int vs){
        int r = marks[v];
        int z = marks[vs];
        for(int i = 0; i<nVer; i++){
            if(marks[i] == r)
                marks[i] = z;
        }
    }
    
    public static void build(int NumNodes, Rebro[] Branches){
        nVer = NumNodes;
        
        int nOst = nVer-1;
        
        marks = new int[nVer];
        ostderevo = new int[nOst][2];
        
        for(int i =0; i<nVer; i++)
            marks[i] = i;
        
        //Arrays.sort(Branches);
        ss.sort(Branches);
        
        int p = 0;
        while(reber<nOst){
            if(marks[Branches[p].v1]!=marks[Branches[p].v2]){
                ostderevo[reber][0] = Branches[p].v1;
                ostderevo[reber][1] = Branches[p].v2;
                slitie(Branches[p].v1,Branches[p].v2);
                reber++;
            }
            p++;
        }
    }
 
}
