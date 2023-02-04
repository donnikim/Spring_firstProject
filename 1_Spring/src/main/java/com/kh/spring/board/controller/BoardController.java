package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.Pagination;
import com.kh.spring.member.model.vo.Member;

@Controller
public class BoardController {
	
	
	@Autowired
	private BoardService bService;
	
	
	@RequestMapping("list.bo")
	public String selectBoardList(@RequestParam(value="page", required=false) Integer page,Model model) {
		int currentPage=1;
		
		//int는 null값이 존재할수 없다 기본자료형이니까
		//그럼 어떻게 하면 좋을까?
		//기본자료형이면서 클래스를 가질수 있을까?
		//wrapper class Integer
		if(page!=null) {
			currentPage=page;
		}
		
		int listCount=bService.getListCount(1);
		PageInfo pi=Pagination.getPageInfo(currentPage, listCount, 5);
		
		ArrayList<Board> list =bService.selectBoardList(pi,1);
		
		if(list !=null) {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			return "boardList";
		}else {
				throw new BoardException("게시글 조회 삭제");
		}
	}
	@RequestMapping("writeBoard.bo")
	public String writeBoard() {
		return "writeBoard";
	}
	@RequestMapping("insertBoard.bo")
	public String insertBoard(HttpSession session,@ModelAttribute Board b) {
		String id=((Member)session.getAttribute("loginUser")).getId();
		
		b.setBoardWriter(id);
		b.setBoardType(1);
		int result=bService.insertBoard(b);
		
		if(result>0) {
			return "redirect:list.bo";
		}else {
			throw new BoardException("게시글 쓰기 실패");
		}
	}
	
//	@RequestMapping("selectBoard.bo")
//	public ModelAndView selectBoard(@ModelAttribute Board b,HttpSession session,@RequestParam(value="bId", required=false) int bId,@RequestParam(value="writer", required=false) String boardWriter,
//			@RequestParam(value="page", required=false) int page, ModelAndView mv) {
//		
//		String sessionNickName=((Member)session.getAttribute("loginUser")).getNickName();
//		String sessionId=((Member)session.getAttribute("loginUser")).getId();
//		int boardId=bId;
//		
//		b.setBoardId(boardId);
//		b.setNickName(sessionNickName);
//		b.setBoardWriter(sessionId);
//		
//		if(sessionNickName.equals(boardWriter)){
//			//조회수 증가x
//			Board bSelect =bService.selectBoard(b);
//
//			if(bSelect!=null) {
//				mv.addObject("bSelect",bService);
//				mv.addObject("page",page);
//				mv.setViewName("boardDetail");
//				return mv;
//			}else {
//				throw new BoardException("게시글 상세보기실패");
//			}
//			
//			
//		}else if(!sessionNickName.equals(boardWriter)){
//			
//			bService.viewCount(boardId);
//			Board bSelect =bService.selectBoard(b);
//			System.out.println(bSelect.getBoardTitle());
//			
//			if(bSelect!=null) {
//				mv.addObject("bSelect",bService);
//				mv.addObject("page",page);
//				mv.setViewName("boardDetail");
//				return mv;
//			}else {
//				throw new BoardException("게시글 상세보기실패");
//			}
//		
//		}else {
//			throw new BoardException("게시글 상세보기실패");
//		}
//		
//		
//	}

	@RequestMapping("selectBoard.bo")
	public ModelAndView selectBoard(@RequestParam("bId") int bId, @RequestParam("writer") String writer, @RequestParam("page") int page, HttpSession session,ModelAndView mv) {
		Member m =((Member)session.getAttribute("loginUser"));
		String login=null;
		if(m!=null) {
			login=m.getNickName();
		}
		
		boolean yn=false;
		if(!writer.equals(login)) {
			yn=true; //글을 쓴사람이 조회수가 아니라서 조회수가 올라도 된다 
		}
		Board b = bService.selectBoard(bId,yn); 
		ArrayList<Reply> list=bService.selectReply(bId);
		if(b!=null) {
			mv.addObject("b", b);
			mv.addObject("list",list);
			mv.addObject("page",page);
			mv.setViewName("boardDetail");
			return mv;
		}else {
			throw new BoardException("게시글 상세보기 실패");
		}
		
	}
	
	
		@RequestMapping("updateForm.bo")
		public String updateForm(@RequestParam("boardId") int bId, @RequestParam("page") int page,Model model) {
			Board b =bService.selectBoard(bId,false);
			model.addAttribute("b",b);
			model.addAttribute("page",page);
			return "edit";
		}
		
		@RequestMapping("updateBoard.bo")
		public String updateBoard(@ModelAttribute Board b, @RequestParam("page") int page,Model model, HttpSession session) {
			
			b.setBoardType(1); // 일반 게시판과 첨부게시판을 구분하기 위해서는 boardType를 설정해주어야 함!
			
			int result = bService.updateBoard(b);

			if(result>0) {
				model.addAttribute("bId",b.getBoardId());
				model.addAttribute("writer",((Member)session.getAttribute("loginUser")).getNickName());
				model.addAttribute("page", page);
				return "redirect:selectBoard.bo";
			}else {
				throw new BoardException("게시글 수정 실패");
			}
		
		}
	
		@RequestMapping("delete.bo")
		public String deleteBoard(@RequestParam("boardId") int bId) {
			int result=bService.deleteBoard(bId);
			
			if(result>0) {
				return "redirect:list.bo";
			}else {
				throw new BoardException("게시글 삭제 실패");
			}
		}
		@RequestMapping("list.at")
		public String selectAttmList(@RequestParam(value="page", required=false) Integer page,Model model) {
			int currentPage=1;
			if(page!=null) {
				currentPage=page;
			}
			int listCount=bService.getListCount(2);
			PageInfo pi=Pagination.getPageInfo(currentPage, listCount, 9);
			
			System.out.println(pi);
			ArrayList<Board> bList =bService.selectBoardList(pi,2);
			
			ArrayList<Attachment> aList=bService.selectAttmList(null);
			
			if(bList !=null) {
				model.addAttribute("pi",pi);
				model.addAttribute("bList",bList);
				model.addAttribute("aList",aList);
				return "attmList";
					
				
			}
			
			return "attmList";
		}
		
		@RequestMapping("writeAttm.at")
		public String writeAttm() {
			return "writeAttm";
		}
		
		@RequestMapping("insertAttm.at")
		public String inserAttm(@ModelAttribute Board b,@RequestParam("file") ArrayList<MultipartFile> files,HttpServletRequest request ) {
			String id =((Member)request.getSession().getAttribute("loginUser")).getId();
			b.setBoardWriter(id);

//			System.out.println(b);
//			System.out.println(files);
			
			ArrayList<Attachment> list = new ArrayList<Attachment>();
			//ArrayList<MultipartFile> file안에 있는 객체들을 list안에 담으려고 한다.
			for(int i=0; i<files.size();i++) {
				//for문을 통해 가져올 것이다.
				MultipartFile upload =files.get(i);
				
				
				if(!upload.getOriginalFilename().equals("")) { //upload에 오리지날 이름이 빈칸이 아니라면!
//				if(upload!=null&&!upload.isEmpty()) { //upload가 null이 아니고 upload가 비어있지 않다면! 
					
					
					//현재 파일이 upload객체에 있는 상황임!!
					String[] returnArr = saveFile(upload, request);
					//returnArr[0]에는 저장경로 returnArr[1]에는 우리가 지정한 이름으로 변경한 리네임이 들어 있다.
					
					if(returnArr[1]!=null) {
						//리네임이 null이 아니라면
						Attachment a = new Attachment();
						
						a.setOriginalName(upload.getOriginalFilename());
						a.setRenameName(returnArr[1]);
						a.setAttmPath(returnArr[0]);
						list.add(a);
					}
				}
			}
		
			  //썸네일! 
			for(int i=0; i<list.size();i++) {
				Attachment a =list.get(i);
				if(i==0) {
					a.setAttmLevel(0);
				}else {
					a.setAttmLevel(1);
				}
			}
			int result1=0;
			int result2=0;
			if(list.isEmpty()) {
				b.setBoardType(1);
				result1=bService.insertBoard(b);
			}else {
				b.setBoardType(2);
				result1=bService.insertBoard(b);
			
				result2=bService.insertAttm(list);
			}
			if(result1+result2==list.size()+1) {
				if(result2==0) {
					return"redirect:list.bo";
				}else {
					return"redirect:list.at";
				}
			}else {
				for(Attachment a: list) {
					deleteFile(a.getRenameName(),request);
				}
				throw new BoardException("첨부파일 게시글 삽입 실패");
			}

		}
		
		public String[] saveFile(MultipartFile file, HttpServletRequest request) {
			
			String root=request.getSession().getServletContext().getRealPath("resources");
			//WEB-INF안에 있는 resources(정적파일관리)를 도달하려고 하는 경로
			
			//윈도우 경로
			//String savePath=root+"\\uploadFiles";
			
			//맥os경로 접근 방법
			String savePath=root+"/uploadFiles";
			
			File folder = new File(savePath);
			
			
			if(!folder.exists()) {
				folder.mkdirs();//폴더가 없으면 폴더를 만들어줘!!
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmssSSS");
			//날짜이용하기 위해 simpleDateFormat이용할 거임!!
			
			int ranNum=(int)(Math.random()*100000);
			String originFileName=file.getOriginalFilename();
			String renameFileName=sdf.format(new Date(System.currentTimeMillis()))+ranNum+originFileName.substring(originFileName.lastIndexOf("."));
			//	이름을 다시 지어			ㄴ데이터 업로드된 날짜 및 시간            +        ㄴ랜덤값     + 원본파일에서 가장 마지막 . 뒤에 추츨(파일확장자명 추츨)	
			
			
			// 변경된 이름을 파일안에 저장한다!!
			String renamePath=folder+"\\"+renameFileName;
			
			try {
				file.transferTo(new File(renamePath));
				
				
			} catch (Exception e) {
				System.out.println("파일 전송 에러: "+e.getMessage());
			}
			String[] returnArr= new String[2];
			returnArr[0]=savePath;
			returnArr[1]=renameFileName;
			
			return returnArr;
			
		}

		public void deleteFile(String fileName, HttpServletRequest request) {
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath=root+"\\uploadFiles";
			
			File f = new File(savePath+"\\"+fileName);
			if(f.exists()) {
				f.delete();
			}
		}
		
		@RequestMapping("selectAttm.at")
		public ModelAndView selectAttm(@RequestParam("bId") int bId, @RequestParam("writer") String writer, @RequestParam("page") int page, ModelAndView mv, HttpSession session) {
			Member m = (Member)session.getAttribute("loginUser");
			String login= null;
			if(m!=null) {
				login=m.getNickName();
			}
			boolean yn=false;
			
			if(!writer.equals(login)) {
				yn=true;
			}
			Board b = bService.selectBoard(bId, yn);
			ArrayList<Attachment> list = bService.selectAttmList((Integer)bId); //기본자료형은 null값이 될수 없으므로 박싱 (wrapper class로 변환한다.)
			System.out.println(list);
			if(b!=null) {
				mv.addObject("b",b).addObject("page",page).addObject("list",list).setViewName("attmDetail");
			
			}else {
				throw new BoardException("첨부파일 게시글 상세보기 실패");
			}
			return mv;
		}

		@RequestMapping("updateForm.at")
		public String updateAttmForm(@RequestParam("boardId") int bId,@RequestParam("page") int page, Model model) {
			//update 클릭 시 boardId랑 page가 필요하므로 넘겨준다 !!
			
			Board b=bService.selectBoard(bId, false);
			ArrayList<Attachment> list =bService.selectAttmList(bId);
			model.addAttribute("b",b);
			model.addAttribute("list",list);
			model.addAttribute("page",page);
			
			return "edit";
		}
		
		@RequestMapping("updateAttm.at")
		public String updateAttm(@ModelAttribute Board b, @RequestParam("page") int page, @RequestParam("deleteAttm") String[] deleteAttm,
				@RequestParam("file") ArrayList<MultipartFile> files,HttpServletRequest request,Model model) {
			System.out.println(b);
			System.out.println(Arrays.toString(deleteAttm));
			System.out.println(files);
			
			/*
			 
			 	1. 새 파일o
			 	 (1) 기존 파일 모두 삭제--> 기존파일 모두 삭제 해놓고 새 파일만 저장
			 	 (2) 기존 파일 일부 삭제--> 기존파일 일부 삭제 하고 새 파일만 저장  
			 	 (3) 기존 파일 모두 유지--> 새파일 저장
			 	2. 새 파일x
			 	 (1) 기존 파일 모두 삭제--> 기존 파일 모두 삭제
			 	 (2) 기존 파일 일부 삭제--> 기존 파일 일부 삭제
			 	 (3) 기존 파일 모두 유지--> board만 수정 
			 	
			 	 1-(1) ==> 새파일 중에서 level 0,1 지정
			 	 2-(1) => 일반게시판으로 이동 : 일반게시판을 가려면 board_type를 1로 만들어야 함!! update 구문을 바꿔야 함! 
			 	 1-(2),2-(2) => 삭제할 파일의 level검사 후 level이 0인 차일이 삭제되면 다른 파일의 레벨을 0으로 재지정 
			  		1-(2)=>새 파일의 레벨 모두 1로 지정
			  */
			
			
			b.setBoardType(2);//현재 우리가 작성하는 컨트롤메소드는 일반게시판이 아닌 첨부게시판이므로 boardType setting을 2로 설정해둔

			
//			1. 새파일이 있다 무조건 저장
			ArrayList<Attachment> list =new ArrayList<>(); //update시 user가 기존파일 말고 새로운 파일을 업로드 하려할때 위 list에 담아준다
			
			for(int i=0;i<files.size();i++ ) { //requestparam을 통해 받은 타입 ArrayList<Attachment> files를 for문 돌릴꺼야 files개수만큼!
				
				MultipartFile upload=files.get(i);// files에 있는 file 이 존재한다면 upload에 담을 꺼야!!
				
				if(!upload.getOriginalFilename().equals("")) {//upload안에 있는 오리지날 이름이 공란이 아니라면
					String[] returnArr=saveFile(upload,request);// String 배열안에 저장할꺼야!! Save메소드는 우리가 저번시간에 만들었다! 
					//	우리가 만든 메소드인 saveFile의 반환값은 returnArr[0] 은 저장경로를 저장해두었고 returnArr[1]에는 리네임한 파일이름을 집어 넣었다.
					
					if(returnArr[1]!=null) { //returnArr[1]에는 리네임한 값이 있을 껀데 만일 null 값이 없다면 밑에 있는 메소드 실행
						
						Attachment a =new Attachment(); //vo로 만든 곳에 값을 임시저장할수 있는 객체를 만들고
						
						a.setOriginalName(upload.getOriginalFilename());//저장하려는 값을 넣어준다
						a.setRenameName(returnArr[1]);//리네임된 이름
						a.setAttmPath(returnArr[0]);//저장경로
						
						list.add(a);//vo객체에 넣었으면 이걸 리스트안에 넣어 준다! 
					}
				}
			}
			
//			2. 선택한 파일들 삭제
			ArrayList<String> delRename = new ArrayList<>();// 내가 삭제하겠다라고 체크한 첨부파일들의 리네임을 리스트에 담는다.
			
			ArrayList<Integer> delLevel = new ArrayList<>();// 내가 삭제하겠다라고 체크한 첨부파일들의 레벨(섬네일인지 아닌지)정보를 담는다.
			for(String rename: deleteAttm) { //현재 deleteAttm은 view에서 가져온 String[]의 객체배열이다 String 객체 배열안에 있는 String 값들을 rename에 넣어 준다. 
				if(!rename.equals("")) {//리네임이 비워져있지 않으면 
					String[] split = rename.split("/");// 스플릿으로 나눈다 /앞에 있는 것은 renameName이고 /뒤에 있는 정보는 AttmLevel이다.
					delRename.add(split[0]);
					delLevel.add(Integer.parseInt(split[1]));
				}
			}
			
			int deleteAttmResult=0;
			
			boolean existBeforeAttm=true;
			
			if(!delRename.isEmpty()) {//저장 했던 첨부파일 중 하나라도 삭제하겠다고 한 경우
				deleteAttmResult=bService.deleteAttm(delRename);// 현재 DB에서는 Rename을 삭제 했지만 현재 컴퓨터에 저장된 upload파일은 삭제하지 않았다.
				
			
			if(deleteAttmResult>0) {//DB에 delete문을 실행 하고 나온 값이 존재 한다면 
				for(String rename:delRename) { //delRename안에 있는 스트링 값을 rename에 담고 
					deleteFile(rename,request);// 우리가만든 deleteFile메소드에 접근하여 upload파일안에 있는 파일들을 지워준다 
					}
				}
			
			
			if(delRename.size()==deleteAttm.length) {//숫자가 같이 않을 경우는 부분삭제거나 삭제파일이 없는경우이고 위의 경우는 전부 삭제하는 경우이다.!
				existBeforeAttm=false;//이전에 넣었던 Attachment가 존재하지 않았다
				if(list.isEmpty()) {//내가 새로운 파일을 넣지 않았을 경우 list에 값이 존재하지 않는다.
					
					b.setBoardType(1);//즉 위의 조건들을 볼때 모든파일을 삭제하였고 새로 추가한 첨부파일을 넣지 않았다:->첨부파일게시판에 있을 이유가 없으므로 일반게시판으로 옮긴다.
					
					}
				}else {// 전부삭제하는게 아니야 :일부 삭제 로직 구
					
					for(int level :delLevel) {
						if(level==0) {// 우리가 삭제한것들중에 level이 0이 있는가? 썸네일삭제를 한다면 다른 사진을 썸네일 설정을 해야하기 때문에 알아야 한다.
							bService.updateAttmLevel(b.getBoardId());
							break;
						}
					}
				}
			}
				
//			   우리가 새로들어온 파일을 파일에는 저장하였지만 DB에는 저장하지 않았다.
				for(int i=0;i<list.size();i++) {
					Attachment a =list.get(i);
					
					a.setRefBoardId(b.getBoardId()); //우리가 어디 board에 새파일을 집어 넣을건지 정해주어야 함!!
					
					if(existBeforeAttm) {// 이전에 넣었던 첨부파일이 존재를 한다!!
						a.setAttmLevel(1); //새로 집어 넣는 파일은 레벨을 1로 넣는다
					}else {
						if(i==0) { //이전에 넣었던 파일이 존재하지 않으면 섬네일을 정해주어야 
							a.setAttmLevel(0);
						}else {
							a.setAttmLevel(1);
						}
					}
				}
				int updateBoardResult=bService.updateBoard(b);

				int updateAttmResult=0;

				if(!list.isEmpty()) { //만약에 리스트가 비워져 있지 않다!!
					updateAttmResult=bService.insertAttm(list);
				}
				
				if(updateBoardResult+updateAttmResult==list.size()+1) {//내가 보드 수정한 값과 내가 attm수정했다 값과 updateBoard값이 플러스된 list.Size와 같다
					if(delRename.size()==deleteAttm.length&&updateAttmResult==0) {//삭제는 다됬고 업데이트가 된것이 없어!!
						return "redirect:list.bo"; //그러면 일반게시판 리스트로 돌아감!!
					
					}else {//일부만 삭제하였을 경우는 Attm리스트에 넣어준다 
//						return "redirect:selectAttm.at?bId"+b.getBoardId()+"&writer="+((Member)request.getSession().getAttribute("loginUser")).getNickName()+"&page="+page;
						model.addAttribute("bId",b.getBoardId());
						model.addAttribute("writer",((Member)request.getSession().getAttribute("loginUser")).getNickName());
						model.addAttribute("page",page);
						return "redirect:selectAttm.at";
					}
					
				}else {
					throw new BoardException("첨부파일게시글 수정 실패");
				
				}
		}
		
		// 개 ㅈ같은 json gson하면 될걸....ㅎ
//		@RequestMapping(value="insertReply.bo",produces="application/json; charset=UTF-8")// produces : 내가 데이터를 어떤형식으로 만들건지 정해주는 속
//		@ResponseBody
//		public String insertReply(@ModelAttribute Reply r) {
//			int result =bService.insertReply(r);
//			ArrayList<Reply> list = bService.selectReply(r.getRefBoardId());
//			
//			JSONArray array = new JSONArray();
//			for(Reply reply : list) {
//				JSONObject json=new JSONObject();
//				json.put("replyId", reply.getReplyId());
//				json.put("replyContent", reply.getReplyContent());
//				json.put("refBoardId", reply.getRefBoardId());
//				json.put("replyWriter", reply.getReplyWriter());
//				json.put("nick", reply.getReplyWriter());
//				json.put("replyCreateDate", reply.getReplyCreateDate());
//				json.put("replyModifyDate", reply.getReplyModifyDate());
//				json.put("replyStatus", reply.getReplyStatus());
//				array.put(json);
//			}
//			
//			return array.toString();
//		}
		
		
		//띠발 이거면 되는걸 json을 배웠네 ㅎㅎ;;
		@RequestMapping(value="insertReply.bo")// produces : 내가 데이터를 어떤형식으로 만들건지 정해주는 속
		public void insertReply(@ModelAttribute Reply r,HttpServletResponse response) {
			int result =bService.insertReply(r);
			ArrayList<Reply> list = bService.selectReply(r.getRefBoardId());
			
			response.setContentType("application/json; charset=UTF-8");
//			Gson gson=new Gson();
			
			GsonBuilder gb= new GsonBuilder();
			GsonBuilder gb2= gb.setDateFormat("yyyy-MM-dd");
			Gson gson = gb2.create();
			try {
				gson.toJson(list,response.getWriter());
			} catch (JsonIOException|IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
}