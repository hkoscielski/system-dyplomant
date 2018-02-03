package com.example.hubson.systemdyplomant.view.subjects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.repository.local.entity.Supervisor;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Klasa umożliwiająca powiązanie danych o tematach prac dyplomowych z odpowiednimi elementami listy.
 */
public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder> implements Filterable {
    /**
     * Lista tematów prac dyplomowych wraz z powiązanymi z nimi dyplomantami, promotorami oraz statusami
     */
    private List<SubjectJoined> subjects;

    /**
     * Przefiltrowana lista tematów prac dyplomowych wraz z powiązanymi z nimi dyplomantami, promotorami oraz statusami
     */
    private List<SubjectJoined> filteredSubjects;

    /**
     * Wywołanie zwrotne zachodzące podczas wyboru tematu pracy dyplomowej
     */
    private final SubjectListCallback subjectListCallback;

    /**
     * Tworzy obiekt adaptera o określonym wywołaniu zwrotnym dla wybory pracy dyplomowej
     * @param subjectListCallback wywołanie zwrotne zachodzące podczas wyboru tematu pracy dyplomowej
     */
    public SubjectListAdapter(@NonNull SubjectListCallback subjectListCallback) {
        this.subjects = new ArrayList<>();
        this.filteredSubjects = this.subjects;
        this.subjectListCallback = subjectListCallback;
    }

    /**
     * Metoda wywoływana w momencie gdy RecyclerView potrzebuje nowego obiektu typu ViewHolder
     * do przechowania referencji dla widoków, które posłużą do wyświetlenia kolejnego elementu.
     * @param parent Grupa widoków, do której nowy widok zostanie dodany
     * @param viewType Typ nowego widoku
     * @return Nowy obiekt ViewHolder przechowujący referencje do widoków określonego typu
     */
    @Override
    public SubjectListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Metoda wywoływana w momencie, gdy RecyclerView chce wyświetlić dane na określonej pozycji w widokach
     * przechowywanych w przekazywanym ViewHolderze.
     * @param holder ViewHolder, którego widoki powinny zostać zaktualizowane nowymi danymi
     * @param position Pozycja elementu listy
     */
    @Override
    public void onBindViewHolder(SubjectListAdapter.ViewHolder holder, int position) {
        SubjectJoined subjectJoined = filteredSubjects.get(position);
        SubjectStatus subjectStatus = subjectJoined.getSubjectStatus();
        Supervisor supervisor = subjectJoined.getSupervisor();

        holder.textSubject.setText(subjectJoined.getSubjectPl());
        holder.textTakenUp.setText(String.format("%s/%s", subjectJoined.getTakenUp(), subjectJoined.getLimit()));
        holder.textSubjectStatus.setText(subjectStatus != null ? subjectStatus.getStatusName() : "nie udało sie");
        holder.textSupervisor.setText(supervisor != null ? supervisor.getAcademicTitle() + " " + supervisor.getName() + " " + supervisor.getSurname() : "brak");
        StringBuilder creators = new StringBuilder();
        for(Graduate graduate : subjectJoined.getGraduates()) {
            creators.append(graduate.getName()).append(" ").append(graduate.getSurname()).append(" ").append(graduate.getStudentNo()).append("\n");
        }
        holder.textCreators.setText(creators.toString());
        holder.btnChoose.setEnabled(subjectJoined.getTakenUp() != subjectJoined.getLimit());
        holder.btnChoose.setOnClickListener(v -> subjectListCallback.onSubjectClicked(subjectJoined));
    }

    /**
     * Metoda zwracająca liczbę wszystkich elementów listy, mających zostać wyświetlonych.
     * @return liczba elementów listy do wyświetlenia
     */
    @Override
    public int getItemCount() {
        return filteredSubjects.size();
    }

    /**
     * Metoda ustawiająca nowe dane do wyświetlenia.
     * @param subjects nowe dane o tematach prac dyplomowych
     */
    public void setData(List<SubjectJoined> subjects) {
        this.subjects = subjects;
        this.filteredSubjects = subjects;
        notifyDataSetChanged();
    }

    /**
     * Metoda powiadamiająca adapter, że zajęte tematy prac dyplomowych mają zostać ukryte.
     */
    public void hideTakenUp() {
        List<SubjectJoined> result = new ArrayList<>();
        for(SubjectJoined subjectJoined : filteredSubjects) {
            if(subjectJoined.getTakenUp() != subjectJoined.getLimit()) {
                result.add(subjectJoined);
            }
        }
        filteredSubjects = result;
        notifyDataSetChanged();
    }

    /**
     * Metoda powiadamiająca adapter, że wszystkie możliwe tematy prac dyplomowych mają zostać wyświetlone.
     */
    public void showAllSubjects() {
        filteredSubjects = subjects;
        notifyDataSetChanged();
    }

    /**
     * Metoda powiadamiająca adapter, że dane tematów prac dyplomowych mają zostać posortowane według nazwy tematu.
     */
    public void sortBySubjectName() {
        Collections.sort(filteredSubjects, (s1, s2) -> s1.getSubjectPl().compareTo(s2.getSubjectPl()));
        notifyDataSetChanged();
    }

    /**
     * Metoda powiadamiająca adapter, że dane tematów prac dyplomowych mają zostać posortowane według nazwiska promotora.
     */
    public void sortBySupervisorSurname() {
        Collections.sort(filteredSubjects, (s1, s2) -> s1.getSupervisor().getSurname().compareTo(s2.getSupervisor().getSurname()));
        notifyDataSetChanged();
    }

    /**
     * Metoda zwracająca nowy obiekt klasy <code>Filter</code>, który pozwala na przefiltrowanie wszystkich tematów
     * w oparciu o określony wzorzec.
     * @return
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    filteredSubjects = subjects;
                } else {
                    List<SubjectJoined> filteredList = new ArrayList<>();
                    for(SubjectJoined subjectJoined : subjects) {
                        if(subjectJoined.getSubjectPl().toLowerCase().contains(charString.toLowerCase())
                                || subjectJoined.getSupervisor().getName().toLowerCase().contains(charString.toLowerCase())
                                || subjectJoined.getSupervisor().getSurname().toLowerCase().contains((charString.toLowerCase()))) {
                            filteredList.add(subjectJoined);
                        }
                    }

                    filteredSubjects = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredSubjects;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredSubjects = (ArrayList<SubjectJoined>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    /**
     * Klasa przechowująca referencje do widoków dla pojedynczego elementu listy.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_subject) TextView textSubject;
        @BindView(R.id.text_supervisor) TextView textSupervisor;
        @BindView(R.id.text_subject_status) TextView textSubjectStatus;
        @BindView(R.id.text_taken_up) TextView textTakenUp;
        @BindView(R.id.text_creators) TextView textCreators;
        @BindView(R.id.btn_choose) Button btnChoose;


        /**
         * Tworzy nowy obiekt ViewHolder.
         * @param itemView Referencja do widoku, który będzie rodzicem dla określonych we ViewHolderze widoków.
         */
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
