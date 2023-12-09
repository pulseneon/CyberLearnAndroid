package com.rudnev.imreading

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rudnev.imreading.ui.theme.ImReadingTheme
import com.rudnev.imreading.utils.FirstShowActivityUtil
import com.rudnev.imreading.utils.TokenValidateUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var notificationManager: NotificationManagerCompat
        notificationManager = NotificationManagerCompat.from(this)

        AsyncTask.execute {
            SimulateAttack(notificationManager)
        }

        setContent {
                    ImReadingTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {

                            if (!FirstShowActivityUtil.wasFirstActivityShown(this)){
                                Log.w("Debug", "wasFirstActivityShown))")
                                val intent = Intent(this, PreviewActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                startActivity(intent)
                            }
                            else{
                                if (TokenValidateUtil.getToken(this) == null){
                                    val intent = Intent(this, LoginActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    startActivity(intent)
                                }

                                StartFormPreview()
                            }
                        }
                    }
                }
            }

    val channelId = "Progress Notification" as String
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.w("Debug", "nofity granded")
        } else {
            Log.w("Debug", "nofity cancel")
        }
    }

    // todo: вынести под цикл вне загрузки приложения
    fun SimulateAttack(notificationManager: NotificationManagerCompat){
        while(true) {
            Thread.sleep(100_000)
            Log.w("Debug", "simulate")

            val notification =
                NotificationCompat.Builder(this, channelId)
                    .setContentTitle("Анастасия")
                    .setSmallIcon(R.drawable.ni_bad)
                    .setContentText("Привет! Давно не видились, займи пожалуйста 5.000))")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setOngoing(true)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w("Debug", "error notify")
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                continue;
            }
            try{
                notificationManager.notify(1, notification.build())
            }
            catch(e: Exception){
                Log.w("Debug", "error " + e.message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("Поиск") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Text(
            text = "Рекомендация",
            color = Color.Black,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium)
        )

             Box(
                modifier = Modifier
                    .requiredWidth(width = 376.dp)
                    .requiredHeight(height = 138.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
                    .shadow(2.dp, RoundedCornerShape(0.dp))
                ) {
            Text(
                text = "Ссылки в электронных письмах – это распространенный инструмент, используемый хакерами, чтобы обманом заставить людей отказаться от своей защищенной информации. Это часто бывает в форме банковских выписок, бронирования авиабилетов, электронных писем для восстановления пароля и т. д.\n",
                color = Color.Black.copy(alpha = 0.8f),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 4.dp, y = 10.dp)
                    .requiredWidth(width = 358.dp)
                    .requiredHeight(height = 152.dp)
            )
        }

        Text(
            text = "Доступные тесты",
            color = Color.Black,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium)
        )

        Button(
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://26.170.53.65:5173/"))
                context.startActivity(browserIntent)
            },
            modifier = Modifier
                .height(48.dp)
                .padding(top = 8.dp)
                // .padding(horizontal = 24.dp)
        ) {
            Text("Финансовые нарушения", color = Color.White)
        }

        Button(
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://26.170.53.65:5173/"))
                context.startActivity(browserIntent)
            },
            modifier = Modifier
                .height(48.dp)
                .padding(top = 8.dp)
            // .padding(horizontal = 24.dp)
        ) {
            Text("Защита персональных данных", color = Color.White)
        }


        Button(
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://26.170.53.65:5173/"))
                context.startActivity(browserIntent)
            },
            modifier = Modifier
                .height(48.dp)
                .padding(top = 8.dp)
            // .padding(horizontal = 24.dp)
        ) {
            Text("Защита личных цифровых устройств", color = Color.White)
        }


        Button(
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://26.170.53.65:5173/"))
                context.startActivity(browserIntent)
            },
            modifier = Modifier
                .height(52.dp)
                .padding(top = 8.dp)
            // .padding(horizontal = 24.dp)
        ) {
            Text("Правила работы в сети интернет", color = Color.White)
        }


        Box(
            modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chatbubble),
                        contentDescription = "ChatBubble",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Text("Чат", color = Color.Black)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.homepage),
                        contentDescription = "Home Page",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Text("Главная", color = Color(0xff263570), style = TextStyle(fontWeight = FontWeight.Bold))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.customer),
                        contentDescription = "Customer",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Text("Профиль", color = Color.Black)
            }
        }
    }
}



@Preview()
@Composable
private fun StartFormPreview() {
    StartForm(Modifier)
}