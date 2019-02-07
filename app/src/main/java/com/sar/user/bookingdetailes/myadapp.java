package com.sar.user.bookingdetailes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class myadapp extends RecyclerView.Adapter<myadapp.ViewHolder> {
    private List<listitem> listitems;
    private Context context;
    public myadapp(List<listitem> listitems, Context context) {
        this.listitems = listitems;

        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listp,viewGroup,false);
        Log.i("firsttttt","j");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        listitem listItem=listitems.get(i);
        viewHolder.head.setText(listItem.getHead());
        viewHolder.des.setText(listItem.getDes());



    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView head;
        public  TextView des,time,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head=(TextView)itemView.findViewById(R.id.head);
            des=(TextView)itemView.findViewById(R.id.des);
            time=itemView.findViewById(R.id.textView);
            status=itemView.findViewById(R.id.textView2);

        }

    }
}
