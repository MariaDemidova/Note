package com.example.note;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteListFragment extends Fragment {
    NoteSource noteSource;
    NotesAdapter adapter;
    private boolean isLand = false;

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void initView(View view ) {

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);

        noteSource = new NoteSourceImpl(getContext());
        adapter = new NotesAdapter();
        adapter.setList(noteSource);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setHasFixedSize(true);

        adapter.setListener(this::showNote);

//        showNote(0);
        Log.d("sdfdsg", String.valueOf(noteSource.size()));
        Log.d("sdfdsg", String.valueOf(adapter.getItemCount()));

    }

    private void showNote(int position) {
        if (isLand) {
            //  showNote(0);
            showNoteLand(position);
        } else {
            showNotePort(position);
        }
    }

    void showNotePort(int position) {
        Fragment noteFragment = NoteTextFragment.newInstance(noteSource.getNote(position));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerNoteList, noteFragment)
                .addToBackStack("NoteTextFragment")
                .commit();
    }

    void showNoteLand(int position) {
        Fragment noteFragment = NoteTextFragment.newInstance(noteSource.getNote(position));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.landscapeContainer, noteFragment)
                .addToBackStack("NoteTextFragment")
                .commit();
    }

}