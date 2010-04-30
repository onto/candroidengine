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

public class Client extends Thread{

	private InetAddress ip;
	private int port;


	private DatagramSocket socket;
	


	private ArrayList<InetAddress> otherclients = new ArrayList<InetAddress>();

	private boolean listen = true;
	public boolean allowJoin = true;
	private boolean hasJoinRequest = false;
	public boolean isConnected = false;
	
	Context context;


	public Client(Context c, int port) throws UnknownHostException, MalformedURLException, IOException {
		this.port = port;
		context=c;

		ip = InetAddress.getByName(getExternalIP());
		Log.d(Constants.LOGTAG, getExternalIP());
		
		socket = new DatagramSocket();
	}

	@Override
	public void run() {
	
		while(listen){
			byte[] buf = new byte[128];
			DatagramPacket packet = new DatagramPacket(buf, buf.length); 

			try {
				socket.receive(packet);
				String data = new String(packet.getData());
				Log.d(Constants.LOGTAG, data);

				if(!data.contains(Constants.JOINACCEPTED)){
					if(!data.equals(Constants.JOINREQUEST)){
						if(!data.equals(Constants.LEAVESOCKET)){
							onRecive(data);
							Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
						}else{
							otherclients.remove(packet.getAddress());
						}
					}else{
						if(allowJoin){
							String msg =Constants.JOINACCEPTED;
							for(InetAddress i : otherclients){
								String adress = new String(i.getAddress());
								msg+=","+adress;
							}
							send(packet.getAddress(),msg,port);
						}
					}
				}else{
					if(hasJoinRequest){
						String[] ips = data.split(",");
						for(int i = 1; i < ips.length; i++){
							otherclients.add(InetAddress.getByName(ips[i]));
						}
						hasJoinRequest=false;
					}
				}
			} catch (IOException e){}
		}
	}

	public void connect(InetAddress server){
		try {
			socket= new DatagramSocket(port, server);
			isConnected = true;
		} catch (SocketException e) {
			Log.e(Constants.LOGTAG, "Failed to connect to " + server.getAddress());
		}
	}
	
	public void join(InetAddress destination){
		byte[] joinbuf = (Constants.JOINREQUEST).getBytes(); 
		hasJoinRequest = true;
		try {
			socket.send(new DatagramPacket(joinbuf,joinbuf.length,destination,port));
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Join faild to: " + destination.getHostAddress());
		}
	}

	public void send(String msg){
		send(msg.getBytes());
	}

	public void send(InetAddress address,String msg, int port){
		try {
			socket.send(new DatagramPacket(msg.getBytes(),msg.getBytes().length,address,port));
			Log.d(Constants.LOGTAG, "Send: " + msg + " to: " + address + ":" + port);
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send Packet to " + address.getAddress());
		}
	}

	public void send(byte[] b){
		for(InetAddress dest : otherclients){
			try {
				socket.send(new DatagramPacket(b,b.length,dest,port));
			} catch (IOException e) {
				Log.e(Constants.LOGTAG, "Failed to send Packet to " + dest.getHostAddress());
			}
		}
	}

	public void leave(){
		send(Constants.LEAVESOCKET);
	}
	
	public InetAddress getIP(){
		return ip;
	}

	public void addClient(InetAddress yourIp){
		otherclients.add(yourIp);
	}

	public void removeClient(InetAddress ip){
		otherclients.remove(ip);
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

	public void onRecive(String data){

	}
}
