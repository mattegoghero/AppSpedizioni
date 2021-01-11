package data;

import java.awt.Color;
import java.io.Serializable;

/**
 * Rappresenta i possibili stati di una qualsiasi spedizione.
 * @author Matteo Federico Goghero - 143143
 * @see StatoSpedizioneInterface
 */
public enum StatoSpedizione implements StatoSpedizioneInterface, Serializable {
	IN_PREPARAZIONE{
		@Override
		public Color getColor() {
			return Color.lightGray;
		}
		@Override
		public String getName() {
			return "IN PREPARAZIONE";
		}
		@Override
		public boolean isForNormal() {
			return true;
		}
	},
	IN_TRANSITO{
		@Override
		public Color getColor() {
			return Color.yellow;
		}
		@Override
		public String getName() {
			return "IN TRANSITO";
		}
		@Override
		public boolean isForNormal() {
			return true;
		}
	},
	RICEVUTA{
		@Override
		public Color getColor() {
			return Color.green;
		}
		@Override
		public String getName() {
			return "RICEVUTA";
		}
		@Override
		public boolean isForNormal() {
			return true;
		}
	},
	FALLITA{
		@Override
		public Color getColor() {
			return Color.red;
		}
		@Override
		public String getName() {
			return "FALLITA";
		}
		@Override
		public boolean isForNormal() {
			return true;
		}
	},
	RIMBORSO_RICHIESTO{
		@Override
		public Color getColor() {
			return Color.pink;
		}
		@Override
		public String getName() {
			return "RIMBORSO RICHIESTO";
		}
		@Override
		public boolean isForNormal() {
			return false;
		}
	},
	RIMBORSO_EROGATO{
		@Override
		public Color getColor() {
			return Color.magenta;
		}
		@Override
		public String getName() {
			return "RIMBORSO EROGATO";
		}
		@Override
		public boolean isForNormal() {
			return false;
		}
	}
}


