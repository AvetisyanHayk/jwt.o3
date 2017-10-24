<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="o3" uri="http://jwt.o3/tags"%>
<!DOCTYPE html>
<html>
    <o3:head title="Homepage"/>
    <body>
        <header>
            <o3:header/>
        </header>
        <div class="container">
            <form>
                <aside>
                    <button type="submit" class="button button-primary"><i class="fa fa-fw fa-search"></i> Search</button>
                    <button type="reset" class="button button-ghost-warning-inverse"><i class="fa fa-fw fa-reply"></i> Reset</button>
                    <a href="<c:url value='/'/>" class="button button-ghost-warning-inverse"><i class="fa fa-fw fa-remove"></i> Filters</a>

                    <label for="keyword" class="input-label">Keyword</label>
                    <input type="text" id="keyword" name="keyword" placeholder="Keyword" value="${param.keyword}" class="input"/>

                    <fieldset class="filter-box">
                        <legend><i class="fa fa-fw fa-calendar"></i> Year</legend>
                        <c:forEach items="${years}" var="year">
                            <c:set var="yearFound" value="false"/>
                            <c:set var="checkedYear" value=''/>
                            <c:forEach items="${paramValues.year}" var="paramYear">
                                <c:if test="${not yearFound and paramYear eq year}">
                                    <c:set var="checkedYear" value=' checked="checked"'/>
                                </c:if>
                            </c:forEach>
                            <label for="year-${year}">
                                <input type="checkbox" name="year" id="year-${year}" value="${year}"${checkedYear}/>
                                ${year}</label>
                            </c:forEach>
                    </fieldset>
                    <fieldset class="filter-box">
                        <legend><i class="fa fa-fw fa-leaf"></i> Genre</legend>
                        <c:forEach items="${genres}" var="genre">
                            <c:set var="genreFound" value="false"/>
                            <c:set var="checkedGenre" value=''/>
                            <c:forEach items="${paramValues.genreid}" var="paramGenre">
                                <c:if test="${not genreFound and paramGenre eq genre.id}">
                                    <c:set var="checkedGenre" value=' checked="checked"'/>
                                </c:if>
                            </c:forEach>
                            <label for="genre-${genre.id}">
                                <input type="checkbox" name="genreid" id="genre-${genre.id}" value="${genre.id}"${checkedGenre}/>
                                ${genre.name}</label>
                            </c:forEach>
                    </fieldset>
                    <button type="submit" class="button button-primary"><i class="fa fa-fw fa-search"></i> Search</button>
                    <button type="reset" class="button button-ghost-warning-inverse"><i class="fa fa-fw fa-reply"></i> Reset</button>
                    <a href="<c:url value='/'/>" class="button button-ghost-warning-inverse"><i class="fa fa-fw fa-remove"></i> Filters</a>

                </aside>

                <main class="container">
                    <fieldset>
                        <c:choose>
                            <c:when test="${not empty param.page}">
                                <c:set var="pageNumber" value="${param.page}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="pageNumber" value="1"/>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" id="page" name="page" value="${pageNumber}"/>
                        <legend>Results</legend>
                        <label for="sortby">Sort by</label>
                        <select id="sortby" name="sortby">
                            <c:forEach items="${sortOrderKeys}" var="key" varStatus="loop">
                                <option value="${key}"<c:if test="${not empty param.sortby and param.sortby eq key or loop.index eq 0}"> selected="selected"</c:if>>${key}</option>
                            </c:forEach>
                        </select>

                        <label for="perpage">Per page</label>
                        <select id="sortby" name="perpage">
                            <c:forEach items="${perPageTemplates}" var="template" varStatus="loop">
                                <option value="${loop.index}"<c:if test="${not empty param.perpage and param.perpage eq loop.index or loop.index eq 0}"> selected="selected"</c:if>>${template}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">Go!</button>
                    </fieldset>

                </main>
            </form>
        </div>
        <footer>
            <o3:footer/>
        </footer>
        <o3:tail/>
    </body>
</html>
