package com.kh.spring.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.member.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.LoginLog;
import com.kh.spring.member.model.vo.Member;

//어노테이션 컨트롤러는 객체를 만들어 주며 컨트롤러의 성질을 가지고 있다를 알려주 어노테이션 중 하나이다!!
//컨트롤 보다 상위 버전 어노테이션 컴포넌트->이 어노테이션은 딱 객체만 만들어 준다..


@SessionAttributes("loginUser")
@Controller 
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService mService;
	//다른곳에서 사용하기 위해서 자동으로 의존성 주입
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@RequestMapping(value="loginView.me", method=RequestMethod.GET)
	//1.핸들러 매핑작업 
	//requestMapping 핸들러 매핑 작업 value=url method=GET방식
	public String loginView() {
		//return "member/login";
		return "login";
	}
	
	/******** 파라미터 전송 받기********/
//	//1.JSP/Servlet 방식 HttpServletRequest이용
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public String login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("id1:"+id);
//		System.out.println("pwd1:"+pwd);
//		return null;
//	}
//	
//	//2.@RequestParam이용
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public String login(@RequestParam(value="id", defaultValue="hello") String id, @RequestParam("pwd") String pwd,
//						@RequestParam(value="test", required=false) String test) {
//		
//		//value => view에서 받아올 파라미터 이름 value는 디폴트 속성이기 때문에 생략 가능!!
//		//defaultValu => 들어오는 값이 없을 때 설정한 값으로 채워 넣는다. 값이 있으면 값으로 나옴
//		
//		//view에 없는 requestParam value ="test"를 넣어보자
//		//400error 내가 요청하지 않은 파라미터가 들어가 있다.
//		//Required request parameter 'test' for method parameter type String is not present
//		//하지만 test파라미터는 지금당장은 필요하지 않지만 나중에(페이징처리) 필요해!!!
//		//value="test" 옆에 required=fales를 넣어 줌으로 써 꼭 필요하지 않은 속성으로 인식 시켜 준다.
//		
//		System.out.println("id2:"+id);
//		System.out.println("pwd2:"+pwd);
//		System.out.println("test : "+test);
//		
////		//RequestParam은 넘어온 값이 int라면 굳이 파싱 할 필요 없는 장점이 있다.!!
////		
//		return null;
////	}
	
	// 3. @RequestParam 생략
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public String login(String id, String pwd) {
//		
//		//내가 view에서 넘기는 파라미터 name과 컨트롤러에서 받는 파라미터명이 같으면 requestParam 생략가능
//		//생략해도 매핑이됨!! 파라미터 명만 신경써주면 굉장히 편한데....... 
//		//사실 선생님은 3번방법 비추!!!!!!!!
//		//습관이 들면 회사가서도 어노테이션을 생략하게 될건데 회바회이므로 알아서 하기
//		//어노테이션을 써야 가독성도 있고, 어떻게 받아오는지 알수 있다.
//		
//		System.out.println("id3:"+id);
//		System.out.println("pwd3:"+pwd);
//		
//		return null;
//	}
	
	// 4.@ModelAttribute 이용
//		@RequestMapping(value="login.me", method=RequestMethod.POST)
//		public String login(@ModelAttribute Member m) {
//
//			System.out.println("id4:"+m.getId());
//			System.out.println("pwd4:"+m.getPwd());
//			
//			return null;
//		}
	//5.@modelAttribtute생략
//		@RequestMapping(value="login.me", method=RequestMethod.POST)
//		public String login(Member m) {
//
////			System.out.println("id5:"+m.getId());
////			System.out.println("pwd5:"+m.getPwd());
//			
//			//결합도가 높다! 확인하기 
//			
//			//1. 매 요청마다 새로운 객체가 만들어짐
//			//2. 클래스 명을 변경할 때 마다 직접적인 영향을 받음
//
//			
//		
//			Member loginUser =mService.login(m);
//			//매번 서비스 객체 생성하지 말고 필드에 private로 서비스 객체를 넣어버리자
//			
//			return null;
//		}
	
	//*****뷰에 데이터를 전달하는 방법*********
	
	//1.Model사용
//		@RequestMapping(value="login.me", method=RequestMethod.POST)
//		public String login(Member m,Model model, HttpSession session) {
//
//		
//			Member loginUser =mService.login(m);
//			
//			System.out.println(loginUser);
//			
//			if(loginUser !=null) {
//				session.setAttribute("loginUser", loginUser);
//			
////				return "../home";
//				return "redirect:home.do";
//				//내가 다시 url을 바꾸겟다
//				//처음부터 인코딩을 한 상태에서 보내야 함.... 
//				//
//				
//			}else {
//				
//				model.addAttribute("msg","로그인 실패");
//				
//				
//				return "../common/errorPage";
//			}
//			
//		}
	
	
//		//2.ModelAndView 사용 Model + View
//		@RequestMapping(value="login.me", method=RequestMethod.POST)
//		public ModelAndView login(Member m,ModelAndView mv, HttpSession session) {
//
//		
//			Member loginUser =mService.login(m);
//			
//			System.out.println(loginUser);
//			
//			if(loginUser !=null) {
//				session.setAttribute("loginUser", loginUser);
//				
//				mv.setViewName("redirect:home.do");
//				
//			}else {
//				
//				mv.addObject("msg","로그인 실패");
//				mv.setViewName("../common/errorPage");
//				
//			}
//		
//			return mv;
//		} 
		//3.session에 저장을 할 떄 @SessionAttributes 사용
//		@RequestMapping(value="login.me", method=RequestMethod.POST)
//		public String login(Member m,Model model) {
//
//		
//			Member loginUser =mService.login(m);
//			
//			
//			
//			if(loginUser !=null) {
//				model.addAttribute("loginUser",loginUser);
//				System.out.println(loginUser);
//				
//				return "redirect:home.do";
//				
//				
//			}else {
////				model.addAttribute("msg","로그인 실패");
////				return "../common/errorPage";
//				
//				throw new MemberException("로그인에 실패하였습니다.");
//			}
//		
//		} 	
//		@RequestMapping("logout.me")
//		public String logout(HttpSession session) {
//			session.invalidate();
//			return "redirect:home.do";
//		}

		@RequestMapping("logout.me")
		public String logout(SessionStatus status) {
			status.setComplete();
			return "redirect:home.do";
		}
		//회원가입 페이지 이동
		@RequestMapping("enroll.me")
		public String enroll() {
			
			if(logger.isDebugEnabled()) {
			logger.debug("회원 등록 페이지");
			}
			
			return "enroll";
		}
		//회원 가입완료 페이지
		@RequestMapping("insertMember.me")
		public String insertMember(@ModelAttribute Member m, @RequestParam("emailId") String emailId, @RequestParam("emailDomain") String emailDomain) {
			
			
			// 부족한 부분 n가지
			// 1. 한글이 깨짐
			// 2. 이메일이 null값이 들어감 
			// 3. 이런식으로 값을 받으면 개발자들에게 사생활 정보가 노출됨(평문비밀번호 노출)
			
			String email =null;
			if(!emailId.trim().equals("")) {
				email = emailId+"@"+emailDomain;
			}
			m.setEmail(email);
			String encPwd=bcrypt.encode(m.getPwd());
			m.setPwd(encPwd);
			
			int result=mService.insertMember(m);
			
			if(result>0) {
				return "redirect:home.do";
			}else {
				throw new MemberException("회원가입 실패");
			}
		}
		
		//암호화 후 로그인
		@RequestMapping(value="login.me", method=RequestMethod.POST)
		public String login(Member m,Model model,@RequestParam("beforeURL") String beforeURL) {
			
			Member loginUser =mService.login(m);
			
			boolean check=bcrypt.matches(m.getPwd(), loginUser.getPwd());

			if(check) {
				
				LoginLog lg= mService.searchLog(loginUser.getId());
				
				if(lg==null) {//이 사람 로그인이 처음로그인이면 log 테이블에 넣어줘
					int insertTime=mService.insertTime(loginUser.getId());
				}else {//한번이라도 접속을 하였고 오늘날짜에 로그인이 되었는지 확인해줘
					LoginLog loginCheck=mService.loginCheck(loginUser.getId());
					
					if(loginCheck!=null) {//오늘날짜에 로그인을 했으면 시간을 바꿔준다
						mService.updateTime(loginUser.getId());
					}else {//마지막로그인이 어제이면 login_check값을 N으로 바꿔주고 오늘날짜 로그인 받기 
						mService.updateCheck(loginUser.getId());
						mService.insertTime(loginUser.getId());
					}
				}
				
				model.addAttribute("loginUser",loginUser);
				logger.info(loginUser.getId());

				
				
				
				
				
				
				
				
				
				
				if(beforeURL.equals("")) {
					return "redirect:home.do";	
				}else {
					return "redirect:"+beforeURL;
				}
				
				
			}else {
				throw new MemberException("로그인에 실패하였습니다.");
			}
		} 	
		@RequestMapping("myInfo.me")
		public String myInfo(HttpSession session, Model model) {
			String id = ((Member)session.getAttribute("loginUser")).getId();
			
			ArrayList<HashMap<String, Object>>list =mService.selectMyList(id);
			model.addAttribute("list",list);
			
			
			return "myInfo";
		}
		
		@RequestMapping("myEdit.me")
		public String myEdit() {
			return "edit";
		}
		@RequestMapping("updateMember.me")
		public String updateMyInfo(@ModelAttribute Member m, Model model,@RequestParam("emailId") String emailId, @RequestParam("emailDomain") String emailDomain) {

			String email =null;
			if(!emailId.trim().equals("")) {
				email = emailId+"@"+emailDomain;
			}
			m.setEmail(email);
			
			int result=mService.updateMember(m);
			
			if(result>0) {
				model.addAttribute("loginUser",mService.login(m));
				return "redirect:home.do";
				
			}else {
				throw new MemberException("회원정보 수정 실패");
			}

		}
		@RequestMapping("updatePassword.me")
		public String updatePassword(@RequestParam("currentPwd") String currentPwd, @RequestParam("newPwd") String newPwd,Model model) {
			Member m=(Member)model.getAttribute("loginUser");
			
			if(bcrypt.matches(currentPwd, m.getPwd())) {
				HashMap<String, String> map= new HashMap<String,String>();
				map.put("id", m.getId());
				map.put("newPwd", bcrypt.encode(newPwd));
				
				int result=mService.updatePwd(map);
				if(result>0) {
					model.addAttribute("loginUser",mService.login(m));
					return "redirect:home.do";
				}else {
					throw new MemberException("비밀번호 수정 실패");
				}
				
			}else {
				throw new MemberException("비밀번호 수정 실패");
			}
		}

		@RequestMapping("deleteMember.me")
		public String deleteMember(Model model) {
			int result =mService.deleteMember(((Member)model.getAttribute("loginUser")).getId());
			if(result>0) {
					return "redirect:logout.me";
			}else {
				throw new MemberException("회원탈퇴를 실패하였습니다.");
			}
		}
		@RequestMapping("checkId.me")
		public void checkId(@RequestParam("id") String id, PrintWriter out) {
			System.out.println(id);
			int count=mService.checkId(id);
			System.out.println(count);
			
			String result= count==0?"yes":"no";
			System.out.println(result);
			out.println(result);
			
		}
		@RequestMapping("checkNickName.me")
		@ResponseBody 
		public String checkNickName(@RequestParam("nickName") String nickName) {
			int count=mService.checkNickName(nickName);
			String result=count==0?"yes":"no";
			//스트링 리턴값을 사용하면 뷰리졸버쪽에서url로 인식하여 result.me로 반환이 된다.
			//그냥 스트링 값을 뷰로 넘기고 싶을 때는 어노테이션 ResponseBody를 사용하여 값을 받아온 view쪽으로 넘겨준다
			return result;
		}

}
