package cn.swu.edu.opensource.openswu_webapi_jersey.schedule;

import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/8.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * javabean：从教务系统获取的课程表对象。
 */
public class Schedule {
    private List<kbList> kbList;

    public static class kbList{
        //课程名称
        private String kcmc;
        //总长度（课程的长度,如'1-18周'）
        private String zcd;
        //节次数
        private String jcs;
        //星期几
        private String xqj;
        //学区名称
        private String xqmc;
        //教师姓名
        private String xm;
        //上课场地（地点）
        private String cdmc;

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getZcd() {
            return zcd;
        }

        public void setZcd(String zcd) {
            this.zcd = zcd;
        }

        public String getJcs() {
            return jcs;
        }

        public void setJcs(String jcs) {
            this.jcs = jcs;
        }

        public String getXqj() {
            return xqj;
        }

        public void setXqj(String xqj) {
            this.xqj = xqj;
        }

        public String getXqmc() {
            return xqmc;
        }

        public void setXqmc(String xqmc) {
            this.xqmc = xqmc;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getCdmc() {
            return cdmc;
        }

        public void setCdmc(String cdmc) {
            this.cdmc = cdmc;
        }
    }

    public List<Schedule.kbList> getKbList() {
        return kbList;
    }

    public void setKbList(List<Schedule.kbList> kbList) {
        this.kbList = kbList;
    }
}
