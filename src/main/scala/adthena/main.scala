package adthena

import adthena.product.{Apple, Bread, Milk, Soup}


object shoppingBasket {

  def main(args: Array[String]) = {

    //Currency used
    val currency = Currency.Pound

    // articles bought by customer
    val apples = new Good(Apple, 1) //
    val milk = new Good(Milk, 1)
    val bread = new Good(Bread, 2)
    val soup = new Good(Soup, 2)
    println("----articles and prices-----")
    println("apples price: "+ currency.toString+ apples.product.price )
    println("Milk price: "+ currency.toString+ milk.product.price )
    println("Bread price: "+ currency.toString+ bread.product.price )
    println("Soup price: "+ currency.toString + soup.product.price )

    //customer basket
    println("---------basket 1-------------")
    val basket = new Basket(List(apples, milk, bread))

    // Discount rules
    val rule1 = new DiscountRule(new Good(Apple, 1), (Apple, DiscountType.Discount, 10))
    val rule2 = new DiscountRule(new Good(Soup, 2), (Bread, DiscountType.Discount, 50))

    // calculates discount when applying rule 1
    val discount = DiscountEngine.compute(basket, rule1)

    println("SubTotal: " + currency.toString + basket.price)
    println(discount.map(offer => offer._1 + ": " + offer._2 ).mkString("\n"))
    println("Total  price: ",currency.toString + (basket.price - discount.map(_._2).sum))

    println("---------basket 2-------------")
    // new basket
    val basket2 = new Basket(List(soup, milk, bread))

    // calculates discount applying rule 2
    val discount2 = DiscountEngine.compute(basket2, rule2)

    println("SubTotal: " + currency.toString + basket2.price)
    println(discount2.map(offer => offer._1 + ": " + offer._2 ).mkString("\n"))
    println("Total  price: "+ currency.toString+ (basket2.price - discount2.map(_._2).sum))

    println("---------basket 3-------------")
    // calculate discount applying rule 1 and 2 on basket 3
    val basket3 = new Basket(List(apples, soup, milk, bread))
    val discount3 = DiscountEngine.compute(basket3, List(rule1, rule2))
    println("SubTotal: " + currency.toString + basket3.price)
    println(discount3.map(offer => offer._1 + ": " + offer._2 ).mkString("\n"))
    println("Total  price: "+ currency.toString+ (basket3.price - discount3.map(_._2).sum))
  }
}

