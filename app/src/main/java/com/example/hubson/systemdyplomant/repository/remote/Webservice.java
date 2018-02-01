package com.example.hubson.systemdyplomant.repository.remote;

import android.arch.lifecycle.LiveData;

import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;
import com.example.hubson.systemdyplomant.repository.local.entity.DeclarationStatus;
import com.example.hubson.systemdyplomant.repository.local.entity.FormOfStudies;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.repository.remote.response_model.ApiResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.DeclarationStatusResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.GraduateResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.PostResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoinedResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectStatusResponse;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SupervisorResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Webservice {

    @POST("declaration/create.php")
    LiveData<ApiResponse<PostResponse>> createDeclaration(@Body Declaration declaration);

    @POST("subject/update.php")
    LiveData<ApiResponse<PostResponse>> updateSubject(@Body Subject subject);

    @POST("graduate/update.php")
    LiveData<ApiResponse<PostResponse>> updateGraduate(@Body Graduate graduate);

    @GET("graduate/read.php")
    LiveData<ApiResponse<GraduateResponse>> getGraduates();

    @GET("graduate/read_one.php")
    LiveData<ApiResponse<GraduateResponse>> getGraduate(@Query("id_graduate") int idGraduate);

    @GET("form_of_studies/read_one.php")
    LiveData<ApiResponse<FormOfStudies>> getFormOfStudies(@Query("id_form") int idForm);

    @GET("subject/read.php")
    LiveData<ApiResponse<SubjectResponse>> getSubjects();

    @GET("subject/read_one.php")
    LiveData<ApiResponse<Subject>> getSubject(@Query("id_subject") int idSubject);

    @GET("subject_joined/read.php")
    LiveData<ApiResponse<SubjectJoinedResponse>> getSubjectsJoined();

    @GET("subject_status/read.php")
    LiveData<ApiResponse<SubjectStatusResponse>> getSubjectStatuses();

    @GET("subject_status/read_one.php")
    LiveData<ApiResponse<SubjectStatus>> getSubjectStatus(@Query("status_name") String statusName);

    @GET("supervisor/read_one.php")
    LiveData<ApiResponse<SupervisorResponse>> getSupervisor(@Query("id_supervisor") int idSupervisor);

    @GET("declaration_status/read_one.php")
    LiveData<ApiResponse<DeclarationStatus>> getDeclarationStatus(@Query("status_name") String statusName);

    @GET("declaration_status/read.php")
    LiveData<ApiResponse<DeclarationStatusResponse>> getDeclarationStatuses();
}
