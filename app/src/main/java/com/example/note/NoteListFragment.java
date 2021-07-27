package com.example.note;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {
    private ArrayList<Note> notes;

    private boolean isLand = false;

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setReenterTransition(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        initView(view);
    }

    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        createNote();
        for (int i = 0; i < notes.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setText(notes.get(i).getName());
            textView.setTextSize(25);
            linearLayout.addView(textView);

            final int finalI = i;
            textView.setOnClickListener(v -> showNote(notes.get(finalI)));
        }
    }

    private void showNote(Note note) {
        if (isLand) {
            showNoteLand(note);
        } else {
            showNotePort(note);
        }
    }

    public void createNote() {
        notes = new ArrayList<>();
        notes.add(new Note("Первая", "12.04.2021", "Тут текст первой заметки"));
        notes.add(new Note("Вторая", "13.04.2021", "Во второй заметке текст интереснее"));
        notes.add(new Note("Третья", "14.04.2021", "Третья заметка так себе"));
    }

    void showNoteLand(Note note) {
        Fragment noteFragment = NoteTextFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.landscapeContainer, noteFragment)
                .commit();
    }

    void showNotePort(Note note) {
        Fragment noteFragment = NoteTextFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerNoteList, noteFragment)
                .commit();
    }
}