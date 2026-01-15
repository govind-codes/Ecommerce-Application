package com.javaexpress.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.metadata.IIOInvalidTreeException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.order.dto.CartItemResponseDto;
import com.javaexpress.order.dto.OrderItemResponseDto;
import com.javaexpress.order.dto.OrderResponseDto;
import com.javaexpress.order.dto.PlaceOrderRequestDto;
import com.javaexpress.order.dto.ProductResponseDto;
import com.javaexpress.order.exception.ResourceNotFoundException;
import com.javaexpress.order.feignclients.CartFeignClient;
import com.javaexpress.order.feignclients.ProductFeignclient;
import com.javaexpress.order.feignclients.UserFeignclient;
import com.javaexpress.order.model.Order;
import com.javaexpress.order.model.OrderItem;
import com.javaexpress.order.repository.OrderRepository;
import com.javaexpress.user.dto.UserDto;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	UserFeignclient userFeignclient;
	
	@Autowired
	CartFeignClient cartFeignClient;
	
	@Autowired
	ProductFeignclient productFeignclient;

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public OrderResponseDto placeOrder(PlaceOrderRequestDto request) {
		
		UserDto userDto = validateUser(request.getUserId());
		if(userDto == null) {
			throw new ResourceNotFoundException("User not Found in DB");
		}
		
		List<CartItemResponseDto> cartItems = fetchCartItems(request.getUserId());
		if(cartItems == null || cartItems.isEmpty()) {
			throw new ResourceNotFoundException("Cart is Empty");
		}
		
		BigDecimal totalPrice = calculateTotalPrice(cartItems);
		
		List<OrderItem> orderItems = buildOrderItems(cartItems);
		
		Order order = createOrder(request, totalPrice, orderItems);
		
		Order dbOrder = orderRepository.save(order);
		
		cartFeignClient.clearUserCart(request.getUserId());
		
		return mapToOrderResponse(dbOrder,userDto);
	}


	


	@Override
	public OrderResponseDto updateOrderStatus(Long orderId, String status) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found for orderId: "+ orderId));
		
		order.setStatus(status);
		orderRepository.save(order);
		
		OrderResponseDto orderDto= new OrderResponseDto();
		BeanUtils.copyProperties(order, orderDto);
		
		return orderDto;
	}

	@Override
	public List<OrderResponseDto> getOrdersByUser(Long userId) {
		// TODO Auto-generated method stub
		List<Order> orders= orderRepository.findByUserId(userId);
		if(orders == null || orders.isEmpty()) {
			throw new ResourceNotFoundException("Orders not Found in  for user id: "+userId);
		}
		
		List<OrderResponseDto> ordersDto = new ArrayList<>();
		for(Order order : orders) {
			OrderResponseDto orderDto = new OrderResponseDto();
		BeanUtils.copyProperties(order, orderDto);
		ordersDto.add(orderDto);
		}
		return ordersDto;
	}

	@Override
	public OrderResponseDto getOrderById(Long orderId) {
		// TODO Auto-generated method stub
		
	 Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Oder not found for order id: "+orderId));
		OrderResponseDto orderDto= new OrderResponseDto();
		BeanUtils.copyProperties(order, orderDto);
	 return orderDto;
	}
	
	private OrderResponseDto mapToOrderResponse(Order dbOrder, UserDto userDto) {
		OrderResponseDto dto = new OrderResponseDto();
		BeanUtils.copyProperties(dbOrder, dto,"items"); // Exclusde items to map manually
		dto.setOrderId(dbOrder.getOrderId());
		dto.setUserDto(userDto);
		
		List<OrderItemResponseDto> orderItemsResponse = dbOrder.getItems().stream().map(item -> {
			OrderItemResponseDto itemDto = new OrderItemResponseDto();
			BeanUtils.copyProperties(item, itemDto);
			return itemDto;
		}).collect(Collectors.toList());
		
		dto.setItems(orderItemsResponse);
		return dto;
	} 



	private Order createOrder(PlaceOrderRequestDto request, BigDecimal totalPrice, List<OrderItem> orderItems) {
		// TODO Auto-generated method stub
		
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setStatus("PLACED");
		order.setTotalPrice(totalPrice);
		
		for(OrderItem orderItem : orderItems) {
			orderItem.setOrder(order);
		}
		order.setItems(orderItems);
		
		
		return order;
	}


	private List<OrderItem> buildOrderItems(List<CartItemResponseDto> cartItems) {
		
		List<OrderItem> orderItems= new ArrayList<>();
		
		for(CartItemResponseDto cartItem: cartItems) {
		ProductResponseDto product= productFeignclient.fetchProduct(cartItem.getProductId());
		OrderItem orderItem= new OrderItem();
		orderItem.setProductId(cartItem.getProductId());
		orderItem.setQuantity(cartItem.getQuantity());
		orderItem.setPrice(product.getProductPrice());
		orderItems.add(orderItem);
		}
		
		
		return orderItems;
	}
	public BigDecimal calculateTotalPrice(List<CartItemResponseDto> cartItems) {
		
		
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(CartItemResponseDto cartItem : cartItems) {
			ProductResponseDto productResponseDto = productFeignclient.fetchProduct(cartItem.getProductId());
			Integer quantity = cartItem.getQuantity(); 
			BigDecimal individualPrice = productResponseDto.getProductPrice().multiply(BigDecimal.valueOf(quantity));
			totalPrice = totalPrice.add(individualPrice);
		}
		return totalPrice;
		
	}
	
	public List<CartItemResponseDto> fetchCartItems(Long userid){
		
		List<CartItemResponseDto> cartItemsDto=cartFeignClient.getCartByUserId(userid);
		
		return cartItemsDto;
		
	}
	
	public UserDto validateUser(Long userid) {
		UserDto userDto= userFeignclient.findUserId(userid.intValue());

		return userDto;
	}

}
