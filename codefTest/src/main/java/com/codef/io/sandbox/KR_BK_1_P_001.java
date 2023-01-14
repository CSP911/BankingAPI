package com.codef.io.sandbox;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;
import com.codef.io.util.AccountManager;
/**
 * 은행 개인 보유계좌	
 */
public class KR_BK_1_P_001 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.TEST_DOMAIN + CommonConstant.KR_BK_1_P_001;
		String cId = "";
		AccountManager am = new AccountManager();
		try{
			cId = am.create("BK", "P", "1", "0088", "", "", "910911");
			System.out.println("######### ConnectedID request :: " + cId);
		} catch (Exception e){
			System.out.println(e);
			return;
		} 
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId", cId);	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		
		bodyMap.put("organization", "0088"); 
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
		System.out.println(result);
		am.delete(cId);
	}
}