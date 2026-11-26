package enriquez.mariajose.thecheezery.model.data.database.entity

import android.content.Context
import enriquez.mariajose.thecheezery.domain.Product

fun Product.toEntity(context: Context, category: String): ProductEntity {
    return ProductEntity(
        name = name,
        price = price,
        image = context.resources.getResourceEntryName(image),
        description = description,
        category = category
    )
}

fun ProductEntity.toProduct(context: Context): Product {
    val imageResource = image?.let {
        context.resources.getIdentifier(it, "drawable", context.packageName)
    } ?: 0
    require(imageResource != 0) { "No se encontró la imagen guardada para $name." }

    return Product(
        name = name,
        price = price,
        image = imageResource,
        description = description
    )
}
