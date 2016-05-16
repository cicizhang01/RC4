import org.apache.commons.codec.DecoderException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.DecoderException;

import org.apache.commons.codec.binary.Hex;

public class Receive implements Intery{
private int[] S;
	
	private byte[] key;
	public Receive( byte[] keyinput){
		S=new int[256];
		
		for (int i=0; i<256;i++){
			S[i]=i;
		}
		
		key=new byte[keyinput.length];
		
		for(int i=0;i<keyinput.length;i++){
			key[i]=keyinput[i];
		}
		KSA();
		//System.out.println("Finished constructor");
	}
	public byte[] getKey(){
		return key;
	}
	public int[] getS(){
		return S;
	}
	public void KSA(){
		int j=0;
		
		for(int i=0; i<256;i++){
			
			j=(j+S[i]+key[i%key.length])%256;
		
			int temp=S[i];
			S[i]=S[j];
			S[j]=temp;
			temp=0;
		}
		//System.out.println("Finished KSA");
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
		//System.out.println("Finished PRGA");
		return output;
	}
	private int[] toInts(byte[] bytes) {
		int[] output = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			output[i] = bytes[i];
		}
		//System.out.println("Finished toInts");
		return output;
		
	}

	public byte[] toBytes(int[] ints) {
		byte[] output = new byte[ints.length];
		for (int i = 0; i < ints.length; i++) {
			output[i] = (byte) ints[i];
		}
		//System.out.println("Finished toBytes");
		return output;
	}
	@Override
	public String encrypt(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String cipherText) {
		// TODO Auto-generated method stub
		try {
			int[] cipherints = toInts(Hex.decodeHex(cipherText.toCharArray()));
			for (int i = 0; i < cipherints.length; i++) {
				cipherints[i] = cipherints[i] & 0xff;
			}
			byte[] plaintext = toBytes(PRGA(cipherints));
			return new String(plaintext, Charset.forName("ASCII"));
		} catch (DecoderException e) {
		}
		return null;
		
	}
	

}
