package com.data2semantics.replication.localDaemon;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Hello world!
 *
 */
public class LocalDaemon 
{
	public static boolean isReachable(String hostname) throws IOException, InterruptedException {
		Process p1 = Runtime.getRuntime().exec("ping -c 1 " + hostname);
		int returnVal = p1.waitFor();
		return (returnVal == 0);
	}
    public static void main(String[] args) throws IOException, InterruptedException
    {
    	
        System.out.println(LocalDaemon.isReachable("localhost"));
    }
}
