<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="o3" uri="http://jwt.o3/tags"%>
<!DOCTYPE html>
<html>
    <o3:head title="Homepage"/>
    <body>
        <header>
            <o3:header/>
        </header>
        
        <aside>
            <form>
                <label for="keyword">Keyword</label>
                <input type="text" id="keyword" name="keyword" placeholder="Keyword" value="${param.keyword}"/>
                
                <fieldset>
                    <legend>Year</legend>
                    <c:forEach items="${years}" var="year">
                        <input type="checkbox" name="year" id="year-${year}" value="${year}"/>
                        <label for="year-${year}">${year}</label>
                    </c:forEach>
                </fieldset>
                <fieldset>
                    <legend>Genre</legend>
                    <c:forEach items="${genres}" var="genre">
                        <input type="checkbox" name="genreid" id="genre-${genre.id}" value="${genre.id}"/>
                        <label for="genre-${genre.id}">${genre.name}</label>
                    </c:forEach>
                </fieldset>
                
                <button type="submit"><i class="fa fa-fw fa-search"></i> Search</button>
                <a href="<c:url value='/'/>"><i class="fa fa-fw fa-reply"></i> Reset</a>
            </form>
        </aside>

        <main class="container">
        </main>

        <footer>
            <o3:footer/>
        </footer>
        <o3:tail/>
    </body>
</html>
