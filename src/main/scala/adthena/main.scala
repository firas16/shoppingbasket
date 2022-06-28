package adthena

import adthena.product.{Apple, Bread, Milk, Soup}


object Hello {
  def main(args: Array[String]) = {

    val currency = Currency.Pound
    val apples = new Good(Apple, 1) //
    val milk = new Good(Milk, 1)
    val bread = new Good(Bread, 1)
    val soup = new Good(Soup, 2)
    val basket = new Basket(List(apples, milk, bread))

    println("apples price: "+ apples.product.price)
    println("Milk price: "+ milk.product.price)
    println("Bread price: "+ bread.product.price)
    println("Soup price: "+ soup.product.price)

    val rule1 = new DiscountRule(new Good(Apple, 1), (Apple, DiscountType.Discount, 10))
    val rule2 = new DiscountRule(new Good(Soup, 2), (Bread, DiscountType.Discount, 50))
    val discount = DiscountEngine.compute(basket, rule1)

    println("SubTotal  price: " + currency.toString + basket.price)
    println(discount.map(offer => offer._1 + ": " + offer._2 ).mkString("\n"))
    println("Total  price: ",currency.toString + (basket.price - discount.map(_._2).sum))

    val basket2 = new Basket(List(soup, milk, bread))
    val discount2 = DiscountEngine.compute(basket2, rule2)

    println("SubTotal  price: " + currency.toString + basket2.price)
    println(discount2.map(offer => offer._1 + ": " + offer._2 ).mkString("\n"))
    println("Total  price: "+ currency.toString+ (basket2.price - discount2.map(_._2).sum))
  }
}

