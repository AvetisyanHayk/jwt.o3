<%-- 
    Document   : index
    Created on : Oct 10, 2017, 1:06:41 PM
    Author     : Hayk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="o3" uri="http://jwt.o3/tags"%>
<!DOCTYPE html>
<html>
    <o3:head title="Homepage"/>
    <body>
        <header>
            <o3:header/>
            <form>
                <label for="year">Year</label>
                <select id="year" name="year">
                    <option value="">-</option>
                    <c:forEach items="${years}" var="year">
                        <option value="${year}"<c:if test="${year eq param.year}"> selected="selected"</c:if>>${year}</option>
                    </c:forEach>
                </select>
                
                <label for="genre">Genre</label>
                <select id="genreid" name="genreid">
                    <option value="">-</option>
                    <c:forEach items="${genres}" var="genre">
                        <option value="${genre.id}"<c:if test="${genre.id eq param.genreid}"> selected="selected"</c:if>>${genre.name}</option>
                    </c:forEach>
                </select>
                
                <label for="keyword">Keyword</label>
                <input type="text" id="keyword" name="keyword" placeholder="Keyword" value="${param.keyword}"/>
                
                <button type="submit"><i class="fa fa-fw fa-search"></i> Search</button>
                <a href="<c:url value='/'/>"><i class="fa fa-fw fa-reply"></i> Reset</a>
            </form>
        </header>

        <main class="container">
        </main>

        <footer>
            <o3:footer/>
        </footer>
        <o3:tail/>
    </body>
</html>
