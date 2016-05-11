import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.DecoderException;

import org.apache.commons.codec.binary.Hex;

public class Transmit implements Intery{
	private int[] S;
	private char[] inputted;
	private byte[] key;
	public Transmit(char[] input, byte[] keyinput){
		int[] S=new int[256];
		for (int i=0; i<256;i++){
			S[i]=i;
			
		}
		char[] inputted =new char[input.length];
		byte[] key=new byte[keyinput.length];
		inputted=input;
		key=keyinput;
		
		KSA();
	}
	public void KSA(){
		int j=0;
		for(int i=0; i<256;i++){
			j=(j+S[i]+key[i%key.length])%256;
		
			int temp=S[i];
			S[i]=S[j];
			S[j]=temp;
		}
	
	}
	public int[] PRGA(int[] input){
		final int[] output = new int[input.length];

		final int[] S = new int[this.S.length];
		System.arraycopy(this.S, 0, S, 0, S.length);

		int i = 0, j = 0, k, t;
		for (int counter = 0; counter < input.length; counter++) {
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			S[i] ^= S[j];
			S[j] ^= S[i];
			S[i] ^= S[j];
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			output[counter] = input[counter] ^ k;
		}

		return output;
	}
	private int[] toInts(byte[] bytes) {
		int[] output = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			output[i] = bytes[i];
		}
		return output;
	}

	private byte[] toBytes(int[] ints) {
		byte[] output = new byte[ints.length];
		for (int i = 0; i < ints.length; i++) {
			output[i] = (byte) ints[i];
		}
		return output;
	}
	public String encrypt(String message){
		byte[] plaintext = new byte[0];
		plaintext = message.getBytes("ASCII");
		byte[] ciphertext = toBytes(PRGA(toInts(plaintext)));
		return Hex.encodeHexString(ciphertext);
	}
	@Override
	public String decrypt(String cipherText) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
