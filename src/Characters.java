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
		this.add(new Character(chr,p,this.size()+1));
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
	public void SortByScore()//C şıkkı için.
	{
		Collections.sort(this, new Comparator<Character>() {
			public int compare(Character c1, Character c2){
			return Integer.compare(c2.getScore(),c1.getScore());
			}
		});
	}

	public void SortByIndex()//C şıkkı için.
	{
		Collections.sort(this, new Comparator<Character>() {
			public int compare(Character c1, Character c2){
			return Integer.compare(c1.getIndex(),c2.getIndex());
			}
		});
	}
}