package dev.mdb.mdbpokedex;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dev.mdb.mdbpokedex.utils.Utils;

public class Pokemon {

    private static JSONObject pokemonData;
    public static ArrayList<Pokemon> pokemons;

    private String pokemonName;
    private int pokemonNumber;
    private int attack;
    private int defense;
    private String flavorText;
    private int healthPoints;
    private int specialAttack;
    private int specialDefense;
    private String species;
    private int speed;
    private int totalPoints;
    private ArrayList<String> types;
    private String pictureLink;

    public Pokemon(String name) {
        // Clean name from unusual spacing.
        pokemonName = name.replace("  ", " ").replace("( ", "(").replace(" )", ")");
        System.out.println(name);
        // Instantiate all of the object's members.
        try {
            JSONObject pokemon = pokemonData.getJSONObject(name);
            pokemonNumber = pokemon.getInt("#");
            attack = pokemon.getInt("Attack");
            defense = pokemon.getInt("Defense");
            flavorText = pokemon.getString("FlavorText");
            healthPoints = pokemon.getInt("HP");
            specialAttack = pokemon.getInt("Sp. Atk");
            specialDefense = pokemon.getInt("Sp. Def");
            species = pokemon.getString("Species");
            speed = pokemon.getInt("Speed");
            totalPoints = pokemon.getInt("Total");
            JSONArray rawTypes = pokemon.getJSONArray("Type");
            types = new ArrayList<>();
            for (int i = 0; i < rawTypes.length(); i++) {
                types.add(rawTypes.getString(i));
            }
            pictureLink = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/" + Utils.forceThreeDigits(pokemonNumber) + ".png";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void changeName(String text){
        pokemonName = text;
    }

    /** Prepares JSON file for being loaded into the program.
     *  IMPORTANT: MUST BE CALLED SOMEWHERE IN YOUR CODE BEFORE "getPokemon()" CAN BE USED.
     **/
    public static boolean populateData(Context context) {
        try {
            pokemonData = new JSONObject(Utils.loadJSONFromAsset(context, "pokeData.json"));
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    /** Returns an Arraylist of all pokemon that were in the JSON file
     * IMPORTANT: MUST CALL populateData() BEFORE USING THIS METHOD.
     **/
    public static ArrayList<Pokemon> getPokemon() {
        ArrayList<Pokemon> pokemon = new ArrayList<>();
        Iterator pokemonNames = pokemonData.keys();
        while (pokemonNames.hasNext()) {
            String name = pokemonNames.next().toString();
            pokemon.add(new Pokemon(name));
        }
        pokemons = pokemon;
        return pokemon;
    }

    /** Returns a list of strings that represent all the different types of pokemon that exist:
     *      ex: "Fire", "Electric", etc...
     */
    public static Collection<? extends String> getPokemonTypes() {
        Set<String> types = new HashSet<>();
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            for (int j = 0; j < pokemon.getTypes().size(); j++) {
                types.add(pokemon.getTypes().get(j));
            }
        }
        return types;
    }


    /** The following return specific information about a pokemon object. **/
    public String getName() {
        return pokemonName;
    }

    public int getPokemonNumber() {
        return pokemonNumber;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public String getSpecies() {
        return species;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public String getPictureURL() {
        return pictureLink;
    }
}