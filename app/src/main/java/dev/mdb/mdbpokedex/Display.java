package dev.mdb.mdbpokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        processIntent();
    }

    private void processIntent(){
        //Checks if Extras exists --> a lot of incoming extras so if two exist then they all do
        if(getIntent().hasExtra("name") && getIntent().hasExtra("speed")){

            Picasso.with(this)
                    .load(getIntent().getStringExtra("url"))
                    .into((ImageView) findViewById(R.id.pokeImage));

            TextView name = findViewById(R.id.text_name_number);
            String name_text = getIntent().getStringExtra("name") + " "
                    + getIntent().getStringExtra("number");
            name.setText(name_text);

            TextView health = findViewById(R.id.text_health);
            String health_text = "Health: " + getIntent().getStringExtra("health");
            health.setText(health_text);

            TextView attack = findViewById(R.id.text_attack);
            String attack_text = "Attack/Special: " + getIntent().getStringExtra("attack")
                    + "/" + getIntent().getStringExtra("special_attack");
            attack.setText(attack_text);

            TextView defense = findViewById(R.id.text_defense);
            String defense_text = "Defense/Special: " + getIntent().getStringExtra("defense")
                    + "/" + getIntent().getStringExtra("special_defense");
            defense.setText(defense_text);

            TextView species = findViewById(R.id.text_species);
            String species_text = "Species: "+ getIntent().getStringExtra("species");
            species.setText(species_text);

            TextView total = findViewById(R.id.text_points);
            String total_text = "Total Points: " + getIntent().getStringExtra("total_points");
            total.setText(total_text);

            TextView speed = findViewById(R.id.text_speed);
            String speed_text = "Speed: " + getIntent().getStringExtra("speed");
            speed.setText(speed_text);

            TextView flavor = findViewById(R.id.text_flavor);
            String flavor_text = getIntent().getStringExtra("flavor_text");
            flavor.setText(flavor_text);
        }
    }

}
