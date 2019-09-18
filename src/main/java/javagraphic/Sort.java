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
public class Sort {
     /*static public void sort(Rebro[] arr){
        sort(arr,0,arr.length-1);
     }
     static public void sort(Rebro[] arr,int a, int b) {
        Rebro m = arr[b];
        int i = a;
        int j = b - 1;
        while(true){
            while(arr[i].weight<m.weight)
                i++;
            while(arr[j].weight>=m.weight&&j!=a)
                j--;  
            if(j>i){
                Rebro buff;
                buff = arr[i];
                arr[i] = arr[j];
                arr[j] = buff;
            } else {
                arr[b] = arr[i];
                arr[i] = m;
                break;
            }
        }
        if(i>a+1)
         sort(arr,a,i-1);
        if(i<b-1)
         sort(arr,i+1,b);
     }*/
    static private Rebro[] ptr;
    static int len;
    static public void sort(Rebro[] a){
        len = a.length;
        ptr = a;
        
        sortTree(0);
        
        for(int i=len-1; i>0;i--){
            Rebro val = ptr[0];
            ptr[0] = ptr[i];
            ptr[i] = val;
            len--;
            halfSort(0);
        }
        
    }
    
    static private void sortTree(int n){
        Rebro max = ptr[n];
        int next = -1;
        int parent = n;
        if((n=2*n+1) < len){
            sortTree(n);
            if(ptr[n].weight>max.weight){ 
                max = ptr[n];
                next = n;
            }
        }
        if((n+1) < len){
            sortTree(++n);
            if(ptr[n].weight>max.weight){ 
                max = ptr[n];
                next = n;
            }
        }
        if(next > 0){
            ptr[next] = ptr[parent];
            ptr[parent] = max;
            sortTree(next);
        }
    }
    
    static private void halfSort(int n){
        Rebro max = ptr[n];
        int next = -1;
        int parent = n;
        if((n=2*n+1)<len&&ptr[n].weight>max.weight){ 
            max = ptr[n];
            next = n;
        }
        if(++n < len&&ptr[n].weight>max.weight){ 
            max = ptr[n];
            next = n;
        }
        if(next > 0){
            ptr[next] = ptr[parent];
            ptr[parent] = max;
            halfSort(next);
        }
    }
}
