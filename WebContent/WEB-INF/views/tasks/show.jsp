<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
	<c:param name="content">
		<c:choose>
			<c:when test="${task != null}">
				<h2>id : ${task.id} のタスク詳細ページ</h2>

				<table>
					<tbody>
						<tr>
							<th>タイトル</th>
							<td><c:out value="${task.title}" /></td>
						</tr>
						<tr>
							<th>タスク内容</th>
							<th><c:out value="${task.content}" /></th>
						</tr>
						<tr>
							<th>作成日時</th>
							<th><c:out value="${task.created_at}" /></th>
						</tr>
						<tr>
							<th>更新日時</th>
							<th><c:out value="${task.updated_at}" /></th>
						</tr>
					</tbody>
				</table>

				<p><a href="<c:url value="/index" />">一覧に戻る</a></p>
				<p><a href="<c:url value="/edit?id=${task.id}" />">このタスクを編集する</a></p>
			</c:when>
			<c:otherwise>
				<h2>お探しのデータは見つかりませんでした。</h2>
				<p><a href="<c:url value="/index" />">一覧に戻る</a></p>
			</c:otherwise>
		</c:choose>
	</c:param>
</c:import>