package nervousapps.pwidgetfm_kotlin

/**
 * Created by achillepenet on 7/31/17.
 */

import android.appwidget.AppWidgetManager
import android.content.*
import android.database.Cursor
import android.media.AudioManager
import android.net.Uri
import android.widget.RemoteViews
import android.widget.Toast

class PlugReceiver : ContentProvider() {

    override fun onCreate(): Boolean {

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val playpause = Intent(context, MusicService::class.java)
                playpause.putExtra("KEY_COMMAND", "STOP")
                playpause.putExtra("KEY_MESSAGE", "PWFM")

                context.stopService(playpause)

                val remoteView = RemoteViews(getContext()!!.packageName, R.layout.pwidget_fm)

                remoteView.setImageViewResource(R.id.widget_button_play, R.drawable.play)
                AppWidgetManager.getInstance(getContext()).updateAppWidget(ComponentName(getContext()!!, PWidgetFM::class.java), remoteView)
                Toast.makeText(getContext(), "PWFM is off", Toast.LENGTH_LONG).show()
            }
        }
        val filter = IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        context!!.registerReceiver(receiver, filter)
        return true
    }

    override fun query(p0: Uri?, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(p0: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
