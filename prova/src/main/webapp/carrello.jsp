<%@ page import="org.example.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Prodotti</title>
    </head>

    <body>
    <%String username = (String)request.getAttribute("username");
    String password = (String)request.getAttribute("password");%>
    <h1>Lista Prodotti per <%= username %></h1>
    <%  ArrayList<Prodotto> risultati = (ArrayList<Prodotto>) request.getAttribute("risultati");
        ArrayList<Integer> quantitaP = (ArrayList<Integer>) request.getAttribute("quantitaP");
        if (risultati != null && quantitaP != null && risultati.size() == quantitaP.size()) {
            for (int i = 0; i < risultati.size(); i++) {
                Prodotto p = risultati.get(i);
                int quantita = quantitaP.get(i);%>
        <div class="prodotto">
            <img src="<%= p.getImmagine() %>"/>
            <h3><%= p.getNome() %></h3>
            <p><%= p.getDescrizione() %></p>
            <p>Prezzo: €<%= p.getPrezzo() %></p>
            <p>Quantita: <%=quantita %></p>
        </div>

    <%}%>

    <form action="ordine" method="POST" >
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="password" value="<%= password %>">
        <% for (int i = 0; i < risultati.size(); i++) { %>
        <input type="hidden" name="idProdotto" value="<%= risultati.get(i).getId_prodotto() %>">
        <input type="hidden" name="quantita" value="<%= quantitaP.get(i) %>">
        <%}%>
        <button type="submit">Ordina ORA!</button>
    </form>

    <% }else{%>
    <p>Non ci sono prodotti nel carrello.</p>
    <%}%>




    <br>
    <a href="home?username=<%= username %>&password=<%=password%>">Torna alla ricerca</a>
    </body>
</html>

