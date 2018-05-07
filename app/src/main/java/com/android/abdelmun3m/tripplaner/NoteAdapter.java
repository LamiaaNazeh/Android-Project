package com.android.abdelmun3m.tripplaner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abdelmun3m on 04/04/18.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{


    ArrayList<String> notes;
    Context context ;


    public NoteAdapter(Context context){

            this.context = context;
    }

    public void addNewNote(String note){

        if(notes == null){
            notes = new ArrayList<>();
        }
        notes.add(note);
        notifyDataSetChanged();

    }
    NoteViewHolder holder;
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =   inflater.inflate(R.layout.note_list_item,parent,false);
        holder = new NoteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return notes != null? notes.size():0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{


        TextView noteText;
        ImageView btnDelete;
        public NoteViewHolder(final View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.tv_note_text);
            btnDelete = itemView.findViewById(R.id.btn_delete_note);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notes.remove(getLayoutPosition());
                    NoteAdapter.this.notifyDataSetChanged();
                }
            });
        }


        public void bind(int position){
            noteText.setText(notes.get(position));
        }
    }



}
