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
class Rebro implements Comparable<Rebro> {
    int v1;
    int v2;
    int weight;
    @Override
    public int compareTo(Rebro e){
            if (weight < e.weight) return -1;
            if (weight > e.weight ) return 1;
            return 0;
    }
}

