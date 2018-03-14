<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<html>
<body>



	<c:forEach items="${items}" var="item">
	    <div>
            Nume : ${item.nume}<br>
            Prenume : ${item.prenume}<br>
            Email : ${item.email}<br>
            Numar Telefon : ${item.numarDeTelefon}<br>
            Tara : ${item.tara}<br>

            <form method="post" action="">
                <input type="hidden" name="item_id" value="<c:out value="${item.id}"/>">
                <button name="delete">
                    Sterge
                </button>
                <button name="edit">
                    Editeaza
                </button>
            </form>

        </div>
    </c:forEach>

</body>
</html>