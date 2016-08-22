package com.cpsc476A1.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

public class LinkBean {


	public String uniqueID() throws NoSuchAlgorithmException
	{
		StringBuilder result1;
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
	    //generate a random number
	    String randomNum = new Integer(prng.nextInt()).toString();
	    //get its digest
	    MessageDigest sha = MessageDigest.getInstance("SHA-1");
	    byte[] result =  sha.digest(randomNum.getBytes());
	    
	    result1 = new StringBuilder();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int idx = 0; idx < result.length; ++idx) 
	    {
	      byte b = result[idx];
	      result1.append(digits[ (b&0xf0) >> 4 ]);
	      result1.append(digits[ b&0x0f]);
	    }
		return result1.toString();
	}
	
	public Map sortedMap(HttpServletRequest request)
	{
		Map m1 = new LinkedHashMap();
		Map hs = (Map) request.getServletContext().getAttribute("link");
		Iterator<Map.Entry<String,linkUser>> iter = hs.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String,linkUser> entry = iter.next();
		    m1.put(entry.getKey(),entry.getValue().getVote());
		}
		Set<Entry<String, Integer>> set = m1.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        //here
        m1.clear();
        for(Map.Entry<String, Integer> entry:list){
        	m1.put(entry.getKey(),entry.getValue());
        }
		//here
		return m1;
	}
}
