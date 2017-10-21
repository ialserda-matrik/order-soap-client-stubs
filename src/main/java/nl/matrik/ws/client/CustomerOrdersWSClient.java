package nl.matrik.ws.client;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nl.matrik.ws.orders.CustomerOrdersPortType;
import nl.matrik.ws.orders.CustomerOrdersService;
import nl.matrik.ws.orders.GetOrdersRequest;
import nl.matrik.ws.orders.GetOrdersResponse;
import nl.matrik.ws.orders.Order;
import nl.matrik.ws.orders.Product;


public class CustomerOrdersWSClient {

    public static void main(String[] args) {

        try {
            CustomerOrdersService customerOrdersService = new CustomerOrdersService(new URL("http://localhost:8080/wsdlfirst-orders/services/customerOrders?wsdl"));
            CustomerOrdersPortType customerOrdersPort = customerOrdersService.getCustomerOrdersPort();
            GetOrdersRequest request = new GetOrdersRequest();
            request.setCustomerId(BigInteger.valueOf(1));
            GetOrdersResponse response = customerOrdersPort.getOrders(request);
            List<Order> orders = response.getOrder();

            for(Order order: orders) {
                List<Product> products = order.getProduct();
                for(Product product: products) {
                    System.out.println("Product description: " + product.getDescription());
                    System.out.println("Product quantity: " + product.getQuantity());
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


}
