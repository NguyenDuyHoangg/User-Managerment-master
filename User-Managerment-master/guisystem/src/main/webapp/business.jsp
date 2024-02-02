<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ======= Business Category Section ======= -->
    <section class="category-section">
      <div class="container" data-aos="fade-up">

        <div class="section-header d-flex justify-content-between align-items-center mb-5">
          <h2>Dự án</h2>
          <div><a href="category.html" class="more">Xem tất cả</a></div>
        </div>

        <div class="row">
          <%
          	String services = (String)session.getAttribute("services");
          if(services!=null){
        	  out.print(services);
          }
          %>
          
        </div>
      </div>
    </section><!-- End Business Category Section -->