package adthena

import DiscountType.{Discount, DiscountType, FreeItem}
import adthena.product.Product

case class DiscountRule(constraint: Good, offer: (Product, DiscountType, Double)){
  override def toString: String = {
    val txt = this.offer._2 match {
      case Discount => this.offer._1 + " " + this.offer._3 + "%" + " off"
      case FreeItem => this.offer._1 + " " + this.offer._3 + " " + this.offer._2
    }
    txt
  }
}
