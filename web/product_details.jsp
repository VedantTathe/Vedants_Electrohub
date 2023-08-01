<%@page import="java.io.File"%>
<%@page import="Bean.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product</title>
        <%@ include file = "components/css/common_css_file.html" %>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            html, body{
                height: 100%;
                width: 100%;
            }

            nav li{
                cursor: pointer;
            }

            nav a{
                text-decoration: none;
                color: #ffffff8c;
            }

            .review{
                font-size: 1rem;
                background-color: #00ab00;
                width: fit-content;
                padding: 1px 5px;
                border-radius: 5px;
                margin: 0 5px 0 0;
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 5px;
                flex-wrap: nowrap;

            }

            #product-img{
                transition: 0.5s;
                width: 25rem;
            }

            #product-img:hover{
                transform: scale(1.1);
            }

            .mybox{
                flex-direction: column;
            }

            .mybtn {
                color: white;
                border: 1px solid white;
                transition: all ease 0.5s;
                width: 10rem;
            }

            .mybtn:hover {
                background-color: white;
                color: white;
                border: 1px solid black;
                transform: translateY(-5px);
            }

            .col1{
                padding-bottom: 0;
            }

            .col2{
                padding: 20px;
            }

            @media only screen and (min-width: 992px)
            {
                .col2{
                    overflow-y: scroll;
                    height: 92vh;
                }
                .col1{
                    height:92vh;
                    border-right: 2px solid grey;
                }
                body{
                    overflow: hidden;
                }

                .mybox{
                    flex-direction: row;
                }

                .col2{
                    padding: 40px;
                }

            }

            @media only screen and (max-width: 570px)
            {
                #product-img{
                    width: 16rem;
                }

                .mybtn{

                    width: 6.5rem;
                    font-size: 0.7rem;
                }
            }

        </style>

    </head>

    <body>

        <%
            Object pr = session.getAttribute("product");
            if (pr != null) {
                Product product = (Product) session.getAttribute("product");
        %>

        <div>
            <!---------------------------------------Navbar------------------------------------->
            <%@ include file = "components/navbar.jsp" %>

            <div class="mybox container d-flex gap-sm-2 gap-lg-5 ">
                <div class="col1 p-0 d-flex flex-column justify-content-center align-items-center" style="">
                    <div class="" style="width:fit-content; height: fit-content; margin:auto;" >
                        <%                            String path = "components/images" + "/" + "products" + "/" + product.getPImageName();
                        %>
                        <img id="product-img" class="ps-5 pe-5 pt-3 pb-4" src="<%=path%>" style="" alt="<%=product.getPImageName()%>"/>
                        <div class="d-flex justify-content-center align-items-center gap-5">
                            <%
                                boolean flag = true;
                                Cookie[] cookies = request.getCookies();
                                if (cookies != null) {
                                    for (Cookie cookie : cookies) {
                                        String imgname = cookie.getValue();
                                        if (imgname.equals(product.getPImageName())) {
                                            flag = false;
                                        }
//                                        out.println(imgname+"-"+product.getPImageName());
                                        
                                    }
                                }

//                                out.println(flag);
                            %>
                            <%                                if (flag) {
                            %>
                            <a href="<%=request.getContextPath() + "/AddToCart"%>"><button class="btn mybtn" style="background-color: #ff9f00;" id="cartbtn"><i class="fa-solid fa-cart-shopping"></i> Add To Cart</button></a>

                            <%
                                }
                            %>
                            <a href="<%=request.getContextPath() + "/BuyNowServlet"%>"><button class="btn mybtn" style="background-color:#fb641b;" id="buybtn"><i class="fa-sharp fa-solid fa-bag-shopping"></i> Buy Now</button></a>

                        </div>
                    </div>

                </div>

                <div class="col2 d-flex flex-column gap-2">
                    <h5><%=product.getPName()%></h5>
                    <div class="d-flex justify-content-start align-items-center">
                        <div class="review">
                            <span><%=product.getPRating()%></span>
                            <i class="fa-solid fa-star" style="font-size:0.8rem;"></i>


                        </div>
                        <span>(<%=product.getPReviewsNum()%> Ratings)</span>
                    </div>
                    <h6>Product Price: </h6>
                    <h4>&#8377;<%=product.getPPrice()%></h4>
                    <div>
                        <p>
                            <%=product.getPDesc()%>
                        </p>

                    </div>


                </div>           
            </div>







            <%
                }
            %>

            <script>


                document.getElementById("home-link").classList.remove("active");


            </script>


    </body>
</html>
