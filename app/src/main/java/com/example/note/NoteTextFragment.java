package com.example.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteTextFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_DATE = "date";
    private static final String ARG_DESCRIPTION = "description";

    Note note;
    private TextView textViewNoteName;
    private TextView textViewNoteDate;
    private TextView textViewNoteDescription;

    public NoteTextFragment() {
    }

    public static NoteTextFragment newInstance(Note note) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, note.getName());
        args.putString(ARG_DATE, note.getDate());
        args.putString(ARG_DESCRIPTION, note.getDescription());

        NoteTextFragment fragment = new NoteTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_NAME);
            String date = getArguments().getString(ARG_DATE);
            String description = getArguments().getString(ARG_DESCRIPTION);

            note = new Note(name, date, description);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_text, container, false);
        textViewNoteName = view.findViewById(R.id.textViewForName);
        textViewNoteDate = view.findViewById(R.id.textViewForDate);
        textViewNoteDescription = view.findViewById(R.id.textViewForDesc);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateText();
    }

    private void updateText() {
        textViewNoteName.setText(note.getName());
        textViewNoteDate.setText(note.getDate());
        textViewNoteDescription.setText(note.getDescription());
    }
}