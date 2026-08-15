<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="com.tap_food.model.Cart"%>
<%@ page import="com.tap_food.model.CartItem"%>


<%
Cart cart=(Cart)session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>FoodExpress | Cart</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial,Helvetica,sans-serif;
}

body{
    background:#f2f2f2;
}

.container{
    width:95%;
    max-width:1400px;
    margin:30px auto;
    display:flex;
    gap:25px;
}

.left{
    width:68%;
}

.right{
    width:32%;
}

.card{

    background:white;
    border-radius:15px;
    padding:20px;
    margin-bottom:20px;
    box-shadow:0 2px 10px rgba(0,0,0,.08);

}

.header{

display:flex;
justify-content:space-between;
align-items:center;
margin-bottom:20px;

}

.header h1{

font-size:35px;
color:#222;

}

.restaurant{

font-size:18px;
color:#ff5200;
font-weight:bold;

}

.item{

display:flex;
justify-content:space-between;
align-items:center;
padding:20px 0;
border-bottom:1px solid #eee;

}

.food{

display:flex;
align-items:center;
gap:20px;

}

.food img{

width:110px;
height:110px;
object-fit:cover;
border-radius:12px;

}

.details h3{

font-size:22px;
margin-bottom:8px;

}

.details p{

color:#777;
font-size:15px;

}

.price{

margin-top:8px;
font-size:18px;
font-weight:bold;

}

.qty{

display:flex;
align-items:center;
gap:10px;

}

.qty input{

width:60px;
padding:7px;
text-align:center;

}

button{

padding:10px 18px;
border:none;
border-radius:8px;
cursor:pointer;
font-size:15px;

}

.update{

background:#ff5200;
color:white;

}

.remove{

background:#e53935;
color:white;

}

.bill h2{

margin-bottom:20px;

}

.row{

display:flex;
justify-content:space-between;
margin:18px 0;

}

.total{

font-size:22px;
font-weight:bold;
border-top:1px solid #ddd;
padding-top:20px;

}

.checkout{

width:100%;
background:#16a34a;
color:white;
padding:15px;
font-size:18px;
margin-top:25px;

}

.addMore{
    display:inline-block;
    text-decoration:none;
    padding:10px 18px;
    border:2px solid #ff5200;
    color:#ff5200;
    border-radius:8px;
    font-weight:bold;
}

.empty{

text-align:center;
font-size:30px;
padding:80px;
background:white;
border-radius:15px;

}

@media(max-width:900px){

.container{

flex-direction:column;

}

.left,
.right{

width:100%;

}

.item{

flex-direction:column;
align-items:flex-start;
gap:20px;

}

}

</style>

</head>

<body>

<div class="container">

   <div class="left">

<%
if(cart==null || cart.getItems().isEmpty()){
%>

<div class="empty">
    🛒 Your Cart is Empty
</div>

<%
}else{

double grandTotal = 0;
%>

<div class="card">

    <div class="header">
        <div>
            <h1>Your Cart</h1>
            <p class="restaurant">FoodExpress</p>
        </div>
<a href="<%=request.getContextPath()%>/menu?restaurantId=<%=session.getAttribute("restaurantId")%>" class="addMore">
    + Add More Items
</a>
    </div>

<%
for(CartItem item : cart.getItems().values()){

double total = item.getPrice() * item.getQty();
grandTotal += total;
%>

<div class="item">

    <div class="food">

        <img src="Images/default-food.png" alt="<%=item.getName()%>">

        <div class="details">

            <h3><%=item.getName()%></h3>

            <p>Fresh & Delicious</p>

            <div class="price">
                ₹ <%=item.getPrice()%>
            </div>

        </div>

    </div>

    <div>

        <form action="CartServlet" method="post">

    <input type="hidden" name="action" value="update">

    <input type="hidden" name="menuId" value="<%=item.getMenuId()%>">

    <input type="hidden" name="restaurantId" value="<%=item.getRestaurantId()%>">

    <input type="number"
           name="qty"
           value="<%=item.getQty()%>"
           min="1">

    <button type="submit" class="update">
        Update
    </button>

</form>

        <br>

        <form action="CartServlet" method="post">

            <input type="hidden"
                   name="action"
                   value="remove">

            <input type="hidden"
                   name="menuId"
                   value="<%=item.getMenuId()%>">

            <button
                type="submit"
                class="remove">
                Remove
            </button>

        </form>

    </div>

</div>

<%
}
%>

</div>

    <%
double deliveryFee = 40;
double platformFee = 5;
double discount = 30;

double finalAmount = grandTotal + deliveryFee + platformFee - discount;
%>

<div class="card bill">

    <h2>Bill Details</h2>

    <div class="row">
        <span>Item Total</span>
        <span>₹ <%=grandTotal%></span>
    </div>

    <div class="row">
        <span>Delivery Fee</span>
        <span>₹ <%=deliveryFee%></span>
    </div>

    <div class="row">
        <span>Platform Fee</span>
        <span>₹ <%=platformFee%></span>
    </div>

    <div class="row" style="color:green;">
        <span>Discount</span>
        <span>- ₹ <%=discount%></span>
    </div>

    <div class="row total">
        <span>To Pay</span>
        <span>₹ <%=finalAmount%></span>
    </div>

    <form action="checkout.jsp" method="post">

        <button type="submit" class="checkout">
            Proceed to Checkout →
        </button>

    </form>

</div>

<br>

<div class="card">

    <h3>Why order from FoodExpress?</h3>

    <br>

    <p>🚀 Fast Delivery in 30-40 mins</p>

    <br>

    <p>🍔 Fresh & Hygienic Food</p>

    <br>

    <p>💳 Secure Online Payments</p>

    <br>

    <p>⭐ Best Restaurants Near You</p>

</div>
<%
}
%>

</div>   

</div>   

</body>
</html>