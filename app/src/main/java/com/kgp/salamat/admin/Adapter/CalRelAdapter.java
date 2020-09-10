package com.kgp.salamat.admin.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.AdminActivity;
import com.kgp.salamat.admin.Detail.DetailPaslonActivity;
import com.kgp.salamat.admin.Detail.DetailTerimaActivity;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Model.CalRelModel;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.admin.Service.URL;
import com.kgp.salamat.admin.TerimaRelawanActivity;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalRelAdapter extends RecyclerView.Adapter<CalRelAdapter.holder> {
    public static final String DATACALON = "dhSllSks";
    public static final String DATAjEXTRA = "lDDkkl";
    Context context;
    List<CalRelModel>calonrel;
    String id_df,nama;
    ProgressDialog dialog;

    public CalRelAdapter(Context context, List<CalRelModel> calonrel) {
        this.context = context;
        this.calonrel = calonrel;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_terima_relawan, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        CalRelModel dm = calonrel.get(position);
        holder.nama.setText(dm.getNama_lengkap().toString());
        holder.nik.setText(dm.getNik().toString());
        holder.alamat.setText(dm.getAlamat().toString());
        holder.tps.setText(dm.getTps().toString());
        holder.id.setText(dm.getId_daftar().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah=new Intent(context, DetailTerimaActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable(DATACALON, Parcels.wrap(calonrel.get(position)));
                pindah.putExtra(DATAjEXTRA,bundle);
                context.startActivity(pindah);
            }
        });
        holder.btntolakmantan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama=dm.getNama_lengkap();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Toalak Relawan")
                        .setMessage("Tolak "+nama+ " ?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog= new ProgressDialog(context);
                                dialog.setMessage("Loading ...");
                                dialog.setCancelable(false);
                                dialog.show();
                                id_df = dm.getId_daftar();
                                StringRequest stringRequest= new StringRequest(Request.Method.POST, URL.tolakcalrel, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        dialog.dismiss();
                                        Toast.makeText(context, nama + " dtolak !", Toast.LENGTH_SHORT).show();
                                        holder.cardView.removeViewAt(position);
                                        notifyDataSetChanged();


                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(context, "Gagagl menghubungi server", Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("id_daftar", id_df);

                                        //   Log.d(TAG, "getParams: "+params);
                                        return params;
                                    }
                                };
                                RequestHAndler.getInstance(context).addToRequestQueue(stringRequest);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    
    public void hapus(){
       
    }

    @Override
    public int getItemCount() {
        return calonrel.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView nama,nik,alamat,tps,id;
        Button btntolakmantan;
        CardView cardView;
        String nohp,email;
        public holder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvnamacalrel);
            nik = itemView.findViewById(R.id.tvnikcalrel);
            alamat = itemView.findViewById(R.id.tvalamatcalrel);
            tps = itemView.findViewById(R.id.tvtpscalrel);
            id = itemView.findViewById(R.id.idcalrel);
            btntolakmantan = itemView.findViewById(R.id.btntolak);
            cardView = itemView.findViewById(R.id.cardnyacoy);
        }
    }
}
