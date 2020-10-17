package dev.mdb.mdbpokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private ArrayList<Pokemon> pokeList;
    private Context context;
    private OnItemClickListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView poke_image;
        public TextView poke_name;
        public ViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            poke_image = itemView.findViewById(R.id.pokemonImage);
            poke_name = itemView.findViewById(R.id.pokemonName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public Adapter(ArrayList<Pokemon> exampleList, Context context){
        pokeList = exampleList;
        this.context = context;
    }

    public void filterList(ArrayList<Pokemon> filteredList) {
        pokeList = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        ViewHolder vh = new ViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon currentItem = pokeList.get(position);

        //holder.mImageView.setImageResource(currentItem.getImageResource());
        Picasso.with(context)
                .load(currentItem.getPictureURL())
                .into(holder.poke_image);
        holder.poke_name.setText(currentItem.getName()+"");
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }

}

