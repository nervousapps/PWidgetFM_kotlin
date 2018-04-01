package nervousapps.pwidgetfm_kotlin


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat


internal class NotificationHelper (context: Context) : ContextWrapper(context) {

    companion object {
        val PRIMARY_CHANNEL = "default"
        lateinit var mNotificationManager : NotificationManager
        lateinit var chan1 : NotificationChannel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        mNotificationManager = this
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        chan1 = NotificationChannel(PRIMARY_CHANNEL,
                getString(R.string.notification_title), NotificationManager.IMPORTANCE_DEFAULT)
        chan1.lightColor = Color.LTGRAY
        chan1.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        mNotificationManager.createNotificationChannel(chan1)
    }

    fun notificationbuilder() : NotificationCompat.Builder {
        val intent = Intent(this, WidgetControlPanl::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        // You only need to create the channel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        return NotificationCompat.Builder(this, PRIMARY_CHANNEL)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_message))
                .setSmallIcon(R.drawable.ic_launcher)
                .setBadgeIconType(R.drawable.ic_launcher)
                .setColor(getColor(R.color.primary_dark_material_dark))
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent)
    }
}
