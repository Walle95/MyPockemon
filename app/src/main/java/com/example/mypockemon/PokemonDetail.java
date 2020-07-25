package com.example.mypockemon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mypockemon.Adapter.PokemonTypeAdapter;
import com.example.mypockemon.Common.Common;
import com.example.mypockemon.Model.Pokemon;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name,pokemon_height,pokemon_weight,pokemon_spawn_time,pokemon_spawn_chance;
    RecyclerView recycler_type,recycler_weakness;

    static PokemonDetail instance;

    public static PokemonDetail getInstance() {
        if(instance == null)
            instance = new PokemonDetail();
        return instance;
    }

    public PokemonDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        View itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon;
        //Get position from Argument
        if (getArguments().get("num") == null)
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));
        else
            pokemon = null;

        pokemon_img = (ImageView)itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView)itemView.findViewById(R.id.name);
        pokemon_height = (TextView)itemView.findViewById(R.id.height);
        pokemon_weight = (TextView)itemView.findViewById(R.id.weight);
        pokemon_spawn_time = (TextView)itemView.findViewById(R.id.spawn_time);
        pokemon_spawn_chance = (TextView)itemView.findViewById(R.id.spawn_chance);
        //pokemon_attack = (TextView)itemView.findViewById(R.id.attack);

        recycler_type = (RecyclerView)itemView.findViewById(R.id.recycler_type);
        recycler_type.setHasFixedSize(true);
        recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_weakness = (RecyclerView)itemView.findViewById(R.id.recycler_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        setDetailPokemon(pokemon);





        return itemView;
    }

    private void setDetailPokemon(Pokemon pokemon) {
        //Load image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);

        pokemon_name.setText(pokemon.getName());

        pokemon_weight.setText("Weight: "+pokemon.getWeight());
        pokemon_height.setText("Height: "+pokemon.getHeight());
        pokemon_spawn_time.setText("Spawn time: "+pokemon.getSpawn_time());
        pokemon_spawn_chance.setText("Spawn chance: "+pokemon.getSpawn_chance());

        //Set Type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        //Set Weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getWeaknesses());
        recycler_weakness.setAdapter(weaknessAdapter);
    }
}
