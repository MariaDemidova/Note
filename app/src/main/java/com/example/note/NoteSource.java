package com.example.note;

public interface NoteSource {
    NoteSource init(NotesSourceResponse notesSourceResponse);

    Note getNote(int position);

    int size();

    void deleteCardData(int position);

    void addCardData(Note note);

    void updateCardData(int Position, Note note);

    void clearCardData();
}
