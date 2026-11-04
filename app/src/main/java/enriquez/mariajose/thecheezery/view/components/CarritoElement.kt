package enriquez.mariajose.thecheezery.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.data.oreoMilkshake
import enriquez.mariajose.thecheezery.domain.Product
import enriquez.mariajose.thecheezery.ui.theme.Very_purple

@Composable
fun CarritoElement(product: Product, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            color = Color.DarkGray,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "$${product.price}",
            color = Very_purple,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )

        IconButton(
            onClick = { onRemove() },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.delete),
                contentDescription = "Eliminar",
                tint = Very_purple
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CarritoElementPreview() {
    CarritoElement(oreoMilkshake, {})
}