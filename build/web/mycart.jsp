<%@page import="java.util.Vector"%>
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
                width: 16.5rem;
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


        </style>
    </head>
    <body>
        <!---------------------------------------Navbar------------------------------------->
        <%@ include file = "components/navbar.jsp" %>

        <%            Vector<String> pimgname = new Vector<String>();
            Cookie[] cookies = request.getCookies();
            pimgname.clear();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
//                    out.println(cookie.getName());
                    String x = cookie.getName().trim();
                    String name = x.substring(0, 6);
//                    out.println(name);
                    if (name.equals("mycart")) {
                        String imgname = cookie.getValue();
//                       out.println(imgname);
                        pimgname.add(imgname);
                    }
                }
            }

        %>

        <div id="products" class="p-4 p-m-0">

            <div class="container allproducts " >
                <%    for (String img : pimgname) {

                        try {
                            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");
                            String sql = "select * from products where imgname = ?";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, img);
                            ResultSet rs = ps.executeQuery();

                %>



                <%                    if (rs.next()) {

                        String imgname = rs.getString("imgname");
                        String path = "components/images/products" + "/" + imgname;
                %>
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <a onclick="call('<%=imgname%>')" class="<%=rs.getString("pcat")%> dynamic-product ALL"  style="text-decoration:  none;">
                        <div class="btn product">

                            <div class="card" style="border: none">
                                <div class="card-body">
                                    <img src="<%=path%>" alt="<%=imgname%>">

                                    <p class="ellipsis p-1"><%=rs.getString("pname")%></p>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <div class="review">
                                            <span><%=rs.getString("rating")%></span>
                                            <i class="fa-solid fa-star"></i>
                                        </div>

                                        <span>(<%=rs.getString("reviewsnum")%>)</span>
                                    </div>
                                    <div class="price pt-1">
                                        <strong>&#8377;<%=rs.getString("price")%></strong>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </a>

                    <a >
                        <div>

                            <button onclick="call('<%=rs.getString("imgname")%>')" class="btn mybtn" style="background-color:#fb641b;" id="buybtn">
                                <i class="fa-sharp fa-solid fa-bag-shopping"></i> Remove</button>


                        </div>
                    </a>
                </div>
                <%

                    }

                %>


                <%                    rs.close();
                            ps.close();
                            con.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }
                %>


            </div>

            <form action="<%=request.getContextPath() + "/RemoveFromCart"%>" method="get" id="hidden_form" class="d-nonr">
                <input type="text" id="clicked_elem" name="ClickedElem" value="" class="d-none"/>
            </form>
        </div>

        <%@ include file = "components/js/javascript.html" %>
        <script>

            document.getElementById("home-link").classList.remove("active");
            document.getElementById("cart-link").classList.add("active");


        </script>
    </body>
</html>
