package com.example.note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private NoteSource dataSource;
    public static final String TAG = "NoteAdapter";
    private OnItemClickListener clickListener;

    public NotesAdapter() {
    }

    public void setList (NoteSource dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    public void setListener(@NonNull OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.bind(dataSource.getNote(position));

        Log.d(TAG, String.valueOf(dataSource.getNote(position).getName()));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView date;
       // private final TextView description;
        private final CheckBox checkBox;

        TextView textView;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            date = itemView.findViewById(R.id.item_date);
           // description = itemView.findViewById(R.id.descField);
            checkBox = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(v -> {
                clickListener.onItemClick(getAdapterPosition());
            });
        }

        public void bind(@NonNull Note note) {
            name.setText(note.getName());
            date.setText(note.getDate());
       //     description.setText(note.getDescription());

        }
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
}
