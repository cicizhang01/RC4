
public class Transmit implements Intery{
	private char[] S;
	private char[] inputted;
	private byte[] key;
	public Transmit(char[] input, byte[] keyinput){
		char[] S=new char[256];
		for (int i=0; i<256;i++){
			S[i]=(char)i;
			
		}
		char[] inputted =new char[input.length];
		byte[] key=new byte[keyinput.length];
		inputted=input;
		key=keyinput;
	}
	public void KSA(){
		int j=0;
		for(int i=0; i<256;i++){
			j=(j+S[i]+key[i%key.length])%256;
		
			char temp=S[i];
			S[i]=S[j];
			S[j]=temp;
		}
	
	}
	public void PRGA(){
		
	}
	
}
