<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap_food.model.Menu"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Restaurant Menu</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, Helvetica, sans-serif;
}

body{
    background:#f5f5f5;
}

header{
    background:#fc8019;
    color:white;
    text-align:center;
    padding:25px;
    font-size:32px;
    font-weight:bold;
}

.container{
    width:90%;
    margin:40px auto;
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(280px,1fr));
    gap:30px;
}

.card{
    background:white;
    border-radius:15px;
    overflow:hidden;
    box-shadow:0 5px 15px rgba(0,0,0,.15);
    transition:.3s;
}

.card:hover{
    transform:translateY(-8px);
}

.card img{
    width:100%;
    height:220px;
    object-fit:cover;
}

.content{
    padding:18px;
}

.content h2{
    margin-bottom:10px;
    color:#333;
}

.price{
    color:#fc8019;
    font-size:20px;
    font-weight:bold;
    margin:10px 0;
}

.rating{
    display:inline-block;
    background:#2e7d32;
    color:white;
    padding:5px 12px;
    border-radius:20px;
    margin:10px 0;
}

.desc{
    color:#555;
    margin-top:10px;
    line-height:1.5;
}

button{
    width:100%;
    margin-top:18px;
    padding:12px;
    background:#fc8019;
    color:white;
    border:none;
    border-radius:8px;
    cursor:pointer;
    font-size:16px;
    font-weight:bold;
}

button:hover{
    background:#e56b0a;
}

</style>

</head>
<body>

<header>
Restaurant Menu
</header>

<div class="container">

<%
List<Menu> allMenus = (List<Menu>)request.getAttribute("allMenus");

if(allMenus != null){
    for(Menu menu : allMenus){
%>

<div class="card">

    <img src="<%=menu.getImagePath()%>" alt="<%=menu.getItemName()%>">

    <div class="content">

        <h2><%=menu.getItemName()%></h2>

        <p class="price">
            ₹ <%=menu.getPrice()%>
        </p>

        <div class="rating">
            ⭐ <%=menu.getRating()%>
        </div>

        <p class="desc">
            <%=menu.getDescription()%>
        </p>
        
        <form action="CartServlet" method="post">
    <input type="hidden" name="menuId" value="<%=menu.getMenuId()%>">
    <input type="hidden" name="restaurantId" value="<%=menu.getRestaurantId()%>">
    <input type="hidden" name="qty" value=1>
    <input type="hidden" name="action" value=add>
    <button type="submit">Add to Cart</button>
</form>

        

    </div>

</div>

<%
    }
}
else{
%>

<h2>No Menu Items Found</h2>

<%
}
%>

</div>

</body>
</html>