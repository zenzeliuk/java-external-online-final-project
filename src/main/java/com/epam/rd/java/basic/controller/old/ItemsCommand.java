//package com.epam.rd.java.basic.controller.old;
//
//
//import com.epam.rd.java.basic.controller.Command;
//
//@Log4j2
//public class ItemsCommand implements Command {
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//
//        HttpSession session = request.getSession();
//
//        String error;
//
//        ServiceFactory factory = new ServiceFactoryImpl();
//        ItemService itemService = factory.getItemService();
//
//        try {
//            List<Item> itemList = itemService.findAll();
//            List<ItemDTO> itemDTOList = ItemMapper.toItemDTOList(itemList);
//            session.setAttribute("items", itemDTOList);
//            return Path.ITEM_PAGE;
//        } catch (ServiceException e) {
//            log.error("Can not find all items" + e.getMessage());
//            error = "Something went wrong, please repeat later.";
//            request.setAttribute(AttributeConstant.ERROR, error);
//            return Path.ERROR_PAGE;
//        }
//    }
//
//}
