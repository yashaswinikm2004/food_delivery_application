<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.tap_food.model.Cart"%>
<%@ page import="com.tap_food.model.CartItem"%>
<%@ page import="java.util.List,com.tap_food.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>FoodExpress - Checkout</title>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background: #f5f5f5;
            color: #333;
        }

        /* Header */

        .top-header {
            background: white;
            padding: 18px 60px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #ddd;
        }

        .logo {
            font-size: 28px;
            font-weight: bold;
            color: #ff5200;
        }

        .secure {
            color: #555;
            font-size: 14px;
        }

        /* Main Container */

        .checkout-container {
            width: 90%;
            max-width: 1200px;
            margin: 35px auto;
            display: grid;
            grid-template-columns: 1.5fr 1fr;
            gap: 30px;
        }

        /* Left Side */

        .left-section {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .box {
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        .box h2 {
            margin-bottom: 20px;
            font-size: 21px;
        }

        /* Address */

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            font-size: 14px;
        }

        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 13px;
            border: 1px solid #ccc;
            border-radius: 7px;
            font-size: 15px;
            outline: none;
        }

        .form-group input:focus,
        .form-group textarea:focus {
            border-color: #ff5200;
        }

        textarea {
            height: 90px;
            resize: none;
        }

        .address-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }

        /* Payment */

        .payment-option {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 12px;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .payment-option:hover {
            border-color: #ff5200;
            background: #fff8f4;
        }

        .payment-option input {
            accent-color: #ff5200;
            transform: scale(1.2);
        }

        .payment-title {
            font-weight: bold;
        }

        .payment-description {
            font-size: 13px;
            color: #777;
            margin-top: 3px;
        }

        /* Right Side */

        .right-section {
            background: white;
            padding: 25px;
            border-radius: 12px;
            height: fit-content;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        .right-section h2 {
            margin-bottom: 20px;
            font-size: 21px;
        }

        .restaurant-name {
            font-size: 14px;
            color: #777;
            margin-bottom: 20px;
        }

        /* Cart Items */

        .cart-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 0;
            border-bottom: 1px solid #eee;
        }

        .item-left {
            display: flex;
            gap: 12px;
            align-items: center;
        }

        .food-image {
            width: 60px;
            height: 60px;
            border-radius: 8px;
            object-fit: cover;
        }

        .item-name {
            font-weight: bold;
            font-size: 15px;
        }

        .item-qty {
            font-size: 13px;
            color: #777;
            margin-top: 5px;
        }

        .item-price {
            font-weight: bold;
        }

        /* Delivery */

        .delivery-box {
            margin: 20px 0;
            padding: 15px;
            background: #f7f7f7;
            border-radius: 8px;
        }

        .delivery-title {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .delivery-time {
            color: #555;
            font-size: 14px;
        }

        /* Bill */

        .bill {
            margin-top: 20px;
        }

        .bill-row {
            display: flex;
            justify-content: space-between;
            margin: 13px 0;
            font-size: 15px;
        }

        .discount {
            color: green;
        }

        .total {
            border-top: 1px solid #ddd;
            padding-top: 15px;
            margin-top: 15px;
            font-size: 19px;
            font-weight: bold;
        }

        /* Place Order */

        .place-order {
            width: 100%;
            background: #ff5200;
            color: white;
            border: none;
            padding: 15px;
            border-radius: 8px;
            font-size: 17px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 20px;
        }

        .place-order:hover {
            background: #e84900;
        }

        .secure-payment {
            text-align: center;
            font-size: 12px;
            color: #777;
            margin-top: 12px;
        }

        /* Responsive */

        @media (max-width: 800px) {

            .checkout-container {
                grid-template-columns: 1fr;
            }

            .top-header {
                padding: 15px 25px;
            }

            .checkout-container {
                width: 94%;
            }

            .address-row {
                grid-template-columns: 1fr;
            }
        }

    </style>
</head>

<body>

    <!-- Header -->

    <div class="top-header">

        <div class="logo">
            FoodExpress
        </div>

        <div class="secure">
            🔒 Secure Checkout
        </div>

    </div>


    <!-- Checkout Container -->

    <div class="checkout-container">


        <!-- ================= LEFT SIDE ================= -->

        <div class="left-section">


            <!-- Delivery Address -->

            <div class="box">

                <h2>📍 Delivery Address</h2>

                <div class="form-group">

                    <label>Full Name</label>

                    <input
                        type="text"
                        placeholder="Enter your name">

                </div>


                <div class="form-group">

                    <label>Phone Number</label>

                    <input
                        type="tel"
                        placeholder="Enter your phone number">

                </div>


                <div class="form-group">

                    <label>Address</label>

                    <textarea
                        placeholder="House No, Street, Area, Landmark"></textarea>

                </div>


                <div class="address-row">

                    <div class="form-group">

                        <label>City</label>

                        <input
                            type="text"
                            placeholder="City">

                    </div>


                    <div class="form-group">

                        <label>PIN Code</label>

                        <input
                            type="text"
                            placeholder="PIN Code">

                    </div>

                </div>

            </div>


            <!-- Payment Method -->

            <div class="box">

                <h2>💳 Payment Method</h2>


                <label class="payment-option">

                    <input
                        type="radio"
                        name="payment"
                        value="upi">

                    <div>

                        <div class="payment-title">
                            📱 UPI
                        </div>

                        <div class="payment-description">
                            Pay using Google Pay, PhonePe, Paytm or other UPI apps
                        </div>

                    </div>

                </label>


                <label class="payment-option">

                    <input
                        type="radio"
                        name="payment"
                        value="card">

                    <div>

                        <div class="payment-title">
                            💳 Credit / Debit Card
                        </div>

                        <div class="payment-description">
                            Pay securely using your card
                        </div>

                    </div>

                </label>


                <label class="payment-option">

                    <input
                        type="radio"
                        name="payment"
                        value="cod">

                    <div>

                        <div class="payment-title">
                            💵 Cash on Delivery
                        </div>

                        <div class="payment-description">
                            Pay when your order is delivered
                        </div>

                    </div>

                </label>

            </div>

        </div>


        <!-- ================= RIGHT SIDE ================= -->

        <div class="right-section">

            <h2>Order Summary</h2>

            <p class="restaurant-name">
                FoodExpress Restaurant
            </p>


            
                        
                    <%
                      Cart cart=(Cart)session.getAttribute("cart");
                      double itemTotal=0;
                      double grandTotal=0;
                      double dc=30;
                      double pf=10;
                      double gst=42;
                      if(cart !=null && cart.getItems().isEmpty())
                      {
                        for(CartItem item:cart.getItems().values())
                        {
                        	itemTotal=grandTotal+item.getPrice();
                        }
                      }
                       grandTotal=itemTotal+dc+pf+gst;
                    %>    
                     
                    <%
if (cart != null && !cart.getItems().isEmpty()) {

    for (CartItem item : cart.getItems().values()) {
%>

    <div class="cart-item">

        <div class="item-left">

           <img src="<%=request.getContextPath()%>/<%=item.getImagePath()%>"
     class="food-image"
     alt="<%=item.getName()%>">

            <div>

                <div class="item-name">
                    <%= item.getName() %>
                </div>

                <div class="item-qty">
                    Qty: <%= item.getQty() %>
                </div>

            </div>

        </div>

        <div class="item-price">
            ₹<%= item.getPrice() * item.getQty() %>
        </div>

    </div>

<%
    }
}
%>
                        
                    
                    
                    

                   


           

            <!-- Delivery Time -->

            <div class="delivery-box">

                <div class="delivery-title">
                    🚀 Estimated Delivery
                </div>

                <div class="delivery-time">
                    30 - 40 minutes
                </div>

            </div>


            <!-- Bill Details -->

            <div class="bill">

                <div class="bill-row">

                    <span>Item Total</span>

                    <span>₹720</span>

                </div>


                <div class="bill-row">

                    <span>Delivery Fee</span>

                    <span>₹40</span>

                </div>


                <div class="bill-row">

                    <span>Platform Fee</span>

                    <span>₹5</span>

                </div>


                <div class="bill-row">

                    <span>GST</span>

                    <span>₹36</span>

                </div>


                <div class="bill-row discount">

                    <span>Discount</span>

                    <span>- ₹30</span>

                </div>


                <div class="bill-row total">

                    <span>To Pay</span>

                    <span>₹771</span>

                </div>

            </div>


            <!-- Place Order -->

            <button class="place-order">

                Place Order → 

            </button>


            <div class="secure-payment">

                🔒 Your payment information is secure

            </div>

        </div>

    </div>

</body>
</html>