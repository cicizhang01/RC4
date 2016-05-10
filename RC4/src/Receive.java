
public class Receive implements Intery{
	private char[] S;
	private char[] inputted;
	private byte[] key;
	public Receive(char[] input, byte[] keyinput){
		char[] S=new char[256];
		for (int i=0; i<256;i++){
			S[i]=(char)i;
			
		}
		char[] inputted =new char[input.length];
		byte[] key=new byte[keyinput.length];
		inputted=input;
		key=keyinput;
	}
	@Override
	public byte PRGA() {
		// TODO Auto-generated method stub
		int j=0;
		int i=0;
		for(int m=0;m<inputted.length;m++){
			i=(i+1)%256;
			j=(j+S[i])%256;
			char temp=S[i];
			S[i]=S[j];
			S[j]=temp;
			byte output= (byte) S[(S[i]+S[j])%256];
			return output;
		}
		return (Byte) null;
	}
		

	@Override
	public void KSA() {
		// TODO Auto-generated method stub
		int j=0;
		for(int i=0; i<256;i++){
			j=(j+S[i]+key[i%key.length])%256;
		
			char temp=S[i];
			S[i]=S[j];
			S[j]=temp;
		}
	}

}
