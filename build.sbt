name := "maze"

version := "0.1"

seq(ProguardPlugin.proguardSettings :_*)

proguardOptions += keepAllScala

proguardOptions += keepMain("main.scala.main")
