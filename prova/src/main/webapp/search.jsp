<%@ page import="org.example.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
    <html>
    <head>
        <title>Risultati della ricerca</title>
    </head>

    <body>
    <h2>Risultati per: <%= request.getAttribute("nome")%></h2>
    <%String username = (String) request.getAttribute("username");
      String password =(String)request.getAttribute("password");%>
    <%ArrayList<Prodotto> risultati = (ArrayList<Prodotto>) request.getAttribute("risultati");
    if(risultati != null && !risultati.isEmpty()){
        for (Prodotto p : risultati) {%>
    <div class="prodotto">
        <img src="<%= p.getImmagine() %>"/>
        <h3><%= p.getNome() %></h3>
        <p><%= p.getDescrizione() %></p>
        <p>Prezzo: €<%= p.getPrezzo() %></p>
        <p>Disponibilità: <%= p.getDisponibilita() %></p>
        <form action="carrello" method="POST">
            <input type="hidden" name="id" value="<%= p.getId_prodotto() %>">
            <input type="hidden" name="username" value="<%= username %>">
            <input type="hidden" name="password" value="<%= password %>">
            <input type="number" name="quantita" value="1" min="1" required>
            <button type="submit">Aggiungi al carrello</button>
        </form>
    </div>

    <%}
    }else{%>
        <p>Nessun prodotto trovato</p>
    <%}%>

    <br>
    <a href="home?username=<%= username %>&password=<%=password%>">Torna alla ricerca</a>
    </body>
</html>
