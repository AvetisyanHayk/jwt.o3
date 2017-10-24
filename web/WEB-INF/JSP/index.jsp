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
        <form>

            <aside>
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

            </aside>

            <main class="container">
                <fieldset>
                    <legend>Results</legend>
                    <label for="sortby">Sort by</label>
                    <select id="sortby" name="sortby">
                        <c:forEach items="${sortOrderKeys}" var="key">
                            <option value="${key}">${key}</option>
                        </c:forEach>
                    </select>
                    
                    <label for="perpage">Sort by</label>
                    <select id="sortby" name="perpage">
                        <c:forEach items="${perPageTemplates}" var="template" varStatus="loop">
                            <option value="${loop.index}">${template}</option>
                        </c:forEach>
                    </select>
                </fieldset>
            </main>
        </form>
        <footer>
            <o3:footer/>
        </footer>
        <o3:tail/>
    </body>
</html>
