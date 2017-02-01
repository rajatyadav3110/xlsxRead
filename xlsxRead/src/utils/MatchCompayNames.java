package utils;

public class MatchCompayNames {
	
	public static float match(String name1, String name2){
		
		try {
			
			if(name1 != null && name2 != null){
				
				name1 = name1.toLowerCase();
				name2 = name2.toLowerCase();

				name1 = name1.replaceAll("\\b limited\\b", "");
				name1 = name1.replaceAll("\\b ltd\\b", "");
				name1 = name1.replaceAll("\\b ltd.\\b", "");
				name1 = name1.replaceAll("\\b private\\b", "");
				name1 = name1.replaceAll("\\b pvt\\b", "");
				name1 = name1.replaceAll("\\b pvt.\\b", "");
				
				name2 = name2.replaceAll("\\b limited\\b", "");
				name2 = name2.replaceAll("\\b ltd\\b", "");
				name2 = name2.replaceAll("\\b ltd.\\b", "");
				name2 = name2.replaceAll("\\b private\\b", "");
				name2 = name2.replaceAll("\\b pvt\\b", "");
				name2 = name2.replaceAll("\\b pvt.\\b", "");
				
				System.out.println(name1);
				System.out.println(name2);
				

				float result = 0.00f;
				
				float wordMatchPercentage = 0f;
				float wordSequenceMatchPercentage = 0f;
				float characterSequenceMatchPercentage = 0f;
				float wordCharacterSequenceMatchPercentage = 0f;
				
				if(!name1.equals(name2)){
					
					char[] alphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					
					for(char char1 : alphabets){
						while(name1.contains(char1+""+char1))
							name1 = name1.replaceAll(char1+""+char1, char1+"");
						
						while(name2.contains(char1+""+char1))
							name2 = name2.replaceAll(char1+""+char1, char1+"");
					}
					
					String[] name1Words = name1.trim().split(" ");
					name1 = "";
					for(String word : name1Words){
						if(!word.equals("")){
							if(name1.equals(""))
								name1 += word.trim();
							else
								name1 += " " + word.trim();
						}
						
					}
					
					String[] name2Words = name2.trim().split(" ");
					name2 = "";
					for(String word : name2Words){
						if(!word.equals("")){
							if(name2.equals(""))
								name2 += word.trim();
							else
								name2 += " " + word.trim();
						}
					}
					
					//Word Match percentage;
					name1Words = name1.trim().split(" ");
					name2Words = name2.trim().split(" ");
					float name1TotalWords = name1.trim().split(" ").length;
					float name2TotalWords = name2.trim().split(" ").length;
					float matchCount = 0;
					for(String word : name1Words){
						if(name2.contains(word.trim())){
							matchCount++;
						}
					}
					wordMatchPercentage = (matchCount / name2TotalWords) * 100;
					System.out.println("wordMatchPercentage :: " + wordMatchPercentage);
					
					if(wordMatchPercentage < 80f){
						
						//Word Sequence Match percentage;
						float loop = name1TotalWords < name2TotalWords ? name1TotalWords : name2TotalWords;
						matchCount = 0;
						for(int i = 0; i < loop; i++){
							if(name1Words[i].equals(name2Words[i]))
								matchCount++;
						}
						wordSequenceMatchPercentage = (matchCount / loop) * 100;
						System.out.println("wordSequenceMatchPercentage :: " + wordSequenceMatchPercentage);
						
						//Character Sequence Match percentage;
						char[] name1Chars = name1.trim().toCharArray();
						char[] name2Chars = name2.trim().toCharArray();
						float name1TotalChars = name1Chars.length;
						float name2TotalChars = name2Chars.length;
						loop = name1TotalChars < name2TotalChars ? name1TotalChars : name2TotalChars;
						matchCount = 0;
						for(int i = 0; i < loop; i++){
							if(name1Chars[i] == (name2Chars[i]))
								matchCount++;
						}
						characterSequenceMatchPercentage = (matchCount / loop) * 100;
						System.out.println("characterSequenceMatchPercentage :: " + characterSequenceMatchPercentage);
						
						//Character Sequence Match percentage for every word;
						char[] word1Chars = null;
						char[] word2Chars = null;
						float word1TotalChars = 0;
						float word2TotalChars = 0;
						float totalChecks = 0f;
						matchCount = 0f;
						for(String word1 : name1Words){
							for(String word2 : name2Words){
								
								word1Chars = word1.trim().toCharArray();
								word2Chars = word2.trim().toCharArray();
								word1TotalChars = word1Chars.length;
								word2TotalChars = word2Chars.length;
								loop = word1TotalChars < word2TotalChars ? word1TotalChars : word2TotalChars;
								for(int i = 0; i < loop; i++){
									totalChecks++;
									if(word1Chars[i] == (word2Chars[i]))
										matchCount++;
								}
								
							}
						}
						
						wordCharacterSequenceMatchPercentage = (matchCount / totalChecks) * 100;
						System.out.println("wordCharacterSequenceMatchPercentage :: " + wordCharacterSequenceMatchPercentage);
						
						result = (wordMatchPercentage + wordSequenceMatchPercentage + characterSequenceMatchPercentage + wordCharacterSequenceMatchPercentage ) / 4;
						
					} else {
						result = wordMatchPercentage;
					}
					
					
					
				} else {
					result = 100f;
				}
				
				return result;
				
			} else {
				return 0.00f;
			}
			
		} catch (Exception e) {
			return 0.00f;
		}
	}
	
	public static void main(String[] args) {
		
		String name1 = "Sumit kumar";
		String name2 = "Amit kumar";
		
		float result = MatchCompayNames.match(name1, name2);
		
		System.out.println(result);
		
		if(result < 50f)
			System.out.println("Allowed!");
		else
			System.out.println("Not Allowed!");
	}

}
