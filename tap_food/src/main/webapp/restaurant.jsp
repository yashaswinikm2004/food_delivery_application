<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap_food.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodExpress - Restaurants</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial,Helvetica,sans-serif;
}

body{
    background:#f5f5f5;
    color:#333;
}

/* Navbar */

nav{
    background:#fff;
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:18px 80px;
    box-shadow:0 2px 10px rgba(0,0,0,.1);
    position:sticky;
    top:0;
    z-index:1000;
}

.logo{
    font-size:30px;
    font-weight:bold;
    color:#fc8019;
}

nav ul{
    display:flex;
    list-style:none;
    gap:30px;
}

nav ul li a{
    text-decoration:none;
    color:#333;
    font-size:17px;
    font-weight:600;
}

nav ul li a:hover{
    color:#fc8019;
}

/* Hero */

.hero{
    height:300px;
    background:linear-gradient(rgba(0,0,0,.45),rgba(0,0,0,.45)),
    url("https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=1400&q=80");
    background-size:cover;
    background-position:center;
    display:flex;
    justify-content:center;
    align-items:center;
    flex-direction:column;
    color:white;
    text-align:center;
}

.hero h1{
    font-size:48px;
}

.hero p{
    margin-top:15px;
    font-size:20px;
}

/* Heading */

.heading{
    text-align:center;
    margin:50px 0 35px;
    font-size:34px;
}

/* Cards */

.container{
    width:90%;
    margin:auto;
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(320px,1fr));
    gap:30px;
    padding-bottom:60px;
}

.card{
    background:#fff;
    border-radius:15px;
    overflow:hidden;
    box-shadow:0 5px 15px rgba(0,0,0,.12);
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
    font-size:24px;
    margin-bottom:12px;
    color:#222;
}

.info{
    margin:8px 0;
    font-size:15px;
    color:#555;
}

.rating{
    display:inline-block;
    background:#2e7d32;
    color:white;
    padding:5px 14px;
    border-radius:20px;
    margin-top:12px;
    font-weight:bold;
}

.status-open{
    color:#2e7d32;
    font-weight:bold;
}

.status-close{
    color:red;
    font-weight:bold;
}

.btn{
    display:block;
    width:100%;
    margin-top:18px;
    padding:12px;
    background:#fc8019;
    color:white;
    text-align:center;
    text-decoration:none;
    border-radius:8px;
    font-size:16px;
    font-weight:bold;
}

.btn:hover{
    background:#e56b0a;
}

footer{
    background:#222;
    color:white;
    text-align:center;
    padding:18px;
}

</style>

</head>

<body>

<nav>

<div class="logo">🍽 FoodExpress</div>

<ul>
<li><a href="#">Home</a></li>
<li><a href="#">Restaurants</a></li>
<li><a href="#">Offers</a></li>
<li><a href="#">Contact</a></li>
<li><a href="login.html">Login</a></li>
<li><a href="register.html">Sign Up</a></li>
<li><a href="#">👤 Profile</a></li>
</ul>

</nav>

<section class="hero">
<h1>Discover India's Best Restaurants</h1>
<p>Order delicious food from your favourite restaurants.</p>
</section>

<h1 class="heading">Popular Restaurants</h1>

<div class="container">

<%
List<Restaurant> allRestaurants=(List<Restaurant>)request.getAttribute("allRestaurants");

if(allRestaurants!=null){
    for(Restaurant restaurant:allRestaurants){
%>

<div class="card">

    <img src="<%=restaurant.getImagePath()%>" alt="<%=restaurant.getName()%>">

    <div class="content">

        <h2><%=restaurant.getName()%></h2>

        <p class="info">
            <b>🍽 Cuisine :</b>
            <%=restaurant.getCuisineType()%>
        </p>

        <p class="info">
            <b>⏱ Delivery :</b>
            <%=restaurant.getDeliveryTime()%> mins
        </p>

        <p class="info">
            <b>📍 Address :</b>
            <%=restaurant.getAddress()%>
        </p>

        <div class="rating">
            ⭐ <%=restaurant.getRating()%>
        </div>

        <p class="info">
            <b>Status :</b>

            <% if(restaurant.getIsActive()){ %>

                <span class="status-open">Open</span>

            <% } else { %>

                <span class="status-close">Closed</span>

            <% } %>

        </p>

        <a class="btn"
           href="menu?restaurantId=<%=restaurant.getRestaurantId()%>">
           View Menu
        </a>

    </div>

</div>

<%
    }
}
%>

</div>

<footer>
© 2026 FoodExpress. All Rights Reserved.
</footer>

</body>
</html>