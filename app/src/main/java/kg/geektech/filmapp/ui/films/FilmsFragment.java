package kg.geektech.filmapp.ui.films;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.filmapp.App;
import kg.geektech.filmapp.R;
import kg.geektech.filmapp.common.OnItemClick;
import kg.geektech.filmapp.data.models.Film;
import kg.geektech.filmapp.databinding.FragmentFilmsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment implements OnItemClick<String> {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;
    private ArrayList<String> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmsAdapter();
        adapter.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setFilms(response.body());
                    Log.e("myTag", "myTag: " + response.body().toString());
                } else {
                    Log.e("myTag", "onResponse: " + response.body().toString());
                    notFound();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Log.e("myTag", "onFailure: " + t.getLocalizedMessage());
                notFound();
            }
        });

        binding.recycler.setAdapter(adapter);

    }

    private void notFound() {

        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.errorFragment);
    }

    @Override
    public void onItemClick(String data) {

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        navController.navigate(R.id.filmDetailFragment, bundle);

    }
}