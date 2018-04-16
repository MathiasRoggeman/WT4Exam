	package edu.ap.spring.controller;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;
	import java.util.Map.Entry;
	import java.util.Set;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

	import edu.ap.spring.redis.RedisService;

	@Controller
	public class RedisController {

	   private List<String> redisInhaalExamens = new ArrayList<String>();
	   private RedisService service;
	   private String student;
	   
		
	   @Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
	   }
	     
	   @RequestMapping(value = "/new", method = RequestMethod.POST)
	   @ResponseBody
	   public String messages() {
		   String html = "<HTML><HEAD><meta http-equiv=\"refresh\" content=\"5\"></HEAD>";
		   html += "<BODY><h1>Messages</h1><br/><br/><ul>";
		   for(String m : redisInhaalExamens) {
			   html += "<li>" + m;
		   }
		   html += "</BODY></HTML>";
		   
		   return html;
	   }
	   
	   @RequestMapping("/list?student=")
	   @ResponseBody
	   public String list() {

		   String html = "<HTML>";
		   
		   html += "<BODY><h1>" + service.bitCount("studentCount") + "</h1><br/><br/><ul>";
		   
		 
		   return html;
	   }
	   
	   // Messaging
	   public void onMessage(String message) {
		   this.redisInhaalExamens.add(message);
	   }
	}


