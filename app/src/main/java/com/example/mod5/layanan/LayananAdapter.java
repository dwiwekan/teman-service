package com.example.mod5.layanan;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mod5.DetailLayananActivity;
import com.example.mod5.R;
import com.google.gson.Gson;

import java.util.List;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.LayananHolder> {

    private Context c;
    private List<Layanan> layanans;
    private Dialog dialog;
    Gson gson=new Gson();
    public interface Dialog {
        void onClick(int position);
    }

    public LayananAdapter(Context c,List<Layanan> layanans){
        this.c = c;
        this.layanans = layanans;
    }

    @NonNull
    @Override
    public LayananHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layanan_item,null);

        return new LayananHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LayananHolder holder, int position) {
//        Glide.with(holder.itemView.getContext()).load(layanans.get(position).getFotoTempat()).into(holder.fotoTempat);
        new DownloadImageHelper((ImageView)holder.fotoTempat).execute(DownloadImageHelper.imageUrl+layanans.get(position).getFotoTempat());
        holder.namaTempat.setText(layanans.get(position).getNamaTempat());
        holder.alamat.setText(layanans.get(position).getAlamat());
        holder.noTelp.setText(layanans.get(position).getNoTelp());
        holder.deskripsi.setText(layanans.get(position).getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String jsonCartModel = gson.toJson(layanans.get(position));
                Intent intent = new Intent(view.getContext(), DetailLayananActivity.class);
                intent.putExtra("layanan_model",jsonCartModel);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Move", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return layanans.size();
    }

    public class LayananHolder extends RecyclerView.ViewHolder {
        TextView namaTempat,deskripsi,noTelp,alamat;
        ImageView fotoTempat;

        public LayananHolder(@NonNull View itemView) {
            super(itemView);
            this.fotoTempat = itemView.findViewById(R.id.image_tempat);
            this.namaTempat = itemView.findViewById(R.id.nama_tempat);
            this.alamat = itemView.findViewById(R.id.alamat_tempat);
            this.noTelp = itemView.findViewById(R.id.notelp_tempat);
            this.deskripsi = itemView.findViewById(R.id.deskripsi_tempat);

            // SUDAH DIPINDAH KE ATAS. BOLEH DISIMPAN KALO MASIH SAYANG
//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
////                    if (dialog!=null){
////                        dialog.onClick(getLayoutPosition());
////                    }
//                    String jsonCartModel = gson.toJson(cartData.get(position));
//                    Intent intent = new Intent(v.getContext(), EditNoteActivity.class);
//                    intent.putExtra("cart_model",jsonCartModel);
//                    v.getContext().startActivity(intent);
//                    Toast.makeText(view.getContext(), "Move", Toast.LENGTH_LONG).show();
//                }
//            });
        }
    }
}
