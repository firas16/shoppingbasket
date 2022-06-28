package adthena

import DiscountType.{Discount, FreeItem}

import scala.collection.mutable.ListBuffer

object DiscountEngine {

  def compute(basket: Basket, rule: DiscountRule): ListBuffer[(String, BigDecimal)] = {

    val discounts = new ListBuffer[(String, BigDecimal)]()
    val eligibleProduct = basket.goods.find(good => good.product == rule.constraint.product)
    if (eligibleProduct.nonEmpty) {
      val nbTimes = (eligibleProduct.get.quantity / rule.constraint.quantity).floor
      val productEligibleOffer = basket.goods.find(good => good.product == rule.offer._1)
      if (productEligibleOffer.nonEmpty) {
        rule.offer._2 match {
          case FreeItem => discounts += (
            (rule.toString(),
              Math.min(productEligibleOffer.get.quantity, nbTimes) * productEligibleOffer.get.product.price)
            )
          case Discount => discounts += ((rule.toString(), Math.min(productEligibleOffer.get.quantity, nbTimes) * productEligibleOffer.get.product.price * rule.offer._3 / 100))
        }
      }

    }
    discounts
  }

  def compute(basket: Basket, rules: List[DiscountRule]): ListBuffer[(String, BigDecimal)] = {

    val discounts = new ListBuffer[(String, BigDecimal)]()
    for (rule <- rules) {
      discounts ++= compute(basket, rule)
    }
    discounts
  }
}


