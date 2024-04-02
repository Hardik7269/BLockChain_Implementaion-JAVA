package block;

import java.security.SecureRandom;
import java.security.Timestamp;
import java.util.Date;

import stringutil.StringUtil;

public class Block {
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //To set Hash for other values
	}
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	public void mineBlock(int difficulty) {
	    String target = generateRandomString(difficulty);
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		
		System.out.println("Block Mined!!! : " + hash);
	}
	
	public String generateRandomString(int difficulty) {
		 String characters = "000000";
		    SecureRandom random = new SecureRandom();
		    StringBuilder sb = new StringBuilder(difficulty);
		    for (int i = 0; i < difficulty; i++) {
		        int randomIndex = random.nextInt(characters.length());
		        sb.append(characters.charAt(randomIndex));
		    }
		    return sb.toString();
	}
}
