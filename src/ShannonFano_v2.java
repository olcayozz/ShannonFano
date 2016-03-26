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

		System.out.println("Text 		: "  + textToCode);
		System.out.println("Coded Text 	: "  + codedText);
		
		//alphabet.SortByScore();
		
		/*
		for (Character c : alphabet )
		{
			System.out.println(c.getScore() + " | " + c.getCharacter() +" = "+ c.getProbability() + " --> "+ c.getCode());
		}
		*/
	}
	private static String Code(Characters alphabet, String textToCompress) {
		for(Character c : alphabet)
		{
			textToCompress = textToCompress.replaceAll(Pattern.quote(c.getCharacter()), c.getCode());
		}
		return textToCompress;
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
		for(char c : readedText.toCharArray())
		{
			word+=String.valueOf(c);
			Character chr = new Character(word,0.0);
			
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

