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

public class CodefClientRequest {
    private String urlPath;

    private String connectedID;
    private String purpose;

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


    public CodefClientRequest(JSONObject data) {
        urlPath = CommonConstant.getRequestDomain();
        if (data.has("purpose"))
            purpose = data.getString("purpose");
        if (data.has("organization"))
            organization = data.getString("organization");
        if (data.has("id"))
            id = data.getString("id");
        if (data.has("password"))
            password = data.getString("password");
        if (data.has("fastId"))
            fastId = data.getString("fastId");
        if (data.has("fastPassword"))
            fastPassword = data.getString("fastPassword");
        if (data.has("account"))
            account = data.getString("account");
        if (data.has("accountPassword"))
            accountPassword = data.getString("accountPassword");
        if (data.has("startDate"))
            startDate = data.getString("startDate");
        if (data.has("endDate"))
            endDate = data.getString("endDate");
        if (data.has("orderBy"))
            orderBy = data.getString("orderBy");
        if (data.has("identity"))
            identity = data.getString("identity");
        if (data.has("smsAuthNo"))
            smsAuthNo = data.getString("smsAuthNo");
    }

    public String requestCodefData() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        switch(purpose){
            case "AccountInquiry":
                urlPath += CommonConstant.KR_BK_1_B_001;
                bodyMap.put("connectedId",connectedID);	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
                bodyMap.put("organization",organization);
                break;
            case "FastInquiry":
                urlPath += CommonConstant.KR_BK_1_B_007;
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
            default:
                return null;
         // 요청 파라미터 설정 종료
        }
        // API 요청
        return ApiRequest.reqeust(urlPath, bodyMap);
    }
}