package com.techetronventures.trek

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techetronventures.trek.ui.theme.TrekTheme


@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    onSignup: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(bottom = 40.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {
                onSignup()
            }
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary) ,
            onClick = {
                onLogin()
            }
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

    }


}


@PreviewLightDark
@Composable
fun AuthButtonPreview(modifier: Modifier = Modifier) {
    TrekTheme {
        AuthButton(onSignup = {}, onLogin = {})
    }
}