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

import com.codef.io.clientmoudle.CodefClientRequest;
import com.codef.io.clientmoudle.FastAccountInquiry;
import com.codef.io.util.AccountManager;
import com.codef.io.util.CommonConstant;


public class CodefClient{
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InterruptedException, ParseException {
        		
		JSONObject testData = new JSONObject();
		testData.put("purpose","fastInquiry");

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

		AccountManager acm = new AccountManager();
		final String cId = acm.create(null, null, null, null, null, null, null);

		testData.put("connectedId", cId);

		CodefClientRequest ccr = new CodefClientRequest(testData);	
		String result = ccr.requestCodefData();		
        System.out.println(result);
    }
}