/** @dealias annotation macro
  *
  * Input:
  *   @dealias val x : SYNONYM = <rhs ignored>
  *
  * Output:
  *   val x : String = <name of the type that SYNONYM stands for>
  */

import language.experimental.macros
import scala.reflect.macros.blackbox.Context
import scala.annotation.StaticAnnotation

class dealiasClass extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro dealiasClass.impl
}

object dealiasClass {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    annottees.head.tree match {
      case q"object $objName extends ..$superclasses { ..$members }" =>
        val newMembers = members map {
          case q"val $lhs : $typeTree = ?" =>
            val tpe = c.typecheck(q"{ ??? : $typeTree }").tpe
            val dealiased = tpe.dealias
            q"val $lhs : String = ${dealiased.toString}"

          case otherwise =>
            otherwise
        }
        c.Expr(q"object $objName extends ..$superclasses { ..$newMembers }")
    }
  }
}
