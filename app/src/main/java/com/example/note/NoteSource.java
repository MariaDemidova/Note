package com.example.note;

import java.util.List;

public interface NoteSource {
    Note getNote(int position);

    int size();

    void deleteCardData(int position);

    void addCardData(Note note);

    void updateCardData(int Position, Note note);

    void clearCardData();
}
