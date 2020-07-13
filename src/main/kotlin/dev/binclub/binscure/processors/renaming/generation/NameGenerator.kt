package dev.binclub.binscure.processors.renaming.generation

import dev.binclub.binscure.configuration.ConfigurationManager.rootConfig

/**
 * @author cookiedragon234 22/Jan/2020
 */
open class NameGenerator(val prefix: String = "") {
	companion object {
		val CHARSET = rootConfig.remap.dictionary.toCharArray()
		
		/**
		 * @param index A unique positive integer
		 * @param charset A dictionary to permutate through
		 * @return A unique constants from for the given integer using permutations of the given charset
		 */
		private fun intToStr(index: Int, charset: CharArray): String {
			var i = index
			val radix = charset.size
			val buf = CharArray(65)
			var charPos = 64
			if (i > 0)
				i = -i
			
			while (i <= -radix) {
				buf[charPos--] = charset[(-(i % radix))]
				i /= radix
			}
			buf[charPos] = charset[-i]
			
			return String(buf, charPos, 65 - charPos)
		}
	}
	protected var index = 0
	
	open fun uniqueRandomString() = prefix + intToStr(index++, CHARSET)
}
