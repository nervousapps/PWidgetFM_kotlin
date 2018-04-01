package nervousapps.pwidgetfm_kotlin

/**
 * Created by achillepenet on 7/31/17.
 */
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_widget__control__panl.*

class WidgetControlPanl : AppCompatActivity() {

    private lateinit var currentTrack: WebView

    private var i = 0

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_widget__control__panl)

        currentTrack = currenttrack
        currentTrack.clearCache(true)
        currentTrack.settings.javaScriptEnabled = true
        currentTrack.loadUrl("file:///android_asset/index.html")

        var eAst = east

        eAst.setOnClickListener{
            Log.d("easter", "Iterator = "+i)
            if(i == 3){
                startActivity(Intent(this, EasterEgg::class.java))
                i = 0
            }else{
                i += 1
            }
        }
    }
}