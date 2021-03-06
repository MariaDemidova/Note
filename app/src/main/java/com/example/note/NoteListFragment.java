package com.example.note;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Calendar;
import java.util.List;

public class NoteListFragment extends Fragment {
    static NoteSource noteSource;
    NotesAdapter adapter;
    private boolean isLand = false;
    RecyclerView recyclerView;

    private Navigation navigation;
    private Publisher publisher;
    private boolean moveToFirstPosition;

    public static NoteListFragment newInstance(NoteSource noteSource) {
        NoteListFragment.noteSource = noteSource;
        return new NoteListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        setHasOptionsMenu(true);
        initView(view);

        noteSource = new NoteSourceFirebaseImpl().init(NoteSource -> adapter.notifyDataSetChanged());
        adapter.setDataSource(noteSource);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        initView(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cards_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return onItemSelected(item.getItemId()) || super.onOptionsItemSelected(item);
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_view);
        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotesAdapter(this);
        adapter.setList(noteSource);
        recyclerView.setAdapter(adapter);
        adapter.setListener(this::showNote);

        if (moveToFirstPosition && noteSource.size() > 0) {
            recyclerView.scrollToPosition(0);
            moveToFirstPosition = false;
        }
    }

    private void showNote(int position) {
        if (isLand) {
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

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return onItemSelected(item.getItemId()) || super.onContextItemSelected(item);
    }

    private boolean onItemSelected(int menuItemId) {
        if (menuItemId == R.id.menu_add) {
            navigation.addFragment(NoteFragment.newInstance(), true);
            publisher.subscribe(note -> {
                noteSource.addCardData(note);
                adapter.notifyItemInserted(noteSource.size() - 1);
                moveToFirstPosition = true;
            });
            return true;
        }
        if (menuItemId == R.id.update) {
            final int updatePosition = adapter.getMenuPosition();
            navigation.addFragment(NoteFragment.newInstance(noteSource.getNote(updatePosition)), true);
            publisher.subscribe(note -> {
                noteSource.updateCardData(updatePosition, note);
                adapter.notifyItemChanged(updatePosition);
            });
            return true;
        }
        if (menuItemId == R.id.delete) {
            int deletePosition = adapter.getMenuPosition();
            noteSource.deleteCardData(deletePosition);
            adapter.notifyItemRemoved(deletePosition);
            return true;
        }
        if (menuItemId == R.id.menu_clear) {
            noteSource.clearCardData();
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }

}