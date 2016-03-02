package com.swu.openswu.jwxt;

import com.swu.openswu.utils.Client;

/**
 * Created by csd on 2016/1/30.
 */
public class SwuGrades {

    //    private Client client ;
    private GradesHelper gradesHelper;
    // 预留
    private GradeHandler gradeHandler;


    public SwuGrades(Client client) {
//        this.client = client;
        this.gradesHelper = new GradesHelper(client);
    }

    public String lookup(SearchParam searchParam) {

        return this.gradesHelper.lookup(searchParam);
    }




}
