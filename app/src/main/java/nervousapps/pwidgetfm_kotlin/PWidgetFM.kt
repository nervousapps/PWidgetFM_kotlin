package nervousapps.pwidgetfm_kotlin

import android.widget.Toast
import android.content.ComponentName
import android.appwidget.AppWidgetManager
import android.widget.RemoteViews
import android.media.MediaPlayer
import android.app.PendingIntent
import android.content.Intent
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.net.Uri


/**
 * Created by achillepenet on 7/31/17.
 */
class PWidgetFM : AppWidgetProvider() {

    internal var radio = "https://www.radioking.com/play/pwfm-provocative-wave-for-music"

    internal var uri = Uri.parse(radio)

    internal lateinit var contextPlay: Context

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val remoteViews = RemoteViews(context.packageName, R.layout.pwidget_fm)
        val watchWidget = ComponentName(context, PWidgetFM::class.java)

        remoteViews.setImageViewUri(R.id.widget_button_play, Uri.parse("https://www.radioking.fr/api/track/cover/d8b210b9-15bc-4694-85ed-941d8caa7e0d"))
        remoteViews.setOnClickPendingIntent(R.id.widget_button_play, getPendingSelfIntent(context, SYNC_CLICKED))
        appWidgetManager.updateAppWidget(watchWidget, remoteViews)
    }

    override fun onReceive(context: Context, intent: Intent) {
        contextPlay = context

        val action = intent.action

        if (SYNC_CLICKED == action) {

            if (mediaPlayer == null) {
                radioInit(context)
            }

            radioPlayPause(context)

        }

        super.onReceive(context, intent)
    }


    protected fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }


    fun radioInit(context: Context) {

        mediaPlayer = MediaPlayer.create(context, uri)

    }

    fun radioPlayPause(context: Context) {

        val remoteView = RemoteViews(context.packageName, R.layout.pwidget_fm)

        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
            remoteView.setImageViewResource(R.id.widget_button_play, R.drawable.play)
            AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, PWidgetFM::class.java), remoteView)
            Toast.makeText(context, "PWFM is off", Toast.LENGTH_LONG).show()
        } else {
            mediaPlayer!!.start()
            remoteView.setImageViewResource(R.id.widget_button_play, R.drawable.pause)
            AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, PWidgetFM::class.java), remoteView)
            Toast.makeText(context, "Let the techno out !!!", Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        var mediaPlayer: MediaPlayer? = null

        private val SYNC_CLICKED = "automaticWidgetSyncButtonClick"

        fun radioPause() {

            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        }
    }
}