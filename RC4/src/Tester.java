import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.DecoderException;

import org.apache.commons.codec.binary.Hex;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] keyInput = {0x12, 0x34,0x23, 0x35,0x65,0x23,0x45,0x78};
		Transmit crying=new Transmit(keyInput);
		System.out.println(crying.encrypt("Hello World"));
	}

	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}
