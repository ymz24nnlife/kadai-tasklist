package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Task;

public class Validator {
	//validation
	public static List<String> validate(Task t){
		List<String> errors = new ArrayList<String>();

		String title_error = validateTitle(t.getTitle());
		if(!title_error.equals("")) {
			errors.add(title_error);
		}

		String content_error = validateContent(t.getContent());
		if(!content_error.equals("")) {
			errors.add(content_error);
		}

		return errors;
	}

	//title check
	private static String validateTitle(String title) {
		if(title == null || title.equals("")){
			return "タイトルを入力してください。";
		}

		return "";
	}

	//content check
	public static String validateContent(String content) {
		if(content == null || content.equals("")) {
			return "タスク内容を入力してください。";
		}

		return "";
	}

}
