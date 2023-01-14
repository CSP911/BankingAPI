package com.codef.io.util;

//import java.io.File;
import java.io.IOException;
//import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

//import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.json.JSONObject;
//import com.codef.io.util.ApiRequest;
//import com.codef.io.util.CommonConstant;
//import com.codef.io.util.RSAUtil;

public class AccountManager {
	HashMap<String, HashMap<String, Object>> AccountData = new HashMap<String, HashMap<String, Object>>();
    public String create(String bType, String cType, String lType, String oc, String id, String pwd, String birth) throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.CREATE_ACCOUNT;	

		HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put("countryCode",	"KR");
		accountMap.put("businessType",	"BK");
		accountMap.put("clientType",  	"P");
		accountMap.put("organization",	"0088");
		accountMap.put("loginType",  	"1");
		accountMap.put("password", RSAUtil.encryptRSA(pwd, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */

		accountMap.put("id", id);
		accountMap.put("birthday",	birth);
		list.add(accountMap);
		
		bodyMap.put("accountList", list);

		String result = ApiRequest.reqeust(urlPath, bodyMap);
		System.out.println(result);

		JSONObject jo = new JSONObject(result);
        JSONObject data = (JSONObject) jo.get("data");
		String cId = data.get("connectedId").toString();
		AccountData.put(cId, accountMap);

		return cId;
	}

	public void delete(String cId) throws IOException, InterruptedException, ParseException {
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.DELETE_ACCOUNT;	
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
		HashMap<String, Object> accountMap = new HashMap<String, Object>();

		HashMap<String, Object> clientData = AccountData.get(cId);
		String bType = (String) clientData.get("countryCode");
		String cType = (String) clientData.get("clientType");
		String lType = (String) clientData.get("loginType");
		String oc = (String) clientData.get("organization");

		accountMap.put("countryCode",	"KR");
		accountMap.put("businessType", bType);
		accountMap.put("clientType", cType);
		accountMap.put("organization", oc);
		accountMap.put("loginType", lType);
		list.add(accountMap);
		
		bodyMap.put("accountList", list);
		
		String connectedId = cId;
		bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		System.out.println(result);
	}
}
