package com.swu.openswu.jwxt;

import com.swu.openswu.utils.Client;

/**
 * Created by csd on 2016/1/30.
 */
public class SwuGrades {

    //    private Client client ;
    private GradesLookuper gradesLookuper;
    // 预留
    private GradeHandler gradeHandler;


    public SwuGrades(Client client) {
//        this.client = client;
        this.gradesLookuper = new GradesLookuper(client);
    }

    public String lookup(SearchParam searchParam) {

        return this.gradesLookuper.lookup(searchParam);
    }




}
