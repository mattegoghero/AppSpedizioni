package customcomponents;

import java.awt.Font;

/**
 * Font personalizzato utlizzato nei vari {@link Component} del programma per ridurre il boilerplate code.
 * @author Matteo Federico Goghero - 143143
 */
public class CustomFont extends Font {
	public CustomFont(int size) {
		super("Tahoma", Font.PLAIN, size);
	}
}
