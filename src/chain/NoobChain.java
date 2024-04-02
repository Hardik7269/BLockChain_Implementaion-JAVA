package chain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import block.Block;

public class NoobChain {

	public static ArrayList<Block> blockChain = new ArrayList<Block>();
	final static int difficulty = 1;

	public static void main(String[] args) {
		// First Block is called genesis Block [Have no previous Hash]

		blockChain.add(new Block("Hello, I'm First Block", "0"));
		System.out.println("Mining Block 1.");
		blockChain.get(0).mineBlock(difficulty);
		
		blockChain.add(new Block("Hello, I'm Second Block", blockChain.get(blockChain.size()-1).hash));
		System.out.println("Mining Block 2..");
		blockChain.get(blockChain.size()-1).mineBlock(difficulty);
		
		blockChain.add(new Block("Hello, I'm Third Block", blockChain.get(blockChain.size()-1).hash));
		System.out.println("Mining Block 3...");
		blockChain.get(blockChain.size()-1).mineBlock(difficulty);
		
		blockChain.add(new Block("Hello, I'm Fourth Block", blockChain.get(blockChain.size()-1).hash));
		System.out.println("Mining Block 4....");
		blockChain.get(blockChain.size()-1).mineBlock(difficulty);
		
		blockChain.add(new Block("Hello, I'm Fifth Block", blockChain.get(blockChain.size()-1).hash));
		System.out.println("Mining Block 5.....");
		blockChain.get(blockChain.size()-1).mineBlock(difficulty);
		
		
		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);	
		System.out.println("The BlockChain");
		System.out.println(blockChainJson);
		
	}
	
	public static Boolean isChainValid() {
		//any changes in blockchain will return false
		if(blockChain.size() == 1) {
			return true;
		}
		Block currentBlock; 
		Block previousBlock;
		
		try {
			for(int i=1; i < blockChain.size(); i++) {
				currentBlock = blockChain.get(i);
				previousBlock = blockChain.get(i-1);
				//compare registered hash and calculated hash:
				if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
					System.out.println("Current Hashes not equal");			
					return false;
				}
				//compare previous hash and registered previous hash
				if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
					System.out.println("Previous Hashes not equal");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
