package carlos.robert.ejemploretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import carlos.robert.ejemploretrofit.adapters.PhotoAdapter;
import carlos.robert.ejemploretrofit.conexiones.ApiConexiones;
import carlos.robert.ejemploretrofit.conexiones.RetrofitObject;
import carlos.robert.ejemploretrofit.modelos.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotosActivity extends AppCompatActivity {
    private PhotoAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Photo> listaPhotos;

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        recycler = findViewById(R.id.contenedorPhotos);
        listaPhotos = new ArrayList<>();
        adapter = new PhotoAdapter(PhotosActivity.this, listaPhotos, R.layout.photo_view_holder);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);

        //recibirá la información del identificador del album

        Intent intent = getIntent();
        if (intent.getExtras().getInt("ALBUM_ID") != 0) {
            doGetPhotos(intent.getExtras().getInt("ALBUM_ID"));
        } else {
            Toast.makeText(this, "ALBUM INCORRECTO", Toast.LENGTH_SHORT).show();
        }
    }

    private void doGetPhotos(int albumId) {
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<ArrayList<Photo>> getPhotos = api.getPhotosAlbumPath(albumId);

        getPhotos.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    listaPhotos.addAll(response.body());

                    adapter.notifyItemRangeInserted(0, listaPhotos.size());
                } else {
                    Toast.makeText(PhotosActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                Toast.makeText(PhotosActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }
}