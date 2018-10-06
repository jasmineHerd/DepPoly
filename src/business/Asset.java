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
//abstract keyword. Declared but not coded at this level
//abstract object cannot be directly instantiated
abstract public class Asset {

    private String assetNm, emsg;
    private double cost, salvage;
    private int life;

    public Asset(String nm, double c, double s, int lf) {
        this.assetNm = nm;
        this.cost = c;
        this.salvage = s;
        this.life = lf;

    }

    protected boolean isValid() {
        this.emsg = "";
        if (this.assetNm.isEmpty()) {
            this.emsg += "Missing Asset Name. ";
        }

        if (this.cost <= 0) {
            this.emsg += "Cost must be positive. ";

        }
        if (this.salvage < 0) {
            this.emsg += "Salvage cannot be negative. ";
        }
        if (this.salvage >= cost) {
            this.emsg += "Salvage must be less than cost. ";
        }
        if (this.life <= 0) {
            this.emsg += "Life must be positive. ";
        }

        return this.emsg.isEmpty();

    }

    //encapsulate globals
    public String getAssetName() {
        return this.assetNm;
    }

    public double getCost() {
        return this.cost;
    }

    public double getSalvage() {
        return this.salvage;
    }

    public int getLife() {
        return this.life;
    }

    //bye bye build-calculates arrays.Thats now done in individual subclass
    public String getErrorMsg() {
        return this.emsg;
    }

    protected void setErrorMsg(String em) {
        this.emsg = em;
    }

    //ok now lets declare abstract methods
    //abstract methods have NO body
    //will be developed in subclass
    abstract public double getBegBal(int yr);
    abstract public double getAnnDep(int yr);
    abstract public double getEndBal(int yr);

//buildok()
    //yearok()
    //begbal,anndep,endbal= all in subclass now
}
