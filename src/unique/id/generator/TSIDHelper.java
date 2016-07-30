package unique.id.generator;

import java.util.HashMap;

public class TSIDHelper {
	
	public static boolean isValid(String inputCode) {
		
		if (hasConsecutiveChars(inputCode, 4) || hasRepeatSequence(inputCode, 4)) {
			return false;
		}
		
		return true;
		
	}
	
	public static HashMap<Character, Character> getScrambleMap() {
		HashMap<Character, Character> map = new HashMap<>();
		
		map.put('0', 'C');
		map.put('1', 'H');
		map.put('2', '9');
		map.put('3', 'R');
		map.put('4', 'A');
		map.put('5', 'F');
		map.put('6', 'X');
		map.put('7', 'S');
		map.put('8', 'K');
		map.put('9', '2');
		map.put('A', 'O');
		map.put('B', 'Z');
		map.put('C', 'M');
		map.put('D', '4');
		map.put('E', 'I');
		map.put('F', 'T');
		map.put('G', 'Y');
		map.put('H', '7');
		map.put('I', 'B');
		map.put('J', 'P');
		map.put('K', '3');
		map.put('L', 'W');
		map.put('M', 'E');
		map.put('N', '1');
		map.put('O', 'G');
		map.put('P', 'V');
		map.put('Q', '5');
		map.put('R', 'D');
		map.put('S', 'L');
		map.put('T', '6');
		map.put('U', 'Q');
		map.put('V', 'J');
		map.put('W', '8');
		map.put('X', 'N');
		map.put('Y', 'U');
		map.put('Z', '0');
		
		return map;
	}
	
	private static boolean hasConsecutiveChars(String input, int sequenceLength) {
		
		int charCount = 1;
		
		for (int i = 0; i < input.length() - 1; ++i) {
			
			char c = input.charAt(i);
			
			if (c == input.charAt(i+1)) {
				
				++charCount;
				
				if (charCount >= sequenceLength) {
					return true;
				}
			}
			else {
				charCount = 1;
			}
		}
		
		return false;
	}
	
	private static boolean hasRepeatSequence(String input, int sequenceLength) {
		
		for (int i = 0; i < (input.length() - (2 * sequenceLength) + 1); ++i) {
			
			if (input.substring(i, i+sequenceLength).equals(input.substring(i+sequenceLength, i+(2*sequenceLength)))) {
				return true;
			}
		}
		
		return false;
	}

}
