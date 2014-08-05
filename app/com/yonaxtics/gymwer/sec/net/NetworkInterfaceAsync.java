package com.yonaxtics.gymwer.sec.net;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import play.Logger;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (8/05/2014)
 * 
 */
public class NetworkInterfaceAsync {

	private String hostAddress;
	private String hardwareAddress;

	public NetworkInterfaceAsync() {

		init();
	}


	
	private void init() {

		Enumeration<NetworkInterface> net = null;
		
		try {
			
			net = NetworkInterface.getNetworkInterfaces();
			
			
				   

			while (net.hasMoreElements()) {

				NetworkInterface element = net.nextElement();

				Enumeration<InetAddress> addresses = element.getInetAddresses();

				while (hostAddress == null && addresses.hasMoreElements()) {

					InetAddress ip = addresses.nextElement();

					if (ip instanceof Inet4Address || ip instanceof Inet6Address) {

						if (ip.isSiteLocalAddress()) {

							hostAddress = ip.getHostAddress();
							NetworkInterface network;

							network = NetworkInterface.getByInetAddress(ip);
							byte[] mac = network.getHardwareAddress();

							StringBuffer sb = new StringBuffer();

							for (int i = 0; i < mac.length; i++) {
								sb.append(String.format("%02X%s", mac[i],
										(i < mac.length - 1) ? "-" : ""));
							}

							hardwareAddress = sb.toString();
						}

					}

				}

			}
			
		} catch (SocketException e) {			
			
			Logger.error(e.getMessage());
		}


	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getHardwareAddress() {
		return hardwareAddress;
	}

	public void setHardwareAddress(String hardwareAddress) {
		this.hardwareAddress = hardwareAddress;
	}

}
