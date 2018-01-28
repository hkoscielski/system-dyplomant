package com.example.hubson.systemdyplomant.view.subjects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder> {
    private List<Subject> subjects;

    public SubjectListAdapter(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public SubjectListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubjectListAdapter.ViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.textSubject.setText(subject.getSubjectPl());
        holder.textTakenUp.setText(subject.getTakenUp() + "/" + subject.getLimit());
//        holder.textSubjectStatus.setText(subject.getSubjectStatus().getStatusName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setData(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_subject) TextView textSubject;
        @BindView(R.id.text_supervisor) TextView textSupervisor;
        @BindView(R.id.text_subject_status) TextView textSubjectStatus;
        @BindView(R.id.text_taken_up) TextView textTakenUp;
        @BindView(R.id.text_creators) TextView textCreators;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
