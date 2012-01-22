package main.scala

import scala.annotation.tailrec

object Style {
  private val CSI = "\033["
  val ENDC = CSI + "0m"
  def addStyle(content: String, style: String = Style.NONE) = "%s %s".format(style, content)

  val NONE = ""
  val BOLD = CSI + "1m"
}

object Formatter {
  val CSI = "\033["
  val ENDC = CSI + "0m"

  // predefined formatting options
  // ex: {BOLD}{stuff}{/BOLD} outputs {stuff} as BOLD.
  val predefStyles = Map(
    "BOLD" -> (CSI+"1m"),
    "/BOLD" -> ENDC
  )
  /**
   * Replace templates of the form {key} in the input String with values from the Map.
   *
   * @param text the String in which to do the replacements
   * @param templates a Map from Symbol (key) to value
   * @returns the String with all occurrences of the templates replaced by their values
   */
   def replaceTemplates(text: String,
                      templates: Map[String, String]): String = {
     val builder = new StringBuilder
     val textLength = text.length

      @tailrec
      def loop(text: String): String = {
        if (text.length == 0) builder.toString
       else if (text.startsWith("{")) {
         val brace = text.indexOf("}")
         if (brace < 0) builder.append(text).toString
         else {
           val replacement = templates.get(text.substring(1, brace)).orNull
           if (replacement != null) {
             builder.append(replacement)
             loop(text.substring(brace + 1))
           } else {
             builder.append("{")
             loop(text.substring(1))
           }
         }
        } else {
          val brace = text.indexOf("{")
          if (brace < 0) builder.append(text).toString
          else {
           builder.append(text.substring(0, brace))
            loop(text.substring(brace))
          }
        }
      }
     loop(text)
  }
  
  def parse(input: String, params: Map[String, String]) = {
    val actualParams = predefStyles ++ params 
    replaceTemplates(input, actualParams)
  }
}
// vim: set ts=4 sw=4 et:
