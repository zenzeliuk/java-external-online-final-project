//package com.epam.rd.java.basic.controller.command.cart;
//
//import com.epam.rd.java.basic.exception.ServiceException;
//import com.epam.rd.java.basic.model.CartItem;
//import com.epam.rd.java.basic.service.CartItemService;
//import org.junit.Test;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class EditCountItemCommandGETTest {
//
//
//    @Test
//    public void execute() throws ServiceException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        CartItemService cartItemService = mock(CartItemService.class);
//
//        when(request.getParameter("count-item")).thenReturn("5");
//        when(request.getParameter("cart-item-id")).thenReturn("1");
//        when(cartItemService.get(1)).thenReturn(new CartItem());
//
//        EditCountItemCommandGET commandGET = new EditCountItemCommandGET();
//        commandGET.execute(request, response);
//
//    }
//}