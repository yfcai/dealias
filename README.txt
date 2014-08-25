Minimal working example of dealiasing not working in class scope.

Usage:
1. Run
2. Look at code

===
Run
===

[$PWD]$ sbt
> client/run
[info] Updating {...}
...
[info] Compiling 1 Scala source to ...
[info] Compiling 2 Scala sources to ...
[info] Running Client
Inside a block, `Synonym` dealiases to `Int`.
[success] Total time: 6 s, completed Aug 22, 2014 5:57:18 PM

============
Look at code
============

Client code lives in client/Client.scala and client/SeparatedClient.scala

Macro is defined in macro/dealias.scala

Tried putting the annotation on top of the entire class.
Does not work, either. The attempt happens in
macro/dealiasClass.scala and client/AnnotatedClient.scala.
