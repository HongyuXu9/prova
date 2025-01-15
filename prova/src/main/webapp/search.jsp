<%@ page import="org.example.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
    <html>
    <head>
        <title>Risultati della ricerca</title>
        <style>
            h2{
                top : 30px;
                text-align: center;
                padding: 20px;
                background-color: orange;
            }

            .listProdotto {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                margin-left: 10px;
                margin-right: 10px;
                gap: 20px;
            }

            .prodotto {
                background-color: lightgrey;
                padding: 10px;
                width: 300px;
                border: 1px solid;
                border-radius: 8px;
            }


            .prodotto img {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }

            .prodotto form input[type=number] {
                width: 20%;
            }
            form button:hover {
                color: red;
            }

            form button:visited {
                color: green;
            }
        </style>
    </head>

    <body>
    <h2>Risultati per: <%= request.getAttribute("nome")%></h2>
    <%String username = (String) request.getAttribute("username");
      String password =(String)request.getAttribute("password");%>
    <%ArrayList<Prodotto> risultati = (ArrayList<Prodotto>) request.getAttribute("risultati");
    if(risultati != null && !risultati.isEmpty()){
        for (Prodotto p : risultati) {%>
    <div class="listProdotto">
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
                <input type="number" name="quantita" value="1" min="1" max="<%= p.getDisponibilita()%>" required>
                <button type="submit">Aggiungi al carrello</button>
            </form>
        </div>
    </div>

    <%}
    }else{%>
        <p style="text-align: center">Nessun prodotto trovato</p>
    <%}%>

    <br>
    <a href="home?username=<%= username %>&password=<%=password%>">Torna alla ricerca</a>
    </body>
</html>
