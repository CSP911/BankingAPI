package com.codef.io.clientmoudle;
import com.codef.io.util.RSAUtil;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;

public class CodefClientRequest{
    private String urlPath;
    private String id;
    private String password;
    private String fastId;
    private String fastPassword;
    private String organization;
    private String account;
    private String accountPassword;
    private String startDate;
    private String endDate;
    private String orderBy;
    private String identity;
    private String smsAuthNo;

    private String connectedID;
    private String purpose;
    


    public CodefClientRequest(JSONObject data){
        urlPath = CommonConstant.getRequestDomain();
        //+CommonConstant.KR_BK_1_B_007;
        purpose=data.getString("purpose");
        switch(purpose){
            case "AccountInquiry":
                urlPath += CommonConstant.KR_BK_1_B_001;
            case "FastInquiry":
                urlPath += CommonConstant.KR_BK_1_B_007;
                id = data.getString("id");
                password = data.getString("password");
                fastId = data.getString("fastId");
                fastPassword = data.getString("fastPassword");
                organization = data.getString("organization");
                account = data.getString("account");
                accountPassword = data.getString("accountPassword");
                startDate = data.getString("startDate");
                endDate = data.getString("endDate");
                orderBy = data.getString("orderBy");
                identity = data.getString("identity");
                smsAuthNo = data.getString("smsAuthNo");
            break;
        }
    }

    public String requestCodefData() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        switch(purpose){
            case "AccountInquiry":
                break;
            case "FastInquiry":
                bodyMap.put("id", id);
                bodyMap.put("password", password);
                bodyMap.put("fastId", fastId);
                bodyMap.put("fastPassword", fastPassword);   
                bodyMap.put("organization", organization);
                bodyMap.put("account", account); 
                bodyMap.put("accountPassword", RSAUtil.encryptRSA(accountPassword, CommonConstant.PUBLIC_KEY));
                bodyMap.put("startDate", startDate);
                bodyMap.put("endDate", endDate);
                bodyMap.put("orderBy", orderBy);   
                bodyMap.put("identity",identity);
                bodyMap.put("smsAuthNo", smsAuthNo);
                break;
         // 요청 파라미터 설정 종료
        }
        // API 요청
        return ApiRequest.reqeust(urlPath, bodyMap);
    }
}