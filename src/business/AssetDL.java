package business;

/**
 *
 * @author jasmineherd
 */
public class AssetDL extends Asset {

    private double[] begbal, endbal, anndep;
    private boolean built;
    private double dlfactor;

    public AssetDL(String nm, double c, double s, int lf, double dlf) {
        super(nm, c, s, lf);//dont send dlf to Asset!
        this.dlfactor = dlf;
        if (super.isValid()) {
            build();
        }
    }

    private void build() {
        try {
            this.begbal = new double[super.getLife()];
            this.anndep = new double[super.getLife()];
            this.endbal = new double[super.getLife()];

            double depSL = (super.getCost() - super.getSalvage()) / super.getLife();
            double ddlRate = (1.0 / super.getLife()) * dlfactor;

            this.begbal[0] = super.getCost();

            for (int i = 0; i < super.getLife(); i++) {
                if (i > 0) {
                    this.begbal[i] = this.endbal[i - 1];
                }

                double ddlWork = this.begbal[i] * ddlRate;
                if (ddlWork < depSL) {
                    ddlWork = depSL;
                }
                if ((this.begbal[i] - ddlWork) < super.getSalvage()) {
                    ddlWork = this.begbal[i] - super.getSalvage();
                }
                this.anndep[i] = ddlWork;
                this.endbal[i] = this.begbal[i] - this.anndep[i];
            }

            built = true;

        } catch (Exception e) {
            this.built = false;
            super.setErrorMsg("Exception during build " + e.getMessage());
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
        return this.anndep[yr-1];

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

}
