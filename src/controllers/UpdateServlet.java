package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())){
			EntityManager em = DBUtil.createEntityManager();
			//データ取得
			Task t = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

			//update
			String title = request.getParameter("title");
			t.setTitle(title);

			String content = request.getParameter("content");
			t.setContent(content);

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			t.setUpdated_at(currentTime);

			//DBUpdate
			em.getTransaction().begin();
			em.getTransaction().commit();
			request.getSession().setAttribute("flush", "登録が完了しました。");
			em.close();

			//不要データ削除
			request.getSession().removeAttribute("task_id");

			//redirect
			response.sendRedirect(request.getContextPath() + "/index");

		}
	}

}
