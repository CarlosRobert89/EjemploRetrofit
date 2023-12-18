package carlos.robert.ejemploretrofit.conexiones;

import java.util.ArrayList;

import carlos.robert.ejemploretrofit.modelos.Album;
import carlos.robert.ejemploretrofit.modelos.Photo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {
    //RECOGEMOS TODOS
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    //RECOGEMOS TODOS CON CIERTO PARAMETRO
    @GET("/photos?")
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") int albumId);

    //RECOGEMOS TODOS DE CIERTO PATH
    @GET("/albums/{albumId}/photos")
    Call<ArrayList<Photo>> getPhotosAlbumPath(@Path("albumId") int albumId);

    @POST("/albums")
    Call<Album> postAlbum(@Body Album a);

    //INSERTAR UN ALBUM CON UN FORMULARIO
    @FormUrlEncoded
    @POST("/albums")
    Call<Album> postAlbumForm(@Field("userID") int idUser, @Field("title") String titulo);

    @DELETE("albums/{id}")
    Call<Album> deleteAlbum(@Path("id") int idAlbum);
}
