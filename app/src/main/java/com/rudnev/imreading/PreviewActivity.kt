package com.rudnev.imreading

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rudnev.imreading.ui.theme.ImReadingTheme
import com.rudnev.imreading.utils.FirstShowActivityUtil

class PreviewActivity : ComponentActivity() {
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

    @SuppressLint("InvalidColorHexValue")
    @Composable
    fun StartForm(modifier: Modifier = Modifier) {
        val context = androidx.compose.ui.platform.LocalContext.current

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
                    .offset(x = 2.dp,
                        y = (-146).dp)
                    .requiredWidth(width = 610.dp)
                    .requiredHeight(height = 476.dp)
                    .blur(radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .background(color = Color(0xff74beed)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-70).dp,
                        y = 276.dp)
                    .requiredWidth(width = 694.dp)
                    .requiredHeight(height = 904.dp)
                    .blur(radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .background(color = Color(0xff74beed)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 2.dp,
                        y = 222.dp)
                    .requiredWidth(width = 738.dp)
                    .requiredHeight(height = 400.dp)
                    .blur(radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .background(color = Color.White))
            Text(
                text = "CyberLearn",
                color = Color.White,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = 100.dp))
            Text(
                text = "Идеальная обучающая система",
                color = Color.White, // 0xFF141044
                style = TextStyle(
                    color = Color(0x22734),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = 135.dp))
            Image(
                painter = painterResource(id = R.drawable.boyreadingabookimage),
                contentDescription = "boy reading a book",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .align(alignment = Alignment.Center))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 38.dp,
                        y = 728.dp)
                    .requiredWidth(width = 314.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Button(
                    onClick = {
                        FirstShowActivityUtil.markFirstActivityShown(context);
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF141044)),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 0.dp,
                            y = 0.dp)
                        .requiredWidth(width = 314.dp)
                        .requiredHeight(height = 56.dp)){ }
                Text(
                    text = "Начать учиться!",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(y = 12.dp))
            }
        }
    }

    @Preview()
    @Composable
    private fun StartFormPreview() {
        StartForm(Modifier)
    }
}