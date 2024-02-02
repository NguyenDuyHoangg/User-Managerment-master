<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../header.jsp" flush="true"></jsp:include>
	<main id="main">
    <section>
      <div class="container">
        <div class="row">
		<%
    		String news = (String)session.getAttribute("news");
          	if(news!=null){
        		out.print(news);
          	}
       	%>
       	</div>
      </div>
    </section>
  </main><!-- End #main -->
	<!-- End #main -->
<jsp:include page="../footer.jsp" flush="true"></jsp:include>