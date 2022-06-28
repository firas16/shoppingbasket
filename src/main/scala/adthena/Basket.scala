package adthena

import Good.price

class Basket(var goods: List[Good]) {
    def price: BigDecimal = goods.map(x => Good.price(x)).sum
}
