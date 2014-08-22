/** Client code to show that @dealias works inside a block
  * but does not work in class scope.
  */

object Client extends App {
  type Synonym = Int

  /** exception during macro expansion:
    * scala.reflect.macros.TypecheckException: not found: type Synonym
    */
  // @dealias val classScope : Synonym = ?

  /** works fine if @dealias is used inside a block */
  val blockScope = {
    @dealias val blockScope : Synonym = ?
    blockScope
  }

  println(s"Inside a block, `Synonym` dealiases to `$blockScope`.")
}
