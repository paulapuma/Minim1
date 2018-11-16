package edu.upc.eetac.dsa.services;
import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Api(value="/OrderService", description = "Endpoint to Order Service")
@Path("/OrderService")
public class OrdersService {
    final static Logger log = Logger.getLogger(OrdersService.class);

    private ProductManagerImpl pm;


    public OrdersService(){
        this.pm = ProductManagerImpl.getInstance();
        if(pm.getInstance().getProductList().isEmpty()){
            ///Creating Products
            pm.getInstance().addProduct(new Product("cafe",2));
            pm.getInstance().addProduct(new Product("bocata",3.5));
            pm.getInstance().addProduct(new Product("zumo",3));
            pm.getInstance().addProduct(new Product("manzana",1));
        }

        if(pm.getInstance().getUserList2().isEmpty()){
            ///Creating users
            pm.getInstance().addUser("pepe");
            pm.getInstance().addUser("jose");
            pm.getInstance().addUser("carlos");

        }

    }


    @GET
    @ApiOperation(value = "get all products in the list", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer = "List of Products")
    })

    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Product> listProducts  = this.pm.getProductList();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(listProducts){};
        return Response.status(201).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "get all users in the list", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "List of Users")
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        System.out.println("GET ALL USERS!!!!");
        ArrayList<User> listUsers = null;
        List<UserTO> listUsersTO = new ArrayList<UserTO>();
        listUsersTO.add(new UserTO("Juan"));
        try {
            listUsers = (ArrayList)this.pm.getUserList2();

            for (User u : listUsers) {
                System.out.println("User " + u.getName());
                listUsersTO.add(new UserTO(u.getName()));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println("GET ALL USERS!!!!2222222");

//       GenericEntity<List<UserTO>> entity = new GenericEntity<List<UserTO>>(listUsersTO){};
//        return Response.status(201).entity(entity).build();
       GenericEntity<List<User>> entity = new GenericEntity<List<User>>(listUsers){};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all orders of a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Order.class, responseContainer = "List of Orders"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@PathParam("user") String user) {
        List<Order> listOrders;//pedidos
        try {
            listOrders = this.pm.getOrdersByUser(user);
            for(Order o:listOrders) {
                log.info("Order: " + o.toString());
            }
            GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(listOrders){};
            return Response.status(201).entity(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }


    @GET
    @ApiOperation(value = "get all products sorted by price", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer = "List of Products")
    })
    @Path("/sortedbyprice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSortedByPrice() {
        List<Product> listproducts  = this.pm.sortProductsByPrice();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(listproducts){};
        return Response.status(201).entity(entity).build();
    }



    @GET
    @ApiOperation(value = "get all products sorted by number of sales", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer = "List of Products")
    })
    @Path("/sortedbysales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        List<Product> listproducts  = this.pm.sortProductsBySales();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(listproducts){};
        return Response.status(201).entity(entity).build();
    }




    @POST
    @ApiOperation(value = "place an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/placeanorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeAnOrder(Order o) throws ProductNotFoundException {

        String userName = o.getUser();

        try {
            this.pm.doAnOrder(userName, o);
            return Response.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }



    @DELETE
    @ApiOperation(value = "serve an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Order.class, responseContainer = "Order")
    })
    @Path("/serveanorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response serveAnOrder(){
        Order order = pm.getOrderList().getFirst();
        this.pm.serveAnOrder();

        return Response.status(201).entity(order).build();
    }


    @POST
    @ApiOperation(value = "add Product", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product p){
        pm.getProductList().add(p);

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add User", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/adduser/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("user") String u){
        pm.getUserList().put(u,new User(u));

        return Response.status(201).build();
    }
}
