package com.example.hubson.systemdyplomant.repository.remote;

import android.arch.lifecycle.LiveData;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.GraduateResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoinedResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectStatusResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Webservice {

    @GET("graduate/read.php")
    LiveData<ApiResponse<GraduateResponse>> getGraduates();

    @GET("subject/read.php")
    LiveData<ApiResponse<SubjectResponse>> getSubjects();

    @GET("subject_joined/read.php")
    LiveData<ApiResponse<SubjectJoinedResponse>> getSubjectsJoined();

    @GET("subject_status/read.php")
    LiveData<ApiResponse<SubjectStatusResponse>> getSubjectStatuses();
}
