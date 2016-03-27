import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShannonFano_v2 {
	public static void main(String[] args) throws IOException{
		String textToCode = ReadFile("textToCode.txt");
		Characters alphabet = getAlphabetFromText(textToCode);
		alphabet = GenerateCodeByUsingShannon(alphabet);
		String codedText = Code(alphabet,textToCode);

		
		String decodedText = DeCode(alphabet,codedText);

		System.out.println("Text         : "  + textToCode);
		System.out.println("\nCoded Text   : "  + codedText);
		System.out.println("\nDecoded Text : "  + decodedText);
		
		alphabet.SortByIndex();
/*
		System.out.println("\nAlphabet;\n");
		for (Character c : alphabet )
		{
			System.out.println(c.getIndex()-1 + " | " + c.getCharacter() +" = "+ c.getProbability() + " --> "+ c.getCode());
		}
*/
	}
	private static String DeCode(Characters alphabet, String compressedText) {
		String plainText = new String(""), word= new String("");
		alphabet.SortByIndex();

		int prev_word_length_finish_index=-1;
		for(int i = prev_word_length_finish_index+1;i<compressedText.length();i++)
		{	
			word+=String.valueOf(compressedText.toCharArray()[i]);
			prev_word_length_finish_index++;
			for(Character chr : alphabet)
			{
				if(chr.getCode().equals(word))
				{
					plainText+=chr.getCharacter();
					i=prev_word_length_finish_index;
					word="";
					break;
				}
			}
		}		
		return plainText;
	}
	private static String Code(Characters alphabet, String textToCompress) {
		String word= new String(""),next_word= new String(""), codedText=new String(""), code = new String("");
		alphabet.SortByIndex();
		boolean nexWordFounded=false;
		
		for(int i = 0; i<textToCompress.length() ;i++)
		{
			int bestMatchScore=0;
			nexWordFounded=false;
			word+=String.valueOf(textToCompress.toCharArray()[i]);
			if(i+1<textToCompress.length()) next_word=word+String.valueOf(textToCompress.toCharArray()[i+1]); else next_word="";
			for(Character chr : alphabet)
			{
				if(nexWordFounded) break;
				if(chr.getCharacter().equals(next_word))
				{
					nexWordFounded=true;
				} else if(chr.getCharacter().equals(word)&& bestMatchScore<=chr.getScore())
				{
					code=chr.getCode();
					bestMatchScore=chr.getScore();
				}
			}
			if(!nexWordFounded) {codedText+=code; word=""; };
		}
		return codedText;
	}
	private static Characters GenerateCodeByUsingShannon(Characters alphabet)
	{
		Double olasiliklarToplami = new Double(0); 
		Double split_p = new Double(0);
		Characters upChars = new Characters(), downChars = new Characters();
		alphabet.SortByScore();
		if(alphabet.size()>1){
			for (Character c : alphabet)
			{
				olasiliklarToplami+=c.getScore();
			}
			split_p = olasiliklarToplami/alphabet.size();
			
			int i = 0;
			for (Character c : alphabet)
			{
				i++;
				if(c.getScore()<split_p)
				{
					c.addBit("0");
					downChars.add(c);
				}else if(c.getScore()==split_p)
				{
					if(i%2==0) {
						c.addBit("1");
						upChars.add(c);
					}
					else{
						c.addBit("0");
						downChars.add(c);
					}
				}
				else
				{
					c.addBit("1");
					upChars.add(c);
				}
			}
			GenerateCodeByUsingShannon(downChars);
			GenerateCodeByUsingShannon(upChars);
		}
		return alphabet;
	}
	private static String ReadFile(String pathToText) throws IOException {
	
		String readedText = new String("");
		for (String line : Files.readAllLines(Paths.get(pathToText))) {
			readedText+=line;
		}
		return readedText;
	}
	private static Characters getAlphabetFromText(String readedText){	
		
		Characters alphabet = new Characters();
		String word = new String();
		for(char c : readedText.toCharArray())//initialCharacters
		{
			Character chr = new Character(String.valueOf(c), 0.0, alphabet.size()+1);
			chr.setFrequancy(0);
			boolean alreadyInAlphabet=false;
			for (Character cInAlphabet : alphabet) {
				if(cInAlphabet.getCharacter().equals(chr.getCharacter())) {alreadyInAlphabet=true; break;}
			}
			if(!alreadyInAlphabet) alphabet.add(chr);
		}
		
		for(char c : readedText.toCharArray())
		{
			word+=String.valueOf(c);
			Character chr = new Character(word,0.0,alphabet.size()+1);
			
			if(alphabet.isEmpty())
			{
				String pattern = Pattern.quote(word);
				Pattern p = Pattern.compile(pattern);
		        Matcher  m = p.matcher(readedText);
		        int frequancyCount=0;
		        
		        while (m.find())
		        	frequancyCount++;
		        
		        chr.setFrequancy(frequancyCount);
				alphabet.add(chr);
				word=String.valueOf(c);
			}
			else
			{
				int i = 0;
				boolean alreadyInAlphabet = false;
				do
				{
					if(alphabet.get(i).getCharacter().equals(chr.getCharacter()))
					{
						alreadyInAlphabet = true;
						break;
					}
				i++;
				}while(i<alphabet.size());
				
				if(!alreadyInAlphabet){
					String pattern = Pattern.quote(word);
					Pattern p = Pattern.compile(pattern);
			        Matcher  m = p.matcher(readedText);
			        int frequancyCount=0;
			        
			        while (m.find())
			        	frequancyCount++;
			        
			        chr.setFrequancy(frequancyCount);
					alphabet.add(chr);
					word=String.valueOf(c);
				}
			}
		}
		return alphabet;
	}
}

