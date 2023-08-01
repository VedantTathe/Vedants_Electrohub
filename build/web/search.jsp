<%@page import="java.util.Vector"%>
<%@page import="Bean.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.io.File"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search</title>
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
            .allproducts{
                height: auto;
                /*overflow-y: scroll;*/
                /*overflow-x: scroll;*/
            }

            .product{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                flex-wrap: wrap;
                width: 12.5rem;
                /*width: min-content;*/
                position: relative;

            }
            .allproducts{
                display: flex;
                justify-content: start;
                align-items: center;
                flex-wrap: wrap;
                /*overflow-x: scroll;*/
            }

            .ellipsis {
                overflow: hidden;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                white-space: pre-wrap;
                margin: 5px;

                width: 85%;
                margin: auto;
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
                flex-wrap: nowrap;

            }

            #products{
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
            }

            .product img{
                width: 100%;
            }
            .card{
                transition: 0.5s;
            }

            .card:hover{
                box-shadow: 14px 7px 25px -2px rgb(108, 108, 108);
            }

            .card:hover .ellipsis{
                color: rgb(0, 110, 236);
            }


            .card-body{
                /*padding: 0;*/

            }

            .search-box{
                width: 75%;
            }

            nav li{
                cursor: pointer;
            }

            nav a{
                text-decoration: none;
                color: #ffffff8c;
            }


            @media only screen and (max-width: 473px)
            {
                .card-body{
                }

                .product{
                    padding: 0;

                    width: 9.3rem;
                }


            }

            @media only screen and (max-width: 370px)
            {

                .product{

                    width: 7.4rem;
                }

            }


            @media only screen and (max-width: 310px)
            {

                .product{

                    width: 6.4rem;
                }


            }

            @media only screen and (max-width: 500px)
            {

                .search-box{
                    width: 100%;
                }

            }

        </style>
    </head>
    <body>
        <!---------------------------------------Navbar------------------------------------->
        <%@ include file = "components/navbar.jsp" %>


        <div class="w-100" style="background-color:#929292;">
            <div class="search-box container pt-5 pb-2 text-center">
                <div class="container d-md-flex justify-content-center align-items-center gap-5">
                    <label class="form-label">Search</label>
                    <%         
out.println((String)session.getAttribute("searchvalue"));
Object myobj = session.getAttribute("searchvalue");
                        if (myobj == null) {
                    %>
                    <input id="search" type="text" class="form-control" placeholder="Search Products Here"/>
                    <%
                    } else {

                    %>
                    <input id="search" type="text" class="form-control" value="<%=(String)session.getAttribute("searchvalue")%>" placeholder="Search Products Here"/>

                    <%
        
                        }
                    %>
                </div>
            </div>
        </div>
        <div id="products" class="ps-4 pe-4 pb-4 p-m-0">


            <div class="container allproducts " >

                <%  Object obj = session.getAttribute("allproducts");
                    if (obj != null) {
                        Vector<Product> products = (Vector) session.getAttribute("allproducts");
                        int i = 0;
                        while (true) {
                            try {
                                Product p = products.elementAt(i);
                                String imgname = p.getPImageName();
                                String path = "components/images/products" + "/" + imgname;
                %>
                <a onclick="call('<%=imgname%>')" class="<%=p.getPCat()%> dynamic-product ALL"  style="text-decoration:  none;">
                    <div class="btn product">

                        <div class="card" style="border: none">
                            <div class="card-body">
                                <img src="<%=path%>" alt="<%=imgname%>">

                                <p class="ellipsis p-1"><%=p.getPName()%></p>
                                <div class="d-flex justify-content-center align-items-center">
                                    <div class="review">
                                        <span><%=p.getPRating()%></span>
                                        <i class="fa-solid fa-star"></i>
                                    </div>

                                    <span>(<%=p.getPReviewsNum()%>)</span>
                                </div>
                                <div class="price pt-1">
                                    <strong>&#8377;<%=p.getPPrice()%></strong>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>


                <%
                            } catch (Exception ex) {
                                break;
                            }
                            i++;
                        }
                    }

                %>

            </div>
            <form action="<%=request.getContextPath() + "/SearchProduct"%>" method="get" id="hidden_form1" class="">
                <input type="text" id="typed_elem" name="TypedElem" value="" class=""/>
            </form>


            <form action="<%=request.getContextPath() + "/GetProductServlet"%>" method="get" id="hidden_form" class="d-none">
                <input type="text" id="clicked_elem" name="ClickedElem" value="" class="d-none"/>
            </form>
        </div>

        <script>
            let search_bar = document.querySelector("#search");
            search_bar.addEventListener('keypress', function () {
                let typed_elem = document.querySelector("#typed_elem");
                typed_elem.value = search_bar.value;
                document.querySelector("#hidden_form1").submit();
            });
                    <%
//out.println((String)session.getAttribute("searchvalue"));
Object myobj1 = session.getAttribute("searchvalue");
                        if (myobj1 == null) {
                    } else {

                    %>
                    search_bar.value = <%=(String)session.getAttribute("searchvalue")%>
                    alert(<%=(String)session.getAttribute("searchvalue")%>);
                    <%
                        }
                    %>
        </script>
        <%@ include file = "components/js/javascript.html" %>
    </body>
</html>
