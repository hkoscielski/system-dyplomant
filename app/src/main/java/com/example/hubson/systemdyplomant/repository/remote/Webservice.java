package com.example.hubson.systemdyplomant.repository.remote;

import android.arch.lifecycle.LiveData;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.GraduateResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoinedResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectStatusResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SupervisorResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Webservice {

    @GET("graduate/read.php")
    LiveData<ApiResponse<GraduateResponse>> getGraduates();

    @GET("subject/read.php")
    LiveData<ApiResponse<SubjectResponse>> getSubjects();

    @GET("subject/read_one.php")
    LiveData<ApiResponse<SubjectResponse>> getSubject(@Query("id_subject") int idSubject);

    @GET("subject_joined/read.php")
    LiveData<ApiResponse<SubjectJoinedResponse>> getSubjectsJoined();

    @GET("subject_status/read.php")
    LiveData<ApiResponse<SubjectStatusResponse>> getSubjectStatuses();

    @GET("supervisor/read_one.php")
    LiveData<ApiResponse<SupervisorResponse>> getSupervisor(@Query("id_supervisor") int idSupervisor);
}
