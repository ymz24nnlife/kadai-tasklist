package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.Validator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();
			em.getTransaction().begin();

			Task t = new Task();

			String title = request.getParameter("title");
			t.setTitle(title);

			String content = request.getParameter("content");
			t.setContent(content);

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			t.setCreated_at(currentTime);
			t.setUpdated_at(currentTime);

			List<String> errors = Validator.validate(t);
			if(errors.size() > 0) {
				em.close();

				//初期値設定、エラーメッセージ送信
				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("task", t);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
				rd.forward(request, response);
			}else {
				//DBに保存
				em.persist(t);
				em.getTransaction().commit();
				request.getSession().setAttribute("flush", "登録が完了しました。");
				em.close();

				//redirect
				response.sendRedirect(request.getContextPath() + "/index");
			}
		}
	}

}
