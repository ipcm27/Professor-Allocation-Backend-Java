package com.example.recode_final_project.service;

import com.example.recode_final_project.dto.AllocationDTO;
import com.example.recode_final_project.model.Allocation;
import com.example.recode_final_project.model.Course;
import com.example.recode_final_project.model.Professor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AllocationService {

    @GET("allocations/")
    Call<List<Allocation>> getAllocations();

    @GET("allocations/{id}")
    Call<Allocation> getAllocationById(@Path("id") int id);

    @GET("allocations/{professor}")
    Call<Allocation> getAllocationByProfessor(@Path("professor") Professor professor);

    @GET("allocations/{course}")
    Call<Allocation> getAllocationByProfessor(@Path("course") Course course);

    @POST("allocations/")
    Call<Allocation> createAllocation(@Body Allocation allocation);

    @PUT("allocations/{id}")
    Call<Allocation> updateAllocation(@Path("id") int id, @Body Allocation allocation);

    @DELETE("allocations/{id}")
    Call<Void> deleteAllocation(@Path("id") int id);


}