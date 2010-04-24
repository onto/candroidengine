package at.bartinger.candroid.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import at.bartinger.candroid.CandroidActivity;
import at.bartinger.candroid.Constants;

/**
 * This class specifies the Client or a Player in a game over the internet.
 * Make sure that you add the INTERNET PERMISSION in you AndroidManifest.xml
 * <uses-permission android:name="android.permission.INTERNET"></uses-permission>
 * @author Dominic Bartl / Bartinger
 *
 */

public class GlobalClient extends Thread{

	private InetAddress ip;
	private int port;


	private DatagramSocket socket;



	private ArrayList<InetAddress> otherclients = new ArrayList<InetAddress>();

	private boolean listen = true;
	public boolean allowJoin = true;
	private boolean hasJoinRequest = false;
	public boolean isConnected = false;

	private int lport;
	private int sport;
	private boolean sending;
	private InetAddress sendto;

	Context context;


	public GlobalClient(Context c, String reciverIP, int listenport, int sendingport, boolean sending) {
		this.lport = listenport;
		this.sport = sendingport;
		this.sending = sending;
		
		try {
			socket = new DatagramSocket(listenport);
		} catch (SocketException e1) {
			Log.d(Constants.LOGTAG, "Socket init failed");
		}

		try {
			sendto = InetAddress.getByName(reciverIP);
		} catch (UnknownHostException e) {
			Log.d(Constants.LOGTAG, "Unknown Host");
		}
	}

	@Override
	public void run() {

		while(true){
			if(sending){
				byte[] buf = ("Hallo Peter").getBytes();

				try {
					DatagramPacket packet = new DatagramPacket(buf, buf.length,sendto,sport);
					socket.send(packet);
					Log.d(Constants.LOGTAG, "SENDED!");
				} catch (IOException e) {
					Log.e(Constants.LOGTAG, "dont send");
				}
			}else{

				try {
					DatagramPacket packet = new DatagramPacket( new byte[11], 11 );
					socket.receive( packet );
					Log.d(Constants.LOGTAG, "RECIVED: "+new String(packet.getData()));
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

		}
	}




	public void send(InetAddress address,String msg, int port){
		try {
			socket.send(new DatagramPacket(msg.getBytes(),msg.getBytes().length,address,port));
			Log.d(Constants.LOGTAG, "Send: " + msg + " to: " + address + ":" + port);
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send Packet to " + address.getAddress());
		}
	}



	public String getExternalIP() throws MalformedURLException, IOException{
		String myip = "";
		Scanner scanner = new Scanner(new URL("http://www.bmw-02-club.at/myip.php").openStream());
		while(scanner.hasNextLine()){
			myip= scanner.nextLine();
		}
		scanner.close();
		return myip;
	}
	
	public void close(){
		socket.close();
	}
}
