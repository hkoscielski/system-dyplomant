package com.example.hubson.systemdyplomant.repository.remote;

import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Webservice {
    @GET("subject/read.php")
    Call<SubjectResponse> getSubjects();
}
