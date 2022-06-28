package adthena

import adthena.product.Product

case class Good(product: Product, quantity: Double)

object Good {
  def price(good: Good) = good.product.price * good.quantity
}