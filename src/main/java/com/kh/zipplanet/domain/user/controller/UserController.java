package com.kh.zipplanet.domain.user.controller;

import com.kh.zipplanet.domain.user.model.*;
import com.kh.zipplanet.domain.user.service.UserService;
import com.kh.zipplanet.global.common.CommonResponse;
import com.kh.zipplanet.global.common.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value="/login")
    @ResponseBody
    public ResponseEntity<CommonResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(userLoginRequest.getUsername() == null || userLoginRequest.getUsername().equals("")) {
            response.setMessage("아이디를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        if(userLoginRequest.getPassword() == null || userLoginRequest.getPassword().equals("")) {
            response.setMessage("비밀번호를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        User user = null;
        try {
            user = userService.login(userLoginRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(user);

        if(user == null) {
            response.setMessage("일치하는 유저가 없습니다. 아이디,패스워드를 다시 확인해주세요.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<CommonResponse> signup(@RequestBody UserSignupRequest userSignupRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(userSignupRequest.getUsername() == null || userSignupRequest.getUsername().equals("")) {
            response.setMessage("아이디를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        if((userSignupRequest.getPassword() == null || userSignupRequest.getPassword().equals("")) ||
                userSignupRequest.getPasswordConfirm() == null || userSignupRequest.getPasswordConfirm().equals("")) {
            response.setMessage("비밀번호를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        if(!userSignupRequest.getPassword().equals(userSignupRequest.getPasswordConfirm())) {
            response.setMessage("비밀번호가 일치하지 않습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
        
        if(userSignupRequest.getPhone() == null || userSignupRequest.getPhone().equals("")) {
            response.setMessage("휴대폰 번호를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        User user = null;
        try {
            int result = userService.signUp(userSignupRequest);
            if(result > 0) { // insert 후 row 가져오기 (다른 방법이있을거야..)
                user = userService.getUserByUsername(userSignupRequest.getUsername());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(user);
        if(user == null) {
            response.setMessage("회원가입을 실패했습니다.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/findId")
    @ResponseBody
    public ResponseEntity<CommonResponse> findId(@RequestBody UserFindIdRequest userFindIdRequest){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        User user = null;
        try {
            user = userService.findId(userFindIdRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(user.getUsername());
        if(user.getUsername() == null) {
            response.setMessage("일치하는 유저가 없습니다. 휴대폰 번호를 다시 확인해주세요.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/findPwd")
    @ResponseBody
    public ResponseEntity<CommonResponse> findPwd(@RequestBody UserFindPwdRequest userFindPwdRequest){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        String pwd = null;
        try {
            pwd = userService.findPwd(userFindPwdRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(pwd);
        if(pwd == null) {
            response.setMessage("일치하는 유저가 없습니다. 아이디, 휴대폰번호를 다시 확인해주세요.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public ResponseEntity<CommonResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int result = 0;
        User username = null;
        User nickname = null;
        User phone = null;
        try {
            username = userService.findUniqueUsername(userUpdateRequest.getUsername());
            nickname = userService.findUniqueNickname(userUpdateRequest.getNickname());
            phone = userService.findUniquePhone(userUpdateRequest.getPhone());
            if (nickname != null && !username.getUsername().equals(nickname.getUsername())) {
                response.setMessage("이미 사용중인 닉네임입니다.");
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
            if (phone != null && !username.getUsername().equals(phone.getUsername())){
                response.setMessage("이미 사용중인 휴대폰번호입니다.");
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
            if (userUpdateRequest.getAddress() == null){
                userUpdateRequest.setAddress("");
            }
            result = userService.updateUser(userUpdateRequest);
            if (result == 0) {
                response.setMessage("회원정보 변경에 실패하였습니다.");
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public ResponseEntity<CommonResponse> deleteUser(@RequestBody String username) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int result = 0;
        try {
            result = userService.deleteUser(username);

            if (result == 0) {
                response.setMessage("회원정보 탈퇴를 실패하였습니다.");
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/randomNickname")
    @ResponseBody
    public ResponseEntity<CommonResponse> searchNickname() {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        String[] dongList = {"개포동", "논현동", "대치동", "도곡동", "삼성동", "세곡동", "수서동", "신사동", "압구정", "역삼동", "울현동", "일원동", "자곡동",
                "청담동", "고덕동", "길동", "둔촌동", "명일동", "상일동", "성내동", "암사동", "천호동", "미아동", "번동", "수유동", "우이동", "가양동", "개화동",
                "공항동", "과해동", "내발산동", "등촌동", "마곡동", "방화동", "염창동", "오곡동", "오쇠동", "외발산동", "화곡동", "남현동", "봉천동", "신림동",
                "광장동", "구의동", "군자동", "능동", "자양동", "중곡동", "화양동", "가리봉동", "개봉동", "고척동", "구로동", "궁동", "신도림동", "오류동", "온수동",
                "천왕동", "항동", "가산동", "독산동", "시흥동", "공릉동", "상계동", "월계동", "중계동", "하계동", "도봉동", "방학동", "쌍문동", "창동", "답십리동",
                "신설동", "용두동", "이문동", "장인동", "전농동", "제기동", "청량리동", "회기동", "휘경동", "노량진동", "대방동", "동작동", "본동", "사당동",
                "상도1동", "상도동", "신대방동", "흑석동", "공덕동", "구수동", "노고산동", "당인동", "대홍동", "도화동", "동교동", "마포동", "망원동", "상수동",
                "상암동", "서교동", "성산동", "신공덕동", "신수동", "신정동", "아현동", "연남동", "염리동", "용강동", "중동", "창전동", "토정동", "하중동", "합정동",
                "현석동", "남가좌동", "냉천동", "대신동", "대현동", "미근동", "봉원동", "북가좌동", "북아현동", "신촌동", "연희동", "영천동", "옥천동", "창천동",
                "천연동", "충정로", "합동", "현저동", "홍은동", "홍제동", "내곡동", "반포동", "방배동", "서초동", "신원동", "양재동", "염곡동", "우면동", "원지동",
                "잠원동", "금호동", "도선동", "마장동", "사근동", "상왕십리동", "성수동", "송정동", "옥수동", "용답동", "응봉동", "하왕십리동", "행당동", "홍의동",
                "길음동", "돈암동", "동선동", "동소문동", "보문동", "삼선동", "상월곡동", "석관동", "성북동", "안암동", "장위동", "정릉동", "종암동", "하월곡동",
                "가락동", "거여동", "마천동", "문정동", "방이동", "삼전동", "석촌동", "송파동", "신천동", "오금동", "잠실동", "장지동", "풍납동", "목동", "신월동",
                "신정동", "당산동", "대림동", "도림동", "문래동", "신길동", "양평동", "양화동", "여의도동", "영등포동", "갈월동", "남영동", "도원동", "동빙고동",
                "동자동", "문배동", "보광동", "산천동", "서계동", "서빙고동", "신계동", "신창동", "용문동", "용산동", "원효로", "이촌동", "이태원동", "주성동",
                "청암동", "청파동", "한강로", "한남동", "효창동", "후암동", "갈현동", "구산동", "녹번동", "대조동", "불광동", "수색동", "신사동", "역촌동", "용암동",
                "증산동", "진광동", "가회동", "견지동", "경운동", "계동", "공평동", "관수동", "관철동", "관훈동", "교남동", "교북동", "구기동", "궁정동", "권농동",
                "낙원동", "내수동", "내자동", "누상동", "누하동", "당주동", "도렴동", "돈의동", "동숭동", "명륜", "묘동", "무악동", "봉익동", "부암동", "사간동",
                "사직동", "삼청동", "서린동", "세종로", "소격동", "송월동", "송현동", "수송동", "송인동", "신교동", "신문로", "신영동", "안국동", "연건동", "연지동",
                "예지동", "옥인동", "와룡동", "운니동", "원남동", "원서동", "이화동", "익선동", "인사동", "인의동", "장사동", "재동", "적선동", "종로", "중학동",
                "창성동", "창신동", "청운동", "청진동", "체부동", "충신동", "통의동", "통인동", "팔판동", "평동", "평창동", "필운동", "행촌동", "혜화동", "홍지동",
                "홍파동", "화동", "효자동", "효제동", "훈정동", "광희동", "남대문로", "남산동", "남창동", "남학동", "다동", "만리동", "명동", "무교동", "무학동",
                "묵적동", "방산동", "봉래동", "북창동", "산림동", "삼각동", "서소문동", "소공동", "수표동", "수하동", "순화동", "신당동", "쌍림동", "예관동",
                "예장동", "오장동", "을지로", "의주로", "인현동", "입정동", "장교동", "장충동", "저동", "정동", "주교동", "주자동", "중림동", "초동", "충무로",
                "충정로", "태평로", "필동", "황학동", "회현동", "흥인동", "망우동", "면목동", "묵동", "상봉동", "신내동", "중화동",};

        String[] adjectives = {"육탄", "살인", "불꽃", "차가운", "뜨거운", "폭주", "벌거벗은", "무서운", "고약한", "불량", "앙큼", "회오리", "폭풍",
                "검은", "어둠", "시뻘건", "횡령", "도주", "강력", "거대", "대왕", "대장", "얼음", "고철", "강철", "탄소섬유", "합금", "달콤", "지옥", "불탄",
                "역병", "전염", "도살", "천국"};

        String[] target = {"감자", "갈매기", "문어", "쭈꾸미", "갈치", "오징어", "토마토", "가물치", "농어", "쏘가리", "고구마", "쏙독새", "직박구리",
                "배추", "곰치", "악어", "원숭이", "몰티져스", "초콜렛", "주먹", "초등학생", "노인", "불곰", "전어", "해물탕", "광어", "세발낙지", "학꽁치",
                "꽁치", "까마귀", "고릴라", "손바닥", "도마뱀", "트리케라톱스", "임종필", "허필탁", "우광덕", "양배추", "크릴새우", "김치찌개", "된장찌개", "어린이",
                "가마우지", "곰벌레", "바퀴벌레", "똥파리", "노래기", "장산범", "처녀귀신", "강시", "장수풍뎅이"};
        String nickname;
        User user = null;

        while (true) {
            int num1 = (int) (Math.random() * dongList.length);
            int num2 = (int) (Math.random() * adjectives.length);
            int num3 = (int) (Math.random() * target.length);
            nickname = dongList[num1] + adjectives[num2] + target[num3];

            try {
                user = userService.findUniqueNickname(nickname);
            } catch (Exception e) {
                System.out.println(e);
            }
            if (user == null) {
                break;
            }
        }
        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(nickname);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
