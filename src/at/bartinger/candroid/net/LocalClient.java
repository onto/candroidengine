package at.bartinger.candroid.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.util.Log;
import at.bartinger.candroid.Constants;

public class LocalClient extends Thread{

	
	MulticastSocket socket;
	DatagramPacket recivedPack;
	PacketListener listener;
	
	public LocalClient(String groupIP, int port, PacketListener pl) {
		try {
			socket = new MulticastSocket(port);
			socket.joinGroup(InetAddress.getByName(groupIP));
			socket.setTimeToLive(1);
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Faild to initialize MulticastSocket("+port+") or join group: "+ groupIP);
		}
		
	}
	
	@Override
	public void run() {
		recivedPack = new DatagramPacket(new byte[Constants.BYTE_BUFFER_SIZE], Constants.BYTE_BUFFER_SIZE);
		while(true){
			try {
				socket.receive(recivedPack);
			} catch (IOException e){}
		}
	}
	
	public void send(byte[] data){
		DatagramPacket pack = new DatagramPacket(data, data.length);
		try {
			socket.send(pack);
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Failed to send packet");
		}
	}
	
	public void send(String msg){
		send(msg.getBytes());
	}
	
	
}
