package dev.mdb.mdbpokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Pokemon> pokemonList;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int layout_type = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pokemon.populateData(getApplicationContext());
        pokemonList = Pokemon.getPokemon();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new dev.mdb.mdbpokedex.Adapter(pokemonList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If the current layout is a Linear Layout, change it to a Grid Layout
                if (layout_type == 0){
                    layout_type = 1;
                    fab.setImageResource(R.drawable.ic_baseline_grid_on_24);
                    mLayoutManager = new LinearLayoutManager(MainActivity.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                }
                else{
                    layout_type = 0;
                    fab.setImageResource(R.drawable.ic_baseline_view_list_24);
                    mLayoutManager = new GridLayoutManager(MainActivity.this,2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                }

            }
        });

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                Intent intent = new Intent(MainActivity.this, Display.class);
                intent.putExtra("name" , pokemonList.get(position).getName());
                intent.putExtra("attack" , Integer.toString(pokemonList.get(position).getAttack()));
                intent.putExtra("special_attack" , Integer.toString(pokemonList.get(position).getSpecialAttack()));
                intent.putExtra("defense" , Integer.toString(pokemonList.get(position).getDefense()));
                intent.putExtra("special_defense" , Integer.toString(pokemonList.get(position).getSpecialDefense()));
                intent.putExtra("flavor_text" , pokemonList.get(position).getFlavorText());
                intent.putExtra("health" , Integer.toString(pokemonList.get(position).getHealthPoints()));
                intent.putExtra("url" , pokemonList.get(position).getPictureURL());
                intent.putExtra("number" , Integer.toString(pokemonList.get(position).getPokemonNumber()));
                intent.putExtra("special_defense" , Integer.toString(pokemonList.get(position).getSpecialDefense()));
                intent.putExtra("total_points" , Integer.toString(pokemonList.get(position).getTotalPoints()));
                intent.putExtra("speed" , Integer.toString(pokemonList.get(position).getSpeed()));
                intent.putExtra("species" , pokemonList.get(position).getSpecies());

                MainActivity.this.startActivity(intent);
                //mAdapter.notifyDataSetChanged();
            }
        });

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    private void filter(String text){
        ArrayList<Pokemon> filteredList = new ArrayList<>();

        for (Pokemon pokemon: pokemonList){
            if (pokemon.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(pokemon);
            }
        }
        mAdapter.filterList(filteredList);
        mAdapter.notifyDataSetChanged();
    }

}




