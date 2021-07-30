package com.example.note;

import android.content.Context;

public class NoteSourceImpl implements NoteSource {

    private final Context context;
    private Note[] notes;

    public NoteSourceImpl(Context context) {
        this.context = context;

        notes = new Note[]{
                new Note(context.getResources().getString(R.string.name1),
                        context.getResources().getString(R.string.date1),
                        context.getResources().getString(R.string.description1)
                ),
                new Note(context.getResources().getString(R.string.name2),
                        context.getResources().getString(R.string.date2),
                        context.getResources().getString(R.string.description2)
                ),
                new Note(context.getResources().getString(R.string.name3),
                        context.getResources().getString(R.string.date3),
                        context.getResources().getString(R.string.description3)
                ),
                new Note(context.getResources().getString(R.string.name4),
                        context.getResources().getString(R.string.date4),
                        context.getResources().getString(R.string.description4)
                ),
                new Note(context.getResources().getString(R.string.name5),
                        context.getResources().getString(R.string.date5),
                        context.getResources().getString(R.string.description5)
                ),
                new Note(context.getResources().getString(R.string.name6),
                        context.getResources().getString(R.string.date6),
                        context.getResources().getString(R.string.description6)
                ),
                new Note(context.getResources().getString(R.string.name7),
                        context.getResources().getString(R.string.date7),
                        context.getResources().getString(R.string.description7)
                ),
                new Note(context.getResources().getString(R.string.name8),
                        context.getResources().getString(R.string.date8),
                        context.getResources().getString(R.string.description8)
                ),
                new Note(context.getResources().getString(R.string.name9),
                        context.getResources().getString(R.string.date9),
                        context.getResources().getString(R.string.description9)
                ),
                new Note(context.getResources().getString(R.string.name10),
                        context.getResources().getString(R.string.date10),
                        context.getResources().getString(R.string.description10)
                ),
                new Note(context.getResources().getString(R.string.name11),
                        context.getResources().getString(R.string.date11),
                        context.getResources().getString(R.string.description11)
                ),
                new Note(context.getResources().getString(R.string.name12),
                        context.getResources().getString(R.string.date12),
                        context.getResources().getString(R.string.description12)
                ),
                new Note(context.getResources().getString(R.string.name13),
                        context.getResources().getString(R.string.date13),
                        context.getResources().getString(R.string.description13)
                ),
                new Note(context.getResources().getString(R.string.name14),
                        context.getResources().getString(R.string.date14),
                        context.getResources().getString(R.string.description12)
                ),
                new Note(context.getResources().getString(R.string.name15),
                        context.getResources().getString(R.string.date15),
                        context.getResources().getString(R.string.description12)
                ),
                new Note(context.getResources().getString(R.string.name16),
                        context.getResources().getString(R.string.date16),
                        context.getResources().getString(R.string.description12)
                ),
                new Note(context.getResources().getString(R.string.name17),
                        context.getResources().getString(R.string.date17),
                        context.getResources().getString(R.string.description12)
                ),
        };
    }

    @Override
    public Note getNote(int position) {
        return notes[position];
    }

    @Override
    public int size() {
        return notes.length;
    }
}
