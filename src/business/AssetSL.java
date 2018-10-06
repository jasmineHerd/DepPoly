package business;

/**
 *
 * @author jasmineherd
 */
//we extend an abstract so implement those methods
public class AssetSL extends Asset {

    private double[] begbal, endbal;
    private double anndep;
    private boolean built;

    public AssetSL(String nm, double c, double s, int lf) {
        //recieve values from form
        //call Asset.Subclass needs to call super=first thing
        super(nm, c, s, lf);//send up those variables
        if (super.isValid()) {
            build();
        }
    }

    private void build() {
        try {
            this.begbal = new double[super.getLife()];
            this.endbal = new double[super.getLife()];

            this.anndep = (super.getCost() - super.getSalvage()) / super.getLife();

            this.begbal[0] = super.getCost();

            for (int i = 0; i < super.getLife(); i++) {
                if (i > 0) {
                    this.begbal[i] = this.endbal[i - 1];
                }
                this.endbal[i] = this.begbal[i] - this.anndep;

                built = true;
            }
        } catch (Exception e) {
            this.built = false;
            super.setErrorMsg("SL Exception during build " + e.getMessage());
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
        return this.anndep;

    }

    /**
     *
     * @param yr
     * @return
     */
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
}
