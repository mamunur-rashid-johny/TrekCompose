package com.techetronventures.trek

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techetronventures.trek.ui.theme.TrekTheme

@Composable
fun OnBoardItem(
    modifier: Modifier = Modifier,
    onBoardItemModel: OnBoardItemModel
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(onBoardItemModel.rawLottie))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(300.dp).background(Color.Transparent),
            progress = { progress },
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(onBoardItemModel.strResTitle),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 22.sp
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(onBoardItemModel.strResDescription),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center
        )

    }
}

@PreviewLightDark
@Composable
private fun OnBoardItemPreview() {
    TrekTheme {
        val data = StaticDataSource.getAllOnBoardList()[3]
        OnBoardItem(
            modifier = Modifier.fillMaxSize(),
            onBoardItemModel = data
        )
    }
}