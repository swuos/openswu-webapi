package cn.swu.edu.opensource.openswu_webapi_jersey.info;

import java.util.List;

/**
 * Created by mxd on 2016/5/18.
 * 返回类，将处理成json格式
 */
public class PersonalInfo {
    //学号
    private String SwuID;

    //姓名
    private String name;

    //性别
    private String gender;

    //身份证号
    private String IDNumber;

    //籍贯
    private String origin;

    //生日
    private String birthday;

    //民族
    private String nation;

    //政治面貌 如共青团员
    private String political;

    //户口所在地
    private String locition;

    //年级
    private String grade;

    //学院
    private String college;

    //专业
    private String major;

    //班级
    private String className;

    //毕业高中
    private String graduation;

    //电话号码
    private String phoneNum;

    //家庭地址  和户口所在地不一样
    private String familyLocation;

    //出生地
    private String birthplace;

    //系  所学的专业是什么系
    private String department;

    //学生类别 英语几级
    private String englishLevel;

    //考生号
    private String examNumber;

    //高考分数
    private String graduationScore;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSwuID() {
        return SwuID;
    }

    public void setSwuID(String swuID) {
        SwuID = swuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getLocition() {
        return locition;
    }

    public void setLocition(String locition) {
        this.locition = locition;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFamilyLocation() {
        return familyLocation;
    }

    public void setFamilyLocation(String familyLocation) {
        this.familyLocation = familyLocation;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(String examNumber) {
        this.examNumber = examNumber;
    }

    public String getGraduationScore() {
        return graduationScore;
    }

    public void setGraduationScore(String graduationScore) {
        this.graduationScore = graduationScore;
    }
}