/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author jasmineherd
 */
public class Asset15DL extends AssetDL{
    public static final double DLFACTOR = 1.5;
    
    public Asset15DL(String nm,double c,double s, int lf){
        super(nm, c, s, lf,Asset15DL.DLFACTOR);
    }
}
