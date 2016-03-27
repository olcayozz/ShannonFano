
public class Character {
	private String character = new String();
	private Double probability = new Double(0);
	private String code	= new String("");
	private int frequancy = 0;//C şıkkı için eklendi.
	private int index = 0; //C şıkkı için eklendi

	public Character(){ }
	public Character(String chr, Double p, int index) {
		this.character = chr;
		this.probability = p;
		this.index = index;
	}
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void addBit(String bit) {
		this.code = this.code + bit;
	}
	public int getFrequancy() {
		return frequancy;
	}
	public void setFrequancy(int frequancy) {
		this.frequancy = frequancy;
	}
	public int getScore() {
		return this.frequancy*this.character.length();
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}