package unique.id.generator;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/*
 *  Class used to generate unique TSIDs
 */
public class TSIDGenerator {
	
	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());
	
	private static final String SOURCE_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
	private static final int JVM_ID_LENGTH = 2;
	
	private static final int NUM_MILLIS = 3;
	
	private static final Long YEARS_IN_MILLI = (long) 74003118000000L; // 2345Y, 23D, 21H, 54M (in milliseconds)
	
	private static final SecureRandom random = new SecureRandom();
	
	/*   Precondition: idLength is at least 13
	 * 
	 *   Returns a new ID, with length specified as an argument, 
	 *   that has no sequences of 4 characters and no repeated
	 *   sequences of 4 characters.
	 */
	public static String nextId(int idLength, boolean multipleMachines) {
		
		int machIdLength = (multipleMachines) ? JVM_ID_LENGTH : 0;
		
		String id; 
		
		do {
			id = generateId(idLength, machIdLength);
		}
		while (!TSIDHelper.isValid(id));
		
		return id;
			
	}
	
	/* 
	 *  Returns a new ID, with length specified as an argument, guaranteed to be 
	 *  unique from every previously generated ID and every possible future ID.
	 *  The ID is constructed from the current time-stamp, a unique server
	 *  ID, and a number of random characters. The ID is scrambled to make 
	 *  consecutive IDs difficult to predict, even if they are generated in
	 *  consecutive milliseconds.
	 */
	private static String generateId(int idLength, int machIdLength) {
		
		String id = nextTimeStampId();
		
		id += randomChars(idLength - machIdLength - id.length(), SOURCE_CHARS);
		
		id += uniqueMachineId(machIdLength);
		
		return scramble(id, idLength);
		
	}
	
	/* 
	 *  Returns the current time-stamp, precise to the millisecond, 
	 *  in base36 format (i.e. in the range of chars from [0-9][A-Z]).
	 *  The time-stamp is generated using System.currentTimeMillis(), and an additional
	 *  variable amount of time is added to the current time-stamp to make time-stamps
	 *  generated in consecutive milliseconds still difficult to predict. This
	 *  additional amount of time is calculated as (2345 years, 23 days, 21 hours,
	 *  54 minutes) * (the reverse of the number of milliseconds in the current time-stamp).  
	 *  Java's AtomicReference class is used to ensure that no two time-stamps are ever 
	 *  generated during the same millisecond. 
	 */
	private static String nextTimeStampId() {
		
		// Generate the current time-stamp in milliseconds, using accumulateAndGet
		// to ensure that no two time-stamps are generated during the same millisecond
		Long time_id = currentTime.accumulateAndGet(System.currentTimeMillis(), 
				(prev, next) -> next > prev ? next : prev + 1);
		
		// String representation of the time-stamp
		String time_string = time_id.toString();
		
		// Extract only the millisceonds from the time-stamp
		int millis = Integer.valueOf(time_string.substring(time_string.length() - NUM_MILLIS));
		
		// Add 1111 years, 11 days, 11 hours, 1 minute, multiplied by the reverse of
		// the milliseconds, to the time-stamp
		time_id += (reverse(millis) * YEARS_IN_MILLI);
		
		// Convert the new time_id to base36, convert it to upper-case and return
		// it as a String
		return Long.toString(time_id, 36).toUpperCase();
	}
	
	/* 
	 *  Dummy method to return a unique JVM ID. There must be a unique machine ID
	 *  for every JVM in order to guarantee uniqueness. Implementation of this method
	 *  is left to the user, as implementations will vary depending on the user's Operating
	 *  System as well as other factors.
	 */
	private static String uniqueMachineId(int machIdLength) {
		String mach_id = "";
		
		for (Integer i = 0; i < machIdLength; ++i) {
			mach_id += i.toString();
		}
		
		return mach_id;
	}
	
	/*
	 *  Takes as input the number of chars to be returned and a 
	 *  source String specifying the range of chars that will be used
	 *  to build the String. Returns a String of chars chosen randomly
	 *  from the source String, and of length specified by the 
	 *  input numRandomChars.
	 *  
	 *  Ex: randomDigits(3, "ABCDE") = "EAC"
	 */
	private static String randomChars(int numRandomChars, String source) {
		
		StringBuilder sb = new StringBuilder(numRandomChars);
		
		for (int i = 0; i < numRandomChars; ++i) {
			sb.append( source.charAt( random.nextInt(source.length()) ) );
		}
		
		return sb.toString();
	}
	
	/*
	 *  Precondition: input is a String with characters 
	 *  			  in the range [0-9][A-Z]
	 *  
	 *  Takes an input String and maps each character
	 *  in the String to another distinct character
	 *  using a HashMap. The HashMap will always map the
	 *  same input character to the same output character.
	 *  Returns the resulting String.
	 *  
	 *  Ex: scramble("012345") = "GH4Z8T"
	 */
	private static String scramble(String input, int idLength) {
		
		StringBuilder sb = new StringBuilder(input);
		
		HashMap<Character, Character> scrambleMap = TSIDHelper.getScrambleMap();
		
		// Map each char to another distinct char
		// to scramble the time-stamp
		for (int i = 0; i < idLength; ++i) {
			sb.setCharAt(i, scrambleMap.get(sb.charAt(i)));
		}
		
		return sb.toString();
	}
	
	/*
	 *  Takes an input integer and reverses the digits
	 *  of the integer using StringBuffer.
	 *  
	 *  Ex: reverse(185) = 581
	 *  Ex: reverse(4682) = 2864
	 */
	private static int reverse(int number) {
		String inputString = String.valueOf(number);
		StringBuffer stringBuffer = new StringBuffer(inputString);
		stringBuffer.reverse();
		String reversedString = stringBuffer.toString();
		int reversedInt = Integer.parseInt(reversedString);
		return reversedInt;
	}
}
