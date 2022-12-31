package com.codef.io;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.codef.io.clientmoudle.FastAccountInquiry;

import com.codef.io.util.CommonConstant;


public class CodefClient{
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InterruptedException, ParseException {
        		
		JSONObject testData = new JSONObject();

		testData.put("id", "");
		testData.put("password", "");
		testData.put("fastId", "");
		testData.put("fastPassword", "");
		
		testData.put("organization", CommonConstant.WOORIBANK);
		testData.put("account","");
		testData.put("accountPassword","");
		testData.put("startDate","20221101");
		testData.put("endDate","20221230");
		testData.put("orderBy","1");
		testData.put("identity","");

		testData.put("smsAuthNo","");


		FastAccountInquiry fai = new FastAccountInquiry(testData);	
		String result = fai.requestCodefData();		
        System.out.println(result);
    }
}