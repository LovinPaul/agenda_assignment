<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

    <form method="post" action="">

        <input type="hidden" name="item_id" value="<c:out value="${item.id}"/>">
        <p>Nume*</p>
        <input type="text" name="nume" required="required" value="<c:out value="${item.nume}"/>">
        <p>Prenume*</p>
        <input type="text" name="prenume" required="required" value="<c:out value="${item.prenume}"/>">
        <p>Email</p>
        <input type="text" name="email" value="<c:out value="${item.email}"/>">
        <p>Numar De Telefon*</p>
        <input type="text" name="numar_telefon" required="required" value="<c:out value="${item.numarDeTelefon}"/>">
        <p>Tara</p>
        <input type="text" name="tara" value="<c:out value="${item.tara}"/>">

        <br>
        <br>

        Campurile marcate cu * sunt obligatorii.

        <br>

        <input type="submit" name="save" value="submit">
        <input type="submit" value="cancel" formnovalidate>
    </form>

</body>
</html>