package com.example.contacts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.MainActivity;
import com.example.contacts.R;
import com.example.contacts.data.saveData;
import com.example.contacts.database.DBHandler;

import java.util.List;

public class Recycleviewadapter1 extends RecyclerView.Adapter<Recycleviewadapter1.viewHolder> {
    List<saveData> list;
    DBHandler dbHandler;
    Context context;

    public Recycleviewadapter1(List<saveData> list, DBHandler dbHandler, Context context) {
        this.list = list;
        this.dbHandler = dbHandler;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycleviewadapter1.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row1,parent,false);
        return new Recycleviewadapter1.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycleviewadapter1.viewHolder holder, int position) {
        saveData data=list.get(position);
        holder.tName.setText(data.getName());
        holder.tNumber.setText(data.getNumber());
        holder.bRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.moveFromTrash(data.getId());
                Toast.makeText(context, "Restored", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(context, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(i);
            }
        });
        holder.bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteSpecificContactTrash(data.getId());
                Toast.makeText(context, "Deleted Forever", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tName,tNumber;
        public Button bRestore,bDelete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tName=itemView.findViewById(R.id.trashName);
            tNumber=itemView.findViewById(R.id.trashNumber);
            bRestore=itemView.findViewById(R.id.trashRestore);
            bDelete=itemView.findViewById(R.id.trashDelete);


        }

        @Override
        public void onClick(View view) {

        }
    }
}
