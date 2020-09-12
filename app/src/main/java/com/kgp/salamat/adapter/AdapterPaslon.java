package com.kgp.salamat.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.InputSuaraActivity;
import com.kgp.salamat.R;
import com.kgp.salamat.model.PaslonItem;
import com.kgp.salamat.model.RelawanItem;
import com.kgp.salamat.model.ResponseListPaslon;

import java.util.ArrayList;
import java.util.List;

public class AdapterPaslon extends RecyclerView.Adapter<AdapterPaslon.ViewHolder> {

    private final List<RelawanItem> relawanItems = new ArrayList<>();
    private List<PaslonItem> paslonItems;
    private Context context;

    public AdapterPaslon(List<PaslonItem> paslonItems, Context context){
        this.paslonItems = paslonItems;
        this.context = context;

    }

    @NonNull
    @Override
    public AdapterPaslon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.relawan_listpaslon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNoPaslon.setText(String.valueOf(paslonItems.get(position).getNo_paslon()));
        holder.tvNamaPaslon.setText(String.valueOf(paslonItems.get(position).getNama_paslon()));

    }

    @Override
    public int getItemCount() {
        return paslonItems.size() | relawanItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvNoPaslon, tvNamaPaslon;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            tvNamaPaslon = itemView.findViewById(R.id.tv_relawannamaPaslon);
            tvNoPaslon = itemView.findViewById(R.id.tv_relawannomorPaslon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, InputSuaraActivity.class);
                        intent.putExtra("nama_paslon", paslonItems.get(pos).getNama_paslon());
                        intent.putExtra("no_paslon", paslonItems.get(pos).getNo_paslon());
//                        intent.putExtra("nik", relawanItems.get(pos).getNik());
//                        intent.putExtra("nama_lengkap", relawanItems.get(pos).getNamaLengkap());
//                        intent.putExtra("tps", relawanItems.get(pos).getTps());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }
                }
            });
        }
    }
}
