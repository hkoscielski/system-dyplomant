package com.example.hubson.systemdyplomant.repository.remote.response_model;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import retrofit2.Response;

/**
 * Klasa reprezentująca odpowiedź HTTP oraz REST API na żądania.
 * @param <T> Typ dla zdeserializowanego ciała odpowiedzi
 */
public class ApiResponse<T> {
    /**
     * Kod odpowiedzi HTTP
     */
    public final int code;

    /**
     * Zdeserializowane ciało odpowiedzi
     */
    @Nullable
    public final T body;

    /**
     * Wiadomość dotycząca błędu
     */
    @Nullable
    public final String errorMessage;

    /**
     * Konstruktor do tworzenia obiektów odpowiedzi w przypadku,
     * gdy nastąpi błąd połączenia z REST API lub wystąpi inny nieoczekiwany wyjątek.
     * @param error wyjątek, który zawiera informacje dotyczące niepomyślnej realizacji żądania
     */
    public ApiResponse(Throwable error) {
        code = 500;
        body = null;
        errorMessage = error.getMessage();
    }

    /**
     * Konstruktor do tworzenia obiektów odpowiedzi w przypadku,
     * gdy odpowiedź REST API będzie poprawna. Oznacza to więc, że samo żądanie może zostać zrealizowane niepomyślnie.
     * @param response
     */
    public ApiResponse(Response<T> response) {
        code = response.code();
        if(response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Log.e("ApiResponse", "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
    }

    /**
     * Informuje czy odpowiedź jest pomyślna, tzn czy kod odpowiedzi HTTP pochodzi z przedziału [200..300).
     * @return informacja o pomyślności odpowiedzi
     */
    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
