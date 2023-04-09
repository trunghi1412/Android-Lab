package com.example.contacts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.R;
import com.example.contacts.ViewContact;
import com.example.contacts.data.saveData;

import java.util.ArrayList;
import java.util.List;

public class Recycleviewadapter extends RecyclerView.Adapter<Recycleviewadapter.viewHolder> {
    List<saveData> list;
    Context context;
    boolean isSelectMode=false;
    ArrayList<String> selectedItems;

    public Recycleviewadapter(List<saveData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycleviewadapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycleviewadapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        saveData saveData=list.get(position);
        holder.textView.setText(saveData.getName());
        holder.textView1.setText(saveData.getNumber());
        holder.textView2.setText(saveData.getName().substring(0,1));
        holder.textView3.setText(saveData.getId());
        selectedItems=new ArrayList<>();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectMode==false) {
                    Intent i = new Intent(context, ViewContact.class);
                    i.putExtra("id", list.get(position).getId());
                    i.putExtra("name", list.get(position).getName());
                    i.putExtra("number", list.get(position).getNumber());
                    i.putExtra("description", list.get(position).getDescription());
                    context.startActivity(i);
                }else{
                    if(selectedItems.contains(saveData.getId())){
                        holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                        selectedItems.remove(saveData.getId());
                    }else{
                        holder.itemView.setBackgroundResource(R.color.brown);
                        selectedItems.add(saveData.getId());
                    }
                    if(selectedItems.size()==0){
                        isSelectMode=false;
                    }
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                isSelectMode=true;
                if(selectedItems.contains(saveData.getId())){
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    selectedItems.remove(saveData.getId());
                }else{
                    holder.itemView.setBackgroundResource(R.color.brown);
                    selectedItems.add(saveData.getId());
                }
                if(selectedItems.size()==0){
                    isSelectMode=false;
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public void filterList(ArrayList<saveData> listData) {
        list=listData;
        notifyDataSetChanged();

    }
    public ArrayList<String> getSelectedItems(){
        return selectedItems;
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView,textView1,textView2,textView3;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView2=(TextView) itemView.findViewById(R.id.viewLetter);
            textView=(TextView) itemView.findViewById(R.id.viewName);
            textView1=(TextView) itemView.findViewById(R.id.viewNumber);
            textView3=(TextView) itemView.findViewById(R.id.viewId);
        }

        @Override
        public void onClick(View view) {


        }
    }
}
