package com.example.recode_final_project.service;

import com.example.recode_final_project.dto.CourseDTO;
import com.example.recode_final_project.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CourseService {

    @GET("courses")
    Call<List<Course>> getCourses();

    @GET("courses/{id}")
    Call<Course> getCourseById(@Path("id") int id);

    @POST("courses/")
    Call<Course> createCourse(@Body CourseDTO course);

    @PUT("courses/{id}")
    Call<Course> updateCourse(@Path("id") int id, @Body Course course);

    @DELETE("courses/{id}")
    Call<Void> deleteCourse(@Path("id") int id);

}