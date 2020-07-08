package cards;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CardDetails {

    @Column
    private int counts;
    @Column
    private int use;

    public CardDetails() {
        this.counts = 1;
        this.use = 0;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}
