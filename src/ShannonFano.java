import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShannonFano {
	public static void main(String[] args) {
		String textToCompress = new String("Olcay Özyılmaz");
		Characters alphabet = initializeFrequencyTable();
		alphabet = GenerateCodeByUsingShannon(alphabet);
		
		
		
		for (Character c : alphabet)
		{
			System.out.println(c.getCharacter() +" = "+ c.getProbability() + " --> "+ c.getCode());
		}
	}
	
	public static Characters GenerateCodeByUsingShannon(Characters alphabet)
	{
		Double olasiliklarToplami = new Double(0); //recursive fonksiyon'da sub arrayler için toplam alacak.
		Double split_p = new Double(0);
		Characters upChars = new Characters(), downChars = new Characters();
		alphabet.SortByProfitability();
		if(alphabet.size()>1){
			for (Character c : alphabet)
			{
				olasiliklarToplami+=c.getProbability();
			}
			split_p = olasiliklarToplami/2;
			
			olasiliklarToplami = 0.0;
			for (Character c : alphabet)
			{
				olasiliklarToplami+=c.getProbability();
				if(olasiliklarToplami>split_p)
				{
					c.addBit("0");
					downChars.add(c);
				}else
				{
					c.addBit("1");
					upChars.add(c);
				}
			}
			GenerateCodeByUsingShannon(downChars);
			GenerateCodeByUsingShannon(upChars);
		}else
		{
			return alphabet;
		}
		//alphabet.SortByProfitability();
		return alphabet;
	}
	
	private static Characters initializeFrequencyTable() {
		Characters alphabet = new Characters();
    	alphabet.Add("A", 11.92);
		alphabet.Add("B", 2.844);
		alphabet.Add("C", 0.963);
		alphabet.Add("Ç", 1.156);
		alphabet.Add("D", 4.706);
		alphabet.Add("E", 8.912);
		alphabet.Add("F", 0.461);
		alphabet.Add("G", 1.253);
		alphabet.Add("Ğ", 1.125);
		alphabet.Add("H", 1.212);
		alphabet.Add("I", 5.114);
		alphabet.Add("İ", 8.6);
		alphabet.Add("J", 0.034);
		alphabet.Add("K", 4.683);
		alphabet.Add("L", 5.922);
		alphabet.Add("M", 3.752);
		alphabet.Add("N", 4.487);
		alphabet.Add("O", 2.476);
		alphabet.Add("Ö", 0.777);
		alphabet.Add("P", 0.886);
		alphabet.Add("R", 6.722);
		alphabet.Add("S", 3.014);
		alphabet.Add("Ş", 1.78);
		alphabet.Add("T", 3.014);
		alphabet.Add("U", 3.235);
		alphabet.Add("Ü", 1.854);
		alphabet.Add("V", 0.959);
		alphabet.Add("Y", 3.336);
		alphabet.Add("Z", 1.5);
		return alphabet;
	}
}