package com.rudnev.imreading

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rudnev.imreading.services.AuthService
import com.rudnev.imreading.ui.theme.ImReadingTheme
import com.rudnev.imreading.utils.TokenValidateUtil

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImReadingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   LoginPreview()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Login(modifier: Modifier = Modifier) {
        val context = androidx.compose.ui.platform.LocalContext.current
        val login = remember{mutableStateOf("Email")}
        val password = remember{mutableStateOf("Password")}

        Box(
            modifier = modifier
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 844.dp)
                .clip(shape = RoundedCornerShape(1.dp))
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 2.dp,
                        y = (-146).dp
                    )
                    .requiredWidth(width = 610.dp)
                    .requiredHeight(height = 476.dp)
                    .blur(
                        radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
                    .background(color = Color(0xff74beed)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = (-62).dp,
                        y = 276.dp
                    )
                    .requiredWidth(width = 694.dp)
                    .requiredHeight(height = 904.dp)
                    .blur(
                        radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
                    .background(color = Color(0xff74beed)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 2.dp,
                        y = 222.dp
                    )
                    .requiredWidth(width = 738.dp)
                    .requiredHeight(height = 400.dp)
                    .blur(
                        radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
                    .background(color = Color(0xE8F4FF)))
            Image(
                painter = painterResource(id = R.drawable.womanreadingbook),
                contentDescription = "woman reading a book",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(y = (-150).dp, x = (-15).dp)
                    .requiredHeight(height = 450.dp)
                    .requiredWidth(width = 300.dp))
            Text(
                text = "Авторизация",
                color = Color.White,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 440.dp
                    ))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color.White,
                        fontSize = 20.sp)
                    ) {append("Нет аккаунта? ")}
                    withStyle(style = SpanStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)) {append("Регистрация")}},
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.5.dp,
                        y = 795.dp
                    )
                    .requiredWidth(width = 293.dp)
                    .requiredHeight(height = 23.dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 38.dp,
                        y = 728.dp
                    )
                    .requiredWidth(width = 314.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Button(
                    onClick = {
                       /* lifecycleScope.launch {
                            val auth = AuthService()
                            val authResult = auth.login(login.value, password.value, context)

                            Log.w("Debug", authResult.toString())

                            if (authResult){
                                Log.w("Debug", "auth result true))")
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            }
                        }*/

                        val authService = AuthService()

                        val result = authService.login(login.value, password.value, applicationContext);

                        if (TokenValidateUtil.getToken(applicationContext) != null){
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }
                        else{
                            Toast.makeText(applicationContext, "Не удалось авторизоваться.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF141044)),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 314.dp)
                        .requiredHeight(height = 56.dp)){ }
                Text(
                    text = "Продолжить",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 13.dp
                        )
                        .requiredWidth(width = 168.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 38.dp,
                        y = 501.dp
                    )
                    .requiredWidth(width = 314.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                TextField(
                    value = login.value,
                    onValueChange = { newText -> login.value = newText },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x4B64CA)),
                    textStyle = TextStyle(
                        fontSize = 20.sp, color = Color.White),
                    modifier = Modifier
                        //.align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 314.dp)
                        .requiredHeight(height = 56.dp)
                        .clip(shape = RoundedCornerShape(20.dp)))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 38.dp,
                        y = 587.dp
                    )
                    .requiredWidth(width = 314.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                TextField(
                    value = password.value,
                    onValueChange = { newText -> password.value = newText },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x4B64CA)),
                    textStyle = TextStyle(
                        fontSize = 20.sp, color = Color.White),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 314.dp)
                        .requiredHeight(height = 56.dp)
                        .clip(shape = RoundedCornerShape(20.dp)))
                /*Text(
                    text = ,
                    color = Color.White.copy(alpha = 0.8f),
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = (-6.5).dp,
                            y = 14.dp
                        )
                        .requiredWidth(width = 265.dp))*/
            }
        }
    }

    @Preview
    @Composable
    private fun LoginPreview() {
        Login(Modifier)
    }
}