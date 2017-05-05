package cn.swu.edu.opensource.openswu_webapi_jersey.ecard;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/27.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class EcardInformation {

    private String time;
    private String type;
    private String frequency;
    private String moneyBeforeTrade;
    private String tradeMoney;
    private String moneyAfterTrad;
    private String place;

    public EcardInformation(String time, String type, String frequency, String moneyBeforeTrade, String tradeMoney, String moneyAfterTrad, String place) {
        this.time = time;
        this.type = type;
        this.frequency = frequency;
        this.moneyBeforeTrade = moneyBeforeTrade;
        this.tradeMoney = tradeMoney;
        this.moneyAfterTrad = moneyAfterTrad;
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getMoneyBeforeTrade() {
        return moneyBeforeTrade;
    }

    public void setMoneyBeforeTrade(String moneyBeforeTrade) {
        this.moneyBeforeTrade = moneyBeforeTrade;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getMoneyAfterTrad() {
        return moneyAfterTrad;
    }

    public void setMoneyAfterTrad(String moneyAfterTrad) {
        this.moneyAfterTrad = moneyAfterTrad;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
