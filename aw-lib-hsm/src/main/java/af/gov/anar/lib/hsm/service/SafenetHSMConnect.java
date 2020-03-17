package af.gov.anar.lib.hsm.service;

import af.asr.lib.hsm.util.ByteHexUtil;
import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SafenetHSMConnect {

	private static final String HEADER = "01010006";
	
	//@formatter:off
	public static final String send(final HSMConfig hsmConfig, final String command, final Logger logger) throws UnknownHostException, IOException {
		try(Socket socket = new Socket(hsmConfig.host, hsmConfig.port);
				OutputStream os = socket.getOutputStream();
				InputStream in = socket.getInputStream()) {
				socket.setSoTimeout(30000);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				baos.write(ByteHexUtil.hexToByte(HEADER));
				baos.write((command.length()/2) / 256);
				baos.write((command.length()/2) % 256);
				baos.write(ByteHexUtil.hexToByte(command));
				final byte[] request = baos.toByteArray();
				System.out.println(ByteHexUtil.byteToHex(ByteHexUtil.hexToByte(command)));
				os.write(request);
				os.flush();
				final byte[] responseheader = new byte[4];
				for (int i = 0; i < responseheader.length; i++) {
					responseheader[i] = (byte) in.read();
				}
				final int b1 = in.read() & 0xFF;
				final int b2 = in.read() & 0xFF;
				final int length = b1 * 256 + b2;
				final byte[] response = new byte[length];
				in.read(response);
				System.out.println(ByteHexUtil.byteToHex(response));
				return ByteHexUtil.byteToHex(response);
			} catch (Exception e) {e.printStackTrace();}
			return null;
		}
	
	
}
