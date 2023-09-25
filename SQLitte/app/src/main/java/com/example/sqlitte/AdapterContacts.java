package com.example.sqlitte;

import android.annotation.SuppressLint;
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

public class AdapterContacts extends RecyclerView.Adapter<AdapterContacts.ViewHolder> {
    List<ModelContacts> contacts;
    Context context;
    Database database;

    public AdapterContacts(List<ModelContacts> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
        database = new Database(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_contacts,parent,false);
        ViewHolder vHolder = new ViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder vholder, final int position) {
        final ModelContacts modelContacts = contacts.get(position);

        vholder.textViewID.setText(Integer.toString(modelContacts.getId()));
        vholder.editText_Name.setText(modelContacts.getName());
        vholder.editText_noHP.setText(modelContacts.getnoHP());

        vholder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = vholder.editText_Name.getText().toString();
                String stringnoHP = vholder.editText_noHP.getText().toString();

                database.updatecontacts(new ModelContacts(modelContacts.getId(),stringName,stringnoHP));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        vholder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deletecontacts(modelContacts.getId());
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
