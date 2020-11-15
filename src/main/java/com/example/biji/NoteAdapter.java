package com.example.biji;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements Filterable{
    private Context mContext;

    private List<Note>noteList;
    private List<Note>backList;
    private MyFilter mFilter;

    //@Override
    public int getCount() {
        return noteList.size();
    }

    //@Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   // @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View. inflate(mContext,R.layout.note_layout,null);
        TextView tv_content = (TextView)v.findViewById(R.id.tv_content);
        TextView tv_time = (TextView)v.findViewById(R.id.tv_time);
        String allText = noteList.get(position).getContent();

        tv_content.setText(allText);
        tv_time.setText(noteList.get(position).getTime());

        v.setTag(noteList.get(position).getId());
        return v;
    }

    @Override
    public Filter getFilter() {
        if(mFilter == null){
            mFilter = new MyFilter();
        }
        return mFilter;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        View noteView;
       // TextView noteId;
        TextView noteContent;
        TextView noteTime;
        TextView noteTag;

        public ViewHolder(@NonNull View view) {
            super(view);
            noteView = view;
            noteContent=(TextView) view.findViewById(R.id.tv_content);
            noteTime=(TextView) view.findViewById(R.id.tv_time);
            noteTag=(TextView) view.findViewById(R.id.tv_tag);
        }
    }

    public NoteAdapter(List<Note> noteList) {
        //this.mContext = mContext;
        this.noteList = noteList;
        backList = noteList;
    }

    @NonNull
    //@Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Note note = noteList.get(position);
            }
        });
        return holder;
    }

    //@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteContent.setText(note.getContent());
        holder.noteTime.setText(note.getTime());
        holder.noteTag.setText(note.getTag());
    }

    //@Override
    public int getItemCount() {
        return noteList.size();
    }

    class MyFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();
            List<Note>list;
            if(TextUtils.isEmpty(charSequence)){
                list = backList;
            }else{
                list = new ArrayList<>();
                for (Note note:backList){
                    if(note.getContent().contains(charSequence)){
                        list.add(note);
                    }
                }
            }
            result.values = list;
            result.count = list.size();
            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            noteList = (List<Note>)filterResults.values;
            if(filterResults.count>0){
                notifyDataSetChanged();
            }else {
               //notifyDataSetInvalidated();
            }
        }
    }




}


