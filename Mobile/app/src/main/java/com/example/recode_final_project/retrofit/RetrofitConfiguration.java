package com.example.recode_final_project.retrofit;


import com.example.recode_final_project.service.AllocationService;
import com.example.recode_final_project.service.CourseService;
import com.example.recode_final_project.service.DepartmentService;
import com.example.recode_final_project.service.ProfessorService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfiguration {

    private Retrofit retrofit;

    public RetrofitConfiguration() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://professor-allocation-recodev.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    /*
    CRUD -
     */
    public DepartmentService getDepartmentService() {
        return retrofit.create(DepartmentService.class);
    }

    public CourseService getCourseService() {
        return retrofit.create(CourseService.class);
    }

    public ProfessorService getProfessorService() {
        return retrofit.create(ProfessorService.class);
    }

    public AllocationService getAllocationService() {
        return retrofit.create(AllocationService.class);
    }
}
