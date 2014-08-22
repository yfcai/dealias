/** Client code to show that @dealias does not work even if
  * the synonym declaration is moved to a superclass.
  */

trait SynonymDeclaration {
  type Synonym = Int
}

object SeparatedClient extends SynonymDeclaration {
  /** exception during macro expansion:
    * scala.reflect.macros.TypecheckException: not found: type Synonym
    */
  // @dealias val classScope : Synonym = ?

  /** works fine if @dealias is used inside a block */
  val blockScope = {
    @dealias val blockScope : Synonym = ?
    blockScope
  }

  def run() = println(s"Inside a block, `Synonym` dealiases to `$blockScope`.")
}
