package unique.id.generator;

public class TSIDTest {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; ++i) 
			System.out.println(TSIDGenerator.nextId(24, false)); 
		
	}
	
}
