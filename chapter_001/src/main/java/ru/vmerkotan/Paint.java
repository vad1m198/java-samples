package ru.vmerkotan;

/**
* The {@code Paint} class
* @author Vadim Merkotan
* @since  1.0
*/
public class Paint {
	
	/**
	* Builds a piramid with ^ chars
	* with height equal to h param
	*
	* @param h	height of the piramid
	* @return   String which represent the piramid
	*/
	public String piramid(int h) {
		StringBuilder builder = new StringBuilder();
		if(h < 1) return "";
		if(h == 1) return "^";
		
		for(int i = 1; i <= h; i++) {
			//draw spaces before signs
			for(int k = 1; k <= h-i; k++) {
				builder.append(" ");
			}
			//draw ^ signs
			for(int j = 1; j <=i; j++) {
				builder.append("^ ");
			}
			builder.append("\n\r");
		}
		return builder.toString();		
	}
}