import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.management.relation.RelationServiceNotRegisteredException;

public class ShannonFano {
	public static void main(String[] args) throws IOException {
		//Code sureci baslatiliyor.
		Characters alphabet = initializeFrequencyTable();
		String textToCompress =  ReadFile("textToCode.txt",alphabet);
		String codedText = new String("");
		alphabet = GenerateCodeByUsingShannon(alphabet);
		codedText = Code(alphabet,textToCompress);
		System.out.println("Text 		: "  + textToCompress);
		System.out.println("Coded Text 	: "  + codedText);
		//CODING ENDS.
		
		//Decode sureci baslatiliyor.
		String compressedText = codedText;
		String plainText = new String("");
		Characters alphabet2 = initializeFrequencyTable();
		alphabet2 = GenerateCodeByUsingShannon(alphabet2);//Decode için sadece alfabe ve olasiliklari gönderiyoruz. Ve kod haritasini yeniden olusturuyoruz.
		plainText = DeCode(alphabet,compressedText);
		System.out.println("DeCoded Text	: "  + plainText);
		//DECODING ENDS.
		
		//
		System.out.println("\nAlphabet;\n");
		alphabet.SortByCharacter();
		for (Character c : alphabet )
		{
			System.out.println(c.getCharacter() +" = "+ c.getProbability() + " --> "+ c.getCode());
		}
	}
	
	private static String DeCode(Characters alphabet, String compressedText) {
		String plainText = new String("");
		alphabet.SortByProfitability();
		for ( int x=0; x<=compressedText.length(); x++) {
            for (Character character : alphabet) {
            	if(character.getCode().equals(compressedText.substring(0, x)))
				{
					plainText+=character.getCharacter();
					compressedText=compressedText.substring(x);
					x=0;
				}
			}
        }
		return plainText;
	}
	
	private static String Code(Characters alphabet, String textToCompress) {
		String codedText = new String("");
		for ( int x=0; x<textToCompress.length(); x++) {
            for (Character character : alphabet) {
            	if(character.getCharacter().toCharArray()[0] == String.valueOf(textToCompress.charAt(x)).toCharArray()[0])
				{
					codedText+=character.getCode();
				}
			}
        }
		return codedText;
	}

	private static String ReadFile(String path, Characters alphabet) throws IOException {
		String textOfFile = new String("");
		String regexNoktalamIsaretleri = new String();
		regexNoktalamIsaretleri = "[:punct:]";
		String regexPattern = new String("[^");
		for (Character character : alphabet) {
			regexPattern+=character.getCharacter();
		} 
		regexPattern+="]";
		for (String line : Files.readAllLines(Paths.get(path))) {
			textOfFile+=line.toUpperCase();
		}
		return textOfFile.replaceAll(regexNoktalamIsaretleri, "*").replaceAll(regexPattern, "#");
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
		//alphabet.Add("#", 0.00000001); /* # A şıkkı için tum semboller ve bosluk karakteri icin placeHolder olarak kullanilmıştı Ancak B şıkkına geçince kaldırıldı..*/
		/*
		 * B şıkkı için boşluk, noktalama karakterleri ve bunlarla birlikte turkce karakter dışında kalan tüm karakterler için 3 yeni placeHolder tanımlandı.
		 * Boşuklar için placeHolder " " (boşluk) olarak tanımlandı.
		 * Noktalama işaretleri için placeHolder "*" olarak tanımlandı.
		 * Diğer karakterler için place holder "#" olarak tanımlandı.
		 */
		Character diger		= new Character("#",2.0); //%2 lik dilim
		Character bosluk 	= new Character(" ",5.0); //%5 lik dilim
		Character noktalama	= new Character("*",3.0); //%3 lik dilim
		
		Double totalPropability = new Double(0);
		Double totalDecRange = new Double(0);
		
		for (Character character : alphabet) { 
			totalPropability+=character.getProbability();
		}
		
		totalDecRange=10/totalPropability;
		
		for (Character character : alphabet) { //yeni placeholderlar için olasiliklari duzenle (3 yeni karaktere yer aç)
			character.setProbability(character.getProbability() * totalDecRange);
		}		
		
		alphabet.add(diger);
		alphabet.add(bosluk);
		alphabet.add(noktalama);
		return alphabet;
	}
}