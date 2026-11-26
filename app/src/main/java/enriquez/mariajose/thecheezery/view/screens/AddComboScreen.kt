package enriquez.mariajose.thecheezery.view.screens

import android.database.sqlite.SQLiteException
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductEntity
import enriquez.mariajose.thecheezery.model.data.database.relation.ComboWithProducts
import enriquez.mariajose.thecheezery.viewModel.CheezeryViewModel
import kotlinx.coroutines.launch

@Composable
fun AddComboScreen(
    innerPadding: PaddingValues,
    viewModel: CheezeryViewModel,
    onBack: () -> Unit
) {
    val products by viewModel.products.collectAsState()
    val combos by viewModel.combos.collectAsState()
    val selectedProductIds = remember { mutableStateListOf<Int>() }
    val coroutineScope = rememberCoroutineScope()
    var name by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("Volver")
        }
        Text(
            text = "Crear combo",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it; message = null },
            label = { Text("Nombre del combo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it; message = null },
            label = { Text("Precio") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Text("Selecciona los productos", fontWeight = FontWeight.SemiBold)
        if (products.isEmpty()) {
            Text("Cargando productos...")
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(products, key = { it.id }) { product ->
                    ProductSelectionRow(
                        product = product,
                        checked = product.id in selectedProductIds,
                        onCheckedChange = { checked ->
                            if (checked) {
                                if (product.id !in selectedProductIds) {
                                    selectedProductIds.add(product.id)
                                }
                            } else {
                                selectedProductIds.remove(product.id)
                            }
                            message = null
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                val parsedPrice = price.replace(',', '.').toFloatOrNull()
                when {
                    name.isBlank() -> message = "Escribe un nombre para el combo."
                    parsedPrice == null || !parsedPrice.isFinite() || parsedPrice <= 0f ->
                        message = "Escribe un precio válido mayor que cero."
                    selectedProductIds.isEmpty() ->
                        message = "Selecciona al menos un producto."
                    else -> coroutineScope.launch {
                        try {
                            viewModel.createCombo(
                                name = name,
                                price = parsedPrice,
                                productIds = selectedProductIds.toList()
                            )
                            name = ""
                            price = ""
                            selectedProductIds.clear()
                            message = "Combo guardado."
                        } catch (error: SQLiteException) {
                            message = "No se pudo guardar el combo: ${error.localizedMessage}"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar combo")
        }
        message?.let {
            Text(
                text = it,
                color = if (it == "Combo guardado.") Color(0xFF2E7D32) else MaterialTheme.colorScheme.error
            )
        }
        Text("Combos guardados", fontWeight = FontWeight.SemiBold)
        if (combos.isEmpty()) {
            Text("Aún no hay combos guardados.", color = Color.Gray)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(combos, key = { it.combo.id }) { combo ->
                    SavedComboRow(combo)
                }
            }
        }
    }
}

@Composable
private fun ProductSelectionRow(
    product: ProductEntity,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Column(modifier = Modifier.weight(1f)) {
            Text(product.name)
            Text(product.category, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Text("$${product.price}")
    }
}

@Composable
private fun SavedComboRow(comboWithProducts: ComboWithProducts) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            text = "${comboWithProducts.combo.name} - $${comboWithProducts.combo.price}",
            fontWeight = FontWeight.Medium
        )
        Text(
            text = comboWithProducts.products.joinToString { it.name },
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}
