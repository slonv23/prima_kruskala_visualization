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
import java.util.Arrays;

/**
 *
 * @author Илья
 */
public class Prim {
    static int[][] graph = new int[10][10];
    static int INF = 20;
    
    static int[] dots;
    static int cDots;
    
    public static int[][] ostderevo;
    
    public static void build(int NumNodes, Rebro[] Branches){
        for(int i=0;i<NumNodes;i++)
            for(int j=0;j<NumNodes;j++)
                graph[i][j]=INF;
        
        int len = Branches.length;
        for(int i=0;i<len;i++){
            graph[Branches[i].v1][Branches[i].v2] = Branches[i].weight;
            graph[Branches[i].v2][Branches[i].v1] = Branches[i].weight;
        }
        
        dots = new int[NumNodes];
        dots[0] = 0;
        cDots = 1;
        for(int i=0;i<NumNodes;i++)
            graph[i][0] = INF;
            
        int cOst = NumNodes-1;
        ostderevo = new int[cOst][2];
        
        int reber = 0;
        while(reber<cOst){
            int v1 = 0;
            int v2 = 0;
            int min = INF;
            for(int i=0;i<cDots;i++){
                for(int j=0; j<NumNodes;j++){
                    if(graph[dots[i]][j]<min){
                        min = graph[dots[i]][j];
                        v1 = j;
                        v2 = dots[i];
                    }
                }
            }
            dots[cDots] = v1;
            cDots++;
            ostderevo[reber][0] = v1;
            ostderevo[reber][1] = v2;
            reber++;
            for(int i=0;i<NumNodes;i++){
                graph[i][v1] = INF;
            }
        }
    }
 
}
