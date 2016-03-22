import java.util.HashMap;

public class ShannonFano {
	public static void main(String[] args) {
		String textToCompress = new String("Olcay Özyılmaz");
		HashMap<String, Double> harfOlasilikTablosu = initializeFrequencyTable();
		
	}

	private static HashMap<String, Double> initializeFrequencyTable() {
    	HashMap<String, Double> harfOlasilikTablosu = new HashMap<>();
    	harfOlasilikTablosu.put("A", 11.92);
		harfOlasilikTablosu.put("B", 2.844);
		harfOlasilikTablosu.put("C", 0.963);
		harfOlasilikTablosu.put("Ç", 1.156);
		harfOlasilikTablosu.put("D", 4.706);
		harfOlasilikTablosu.put("E", 8.912);
		harfOlasilikTablosu.put("F", 0.461);
		harfOlasilikTablosu.put("G", 1.253);
		harfOlasilikTablosu.put("Ğ", 1.125);
		harfOlasilikTablosu.put("H", 1.212);
		harfOlasilikTablosu.put("I", 5.114);
		harfOlasilikTablosu.put("i", 8.6);
		harfOlasilikTablosu.put("J", 0.034);
		harfOlasilikTablosu.put("K", 4.683);
		harfOlasilikTablosu.put("L", 5.922);
		harfOlasilikTablosu.put("M", 3.752);
		harfOlasilikTablosu.put("N", 4.487);
		harfOlasilikTablosu.put("O", 2.476);
		harfOlasilikTablosu.put("Ö", 0.777);
		harfOlasilikTablosu.put("P", 0.886);
		harfOlasilikTablosu.put("R", 6.722);
		harfOlasilikTablosu.put("S", 3.014);
		harfOlasilikTablosu.put("Ş", 1.78);
		harfOlasilikTablosu.put("T", 3.014);
		harfOlasilikTablosu.put("U", 3.235);
		harfOlasilikTablosu.put("Ü", 1.854);
		harfOlasilikTablosu.put("V", 0.959);
		harfOlasilikTablosu.put("Y", 3.336);
		harfOlasilikTablosu.put("Z", 1.5);
		return harfOlasilikTablosu;
	}
}
