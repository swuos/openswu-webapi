package cn.swu.edu.opensource.openswu_webapi_jersey.ecard;

import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Param;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/23.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class EcardParam implements Param {

    private String cardno;
    private String password;


    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }


    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String getSwuID() {
        throw new UnsupportedOperationException();
    }
}
