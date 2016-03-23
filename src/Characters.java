import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Characters extends ArrayList<Character> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Characters()
	{

	}
	public void Add(String chr, Double p)
	{
		this.add(new Character(chr,p));
	}
	public void SortByProfitability()
	{
		Collections.sort(this, new Comparator<Character>() {
			public int compare(Character c1, Character c2){
			return c1.getProbability().compareTo(c2.getProbability());
			}
		});
	}
	public void SortByCharacter()
	{
		Collections.sort(this, new Comparator<Character>() {
			public int compare(Character c1, Character c2){
			return c1.getCharacter().compareTo(c2.getCharacter());
			}
		});
	}
}