
public class Character {
	private String character = new String();
	private Double probability = new Double(0);
	private String code	= new String("");

	public Character(){ }
	public Character(String chr, Double p) {
		this.character = chr;
		this.probability = p;
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
}