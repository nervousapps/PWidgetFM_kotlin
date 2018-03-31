package nervousapps.pwidgetfm_kotlin

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.support.v4.content.ContextCompat
import android.widget.RemoteViews
import android.widget.Toast


/**
 * Created by achillepenet on 7/31/17.
 */
class PWidgetFM : AppWidgetProvider() {

    companion object {

        val SYNC_CLICKED = "automaticWidgetSyncButtonClick"

        var isPlaying = false

        var mediaPlayer: MediaPlayer? = null
    }

    internal var radio = "https://www.radioking.com/play/pwfm-provocative-wave-for-music"

    private var uri = Uri.parse(radio)

    private lateinit var contextPlay: Context

    private lateinit var watchWidget : ComponentName

    private lateinit var remoteViews: RemoteViews


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        remoteViews.setOnClickPendingIntent(R.id.widget_button_play, getPendingSelfIntent(context, SYNC_CLICKED))
        appWidgetManager.updateAppWidget(watchWidget, remoteViews)
    }

    override fun onReceive(context: Context, intent: Intent) {
        remoteViews = RemoteViews(context.packageName, R.layout.pwidget_fm)
        watchWidget = ComponentName(context, PWidgetFM::class.java)
        contextPlay = context

        val action = intent.action

        if (SYNC_CLICKED == action) {

            val playpause = Intent(contextPlay, MusicService::class.java)
            playpause.putExtra("KEY_COMMAND", "START")
            playpause.putExtra("KEY_MESSAGE", "PWFM")

            if(isPlaying){
                contextPlay.stopService(playpause)
                Toast.makeText(context, "PWFM is off", Toast.LENGTH_LONG).show()
                remoteViews.setImageViewResource(R.id.widget_button_play, R.drawable.play)
                AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, PWidgetFM::class.java), remoteViews)
            }else{
                mediaPlayer = MediaPlayer.create(context, uri)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ContextCompat.startForegroundService(contextPlay, playpause)
                } else {
                    contextPlay.startService(playpause)
                }
                Toast.makeText(context, "Let the techno out !!!", Toast.LENGTH_LONG).show()
                remoteViews.setImageViewResource(R.id.widget_button_play, R.drawable.pause)
                AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, PWidgetFM::class.java), remoteViews)
            }
        }

        super.onReceive(context, intent)
    }


    private fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }
}