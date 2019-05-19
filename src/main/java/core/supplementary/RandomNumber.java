package core.supplementary;

public class RandomNumber {
public int generateJobId()
	{
		int aNumber = 0; 
		aNumber = (int)((Math.random() * 9000000)+1000000); 
		return ((aNumber));
	}

}