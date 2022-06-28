package adthena

import adthena.product.{Apple, Bread, Milk}


class DiscountEngineTest extends org.scalatest.funspec.AnyFunSpec {
  describe("DiscountEngine") {

    it("should compute a discount of 10% with apple discount rule") {
      //Given
      val apples = new Good(Apple, 1) //
      val milk = new Good(Milk, 1)
      val bread = new Good(Bread, 1)
      val basket = new Basket(List(apples, milk, bread))
      val rule1 = DiscountRule(new Good(Apple, 1), (Apple, DiscountType.Discount, 10))
      //When
      val discounts = DiscountEngine.compute(basket, rule1)
      val expected = Apple.price / 10
      //Then
      assert(discounts.map(_._2).sum == expected)
    }

    it("should add two free items (milk) to the discount sum") {
      //Given
      val apples = new Good(Apple, 4)
      val milk = new Good(Milk, 2)
      val basket = new Basket(List(apples, milk))
      val rule = DiscountRule(new Good(Apple, 2), (Milk, DiscountType.FreeItem, 1))
      //When
      val discounts = DiscountEngine.compute(basket, rule)
      val expected = 2*Milk.price
      //Then
      assert(discounts.map(_._2).sum == expected)
    }

    it("should not output any discount (not enough apples to get milk for free)") {
      //Given
      val apples = new Good(Apple, 1) //
      val milk = new Good(Milk, 2)
      val basket = new Basket(List(apples, milk))
      val rule = DiscountRule(new Good(Apple, 2), (Milk, DiscountType.FreeItem, 1))
      //When
      val discounts = DiscountEngine.compute(basket, rule)
      val expected = 0
      //Then
      assert(discounts.map(_._2).sum == expected)
    }
  }
}
