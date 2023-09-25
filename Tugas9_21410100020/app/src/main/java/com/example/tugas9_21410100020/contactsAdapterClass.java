package com.example.tugas9_21410100020;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class contactsAdapterClass extends RecyclerView.Adapter<contactsAdapterClass.ViewHolder> {

    List<contactsModelClass> contacts;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public contactsAdapterClass(List<contactsModelClass> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contacts_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final contactsModelClass contactsModelClass = contacts.get(position);

        holder.textViewID.setText(Integer.toString(contactsModelClass.getId()));
        holder.editText_Name.setText(contactsModelClass.getName());
        holder.editText_noHP.setText(contactsModelClass.getnoHP());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringnoHP = holder.editText_noHP.getText().toString();

                databaseHelperClass.updatecontacts(new contactsModelClass(contactsModelClass.getId(),stringName,stringnoHP));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deletecontacts(contactsModelClass.getId());
                contacts.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name;
        EditText editText_noHP;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_noHP = itemView.findViewById(R.id.edittext_noHP);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
