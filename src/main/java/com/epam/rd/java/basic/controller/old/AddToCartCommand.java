//package com.epam.rd.java.basic.controller.old;
//
//
//import com.epam.rd.java.basic.controller.Command;
//
//public class AddToCartCommand implements Command {
//
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//
//        User user = (User) session.getAttribute(AttributeConstant.USER);
//        Cart cart = (Cart) session.getAttribute(AttributeConstant.CART);
//        String idItem = request.getParameter(AttributeConstant.ID_ITEM);
//        BigDecimal priceItem = BigDecimal.valueOf(Long.parseLong(request.getParameter(AttributeConstant.PRICE)));
//        int count = Integer.parseInt(request.getParameter(AttributeConstant.COUNT));
//
//        ServiceFactory factory = new ServiceFactoryImpl();
//        CartService cartService = factory.getCartService();
//        CartItemService cartItemService = factory.getCartItemService();
//        StatusService statusService = factory.getStatusService();
//        String error;
//
//        if (user == null) {
//            error = "User was not authorization.";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return Path.ERROR_PAGE;
//        }
//
//        if (cart == null) {
//            try {
//                cart = cartService.getCartByUserIdAndStatusId(user.getId(), Status.EMPTY.getName());
//            } catch (ServiceException e) {
//                try {
//                    Status status = statusService.getByName(Status.EMPTY.getName());
//                    Cart newCart = Cart.builder()
//                            .statusId(status.getId())
//                            .userId(user.getId())
//                            .build();
//                    cart = cartService.create(newCart);
//                } catch (ServiceException serviceException) {
//                    error = "Cannot create new cart with empty status.";
//                    session.setAttribute(AttributeConstant.ERROR, error);
//                    return Path.ERROR_PAGE;
//                }
//            }
//        }
//
//        CartItem cartItem = CartItem.builder()
//                .cartId(cart.getId())
//                .itemId(Integer.parseInt(idItem))
//                .price(priceItem)
//                .countItem(count)
//                .build();
//        try {
//            cartItemService.create(cartItem);
//        } catch (ServiceException e) {
//            error = "Cannot add item to cart.";
//            session.setAttribute(AttributeConstant.ERROR, error);
//            return Path.ERROR_PAGE;
//        }
//
//        session.setAttribute(AttributeConstant.CART, cart);
//        return Path.ITEM_PAGE;
//    }
//
//
//}
