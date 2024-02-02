<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <section class="category-section">
      <div class="container" data-aos="fade-up">

        <div class="section-header d-flex justify-content-between align-items-center mb-5">
          <h2>Hình ảnh dự án</h2>
          <div><a href="category.html" class="more">Xem tất cả</a></div>
        </div>

        <div class="row">
 			<%
 				String images = (String)session.getAttribute("images");
 			if(images!=null){
 				out.print(images);
 			}
 			%>
        </div>
      </div>
    </section>