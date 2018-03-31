package nervousapps.pwidgetfm_kotlin

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import nervousapps.pwidgetfm_kotlin.PWidgetFM.Companion.isPlaying
import nervousapps.pwidgetfm_kotlin.PWidgetFM.Companion.mediaPlayer

class MusicService: Service(){

    private lateinit var mNotif : NotificationHelper

    override fun onBind(intent: Intent?): IBinder {
        Toast.makeText(this, "HEY", Toast.LENGTH_LONG).show()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate() {
        super.onCreate()

        mNotif = NotificationHelper(this)

        radioPlay()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i("LocalService", "Received start id $startId: $intent")
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_NOT_STICKY
    }

    private fun radioPlay() {
            startForeground(1, mNotif.notificationbuilder().build())
            mediaPlayer!!.start()
            isPlaying = true
    }

    override fun onDestroy() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            mediaPlayer = null

            isPlaying = false
        }


        super.onDestroy()
    }

}