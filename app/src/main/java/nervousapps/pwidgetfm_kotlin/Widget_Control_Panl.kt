package nervousapps.pwidgetfm_kotlin

/**
 * Created by achillepenet on 7/31/17.
 */
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class Widget_Control_Panl : AppCompatActivity() {

    internal lateinit var currentTrack: WebView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_widget__control__panl)

        currentTrack = findViewById(R.id.currenttrack)

        currentTrack.settings.javaScriptEnabled = true
        currentTrack.loadUrl("file:///android_asset/index.html")
    }

    companion object {

        var mediaPlayer: MediaPlayer? = null
    }
}