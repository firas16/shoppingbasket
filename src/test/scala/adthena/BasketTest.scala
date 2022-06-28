package adthena

import adthena.product.{Apple, Bread, Milk}
import org.scalatest.funspec.AnyFunSpec

class BasketTest extends AnyFunSpec {
  describe("Basket") {
    it("should compute price of basket") {
      //Given
      val apples = new Good(Apple, 10) //
      val milk = new Good(Milk, 1)
      val bread = new Good(Bread, 1)
      val basket = new Basket(List(apples, milk, bread))
      //When
      val price = basket.price
      val expected = apples.quantity * apples.product.price + milk.quantity*milk.product.price + bread.quantity * bread.product.price
      //Then
      assert(price == expected)
    }
  }
}
