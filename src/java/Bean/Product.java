
package Bean;

import java.io.Serializable;

public class Product implements Serializable
{
    private String PName, PDesc, PCat;
    private String PPrice;
    private String PReviewsNum;
    private String PRating;
    private String PImageName;

    public Product(String PName, String PDesc, String PCat, String PPrice, String PReviewsNum, String PRating, String PImageName) {
        this.PName = PName;
        this.PDesc = PDesc;
        this.PCat = PCat;
        this.PPrice = PPrice;
        this.PReviewsNum = PReviewsNum;
        this.PRating = PRating;
        this.PImageName = PImageName;
    }

    public String getPName() {
        return PName;
    }

    public String getPDesc() {
        return PDesc;
    }

    public String getPCat() {
        return PCat;
    }

    public String getPPrice() {
        return PPrice;
    }

    public String getPReviewsNum() {
        return PReviewsNum;
    }

    public String getPRating() {
        return PRating;
    }

    public String getPImageName() {
        return PImageName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public void setPDesc(String PDesc) {
        this.PDesc = PDesc;
    }

    public void setPCat(String PCat) {
        this.PCat = PCat;
    }

    public void setPPrice(String PPrice) {
        this.PPrice = PPrice;
    }

    public void setPReviewsNum(String PReviewsNum) {
        this.PReviewsNum = PReviewsNum;
    }

    public void setPRating(String PRating) {
        this.PRating = PRating;
    }

    public void setPImageName(String PImageName) {
        this.PImageName = PImageName;
    }
    
    
    
}
