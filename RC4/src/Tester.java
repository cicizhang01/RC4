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

	
}
