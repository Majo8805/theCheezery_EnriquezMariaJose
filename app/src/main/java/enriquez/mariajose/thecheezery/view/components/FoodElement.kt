package enriquez.mariajose.thecheezery.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.data.oreoMilkshake
import enriquez.mariajose.thecheezery.domain.Product
import enriquez.mariajose.thecheezery.ui.theme.DisabledGrey
import enriquez.mariajose.thecheezery.ui.theme.Less_Purple
import enriquez.mariajose.thecheezery.ui.theme.PurpleGrey40
import enriquez.mariajose.thecheezery.ui.theme.Very_purple

@Composable
fun FoodComponent(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.name,
                color = Less_Purple,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            if (product.description != null) {
                Text(
                    text = product.description,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$${product.price}",
            color = Very_purple,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(16.dp))

        FilledIconButton(
            onClick = { },
            modifier = Modifier.size(40.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Very_purple,
                contentColor = Color.White,
                disabledContainerColor = DisabledGrey,
                disabledContentColor = PurpleGrey40
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "Agregar al carrito",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FoodComponentPreview() {
    FoodComponent(oreoMilkshake)
}