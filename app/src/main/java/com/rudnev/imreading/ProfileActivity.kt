package com.rudnev.imreading

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.rudnev.imreading.models.User
import com.rudnev.imreading.services.ProfileService
import com.rudnev.imreading.ui.theme.ImReadingTheme
import kotlinx.coroutines.launch


class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImReadingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartFormPreview()
                }
            }
        }
    }
}

/*
fun ProfileForm(user: User, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(data = user.photo), //painterResource(id = user.avatar),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(user.first_name + " " + user.last_name, color = Color.Black, fontWeight = FontWeight.Bold)
            Text("Уровень: ${round((user.experience/100).toDouble())}", color = Color.Black)
        }

        Text("Профиль", color = Color(0xff263570), style = TextStyle(fontWeight = FontWeight.Bold))

        Box(
            modifier = modifier
                .padding(top = 8.dp)
                .requiredWidth(width = 315.dp)
                .requiredHeight(height = 87.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 27.dp)
                    .requiredWidth(width = 180.dp)
                    .requiredHeight(height = 27.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xff4f35e9),
                            1f to Color.White,
                            start = Offset(0f, 13f),
                            end = Offset(180f * 0.8f, 13f)
                        )
                    )
            )
        }

        */
/* ------------------------------------------------ *//*


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
                Text("Главная", color = Color.Black)
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
                        painter = painterResource(id = R.drawable.customer),
                        contentDescription = "Customer",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }
    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm(user: User, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val exp = user.experience
    val needExp = exp + (100 - (exp - (exp/100)*100))
    val progress = ((exp - (exp/100)*100)/10).toFloat()


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(){
            Box(){
                Image(
                    painter = rememberImagePainter(data = user.photo),
                    contentDescription = "User Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
            Box(){
                Text(
                    user.first_name + " " + user.last_name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                )

                Text(
                    text = "Опыт: " + exp + "/" + needExp,
                    color = Color.DarkGray,
                    style = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 64.dp)
                )
            }
        }

        Box() {
            LinearProgressIndicator(
                progress = ((10-progress)/10).toFloat(),
                color = Color(0xff4f35e9),
                modifier = Modifier
                    .width(700.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }


/*                .padding(start = 4.dp)
                .requiredWidth(width = 100.dp)
                .requiredHeight(height = 100.dp)*/

        Box() {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    ) {
                        append("Ваш уровень:")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(" ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    ) {
                        append(Math.round((user.experience / 100).toDouble()).toString())
                    }
                },
                modifier = Modifier
                    .padding(top = 4.dp)
            )
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
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.homepage),
                        contentDescription = "Home Page",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Text(
                    "Главная",
                    color = Color(0xff263570),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
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


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun StartFormPreview() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(context) {
        scope.launch {
            val profileService = ProfileService(context)
            user = profileService.get()
        }
    }

    user?.let { Log.w("Debug", it.first_name) }
    user?.let { ProfileForm(it, Modifier) }
}