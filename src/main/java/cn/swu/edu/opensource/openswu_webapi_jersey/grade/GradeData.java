package cn.swu.edu.opensource.openswu_webapi_jersey.grade;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/1/6.
 *
 * 用于Gson解析json数据的 POJO
 */

public class GradeData {
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class Items {
        private String cj;
        private String jd;
        private String kcmc;
        private String xf;
        private String kcxzmc;

        public String getCj() {
            return cj;
        }

        public void setCj(String cj) {
            this.cj = cj;
        }

        public String getJd() {
            return jd;
        }

        public void setJd(String jd) {
            this.jd = jd;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getXf() {
            return xf;
        }

        public void setXf(String xf) {
            this.xf = xf;
        }

        public String getKcxzmc() {
            return kcxzmc;
        }

        public void setKcxzmc(String kcxzmc) {
            this.kcxzmc = kcxzmc;
        }
    }

}
