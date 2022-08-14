import android.util.Log
import androidx.lifecycle.ViewModel
import com.sejin.example.sunflower_clone.adapters.UnsplashApi
import com.sejin.example.sunflower_clone.data.UnsplashSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel : ViewModel() {

    lateinit var photos: MutableList<UnsplashPhoto>

    init {
        getPhotos()
    }

    private fun getPhotos() {
        val call = UnsplashApi.retrofitService.searchPhotos(
            "apple",
            10,
            10
        )
        val callback = object : Callback<UnsplashSearchResponse> {
            override fun onResponse(
                call: Call<UnsplashSearchResponse>,
                response: Response<UnsplashSearchResponse>
            ) {
                Log.i("TTAAGG", "onResponse: ${response.body()}")
                photos = response.body()?.results?.toMutableList()!!
            }

            override fun onFailure(call: Call<UnsplashSearchResponse>, t: Throwable) {
                Log.i("TTAAGG", "onFailure: ${t.message}")
            }
        }

        call.enqueue(callback)
    }

    init {
    }
}