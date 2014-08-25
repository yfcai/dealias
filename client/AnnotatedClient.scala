/** Client code to show that @dealias does not work even if
  * the synonym declaration is moved to a superclass.
  *
  * The @dealiasClass annotation scans all class members
  * for declarations of the form
  *
  *   val someName : someType = ?
  *
  * and convert them into
  *
  *   val someName : String = <name of `someType` expanded>
  */

trait SynonymDeclaration2 {
  type Synonym = Int
}

@dealiasClass
object AnnotatedClient extends SynonymDeclaration2 {
  // produces TypeCheckException
  // val classScope : Synonym = ?
}
