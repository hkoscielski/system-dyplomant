package com.example.hubson.systemdyplomant.view.subjects;

import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;

/**
 * Interfejs wykorzystywany przy komunikacji między adapterem a aktywnością
 * odpowiedzialną za wyświetlenie listy tematów prac dyplomowych.
 */
public interface SubjectListCallback {
    /**
     * Metoda wywoływana w momencie kliknięcia na przycisk wyboru tematu z listy.
     * @param subjectJoined wybrany temat pracy inżynierskiej wraz z powiązanymi z nim jego statusem,
     *                      prowadzącym go promotorem oraz realizującymi go dyplomantami
     */
    void onSubjectClicked(SubjectJoined subjectJoined);
}
