package kr.ac.hallym.prac06

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import kr.ac.hallym.prac06.databinding.ActivityMainBinding
import kr.ac.hallym.prac06.databinding.DialogInputBinding
import java.sql.Time
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val eventHandler = object: DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {
            if(p1 == DialogInterface.BUTTON_POSITIVE) {
                Log.d("kkang", "positive button click")
            } else if(p1 == DialogInterface.BUTTON_NEGATIVE) {
                Log.d("kkang", "negetive button click")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button10.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel =
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)

                // 채널에 다양한 정보 설정
                channel.description = "My Channel One Description"
                channel.setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                channel.setSound(uri, audioAttributes)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

                // 채널을 NotificantionManager에 등록
                manager.createNotificationChannel(channel)

                // 채널을 이용하여 builder 생성
                builder = NotificationCompat.Builder(this, channelId)
            } else {
                builder = NotificationCompat.Builder(this)
            }

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("Content Title")
            builder.setContentText("Content Massage")

           // builder.setAutoCancel(false)      // notify 안 지워지는거
           // builder.setOngoing(true)
/*
            val KEY_TEXT_REPLY = "key_text_reply"
            var replyLabel: String = "답장"
            var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }
/*
            val intent = Intent(this, DetailActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_MUTABLE)
            //builder.setContentIntent(pendingIntent)
            builder.addAction(NotificationCompat.Action.Builder(
                android.R.drawable.stat_notify_more, "Action", pendingIntent).build())
            builder.addAction(NotificationCompat.Action.Builder(
                R.drawable.send, "답장", pendingIntent).addRemoteInput(remoteInput).build())*/

            val replyIntent = Intent(this, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(this, 20, replyIntent, PendingIntent.FLAG_MUTABLE)
            builder.addAction(NotificationCompat.Action.Builder(
                R.drawable.send, "답장", replyPendingIntent).addRemoteInput(remoteInput).build())
           */

            builder.setProgress(100, 0, false)

            manager.notify(11, builder.build())

            thread {
                for(i in 1..100) {
                    builder.setProgress(100, 1 ,false)
                    manager.notify(11, builder.build())
                    SystemClock.sleep(100)
                }
            }
        }

        binding.button8.setOnClickListener {
            val notification : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()
        }

        binding.button9.setOnClickListener {
            val vibrator = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager =
                    this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator;
            } else {
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    //VibrationEffect.createOneShot(
                    //    500,
                    //    VibrationEffect.DEFAULT_AMPLITUDE
                    //)
                    VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000), intArrayOf(0, 50, 0, 200), -1)
                )
            } else {
                //vibrator.vibrate(500)
                vibrator.vibrate(longArrayOf(500, 1000, 500, 2000), -1)
            }
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("Test dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("OK", eventHandler)
                setNegativeButton("Cancel", eventHandler)
                setNeutralButton("More", null)
                show()
            }
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items, object: DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("kkang", "선택한 과일: ${items[p1]}" )
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object: DialogInterface.OnMultiChoiceClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                        Log.d("kkang", "${items[p1]}이 ${if(p2) "선택되었습니다." else "선택 해제되었습니다."}" )
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 1, object: DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("kkang", "${items[p1]}이 선택되었습니다." )
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.button7.setOnClickListener {
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("Input")
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }

        findViewById<Button>(R.id.button1).setOnClickListener {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    Log.d("kkang", "year : $p1, month : ${p2+1}, datOfMonth : $p3")
                }
            }, 2022, 9, 24).show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            TimePickerDialog(this, object: TimePickerDialog. OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    Log.d("kkang", "time : $p1, minute : $p2")
                }
            }, 13, 0, true).show()
        }

        /*
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted) {
                Log.d("kkang", "callback, granted..")
            } else {
                Log.d("kkang", "callback, denied..")
            }
        }



        val status = ContextCompat.checkSelfPermission(this, "andriod.premission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED) {
            Log.d("kkang", "permission granted")
        } else {
            //Log.d("kkang", "permission denied")
            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }*/


    }
}