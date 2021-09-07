<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/css/item.css" %>
</style>

<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container d-flex justify-content-center mt-50 mb-50">
    <div class="row">
        <c:forEach items="${items}" var="item">


            <div class="col-md-4 mt-2">
                <div class="card">
                    <div class="card-body">
                        <div class="card-img-actions"><img
                                src="https://icdn.lenta.ru/images/2021/04/27/16/20210427163138131/detail_9b31eaf4376cdff03e0ba1bcaa826a01.jpg"
                                class="card-img img-fluid" width="96" height="350" alt=""></div>
                    </div>
                    <div class="card-body bg-light text-center">
                        <div class="mb-2">
                            <h6 class="font-weight-semibold mb-2"><a href="#" class="text-default mb-2"
                                                                     data-abc="true">
                                Name :<c:out value="${item.name}"/>
                            </a></h6> <a href="#" class="text-muted"
                                         data-abc="true">Laptops
                            & Notebooks</a>
                        </div>
                        <h3 class="mb-0 font-weight-semibold">
                            Price: <c:out value="${item.price}"/>
                        </h3>
                        <div><i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i
                                class="fa fa-star star"></i>
                            <i class="fa fa-star star"></i></div>
                        <div class="text-muted mb-3">
                            Descriptions: <c:out value="${item.description}"/>
                        </div>
                        <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i> Add to
                            cart
                        </button>
                    </div>
                </div>

            </div>

        </c:forEach>

        <%--        <div class="col-md-4 mt-2">--%>
        <%--            <div class="card">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="card-img-actions"><img--%>
        <%--                            src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1562074043/234.png"--%>
        <%--                            class="card-img img-fluid" width="96" height="350" alt=""></div>--%>
        <%--                </div>--%>
        <%--                <div class="card-body bg-light text-center">--%>
        <%--                    <div class="mb-2">--%>
        <%--                        <h6 class="font-weight-semibold mb-2"><a href="#" class="text-default mb-2" data-abc="true">Toshiba--%>
        <%--                            Notebook with 500GB HDD & 8GB RAM</a></h6> <a href="#" class="text-muted"--%>
        <%--                                                                          data-abc="true">Laptops--%>
        <%--                        & Notebooks</a>--%>
        <%--                    </div>--%>
        <%--                    <h3 class="mb-0 font-weight-semibold">$250.99</h3>--%>
        <%--                    <div><i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i--%>
        <%--                            class="fa fa-star star"></i>--%>
        <%--                        <i class="fa fa-star star"></i></div>--%>
        <%--                    <div class="text-muted mb-3">34 reviews</div>--%>
        <%--                    <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i> Add to cart--%>
        <%--                    </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="col-md-4 mt-2">--%>
        <%--            <div class="card">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="card-img-actions"><img--%>
        <%--                            src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1562074043/234.png"--%>
        <%--                            class="card-img img-fluid" width="96" height="350" alt=""></div>--%>
        <%--                </div>--%>
        <%--                <div class="card-body bg-light text-center">--%>
        <%--                    <div class="mb-2">--%>
        <%--                        <h6 class="font-weight-semibold mb-2"><a href="#" class="text-default mb-2" data-abc="true">Toshiba--%>
        <%--                            Notebook with 500GB HDD & 8GB RAM</a></h6> <a href="#" class="text-muted"--%>
        <%--                                                                          data-abc="true">Laptops--%>
        <%--                        & Notebooks</a>--%>
        <%--                    </div>--%>
        <%--                    <h3 class="mb-0 font-weight-semibold">$250.99</h3>--%>
        <%--                    <div><i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i--%>
        <%--                            class="fa fa-star star"></i>--%>
        <%--                        <i class="fa fa-star star"></i></div>--%>
        <%--                    <div class="text-muted mb-3">34 reviews</div>--%>
        <%--                    <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i> Add to cart--%>
        <%--                    </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="col-md-4 mt-2">--%>
        <%--            <div class="card">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="card-img-actions"><img--%>
        <%--                            src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1562074043/234.png"--%>
        <%--                            class="card-img img-fluid" width="96" height="350" alt=""></div>--%>
        <%--                </div>--%>
        <%--                <div class="card-body bg-light text-center">--%>
        <%--                    <div class="mb-2">--%>
        <%--                        <h6 class="font-weight-semibold mb-2"><a href="#" class="text-default mb-2" data-abc="true">Toshiba--%>
        <%--                            Notebook with 500GB HDD & 8GB RAM</a></h6> <a href="#" class="text-muted"--%>
        <%--                                                                          data-abc="true">Laptops--%>
        <%--                        & Notebooks</a>--%>
        <%--                    </div>--%>
        <%--                    <h3 class="mb-0 font-weight-semibold">$250.99</h3>--%>
        <%--                    <div><i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i--%>
        <%--                            class="fa fa-star star"></i>--%>
        <%--                        <i class="fa fa-star star"></i></div>--%>
        <%--                    <div class="text-muted mb-3">34 reviews</div>--%>
        <%--                    <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i> Add to cart--%>
        <%--                    </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="col-md-4 mt-2">--%>
        <%--            <div class="card">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="card-img-actions"><img--%>
        <%--                            src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1562074043/234.png"--%>
        <%--                            class="card-img img-fluid" width="96" height="350" alt=""></div>--%>
        <%--                </div>--%>
        <%--                <div class="card-body bg-light text-center">--%>
        <%--                    <div class="mb-2">--%>
        <%--                        <h6 class="font-weight-semibold mb-2"><a href="#" class="text-default mb-2" data-abc="true">Toshiba--%>
        <%--                            Notebook with 500GB HDD & 8GB RAM</a></h6> <a href="#" class="text-muted"--%>
        <%--                                                                          data-abc="true">Laptops--%>
        <%--                        & Notebooks</a>--%>
        <%--                    </div>--%>
        <%--                    <h3 class="mb-0 font-weight-semibold">$250.99</h3>--%>
        <%--                    <div><i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i--%>
        <%--                            class="fa fa-star star"></i>--%>
        <%--                        <i class="fa fa-star star"></i></div>--%>
        <%--                    <div class="text-muted mb-3">34 reviews</div>--%>
        <%--                    <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i> Add to cart--%>
        <%--                    </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="col-md-4 mt-2">--%>
        <%--            <div class="card">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="card-img-actions"><img--%>
        <%--                            src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1562074043/234.png"--%>
        <%--                            class="card-img img-fluid" width="96" height="350" alt=""></div>--%>
        <%--                </div>--%>
        <%--                <div class="card-body bg-light text-center">--%>
        <%--                    <div class="mb-2">--%>
        <%--                        <h6 class="font-weight-semibold mb-2"><a href="#" class="text-default mb-2" data-abc="true">Toshiba--%>
        <%--                            Notebook with 500GB HDD & 8GB RAM</a></h6> <a href="#" class="text-muted"--%>
        <%--                                                                          data-abc="true">Laptops--%>
        <%--                        & Notebooks</a>--%>
        <%--                    </div>--%>
        <%--                    <h3 class="mb-0 font-weight-semibold">$250.99</h3>--%>
        <%--                    <div><i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i--%>
        <%--                            class="fa fa-star star"></i>--%>
        <%--                        <i class="fa fa-star star"></i></div>--%>
        <%--                    <div class="text-muted mb-3">34 reviews</div>--%>
        <%--                    <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i> Add to cart--%>
        <%--                    </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
    </div>
</div>
</body>
</html>
