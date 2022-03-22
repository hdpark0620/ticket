package com.demo.ticket;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.Issue;

@SpringBootTest
class TicketApplicationTests {

	@Test
	void contextLoads() {
		
	    String uri = "http://localhost/redmine";
	    String apiAccessKey = "";
	    String projectKey = "1";
	    Integer queryId = null; // any

	    RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
	    IssueManager issueManager = mgr.getIssueManager();
	    List<Issue> issues;
		try {
			issues = issueManager.getIssues(projectKey, queryId);
		    for (Issue issue : issues) {
		        System.out.println(issue.toString());
		        
		        Issue data = issueManager.getIssueById(issue.getId(), Include.attachments);
				for (Attachment attachment: data.getAttachments()) {
					System.out.println(attachment.toString());
				}
		    }
		} catch (RedmineException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
