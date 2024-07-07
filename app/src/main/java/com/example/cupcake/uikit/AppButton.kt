package com.example.cupcake.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cupcake.theme.AppColors

@Composable
fun AppButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = AppColors.pink400),
        modifier = modifier.defaultMinSize(minWidth = 250.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = text.uppercase())
    }
}

@Composable
fun AppSecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White),
        modifier = modifier.defaultMinSize(minWidth = 250.dp),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Text(text = text.uppercase(), color = AppColors.pink400)
    }
}