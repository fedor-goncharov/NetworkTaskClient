package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Network {
	public String hostName;		
	public int portNumber;		
	
	public Socket socket;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	DataOutputStream odStream;
	DataInputStream idStream;
	
	public void init()
	{
		try {
			socket = new Socket(hostName, portNumber);
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
			odStream = new DataOutputStream(socket.getOutputStream());
			idStream = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void finalize()
	{
		try {
			odStream.close();
			idStream.close();
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void writeString(String s)
	{
		try {
			odStream.writeUTF(s);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void writeInt(int n)
	{

		try {
			odStream.writeInt(n);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public String readString()
	{
		String s = "";
		try {
			s = idStream.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return s;
	}
	
	public int readInt()
	{
		int n = 0;
		try {
			n = idStream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return n;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> readIntList()
	{
		List<Integer> list = new ArrayList<Integer>();
		try {
			list = (List<Integer>)inStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> readStringList()
	{
		List<String> list = new ArrayList<String>();
		try {
			list = (List<String>)inStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return list;
	}

	public boolean readBool() {
		boolean b = false;
		try {
			b = idStream.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return b;
	}
}


