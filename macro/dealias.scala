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

class dealias extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro dealias.impl
}

object dealias {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    annottees.head.tree match {
      case q"val $lhs : $typeTree = $rhs" =>
        val tpe = c.typecheck(q"{ ??? : $typeTree }").tpe
        val dealiased = tpe.dealias
        c.Expr(q"val $lhs : String = ${dealiased.toString}")
    }
  }
}
