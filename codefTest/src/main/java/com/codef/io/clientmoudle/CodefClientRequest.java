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

    private String connectedId;
    private String purpose;

    private String id;
    private String password;
    private String fastId;
    private String fastPassword; // rsa
    private String organization;
    private String account;
    private String accountPassword; // rsa
    private String startDate;
    private String endDate;
    private String orderBy;
    private String identity;
    private String smsAuthNo;
    private String inquiryType;
    private String pageCount;
    private String accountLoanExecNo;
    private String loginTypeLevel;
    private String clientTypeLevel;

    private String clientType;
    private String cardNo;
    private String departmentCode;
    private String transeType;
    private String cardName;
    private String duplicateCardIdx;
    private String applicationType;
    private String memberStoreInfoType;
    private String cvc;

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
        if (data.has("inquiryType"))
            inquiryType = data.getString("inquiryType");
        if (data.has("pageCount"))
            pageCount = data.getString("pageCount");
        if (data.has("accountLoanExecNo"))
            accountLoanExecNo = data.getString("accountLoanExecNo");
        if (data.has("loginTypeLevel"))
            accountLoanExecNo = data.getString("accountLoanExecNo");
        if (data.has("clientTypeLevel"))
            accountLoanExecNo = data.getString("accountLoanExecNo");
    }

    public String requestCodefData()
            throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
       
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        switch (purpose) {
            case "accountInquiry": // 보유계좌
                urlPath += CommonConstant.KR_BK_1_B_001;

                // connectedID = am.create(bType, cType, lType, oc, id, pwd, birth);
                bodyMap.put("connectedId", connectedId); // 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
                bodyMap.put("organization", organization);
                break;
            case "transactionInquiry": // 수시입출 거래내역
                urlPath += CommonConstant.KR_BK_1_B_002;
                bodyMap.put("connectedId", connectedId); // 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
                bodyMap.put("organization", organization);
                bodyMap.put("account", account);
                bodyMap.put("startDate", startDate);
                bodyMap.put("endDate", endDate);
                bodyMap.put("orderBy", orderBy);
                bodyMap.put("inquiryType", inquiryType);
                bodyMap.put("pageCount", pageCount);
                break;
            case "loanInquiry": // 대출 거래내역
                urlPath += CommonConstant.KR_BK_1_B_004;
                bodyMap.put("connectedId", connectedId); // 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
                bodyMap.put("organization", organization);
                bodyMap.put("account", account);
                bodyMap.put("startDate", startDate);
                bodyMap.put("endDate", endDate);
                bodyMap.put("orderBy", orderBy);
                bodyMap.put("accountLoanExecNo", accountLoanExecNo);
                break;
            case "fastInquiry": // 빠른계좌조회
                urlPath += CommonConstant.KR_BK_1_B_007;
                bodyMap.put("id", id);
                bodyMap.put("password", password);
                bodyMap.put("fastId", fastId);
                bodyMap.put("fastPassword", RSAUtil.encryptRSA(fastPassword, CommonConstant.PUBLIC_KEY));
                bodyMap.put("organization", organization);
                bodyMap.put("account", account);
                bodyMap.put("accountPassword", RSAUtil.encryptRSA(accountPassword, CommonConstant.PUBLIC_KEY));
                bodyMap.put("startDate", startDate);
                bodyMap.put("endDate", endDate);
                bodyMap.put("orderBy", orderBy);
                bodyMap.put("identity", identity);
                bodyMap.put("smsAuthNo", smsAuthNo);
                break;
            case "cardOwn": // 보유카드
                urlPath += CommonConstant.KR_CD_B_001;
                bodyMap.put("organization", organization);
                bodyMap.put("connectedId", connectedId);
                bodyMap.put("identity", identity);
                bodyMap.put("loginTypeLevel", loginTypeLevel);
                bodyMap.put("clientTypeLevel", clientTypeLevel);
                break;
            case "cardApproval": // 카드 승인 내역
                urlPath += CommonConstant.KR_CD_B_002;
                bodyMap.put("organization", organization);
                bodyMap.put("connectedId", connectedId);
                bodyMap.put("identity", identity);
                bodyMap.put("loginTypeLevel", loginTypeLevel);
                bodyMap.put("clientType", clientType);
                bodyMap.put("startDate", startDate);
                bodyMap.put("endDate", endDate);
                bodyMap.put("orderBy", orderBy);
                bodyMap.put("inquiryType", inquiryType);
                bodyMap.put("cardNo", cardNo);
                bodyMap.put("departmentCode", departmentCode);
                bodyMap.put("transeType", transeType);
                bodyMap.put("cardName", cardName);
                bodyMap.put("duplicateCardIdx", duplicateCardIdx);
                bodyMap.put("applicationType", applicationType);
                bodyMap.put("memberStoreInfoType", memberStoreInfoType);
                break;
            case "cardBilling": // 카드 청구 내역
                urlPath += CommonConstant.KR_CD_B_003;
                bodyMap.put("organization", organization);
                bodyMap.put("connectedId", connectedId);
                bodyMap.put("identity", identity);
                bodyMap.put("loginTypeLevel", loginTypeLevel);
                bodyMap.put("clientTypeLevel", clientTypeLevel);
                bodyMap.put("startDate", startDate);
                bodyMap.put("inquiryType", inquiryType);
                bodyMap.put("cardNo", cardNo);
                bodyMap.put("memberStoreInfoType", memberStoreInfoType);
                break;
            case "limitInquiry": // 카드 한도 조회
                urlPath += CommonConstant.KR_CD_B_004;
                bodyMap.put("organization", organization);
                bodyMap.put("connectedId", connectedId);
                bodyMap.put("identity", identity);
                bodyMap.put("loginTypeLevel", loginTypeLevel);
                bodyMap.put("cardNo", cardNo);
                bodyMap.put("departmentCode", departmentCode);
                bodyMap.put("cvc", cvc);
                bodyMap.put("clientTypeLevel", clientTypeLevel);
                bodyMap.put("inquiryType", inquiryType);
                break;
            case "cardPurchase": // 카드 매입 내역
                urlPath += CommonConstant.KR_CD_B_005;
                bodyMap.put("organization", organization);
                bodyMap.put("connectedId", connectedId);
                bodyMap.put("identity", identity);
                bodyMap.put("loginTypeLevel", loginTypeLevel);
                bodyMap.put("clientTypeLevel", clientTypeLevel);
                bodyMap.put("startDate", startDate);
                bodyMap.put("endDate", endDate);
                bodyMap.put("orderBy", orderBy);
                bodyMap.put("inquiryType", inquiryType);
                bodyMap.put("cardNo", cardNo);
                bodyMap.put("memberStoreInfoType", memberStoreInfoType);
                break;
            default:
                return null;
            // 요청 파라미터 설정 종료
        }
        // API 요청
        return ApiRequest.reqeust(urlPath, bodyMap);
    }
}