package at.bartinger.candroid.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import android.content.Context;
import android.util.Log;
import at.bartinger.candroid.Constants;

/**
 * This class specifies the Client or a Player in a game over the Network.
 * Make sure that you add the INTERNET PERMISSION in you AndroidManifest.xml
 * <uses-permission android:name="android.permission.INTERNET"></uses-permission>
 * @author Dominic Bartl / Bartinger
 *
 */

public class GlobalClient extends Thread{

	private int port;
	private PacketListener listener = null;


	private DatagramSocket socket;
	
	public boolean isConnected = false;

	private InetAddress sendto;

	Context context;


	public GlobalClient(Context c, String peerIP, int listenPort, int peerPort) {

		port = peerPort;
		
		try {
			socket = new DatagramSocket(listenPort);
		} catch (SocketException e1) {
			Log.d(Constants.LOGTAG, "Socket init failed");
		}

		try {
			sendto = InetAddress.getByName(peerIP);
		} catch (UnknownHostException e) {
			Log.d(Constants.LOGTAG, "Unknown Host: "+ peerIP);
		}
	}

	@Override
	public void run() {


		try {
			DatagramPacket packet = new DatagramPacket( new byte[11], 11 );
			socket.receive( packet );
			if(listener != null){
				listener.onRecivePacket(packet.getData());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}




	public void send(InetAddress address,String msg){
		try {
			socket.send(new DatagramPacket(msg.getBytes(),msg.getBytes().length,address,port));
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send Packet to " + address.getAddress());
		}
	}
	
	public void sendToPeer(String msg){
		try {
			socket.send(new DatagramPacket(msg.getBytes(),msg.getBytes().length,sendto,port));
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send Packet to " + sendto.getAddress());
		}
	}
	
	public void send(InetAddress address,byte[] data){
		try {
			socket.send(new DatagramPacket(data,data.length,address,port));
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send Packet to " + address.getAddress());
		}
	}
	
	public void sendToPeer(byte[] data){
		try {
			socket.send(new DatagramPacket(data,data.length,sendto,port));
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send Packet to " + sendto.getAddress());
		}
	}

	public String getExternalIP() throws MalformedURLException, IOException{
		String myip = "";
		Scanner scanner = new Scanner(new URL("http://bartinger.kilu.org/myip.php").openStream());
		while(scanner.hasNextLine()){
			myip= scanner.nextLine();
		}
		scanner.close();
		return myip;
	}

	public void close(){
		socket.close();
		this.destroy();
	}
}
