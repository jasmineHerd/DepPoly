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
public class AssetSYD extends Asset {

    private double[] begbal, anndep, endbal, anndeprate;
    private boolean built;

    public AssetSYD(String nm, double c, double s, int lf) {
        super(nm, c, s, lf);
        if (super.isValid()) {
            build();
        }
    }

    private void build() {
        try{
            this.begbal = new double[super.getLife()];
            this.anndep = new double[super.getLife()];
            this.endbal = new double[super.getLife()];
            this.anndeprate = new double[super.getLife()];
            
            double syd = super.getLife() * (super.getLife() + 1) / 2.0;
            double totdep = super.getCost() - super.getSalvage();
            this.begbal[0] = super.getCost();
            for(int i =0;i < super.getLife();i++){
                if(i>0){
                    this.begbal[i] = this.endbal[i-1];
                }
                double deprate = (super.getLife()-i)/ syd;
                
                this.anndep[i] = totdep * deprate;
                this.anndeprate[i] = deprate;
                this.endbal[i] = this.begbal[i]-this.anndep[i];
            }
            this.built = true;
            
        }catch(Exception e){
            super.setErrorMsg("SYD build error: "+ e.getMessage());
            this.built = false;
        }
    }

    private boolean yearok(int year) {
        if (year < 1 || year > super.getLife()) {
            super.setErrorMsg("Year requested is out of range");
            return false;
        }
        return true;
    }

    @Override
    public double getBegBal(int yr) {
        if (this.built == false) {
            build();
            if (this.built == false) {
                return -1;
            }

        }
        if (!yearok(yr)) {
            return -1;
        }
        return this.begbal[yr - 1];

    }

    @Override
    public double getAnnDep(int yr) {
        if (this.built == false) {
            build();
            if (this.built == false) {
                return -1;
            }

        }
        if (!yearok(yr)) {
            return -1;
        }
        return this.anndep[yr - 1];

    }

    @Override
    public double getEndBal(int yr) {
        if (this.built == false) {
            build();
            if (this.built == false) {
                return -1;
            }

        }
        if (!yearok(yr)) {
            return -1;
        }
        return this.endbal[yr - 1];

    }
    public double getAnnDepRate(int yr){
                if (this.built == false) {
            build();
            if (this.built == false) {
                return -1;
            }

        }
        if (!yearok(yr)) {
            return -1;
        }
        return this.anndeprate[yr-1];
    }
}
