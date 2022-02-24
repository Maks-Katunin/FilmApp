package kg.geektech.filmapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geektech.filmapp.data.models.Film;
import kg.geektech.filmapp.databinding.FragmentFilmDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = getArguments().getString("data");
        App.api.getFilmById(data).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() !=null){

                 setFilmDetails(response.body());
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setFilmDetails(Film film) {
        Glide.with(this).load(film.getMovieBanner()).
                into(binding.ivMovieBanner);
        binding.title.setText("Title \n" + film.getTitle() +"\n");
        binding.originalTitle.setText("Original title \n" + film.getOriginalTitle() +"\n");
        binding.director.setText("Director \n" + film.getDirector() +"\n");
        binding.producer.setText("Producer \n" + film.getProducer() +"\n");
        binding.releaseDate.setText("Release date \n" + film.getReleaseDate() +"\n");
        binding.runningTime.setText("Running time \n" + film.getRunningTime() +"\n");
    }
}