package adthena

object Currency extends  Enumeration {
  type Currency = Value
  val Dollar = Value("$")
  val Euro = Value("€")
  val Pound = Value("£")

}
